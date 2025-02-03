package es.unizar.eina.M35_Camping.ui;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import es.unizar.eina.M35_Camping.R;
import es.unizar.eina.M35_Camping.database.Ocupantes;
import es.unizar.eina.M35_Camping.database.Reserva;

/**
 * ViewHolder que representa un elemento de la lista de ocupantes en la interfaz de usuario.
 * Se encarga de mostrar la información del ocupante y gestionar el menú contextual.
 */
class OcupantesEditViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    private final TextView parcelanombreSpinner;  // TextView que muestra el nombre de la parcela
    private final TextView nocupTextView;  // TextView que muestra el número de ocupantes

    /**
     * Constructor privado que inicializa las vistas dentro del ViewHolder.
     *
     * @param itemView La vista del elemento en la lista.
     */
    private OcupantesEditViewHolder(View itemView) {
        super(itemView);

        // Inicializa los TextViews asociados con los datos que se van a mostrar.
        parcelanombreSpinner = itemView.findViewById(R.id.parcelaenreserva);  // Nombre de la parcela
        nocupTextView = itemView.findViewById(R.id.tv_nocup);  // Número de ocupantes

        // Establece el listener para el menú contextual, lo que permite mostrar opciones como editar o eliminar
        itemView.setOnCreateContextMenuListener(this);
    }

    /**
     * Vincula los datos de un objeto Ocupantes a las vistas correspondientes.
     * Este método se llama para asociar los datos del ocupante con la vista.
     *
     * @param ocupantes El objeto Ocupantes que contiene los datos a mostrar.
     */
    public void bind(Ocupantes ocupantes) {
        // Obtiene el contexto de la vista para usarlo si es necesario (por ejemplo, para mostrar recursos)
        Context context = parcelanombreSpinner.getContext();

        // Configura el TextView nocupTextView para mostrar el número de ocupantes.
        if (nocupTextView != null) {
            nocupTextView.setText(String.valueOf(ocupantes.getOcp()));  // Muestra el número de ocupantes
        }

        // Configura el TextView parcelanombreSpinner para mostrar el nombre de la parcela.
        if(parcelanombreSpinner != null) {
            parcelanombreSpinner.setText(ocupantes.getParcelanombre());  // Muestra el nombre de la parcela
        }
    }

    /**
     * Método estático para crear una nueva instancia de ViewHolder.
     * Este método infla la vista del item de la lista y devuelve un nuevo objeto ViewHolder.
     *
     * @param parent El contenedor donde se va a agregar el ViewHolder.
     * @return Un nuevo objeto OcupantesEditViewHolder.
     */
    static OcupantesEditViewHolder create(ViewGroup parent) {
        // Infla la vista del elemento de la lista usando el layout especificado
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.parcela_en_reservaview_item, parent, false);  // Vista inflada
        return new OcupantesEditViewHolder(view);  // Crea y retorna el ViewHolder
    }

    /**
     * Este método se llama cuando se debe crear el menú contextual para el elemento.
     * Añade las opciones de "Editar" y "Eliminar" al menú contextual.
     *
     * @param menu El menú contextual a crear.
     * @param v La vista sobre la que se abre el menú contextual.
     * @param menuInfo Información adicional sobre el menú contextual.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // Añade las opciones de editar y eliminar al menú contextual
        menu.add(Menu.NONE, Reserva.DELETE_ID, Menu.NONE, R.string.menu_delete);  // Opción para eliminar
        menu.add(Menu.NONE, Reserva.EDIT_ID, Menu.NONE, R.string.menu_edit);  // Opción para editar
    }
}
