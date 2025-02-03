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
 * Adapter para manejar una lista de ocupantes en una RecyclerView.
 * Utiliza ListAdapter para manejar automáticamente la actualización eficiente de la lista.
 */
public class OcupantesListAdapter extends ListAdapter<Ocupantes, OcupantesViewHolder> {
    // Variable para mantener la posición seleccionada
    private int position;

    /**
     * Devuelve la posición seleccionada.
     *
     * @return La posición seleccionada.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Establece la posición seleccionada.
     *
     * @param position La nueva posición seleccionada.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Constructor del adaptador que acepta un DiffUtil.ItemCallback para detectar cambios de manera eficiente.
     *
     * @param diffCallback Callback que ayuda a determinar qué elementos han cambiado entre listas.
     */
    public OcupantesListAdapter(@NonNull DiffUtil.ItemCallback<Ocupantes> diffCallback) {
        super(diffCallback);  // Llama al constructor de ListAdapter con el callback proporcionado
    }

    /**
     * Crea un nuevo ViewHolder para mostrar un ocupante.
     *
     * @param parent El grupo de vista donde se agregará el ViewHolder.
     * @param viewType El tipo de vista que se está creando.
     * @return Un nuevo OcupantesViewHolder.
     */
    @Override
    public OcupantesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return OcupantesViewHolder.create(parent);  // Crea un nuevo ViewHolder usando su método estático
    }

    /**
     * Asocia los datos del ocupante a la vista correspondiente en el ViewHolder.
     *
     * @param holder El ViewHolder que debe mostrar el dato.
     * @param position La posición del ocupante en la lista.
     */
    @Override
    public void onBindViewHolder(OcupantesViewHolder holder, int position) {
        // Obtiene el ocupante de la lista en la posición actual
        Ocupantes current = getItem(position);
        holder.bind(current);  // Vincula el ocupante actual al ViewHolder
    }

    /**
     * Devuelve el primer ocupante de la lista o el seleccionado, dependiendo de la lógica.
     *
     * @return El ocupante actual.
     */
    public Ocupantes getCurrent() {
        // Verifica si la lista de elementos actuales no está vacía
        if (getCurrentList() != null && getCurrentList().size() > 0) {
            return getCurrentList().get(0);  // Devuelve el primer elemento de la lista
        }
        return null;  // Si no hay elementos, devuelve null
    }

    /**
     * DiffUtil para comparar eficientemente los elementos de ocupantes cuando se actualiza la lista.
     */
    static class OcupantesDiff extends DiffUtil.ItemCallback<Ocupantes> {
        /**
         * Determina si dos elementos son los mismos (comparación de identidad).
         *
         * @param oldItem El elemento antiguo.
         * @param newItem El nuevo elemento.
         * @return true si los elementos son los mismos, false si no lo son.
         */
        @Override
        public boolean areItemsTheSame(@NonNull Ocupantes oldItem, @NonNull Ocupantes newItem) {
            // Compara por ID de reserva y nombre de parcela para determinar si son el mismo objeto.
            return oldItem.getReservaid() == newItem.getReservaid() && oldItem.getParcelanombre().equals(newItem.getParcelanombre());
        }

        /**
         * Determina si el contenido de los elementos es el mismo (comparación de datos).
         *
         * @param oldItem El elemento antiguo.
         * @param newItem El nuevo elemento.
         * @return true si los contenidos son los mismos, false si no lo son.
         */
        @Override
        public boolean areContentsTheSame(@NonNull Ocupantes oldItem, @NonNull Ocupantes newItem) {
            return oldItem.equals(newItem);  // Compara los contenidos de los objetos Ocupantes
        }
    }
}
