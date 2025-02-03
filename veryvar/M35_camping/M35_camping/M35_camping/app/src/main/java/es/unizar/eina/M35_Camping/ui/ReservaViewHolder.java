package es.unizar.eina.M35_Camping.ui;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import es.unizar.eina.M35_Camping.R;
import es.unizar.eina.M35_Camping.database.Reserva;

/**
 * ViewHolder que representa un item de reserva en la lista.
 * Esta clase maneja la vinculación de datos de una reserva a las vistas correspondientes
 * y también implementa el manejo del menú contextual para cada item.
 */
class ReservaViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

    // Vistas para mostrar los detalles de la reserva
    private final TextView idclienteTextView;
    private final TextView telefonoTextView;
    private final TextView fechaTextView;

    /**
     * Constructor del ViewHolder. Inicializa las vistas y configura el listener para el menú contextual.
     *
     * @param itemView La vista del item en el RecyclerView.
     */
    private ReservaViewHolder(View itemView) {
        super(itemView);

        // Asignación de las vistas correspondientes desde el layout
        idclienteTextView = itemView.findViewById(R.id.idclienteTextView);
        telefonoTextView = itemView.findViewById(R.id.telefonoTextView);
        fechaTextView = itemView.findViewById(R.id.fechaTextView);

        // Establecemos el listener para el menú contextual
        itemView.setOnCreateContextMenuListener(this);
    }

    /**
     * Vincula los datos de un objeto Reserva a las vistas del ViewHolder.
     *
     * @param reserva El objeto Reserva cuyo contenido será mostrado.
     */
    public void bind(Reserva reserva) {
        // Verifica si las vistas no son nulas antes de asignarles valores
        if (idclienteTextView != null && telefonoTextView != null && fechaTextView != null) {
            // Asignación de datos a los TextViews
            idclienteTextView.setText(reserva.getCliente() + " #" + reserva.getId());
            telefonoTextView.setText("Numero tlf: " + reserva.getMovil());
            fechaTextView.setText("Fechas: " + reserva.getFechEnt() + " al " + reserva.getFechSal());
        }
    }

    /**
     * Método estático para crear una nueva instancia del ViewHolder.
     *
     * @param parent El contenedor (parent) donde se añadirá la vista.
     * @return Un nuevo objeto ReservaViewHolder.
     */
    static ReservaViewHolder create(ViewGroup parent) {
        // Infla el layout correspondiente para cada item de reserva
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reservaview_item, parent, false);
        return new ReservaViewHolder(view);  // Retorna un nuevo ViewHolder con la vista inflada
    }

    /**
     * Método que crea el menú contextual cuando el usuario mantiene presionado un item.
     *
     * @param menu El menú contextual que se va a crear.
     * @param v La vista sobre la que se creó el menú contextual.
     * @param menuInfo Información adicional sobre el item que invoca el menú.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // Añadir las opciones "Eliminar" y "Editar" al menú contextual
        menu.add(Menu.NONE, Reserva.DELETE_ID, Menu.NONE, R.string.menu_delete);
        menu.add(Menu.NONE, Reserva.EDIT_ID, Menu.NONE, R.string.menu_edit);
    }
}
