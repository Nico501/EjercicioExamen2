package nicolasrusso.ejercicioexamen2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import nicolasrusso.ejercicioexamen2.adapters.ProductoAdapter;
import nicolasrusso.ejercicioexamen2.databinding.ActivityMainBinding;
import nicolasrusso.ejercicioexamen2.models.ProductoModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<ProductoModel> productos;
    private ActivityResultLauncher<Intent> launcherAddProducto;
    private ProductoAdapter adapter;
    private RecyclerView.LayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        productos = new ArrayList<>();
        adapter = new ProductoAdapter(productos, R.layout.producto_view_holder, this);
        lm = new GridLayoutManager(this, 1);
        binding.contentMain.contenedorMain.setAdapter(adapter);
        binding.contentMain.contenedorMain.setLayoutManager(lm);
        inicializarLaunchers();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherAddProducto.launch(new Intent(MainActivity.this, AddProductoActivity.class));
            }
        });
    }

    private void inicializarLaunchers() {
        launcherAddProducto = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if(o.getResultCode() == RESULT_OK){
                    if(o.getData() != null && o.getData().getExtras() != null){

                        //Esta línea va a dar error, hay que darle a más opciones y a la primera
                        ProductoModel producto = (ProductoModel) o.getData().getExtras().getSerializable("PRODUCTO");
                        productos.add(producto);
                        adapter.notifyItemInserted(productos.size() -1);
                    }
                }
            }
        });
    }
}