package es.unizar.eina.M35_Camping.ui;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import es.unizar.eina.M35_Camping.database.Reserva;
import es.unizar.eina.M35_Camping.R;

/**
 * Adaptador para mostrar una lista de reservas en un RecyclerView.
 * El adaptador gestiona las operaciones de visualización, edición y eliminación de las reservas.
 */
public class ReservaListAdapter extends ListAdapter<Reserva, ReservaViewHolder> {

    // Listener para manejar las acciones de eliminación
    private OnDeleteClickListener onDeleteClickListener;

    // Variable que almacena la posición del item seleccionado
    private int position;

    /**
     * Obtiene la posición actual seleccionada en el adaptador.
     *
     * @return La posición del item seleccionado.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Establece la posición del item seleccionado.
     *
     * @param position La posición del item seleccionado.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Constructor del adaptador.
     *
     * @param diffCallback  El callback para comparar los elementos de la lista.
     * @param listener  El listener para manejar los eventos de eliminación.
     */
    public ReservaListAdapter(@NonNull DiffUtil.ItemCallback<Reserva> diffCallback, OnDeleteClickListener listener) {
        super(diffCallback);  // Llamamos al constructor de la clase padre para manejar la comparación de elementos
        this.onDeleteClickListener = listener;  // Asignamos el listener de eliminación
    }

    /**
     * Crea una nueva vista de holder para un item de la lista.
     *
     * @param parent El contenedor padre donde se ubicará el ViewHolder.
     * @param viewType El tipo de vista (en este caso, no se usa, se puede ignorar).
     * @return Un nuevo objeto de tipo ReservaViewHolder.
     */
    @Override
    public ReservaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ReservaViewHolder.create(parent);  // Crear el ViewHolder para los elementos de la lista
    }

    /**
     * Vincula un item de la lista a su respectivo ViewHolder.
     *
     * @param holder El ViewHolder que contiene las vistas.
     * @param position La posición del item en la lista.
     */
    @Override
    public void onBindViewHolder(ReservaViewHolder holder, int position) {
        Reserva current = getItem(position);  // Obtenemos el item de la lista en la posición actual
        holder.bind(current);  // Vinculamos el objeto Reserva al ViewHolder

        // Configurar el botón de eliminar para el item actual
        ImageButton deleteButton = holder.itemView.findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(v -> {
            if (onDeleteClickListener != null) {
                onDeleteClickListener.onDeleteClick(current);  // Llamamos al listener para manejar la eliminación
            }
        });

        // Configurar el botón de editar para el item actual
        ImageButton editButton = holder.itemView.findViewById(R.id.edit_button);
        editButton.setOnClickListener(v -> {
            // Crear un Intent para abrir la actividad de edición
            Intent intent = new Intent(v.getContext(), ReservaEdit.class);
            intent.putExtra("id", current.getId());
            intent.putExtra("cliente", current.getCliente());
            intent.putExtra("telefono", current.getMovil());
            intent.putExtra("fechent", current.getFechEnt());
            intent.putExtra("fechsal", current.getFechSal());

            // Iniciar la actividad de edición
            v.getContext().startActivity(intent);
        });

        // Configurar el OnClickListener para el LinearLayout con id "reserva"
        holder.itemView.findViewById(R.id.reserva).setOnClickListener(v -> {
            // Crear un Intent para abrir la actividad de detalles
            Intent intent = new Intent(v.getContext(), ReservaShow.class);
            intent.putExtra("id", current.getId());
            intent.putExtra("cliente", current.getCliente());
            intent.putExtra("telefono", current.getMovil());
            intent.putExtra("fechent", current.getFechEnt());
            intent.putExtra("fechsal", current.getFechSal());
            Float precioTotal = current.getPrecioTotal();
            intent.putExtra("preciototal", precioTotal);

            // Iniciar la actividad de detalles
            v.getContext().startActivity(intent);
        });
    }

    /**
     * Obtiene el primer elemento de la lista de reservas.
     *
     * @return El primer elemento de la lista o null si la lista está vacía.
     */
    public Reserva getCurrent() {
        if (getCurrentList() != null && getCurrentList().size() > 0) {
            return getCurrentList().get(0);  // Retorna el primer elemento de la lista
        }
        return null;  // Si la lista está vacía, retorna null
    }

    /**
     * Interfaz para manejar la acción de eliminación de una reserva.
     */
    public interface OnDeleteClickListener {
        /**
         * Método que se llama cuando se hace clic en el botón de eliminar.
         *
         * @param reserva El objeto Reserva que se desea eliminar.
         */
        void onDeleteClick(Reserva reserva);
    }

    /**
     * Clase que compara los elementos de la lista de reservas para optimizar las actualizaciones.
     */
    static class ReservaDiff extends DiffUtil.ItemCallback<Reserva> {

        /**
         * Compara si dos elementos son el mismo.
         *
         * @param oldItem El elemento anterior.
         * @param newItem El nuevo elemento.
         * @return true si los elementos son el mismo, false si no lo son.
         */
        @Override
        public boolean areItemsTheSame(@NonNull Reserva oldItem, @NonNull Reserva newItem) {
            return oldItem.getId() == newItem.getId();  // Comparar por ID
        }

        /**
         * Compara si el contenido de dos elementos es el mismo.
         *
         * @param oldItem El elemento anterior.
         * @param newItem El nuevo elemento.
         * @return true si el contenido es igual, false si no lo es.
         */
        @Override
        public boolean areContentsTheSame(@NonNull Reserva oldItem, @NonNull Reserva newItem) {
            return oldItem.equals(newItem);  // Comparar el contenido completo
        }
    }
}
