package com.example.mynewapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.widget.Button;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;
    private Button btnAdd, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        btnAdd = findViewById(R.id.btnAdd);
        btnAbout = findViewById(R.id.btnAbout);
        // Lấy dữ liệu mô phỏng
        List<Product> products = SimulationData.getProducts();
        productAdapter = new ProductAdapter(products);
        recyclerViewProducts.setAdapter(productAdapter);
        btnAdd.setOnClickListener(v -> startActivity(new Intent(this, AddActivity.class)));
        btnAbout.setOnClickListener(v -> startActivity(new Intent(this, AboutActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload lại danh sách khi quay lại từ AddActivity
        productAdapter = new ProductAdapter(SimulationData.getProducts());
        recyclerViewProducts.setAdapter(productAdapter);
    }
}
