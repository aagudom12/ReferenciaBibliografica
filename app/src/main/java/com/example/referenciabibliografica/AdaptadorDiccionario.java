package com.example.referenciabibliografica;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorDiccionario extends RecyclerView.Adapter<AdaptadorDiccionario.ViewHolder>{
    private List<Palabra> wordList;

    public AdaptadorDiccionario(List<Palabra> wordList) {
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.palabra, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Palabra word = wordList.get(position);
        holder.wordTextView.setText(word.getPalabra());
        holder.meaningTextView.setText(word.getSignificado());
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView wordTextView;
        TextView meaningTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wordTextView = itemView.findViewById(R.id.wordTextView);
            meaningTextView = itemView.findViewById(R.id.meaningTextView);
        }
    }
}
