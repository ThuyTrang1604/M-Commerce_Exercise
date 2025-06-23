package com.example.mynewapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    private EditText edtProductCode, edtProductName, edtUnitPrice;
    private Button btnAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        edtProductCode = findViewById(R.id.edtProductCode);
        edtProductName = findViewById(R.id.edtProductName);
        edtUnitPrice = findViewById(R.id.edtUnitPrice);
        btnAddProduct = findViewById(R.id.btnAddProduct);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = edtProductCode.getText().toString().trim();
                String name = edtProductName.getText().toString().trim();
                String priceStr = edtUnitPrice.getText().toString().trim();
                if (code.isEmpty() || name.isEmpty() || priceStr.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                double price = Double.parseDouble(priceStr);
                int newId = SimulationData.getProducts().size() + 1;
                // Thêm sản phẩm không có ImageLink
                SimulationData.addProduct(new Product(newId, code, name, price, ""));
                Toast.makeText(AddActivity.this, "Product added!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
