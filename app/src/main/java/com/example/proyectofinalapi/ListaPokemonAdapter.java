package com.example.proyectofinalapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.proyectofinalapi.modelos.Pokemon;

import java.util.ArrayList;

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder>  {

    private  ArrayList<Pokemon> dataset;
    private Context context;

    public ListaPokemonAdapter(Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon p = dataset.get(position);
        holder.nombreTextView.setText(p.getName());
        holder.abilityTextView.setText(p.getAbility());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/pokeAPI/sprites/master/sprites/pokemon/" + p.getNumber() + ".png")

                .centerCrop()

                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPokemon(ArrayList<Pokemon> listapokemon) {
        dataset.addAll(listapokemon);
        notifyDataSetChanged();
    }


    public class ViewHolder extends  RecyclerView.ViewHolder {
            private ImageView fotoImageView;
            private TextView nombreTextView;
            private TextView abilityTextView;

            public ViewHolder(View itemView){
                        super(itemView);
                        fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
                        nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
                        abilityTextView = (TextView) itemView.findViewById(R.id.abilityTextView);
                    }

        }

}
