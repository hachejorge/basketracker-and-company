package es.unizar.eina.M35_Camping.ui;

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
 * ViewHolder que representa un ocupante en la lista de ocupantes.
 * Se encarga de mostrar los datos de un ocupante en un ítem de la RecyclerView.
 */
class OcupantesViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    // Referencias a los elementos de la vista para mostrar los datos del ocupante
    private final TextView parcelanombreTextView;
    private final TextView nocupTextView;

    /**
     * Constructor privado que inicializa los elementos de la vista y establece el listener para el menú contextual.
     *
     * @param itemView La vista inflada para este ViewHolder.
     */
    private OcupantesViewHolder(View itemView) {
        super(itemView);

        // Inicialización de las vistas de texto
        parcelanombreTextView = itemView.findViewById(R.id.et_nombreparcela);
        nocupTextView = itemView.findViewById(R.id.et_nocup);

        // Establecer el listener para el menú contextual
        itemView.setOnCreateContextMenuListener(this);
    }

    /**
     * Vincula los datos de un objeto Ocupantes a los elementos de la vista.
     *
     * @param ocupantes El objeto Ocupantes que contiene los datos a mostrar.
     */
    public void bind(Ocupantes ocupantes) {
        // Verifica si cada TextView no es null antes de asignar valores para evitar NullPointerException
        if (parcelanombreTextView != null && nocupTextView != null) {
            // Asigna el nombre de la parcela y el número de ocupante a los TextViews
            parcelanombreTextView.setText(ocupantes.getParcelanombre());
            nocupTextView.setText(String.valueOf(ocupantes.getOcp()));  // Convierte el número de ocupante a String
        }
    }

    /**
     * Método estático que infla la vista para este ViewHolder y la devuelve.
     *
     * @param parent El grupo de vistas al que el ViewHolder será agregado.
     * @return Un nuevo objeto OcupantesViewHolder con la vista inflada.
     */
    static OcupantesViewHolder create(ViewGroup parent) {
        // Infla la vista del item utilizando el LayoutInflater
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.parcela_en_reserva_show_view_item, parent, false);
        return new OcupantesViewHolder(view);
    }

    /**
     * Método que crea el menú contextual para cada ítem de la lista (editar y eliminar).
     * Este menú aparecerá cuando se haga una pulsación prolongada sobre un ítem.
     *
     * @param menu El menú contextual que se va a crear.
     * @param v La vista sobre la que se abrirá el menú.
     * @param menuInfo Información adicional sobre el menú (no utilizada en este caso).
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // Añadir las opciones al menú contextual: editar y eliminar
        menu.add(Menu.NONE, Reserva.DELETE_ID, Menu.NONE, R.string.menu_delete);  // Opción para eliminar
        menu.add(Menu.NONE, Reserva.EDIT_ID, Menu.NONE, R.string.menu_edit);  // Opción para editar
    }
}
