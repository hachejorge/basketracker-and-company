package es.unizar.eina.M35_Camping.ui;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import es.unizar.eina.M35_Camping.R;
import es.unizar.eina.M35_Camping.database.Parcela;

/**
 * ViewHolder que representa una parcela en el RecyclerView.
 * Esta clase se encarga de vincular los datos de una parcela con las vistas correspondientes en la interfaz de usuario.
 */
class ParcelaViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

    private final TextView nombreTextView;  // TextView que muestra el nombre de la parcela
    private final TextView occupantsValueTextView;  // TextView que muestra el número de ocupantes de la parcela
    private final TextView pricePerPersonTextView;  // TextView que muestra el precio por persona

    /**
     * Constructor del ViewHolder. Inicializa las vistas que serán usadas en el RecyclerView.
     *
     * @param itemView Vista del item del RecyclerView que contiene los elementos visuales de la parcela.
     */
    private ParcelaViewHolder(View itemView) {
        super(itemView);

        // Inicialización de los TextViews que se utilizarán para mostrar los datos de la parcela
        nombreTextView = itemView.findViewById(R.id.nombreTextView);
        occupantsValueTextView = itemView.findViewById(R.id.occupantsValueTextView);
        pricePerPersonTextView = itemView.findViewById(R.id.pricePerPersonValueTextView);

        // Configura el contexto de menú para este item, permitiendo mostrar un menú contextual
        itemView.setOnCreateContextMenuListener(this);
    }

    /**
     * Método que vincula los datos de la parcela con las vistas correspondientes.
     *
     * @param parcela El objeto Parcela que contiene la información que se mostrará.
     */
    public void bind(Parcela parcela) {
        // Verifica si cada TextView no es null antes de asignar valores para evitar NullPointerException
        if (nombreTextView != null && occupantsValueTextView != null && pricePerPersonTextView != null) {
            // Asigna los valores de la parcela a los TextViews correspondientes
            nombreTextView.setText(parcela.getNombre());
            occupantsValueTextView.setText("Nº Ocupantes: " + parcela.getMaxOcup());
            pricePerPersonTextView.setText("Precio por Persona: " + parcela.getPrecPer() + "€");
        }
    }

    /**
     * Método estático para crear un nuevo ViewHolder. Infla la vista de un item del RecyclerView.
     *
     * @param parent El ViewGroup al que se adjuntará el item.
     * @return Un nuevo objeto ParcelaViewHolder con la vista inflada.
     */
    static ParcelaViewHolder create(ViewGroup parent) {
        // Infla el layout de un item de parcela
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.parcelaview_item, parent, false);
        return new ParcelaViewHolder(view);  // Devuelve un nuevo ViewHolder con la vista inflada
    }

    /**
     * Método que se llama cuando se crea un menú contextual para un item del RecyclerView.
     * Aquí se definen las opciones disponibles en el menú contextual (editar y eliminar).
     *
     * @param menu El menú contextual que se va a mostrar.
     * @param v La vista que activó el menú contextual.
     * @param menuInfo Información adicional sobre el menú contextual.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // Agrega las opciones de "Eliminar" y "Editar" al menú contextual
        menu.add(Menu.NONE, Parcela.DELETE_ID, Menu.NONE, R.string.menu_delete);
        menu.add(Menu.NONE, Parcela.EDIT_ID, Menu.NONE, R.string.menu_edit);
    }
}
