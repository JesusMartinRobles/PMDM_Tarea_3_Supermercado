package com.example.PR_Tarea3MartinRoblesJesus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.PR_Tarea3MartinRoblesJesus.models.BaseDatos;
import com.example.PR_Tarea3MartinRoblesJesus.models.Producto;

import java.util.ArrayList;

public class EliminarAdapter extends RecyclerView.Adapter<EliminarAdapter.MiViewHolder> implements View.OnClickListener {


    private ArrayList<Producto> listProductos;
    private View.OnClickListener listener;
    BaseDatos db;

    EliminarAdapter(ArrayList<Producto> listProductos) {
        this.listProductos = listProductos;
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produc_deletet, parent,
                false);
        return new MiViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        holder.tvNombre.setText(listProductos.get(position).getName());
        holder.tvPrecio.setText(Double.toString(listProductos.get(position).getPrice()));
        holder.tvId.setText(Integer.toString(listProductos.get(position).getId()));


    }

    @Override
    public int getItemCount() {
        return this.listProductos.size();
    }

    public void setOnClickListener (View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public class MiViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvNombre;
        private final TextView tvId;
        private final TextView tvPrecio;
        private final Button bEliminar;
        public MiViewHolder(@NonNull View view) {
            super(view);

            tvNombre = view.findViewById(R.id.txtProduct);
            tvId = view.findViewById(R.id.idProduct);
            tvPrecio = view.findViewById(R.id.txtPrice);
            bEliminar = view.findViewById(R.id.btnDelete);
            bEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = Integer.parseInt(tvId.getText().toString());
                    deleteProduct(v, id);
                }
            });

        }
    }
    public  void deleteProduct(View v,int id){

        BaseDatos db = new BaseDatos(v.getContext());
        db.deleteProducto(id);
        Toast.makeText(v.getContext(), "Elemento Eliminado", Toast.LENGTH_SHORT).show();
        int index = 0;
        int count = 0;
        for (Producto product: listProductos) {
            if(product.getId()==id){
                index=count;
            }
            count++;
        }

        listProductos.remove(index);
        this.notifyDataSetChanged();
    }
}
