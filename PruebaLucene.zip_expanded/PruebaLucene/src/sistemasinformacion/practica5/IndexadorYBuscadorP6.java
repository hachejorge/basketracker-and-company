package sistemasinformacion.practica5;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.Field;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;


import java.util.ArrayList;
import java.util.Collection;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.io.BufferedReader;

import java.io.File;
import java.util.Scanner;
import java.nio.file.Files;
import java.util.InputMismatchException;

/**
 * Clase de ejemplo de un indexador y buscador usando Lucene
 * @author GrupoPracticasB04_Viernes12_14
 *
 */
public class IndexadorYBuscadorP6{

	/**
	 * Relación de ficheros a indexar / buscar
	 */
	private Collection <String> ficherosAIndexar = new ArrayList<String>();
	
	/**
	 * Analizar utilizado por el indexador / buscador 
	 */
	private Analyzer analizador;	

	/**
	 * Constructor parametrizado
	 * @param ficherosAIndexar Colección de ficheros a indexar
	 */
	public IndexadorYBuscadorP6(Collection<String> ficherosAIndexar){
		this.ficherosAIndexar = ficherosAIndexar;
		
		analizador = new SpanishAnalyzer();	
	}
		
	/**
	 * Añade un fichero al índice
	 * @param indice Indice que estamos construyendo
	 * @param path ruta del fichero a indexar
	 * @throws IOException
	 */
	private void anyadirFichero(IndexWriter indice, String path) 
	throws IOException {
		InputStream inputStream = new FileInputStream(path);
		BufferedReader inputStreamReader = new BufferedReader(
				new InputStreamReader(inputStream,"UTF-8"));
		
		Document doc = new Document();   
		doc.add(new TextField("contenido", inputStreamReader));
		doc.add(new StringField("path", path, Field.Store.YES));
		indice.addDocument(doc);
	}
	
	/**
	 * Indexa los ficheros incluidos en "ficherosAIndexar"
	 * @return un índice (Directory) en memoria, con los índices de los ficheros
	 * @throws IOException
	 */
	private Directory crearIndiceEnUnDirectorio(String directorio) throws IOException{
		IndexWriter indice = null;
		Directory directorioAlmacenarIndice = new MMapDirectory(Paths.get("./" + directorio + "/indice"));

		IndexWriterConfig configuracionIndice = new IndexWriterConfig(analizador);

		indice = new IndexWriter(directorioAlmacenarIndice, configuracionIndice);
		
		for (String fichero : ficherosAIndexar) {
			anyadirFichero(indice, fichero);
		}
		
		indice.close();
		return directorioAlmacenarIndice;
	}
	
	/**
	 * Busca la palabra indicada en queryAsString en el directorioDelIndice.
	 * @param directorioDelIndice
	 * @param paginas
	 * @param hitsPorPagina
	 * @param queryAsString
	 * @throws IOException
	 */
	private void buscarQueryEnIndice(Directory directorioDelIndice, 
										int paginas, 
										int hitsPorPagina, 
										String queryAsString)
	throws IOException{

		DirectoryReader directoryReader = DirectoryReader.open(directorioDelIndice);
		IndexSearcher buscador = new IndexSearcher(directoryReader);
		
		QueryParser queryParser = new QueryParser("contenido", analizador); 
		Query query = null;
		try{
			query = queryParser.parse(queryAsString);
			TopDocs resultado = buscador.search(query, paginas * hitsPorPagina);
			ScoreDoc[] hits = resultado.scoreDocs;
		      
			System.out.println("\nBuscando " + queryAsString + ": Encontrados " + hits.length + " hits.");
			int i = 0;
			for (ScoreDoc hit: hits) {
				int docId = hit.doc;
				
				Document doc = buscador.doc(docId);
				System.out.println((++i) + ". " + doc.get("path") + "\t" + hit.score);
			}

		}catch (ParseException e){
			throw new IOException(e);
		}	
	}
	
	/**
	 * Muestra por pantalla las opciones a elegir por el usuario
	 */
	private static void menuInicial() {
		System.out.println("Seleccione una opción:");
		System.out.println("1.-Indexar un directorio");
		System.out.println("2.-Buscar término");
		System.out.println("3.-Salir\n");
	}
	
	/**
	 * Se guarda en la colección fichs todos los ficheros a indexar dentro del directorio con ruta path
	 * @param fichs colección de ficheros para indexar
	 * @param path ruta del directorio donde se encuentran los ficheros a indexar
	 */
	private static void buscadorFichs(Collection<String> fichs, String path) {
		File directorio = new File(path);
		if (directorio.exists() && directorio.isDirectory()) {
			File[] listFichs = directorio.listFiles();
			for (int i=0; i < listFichs.length; i++) {
				if (listFichs[i].isFile()) {
					fichs.add(path + "/" + listFichs[i].getName());
				}
			}
		}
	}
	
	/**
	 * Programa principal de prueba. Rellena las colecciones "ficheros" y "queries"
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[]args) throws IOException{
		int numero = 0;
		Scanner input = new Scanner(System.in);
		String directorio;
		Collection<String> fichs = new ArrayList<String>();
		
		while (numero != 3) { // mientras que no quiera salir
			menuInicial();
			try {
				numero = input.nextInt();
				input.nextLine();
				switch (numero) {
					case 1 : // indexar un directorio
						System.out.println("Introducir directorio: ");
						directorio = input.nextLine();
						
						fichs = new ArrayList<String>();
						buscadorFichs(fichs, "./" + directorio);
						if (fichs != null) {
							IndexadorYBuscadorP6 index = new IndexadorYBuscadorP6(fichs);
							index.crearIndiceEnUnDirectorio(directorio);
							System.out.println("Índice creado correctamente");
						}
						break;
					case 2 : // buscar término
						System.out.println("Introducir directorio: ");
						directorio = input.nextLine();
						
						if (Files.isDirectory(Paths.get("./" + directorio))) {
							fichs = new ArrayList<String>();
							buscadorFichs(fichs, "./" + directorio);
							IndexadorYBuscadorP6 index = new IndexadorYBuscadorP6(fichs);
							
							System.out.println("Introducir término: ");
							String query = input.nextLine();
							
							if (query != "") { // si no es vacía
								Directory dir = null; // directorio del indice que se ha creado
								if (Files.isDirectory(Paths.get("./" + directorio + "/indice"))) {
									dir = MMapDirectory.open(Paths.get("./" + directorio + "/indice"));
								} else {
									dir = index.crearIndiceEnUnDirectorio(directorio);
								}
								index.buscarQueryEnIndice(dir, fichs.size(), 1, query);
							}
							
						} else {
							System.out.println("No existe el directorio introducido");
						}
						break;
					case 3:
						System.out.println("Se ha salido correctamente");
						break;
					default:
						System.out.println("Se debe introducir un número del 1 al 3");
				}
				System.out.println();
			}
			catch (InputMismatchException except) { // lo introducido no es un número
				System.out.println("Se debe introducir un número\n");
				input.nextLine();
			}
		}
		input.close();
	}
}
