package es.unizar.eina.M35_Camping.ui;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import es.unizar.eina.M35_Camping.R;
import es.unizar.eina.M35_Camping.database.Parcela;

/**
 * Adaptador personalizado para manejar una lista de objetos {@link Parcela} en un RecyclerView.
 * Este adaptador permite eliminar, editar y mostrar los detalles de las parcelas.
 */
public class ParcelaListAdapter extends ListAdapter<Parcela, ParcelaViewHolder> {

    private OnDeleteClickListener onDeleteClickListener;  // Listener para manejar la eliminación de parcelas

    /**
     * Constructor del adaptador que recibe el callback DiffUtil para optimizar el manejo de la lista
     * y un listener para la eliminación de parcelas.
     *
     * @param diffCallback Callback para comparar los elementos de la lista y determinar cambios
     * @param listener Listener para manejar la eliminación de una parcela
     */
    public ParcelaListAdapter(@NonNull DiffUtil.ItemCallback<Parcela> diffCallback, OnDeleteClickListener listener) {
        super(diffCallback);
        this.onDeleteClickListener = listener;
    }

    /**
     * Crea un nuevo {@link ParcelaViewHolder} para contener la vista de un ítem de la lista.
     *
     * @param parent El ViewGroup al que se añadirá el ViewHolder
     * @param viewType Tipo de vista (generalmente no se utiliza en este caso)
     * @return Un nuevo {@link ParcelaViewHolder} para el RecyclerView
     */
    @NonNull
    @Override
    public ParcelaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ParcelaViewHolder.create(parent);  // Creamos el ViewHolder a partir del layout correspondiente
    }

    /**
     * Vincula un objeto {@link Parcela} con el {@link ParcelaViewHolder} para mostrar la información
     * en la vista del ítem del RecyclerView.
     *
     * @param holder El ViewHolder donde se vinculará la parcela
     * @param position La posición de la parcela en la lista
     */
    @Override
    public void onBindViewHolder(ParcelaViewHolder holder, int position) {
        Parcela current = getItem(position);  // Obtenemos la parcela actual de la lista
        holder.bind(current);  // Vinculamos los datos de la parcela al ViewHolder

        // Configuramos el botón de eliminar
        ImageButton deleteButton = holder.itemView.findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(v -> {
            if (onDeleteClickListener != null) {
                onDeleteClickListener.onDeleteClick(current);  // Llamamos al listener para manejar la eliminación de la parcela
            }
        });

        // Configuramos el botón de edición
        ImageButton editButton = holder.itemView.findViewById(R.id.edit_button);
        editButton.setOnClickListener(v -> {
            // Creamos un Intent para abrir la actividad de edición de la parcela
            Intent intent = new Intent(v.getContext(), ParcelaEdit.class);
            intent.putExtra("nombre", current.getNombre());
            intent.putExtra("descripcion", current.getDes());
            intent.putExtra("maxOcupantes", current.getMaxOcup());
            intent.putExtra("precioPorPersona", current.getPrecPer());

            // Iniciamos la actividad de edición
            v.getContext().startActivity(intent);
        });

        // Configuramos el OnClickListener para el LinearLayout que representa la parcela completa
        holder.itemView.findViewById(R.id.parcela).setOnClickListener(v -> {
            // Creamos un Intent para abrir la actividad de detalles de la parcela
            Intent intent = new Intent(v.getContext(), ParcelaShow.class);
            intent.putExtra("nombre", current.getNombre());
            intent.putExtra("descripcion", current.getDes());
            intent.putExtra("maxOcupantes", current.getMaxOcup());
            intent.putExtra("precioPorPersona", current.getPrecPer());

            // Iniciamos la actividad de detalles
            v.getContext().startActivity(intent);
        });
    }

    /**
     * Interfaz para manejar la acción de eliminación de una parcela.
     * El método {@link #onDeleteClick(Parcela)} es llamado cuando el usuario hace clic en el botón de eliminar.
     */
    public interface OnDeleteClickListener {
        /**
         * Método que se llama cuando el usuario solicita eliminar una parcela.
         *
         * @param parcela La parcela que se debe eliminar
         */
        void onDeleteClick(Parcela parcela);  // Método para eliminar una parcela
    }

    /**
     * Clase estática que maneja la comparación de objetos {@link Parcela} para optimizar las actualizaciones en el RecyclerView.
     * Utiliza DiffUtil para determinar si los elementos son iguales o han cambiado.
     */
    static class ParcelaDiff extends DiffUtil.ItemCallback<Parcela> {

        /**
         * Compara si dos objetos {@link Parcela} son el mismo, basándose en el nombre (que debe ser único).
         *
         * @param oldItem El objeto Parcela anterior
         * @param newItem El nuevo objeto Parcela
         * @return true si ambos objetos son el mismo
         */
        @Override
        public boolean areItemsTheSame(@NonNull Parcela oldItem, @NonNull Parcela newItem) {
            return oldItem.getNombre().equals(newItem.getNombre());  // Compara por nombre (único identificador)
        }

        /**
         * Compara si el contenido de dos objetos {@link Parcela} es el mismo.
         *
         * @param oldItem El objeto Parcela anterior
         * @param newItem El nuevo objeto Parcela
         * @return true si el contenido de ambos objetos es el mismo
         */
        @Override
        public boolean areContentsTheSame(@NonNull Parcela oldItem, @NonNull Parcela newItem) {
            return oldItem.equals(newItem);  // Compara si los contenidos son iguales
        }
    }
}
