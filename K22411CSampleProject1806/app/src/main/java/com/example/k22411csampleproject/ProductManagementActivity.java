package com.example.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.models.Category;
import com.example.models.ListCategory;
import com.example.models.Product;

public class ProductManagementActivity extends AppCompatActivity {
    Spinner spinnerCategory;
    ArrayAdapter<Category>adapterCategory;
    ListCategory listCategory;

    ListView lvProduct;
    ArrayAdapter<Product>adapterProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_management);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void addEvents() {
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Category c=adapterCategory.getItem(i);
                displayProductsByCategory(c);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // Đoạn này để xử lý click item sản phẩm trên ListView
        lvProduct.setOnItemClickListener((parent, view, position, id) -> {
            Product selectedProduct = adapterProduct.getItem(position);
            if (selectedProduct != null) {
                Intent intent = new Intent(ProductManagementActivity.this, ProductDetailActivity.class);
                intent.putExtra("SELECTED_PRODUCT", selectedProduct);
                startActivity(intent);
            }
        });
    }

    private void displayProductsByCategory(Category c) {
        //xóa dữ liệu cũ trong listview đi:
        adapterProduct.clear();
        //nạp mới lại dữ liệu cho adapter;
        adapterProduct.addAll(c.getProducts());
    }

    private void addViews() {
        spinnerCategory=findViewById(R.id.spinnerCategory);
        adapterCategory=new ArrayAdapter<>(
                ProductManagementActivity.this,
                android.R.layout.simple_spinner_item);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);

        listCategory=new ListCategory();
        listCategory.generate_sample_product_dataset();
        adapterCategory.addAll(listCategory.getCategories());

        lvProduct=findViewById(R.id.lvProduct);
        adapterProduct=new ArrayAdapter<>(
                ProductManagementActivity.this,
                android.R.layout.simple_list_item_1);
        lvProduct.setAdapter(adapterProduct);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_add_product) {
            Toast.makeText(this, "Mở màn hình thêm mới sản phẩm", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.menu_generate_report) {
            Toast.makeText(this, "Đang tạo báo cáo sản phẩm...", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.menu_help) {
            Toast.makeText(this, "Mở hướng dẫn sử dụng cho sản phẩm", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}