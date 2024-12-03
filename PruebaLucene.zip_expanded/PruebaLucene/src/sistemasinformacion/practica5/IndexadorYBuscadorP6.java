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
	
	//private final static String INDEXDIR = "./ficheros/indice";
	
	

	/**
	 * Constructor parametrizado
	 * @param ficherosAIndexar Colección de ficheros a indexar
	 */
	public IndexadorYBuscadorP6(Collection<String> ficherosAIndexar){
		this.ficherosAIndexar = ficherosAIndexar;
		
		analizador = new SpanishAnalyzer();

/*		try {
			FileReader reader = new FileReader("./ficheros/stopwords.txt");
			analizador = new StandardAnalyzer(reader);
		} catch (Exception e) {
			System.out.println("Error leyendo fichero de Stop Words. Usando valor por defecto");
			analizador = new StandardAnalyzer();
		} 
*/
		//analizador = new SpanishAnalyzer();

	
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
	 * Ejecuta en el índice una búsqueda por cada una de las palabras clave solicitadas. <p>
	 * Las palabras clave solicitadas están en la propiedad global "queries". 
	 * @param directorioDelIndice índice
	 * @param paginas 
	 * @param hitsPorPagina
	 * @throws IOException
	 
	private void buscarQueries(Directory directorioDelIndice, int paginas, int hitsPorPagina)
	throws IOException{
		for (String palabra : queries) {
			buscarQueryEnIndice(directorioDelIndice, 
								paginas, 
								hitsPorPagina, 
								palabra);			
		}
	}
	*/
	
	/**
	 * 
	 */
	private static void menuInicial() {
		System.out.println("Seleccione una opción:");
		System.out.println("1.-Indexar un directorio");
		System.out.println("2.-Añadir un documento al índice");
		System.out.println("3.-Buscar término");
		System.out.println("4.-Salir\n");
	}
	
	/**
	 * Se guarda en la colección fichs todos los ficheros a indexar dentro del directorio con ruta path
	 * @param fichs colección de ficheros para indexar
	 * @param path ruta del directorio donde se encuentran los ficheros a indexar
	 */
	private static void buscadorFichs(Collection<String> fichs, String path) {
		File directorio = new File(path);
		File[] listFichs = directorio.listFiles();
		for (int i=0; i < listFichs.length; i++) {
			if (listFichs[i].isFile()) {
				fichs.add(path + "/" + listFichs[i].getName());
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
		while (numero != 4) { // mientras que no quiera salir
			menuInicial();
			numero = input.nextInt();
			input.nextLine();
			
			if (numero == 1) { // indexar un directorio
				System.out.println("Introducir directorio: ");
				String directorio = input.nextLine();
				
				Collection<String> fichs = new ArrayList<String>();
				buscadorFichs(fichs, "./" + directorio);
				
				IndexadorYBuscadorP6 index = new IndexadorYBuscadorP6(fichs);
				index.crearIndiceEnUnDirectorio(directorio);
				System.out.println("Índice creado correctamente");
				
			} else if (numero == 2) { // añadir un documento al indice
				
			} else if (numero == 3) { // buscar término
				System.out.println("Introducir directorio: ");
				String directorio = input.nextLine();
				
				if (Files.isDirectory(Paths.get("./" + directorio))) {
					Collection<String> fichs = new ArrayList<String>();
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
			}
			System.out.println();
		}
		input.close();
		
		
		/**
		// Establecemos la lista de ficheros a indexar
		Collection <String> ficheros = new ArrayList <String>();
		ficheros.add("./ficheros/uno.txt");
		ficheros.add("./ficheros/dos.txt");
		ficheros.add("./ficheros/tres.txt");
		ficheros.add("./ficheros/cuatro.txt");

		// Establecemos las palabras clave a utilizar en la búsqueda
		Collection <String> queries = new ArrayList <String>();
		queries.add("Contaminacion");
		queries.add("cambio climatico");
		queries.add("cambio climatico");
		queries.add("cambio");
		queries.add("climatico");
		queries.add("por");
		queries.add("aeropuerto");

		// Creamos el idexador / buscador
		IndexadorYBuscador ejemplo = new IndexadorYBuscador(ficheros, queries);

		// Indexamos los ficheros
		Directory directorioDelIndiceCreado = ejemplo.crearIndiceEnUnDirectorio();
		
		// Abrimos un ficher indexado previamente
		//Directory directorioDelIndiceCreado = MMapDirectory.open(Paths.get(INDEXDIR));
		
		// Ejecutamos la búsqueda de las palabras clave
		ejemplo.buscarQueries(directorioDelIndiceCreado, ficheros.size(), 1);
		*/
	}
	
}


