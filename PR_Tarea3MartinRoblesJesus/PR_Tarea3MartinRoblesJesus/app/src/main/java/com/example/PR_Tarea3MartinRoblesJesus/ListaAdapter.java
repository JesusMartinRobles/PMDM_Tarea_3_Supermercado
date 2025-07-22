package com.example.PR_Tarea3MartinRoblesJesus;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.PR_Tarea3MartinRoblesJesus.models.Producto;

import java.time.Instant;
import java.util.ArrayList;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.miViewHolder> implements View.OnClickListener {
    private final Context context;
    private ArrayList<Producto> listProductos;
    private View.OnClickListener listener;
    ListaAdapter(Context context, ArrayList<Producto> lProductos) {
        this.context = context;
        this.listProductos = lProductos;
    }
    @Override
    public void onClick(View view) {

    }
    @Override
    public void onBindViewHolder(@NonNull miViewHolder holder, int position) {
        holder.tvNombre.setText(this.listProductos.get(position).getName());
        holder.tvPrecio.setText(Double.toString(this.listProductos.get(position).getPrice()) +" €");

        boolean isAvilable = (this.listProductos.get(position).isAvilable() == 1);
        if(isAvilable){
            holder.iconDisp.setImageResource(R.drawable.stock);
        }else {
            holder.iconDisp.setImageResource(R.drawable.sin_stock);
        }
        int pos = position;
        if (listProductos.get(position).getURL()!= ""&& listProductos.get(position).getURL()!=null) {
            // Cargar la imagen con Glide
            Glide.with(context)
                    .load(listProductos.get(position).getURL())
                    .apply(new RequestOptions().centerCrop())
                    .into(holder.icon);
        } else {
            holder.icon.setImageResource(android.R.drawable.ic_menu_gallery);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavigateProduct(pos);
            }
        });
    }

    @NonNull
    @Override
    public miViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent,
                false);

        view.setOnClickListener(this);
        return new miViewHolder(view);
    }



    @Override
    public int getItemCount() {
        return this.listProductos.size();
    }
    public void setOnClickListener (View.OnClickListener listener){
        this.listener = listener;
    }
    public class miViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iconDisp;
        private final ImageView icon;
        private final TextView tvNombre;
        private final TextView tvPrecio;

        public miViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.txtProduct);
            tvPrecio = itemView.findViewById(R.id.txtPrice);
            icon = itemView.findViewById(R.id.iconProduct);
            iconDisp = itemView.findViewById(R.id.iconDisp);

        }
    }
    public  void NavigateProduct(int index){

        Intent intent = new Intent(context, DetalleActivity.class);
        Producto producto = listProductos.get(index);
        intent.putExtra("product", producto);
        context.startActivity(intent);
    }
}
