package com.example.ivonne.ropadeportiva.models;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ivonne.ropadeportiva.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Adapter extends  RecyclerView.Adapter<Adapter.ProductosviewHolder>{

    List<Producto> productos;

    public Adapter(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public ProductosviewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler, parent, false);
       ProductosviewHolder holder = new ProductosviewHolder(v);
       return holder;
    }

    @Override
    public void onBindViewHolder(ProductosviewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.textViewNombre.setText(producto.getNombre());
        holder.textViewColor.setText(producto.getColor());
        holder.textViewCantidad.setText(producto.getCantidad());
        holder.textViewPrecio.setText(producto.getPrecio());
        holder.textViewSexo.setText(producto.getSexo());
        holder.textViewTalla.setText(producto.getTalla());
        holder.textViewTela.setText(producto.getTela());
        holder.setOnclickListener();

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static  class ProductosviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private DatabaseReference mAcademicoReference;
        private String coleccionProductos="productos";

        TextView textViewNombre, textViewColor, textViewCantidad, textViewPrecio, textViewSexo, textViewTalla, textViewTela;
        Button añadir;

        public ProductosviewHolder(View itemView){
            super(itemView);

            añadir = itemView.findViewById(R.id.buttonAñadir);

            textViewNombre = (TextView) itemView.findViewById(R.id.textview_nombre);
            textViewColor = (TextView) itemView.findViewById(R.id.textview_color);
            textViewCantidad = (TextView) itemView.findViewById(R.id.textview_cantidad);
            textViewPrecio = (TextView) itemView.findViewById(R.id.textview_precio);
            textViewSexo = (TextView) itemView.findViewById(R.id.textview_sexo);
            textViewTalla = (TextView) itemView.findViewById(R.id.textview_talla);
            textViewTela = (TextView) itemView.findViewById(R.id.textview_tela);
        }

        public void setOnclickListener(){
            añadir.setOnClickListener(this);
        }

    @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.buttonAñadir:
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    mAcademicoReference = database.getReference();
                    String coleccionCarrito="Carrito_Compra";

                    Carrito_C carr = new Carrito_C ( textViewNombre.getText().toString(),textViewColor.getText().toString(),
                            textViewCantidad.getText().toString(),textViewPrecio .getText().toString(),
                            textViewSexo.getText().toString(),textViewTalla.getText().toString(),
                            textViewTela.getText().toString());

                    String cadena = "Prdt" + textViewNombre.getText().toString().substring(0, textViewNombre.getText().toString().indexOf(' ', 0)-1);
                    mAcademicoReference.child(coleccionCarrito).child(cadena).setValue(carr);
            }
        }
    }
}
