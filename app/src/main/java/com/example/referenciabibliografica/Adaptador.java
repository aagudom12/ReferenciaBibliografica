package com.example.referenciabibliografica;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>{
    private List<Elemento> datos;

    private List<Elemento> datosFiltrados;
    private LayoutInflater inflador;
    private Context context;

    //almacena el estado de "favorito" de cada elemento en el recyclerview
    private SharedPreferences sharedPreferences;

    public Adaptador(List<Elemento> itemList, Context context) {
        this.inflador = LayoutInflater.from(context);
        this.datos = itemList;
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("favoritos", Context.MODE_PRIVATE);
        this.datosFiltrados = new ArrayList<>(itemList);
    }

    //indica al RecyclerView cuantos elementos debe mostrar
    @Override
    public int getItemCount() {
        return datosFiltrados.size();
    }

    //Se llama cuando se necesita crear una nueva instancia de ViewHolder para contener los elementos de la lista.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflador.inflate(R.layout.elemento,parent,false);
        return new ViewHolder(view);
    }

    //asocia datos al ViewHolder, además de gestionar la lógica de favoritos
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.bindData(datosFiltrados.get(position));

        // Verificar el estado de favorito y actualizar el botón
        boolean isFavorite = sharedPreferences.getBoolean(datosFiltrados.get(position).getTitulo(), false);
        actualizarEstadoFavorito(holder, isFavorite);

        // Manejar clic del botón para cambiar el estado de favorito
        holder.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition(); // Obtener la posición actual del adaptador
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    boolean isFavorite = sharedPreferences.getBoolean(datos.get(adapterPosition).getTitulo(), false);
                    isFavorite = !isFavorite;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(datosFiltrados.get(adapterPosition).getTitulo(), isFavorite);
                    editor.apply();
                    actualizarEstadoFavorito(holder, isFavorite);
                }
                Elemento contacto = datosFiltrados.get(holder.getAdapterPosition());

                // Cambia el valor de esFavorito
                contacto.setFavorito(!contacto.isFavorito()); // Cambia el valor opuesto

                // Notifica al adaptador que los datos han cambiado
                notifyDataSetChanged();
            }
        });
    }

    public void filterList(ArrayList<Elemento> filteredList) {
        datosFiltrados = filteredList;
        notifyDataSetChanged(); // Notificar al adaptador sobre el cambio en los datos filtrados

    }

    //actualiza el estado visual del botón de favoritos
    @SuppressLint("ResourceAsColor")
    private void actualizarEstadoFavorito(ViewHolder holder, boolean isFavorite) {
        if (isFavorite) {
            holder.favButton.setText("Quitar de favoritos");
            holder.favButton.setBackgroundColor(R.color.white);
        } else {
            holder.favButton.setText("Agregar a favoritos");
        }
    }

    //representa una vista individual dentro del RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView nombre;
        Button favButton;

        //referencias a los elementos visuales
        ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            nombre = itemView.findViewById(R.id.nameTextView);
            favButton = itemView.findViewById(R.id.favButton);
        }

        //actualiza la vista con los datos del elemento específico
        void bindData(final Elemento item){
            iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            nombre.setText(item.getTitulo());
        }
    }
}
