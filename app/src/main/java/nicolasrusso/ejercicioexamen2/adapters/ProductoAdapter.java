package nicolasrusso.ejercicioexamen2.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;

import nicolasrusso.ejercicioexamen2.R;
import nicolasrusso.ejercicioexamen2.models.ProductoModel;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoVH> {
    private List<ProductoModel> objects;
    private int resource;
    private Context context;
    private NumberFormat nf;

    public ProductoAdapter(List<ProductoModel> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
        this.nf = NumberFormat.getCurrencyInstance();
    }

    @NonNull
    @Override
    public ProductoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productoView = LayoutInflater.from(context).inflate(resource, null);
        productoView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ProductoVH(productoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoVH holder, int position) {
        ProductoModel producto = objects.get(position);
        holder.lblNombre.setText(producto.getNombre());
        holder.lblCantidad.setText(String.valueOf(producto.getCantidad()));
        holder.lblPrecio.setText(nf.format(producto.getPrecio()));

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarBorrado(producto, holder.getAdapterPosition()).show();
            }
        });
    }

    private AlertDialog confirmarBorrado(ProductoModel producto, int adapterPosition) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("¿SEGURO?");
        builder.setCancelable(false);
        builder.setNegativeButton("NO", null);
        builder.setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                objects.remove(producto);
                notifyItemRemoved(adapterPosition);
            }
        });
        return builder.create();
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ProductoVH extends RecyclerView.ViewHolder{
        TextView lblNombre, lblCantidad, lblPrecio;
        ImageButton btnEliminar;
        public ProductoVH(@NonNull View itemView) {
            super(itemView);
            lblNombre = itemView.findViewById(R.id.lblNombreProductoVH);
            lblCantidad = itemView.findViewById(R.id.lblCantidadProductoVH);
            lblPrecio = itemView.findViewById(R.id.lblPrecioProductoVH);
            btnEliminar = itemView.findViewById(R.id.btnEliminarProductoVH);
        }
    }
}
