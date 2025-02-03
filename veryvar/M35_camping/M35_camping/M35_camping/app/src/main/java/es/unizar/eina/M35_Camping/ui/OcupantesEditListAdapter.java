package es.unizar.eina.M35_Camping.ui;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import es.unizar.eina.M35_Camping.R;
import es.unizar.eina.M35_Camping.database.Ocupantes;

/**
 * Adaptador para mostrar y gestionar la lista de ocupantes en la interfaz.
 * Este adaptador permite la edición y eliminación de ocupantes de una reserva.
 */
public class OcupantesEditListAdapter extends ListAdapter<Ocupantes, OcupantesEditViewHolder> {
    private OnDeleteClickListener onDeleteClickListener;  // Listener para eliminar un ocupante
    private OnEditClickListener onEditClickListener;  // Listener para editar un ocupante

    /**
     * Constructor del adaptador que recibe el DiffUtil y los listeners para las acciones de eliminar y editar.
     *
     * @param diffCallback Callback de comparación para gestionar la diferencia entre elementos en la lista.
     * @param deleteListener Listener para manejar la acción de eliminar.
     * @param editListener Listener para manejar la acción de editar.
     */
    public OcupantesEditListAdapter(@NonNull DiffUtil.ItemCallback<Ocupantes> diffCallback, OnDeleteClickListener deleteListener, OnEditClickListener editListener) {
        super(diffCallback);  // Llamamos al constructor del ListAdapter con el DiffCallback
        this.onDeleteClickListener = deleteListener;
        this.onEditClickListener = editListener;
    }

    /**
     * Crea un nuevo ViewHolder para la lista de ocupantes.
     *
     * @param parent El contenedor donde se debe agregar el ViewHolder.
     * @param viewType El tipo de vista que se va a crear.
     * @return El nuevo ViewHolder con la vista correspondiente.
     */
    @NonNull
    @Override
    public OcupantesEditViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return OcupantesEditViewHolder.create(parent);  // Creamos y devolvemos el ViewHolder
    }

    /**
     * Vincula los datos de un ocupante con su ViewHolder correspondiente.
     *
     * @param holder El ViewHolder donde se deben vincular los datos.
     * @param position La posición del elemento en la lista.
     */
    @Override
    public void onBindViewHolder(OcupantesEditViewHolder holder, int position) {
        Ocupantes current = getItem(position);  // Obtenemos el ocupante actual de la lista
        holder.bind(current);  // Vinculamos el ocupante con el ViewHolder

        // Configuramos el botón de eliminar
        ImageButton deleteButton = holder.itemView.findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(v -> {
            if (onDeleteClickListener != null) {
                onDeleteClickListener.onDeleteClick(current);  // Llamamos al listener para eliminar el ocupante
            }
        });

        // Configuramos el botón de editar
        ImageButton editButton = holder.itemView.findViewById(R.id.edit_button);
        editButton.setOnClickListener(v -> {
            if (onEditClickListener != null) {
                onEditClickListener.onEditClick(current);  // Llamamos al listener para editar el ocupante
            }
        });
    }

    /**
     * Interfaz para manejar la acción de eliminación de un ocupante.
     * Cuando se hace clic en el botón de eliminar, se ejecuta el método onDeleteClick.
     */
    public interface OnDeleteClickListener {
        /**
         * Método que maneja la acción de eliminar un ocupante.
         *
         * @param ocupantes El objeto Ocupantes que debe ser eliminado.
         */
        void onDeleteClick(Ocupantes ocupantes);
    }

    /**
     * Interfaz para manejar la acción de edición de un ocupante.
     * Cuando se hace clic en el botón de editar, se ejecuta el método onEditClick.
     */
    public interface OnEditClickListener {
        /**
         * Método que maneja la acción de editar un ocupante.
         *
         * @param ocupantes El objeto Ocupantes que debe ser editado.
         */
        void onEditClick(Ocupantes ocupantes);
    }

    /**
     * Clase DiffUtil para manejar la comparación de elementos en el adaptador de manera eficiente.
     * DiffUtil calcula las diferencias entre listas de elementos y optimiza las actualizaciones de la vista.
     */
    static class OcupantesEditDiff extends DiffUtil.ItemCallback<Ocupantes> {

        /**
         * Compara si los elementos de la lista son los mismos, basándose en identificadores únicos.
         *
         * @param oldItem El elemento anterior en la lista.
         * @param newItem El nuevo elemento en la lista.
         * @return true si los elementos son los mismos, false si no lo son.
         */
        @Override
        public boolean areItemsTheSame(@NonNull Ocupantes oldItem, @NonNull Ocupantes newItem) {
            // Compara los elementos por el ID de la reserva y el nombre de la parcela
            return oldItem.getReservaid().equals(newItem.getReservaid()) && oldItem.getParcelanombre().equals(newItem.getParcelanombre());
        }

        /**
         * Compara si los contenidos de los elementos son los mismos.
         *
         * @param oldItem El elemento anterior en la lista.
         * @param newItem El nuevo elemento en la lista.
         * @return true si los contenidos son iguales, false si no lo son.
         */
        @Override
        public boolean areContentsTheSame(@NonNull Ocupantes oldItem, @NonNull Ocupantes newItem) {
            return oldItem.equals(newItem);  // Compara los elementos usando el método equals
        }
    }
}
