package nicolasrusso.ejercicioexamen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import nicolasrusso.ejercicioexamen2.databinding.ActivityAddProductoBinding;
import nicolasrusso.ejercicioexamen2.models.ProductoModel;

public class AddProductoActivity extends AppCompatActivity {
    private ActivityAddProductoBinding binding; // El binding

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_producto);

        // Poder usar el binding en esta actividad
        binding = ActivityAddProductoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAgregarAddProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Agarrar lo que ha escrito el usuario
                String nombre = binding.txtNombreAddProducto.getText().toString();
                String cantidadS = binding.txtCantidadAddProducto.getText().toString();
                String precioS = binding.txtPrecioAddProducto.getText().toString();

                // Si todas las casillas están rellenadas, crear un nuevo producto
                if(!nombre.isEmpty() && !cantidadS.isEmpty() && !precioS.isEmpty()){
                    ProductoModel producto = new ProductoModel(nombre, Integer.parseInt(cantidadS), Float.parseFloat(precioS));

                    // Le paso el producto a mi actividad principal
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PRODUCTO", producto);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}