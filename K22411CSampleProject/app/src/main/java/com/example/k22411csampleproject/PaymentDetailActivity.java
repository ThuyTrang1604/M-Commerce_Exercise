package com.example.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.models.Payment;

public class PaymentDetailActivity extends AppCompatActivity {
    EditText edt_payment_id, edt_payment_name, edt_payment_description;
    CheckBox chk_payment_active;
    Button btnNew, btnSave, btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
        display_infor();
    }

    private void addEvents() {
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_payment_id.setText("");
                edt_payment_name.setText("");
                edt_payment_description.setText("");
                chk_payment_active.setChecked(true);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process_save_payment();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentDetailActivity.this,
                        "Xóa phương thức thanh toán",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void process_save_payment() {
        Payment p = new Payment();
        p.setId(Integer.parseInt(edt_payment_id.getText().toString()));
        p.setName(edt_payment_name.getText().toString());
        p.setDescription(edt_payment_description.getText().toString());
        p.setActive(chk_payment_active.isChecked());

        Intent intent = getIntent();
        intent.putExtra("NEW_PAYMENT", p);
        setResult(500, intent);
        finish();
    }

    private void addViews() {
        edt_payment_id = findViewById(R.id.edt_payment_id);
        edt_payment_name = findViewById(R.id.edt_payment_name);
        edt_payment_description = findViewById(R.id.edt_payment_description);
        chk_payment_active = findViewById(R.id.chk_payment_active);

        btnNew = findViewById(R.id.btnNew);
        btnSave = findViewById(R.id.btnSave);
        btnRemove = findViewById(R.id.btnRemove);
    }

    private void display_infor() {
        Intent intent = getIntent();
        Payment p = (Payment) intent.getSerializableExtra("SELECTED_PAYMENT");
        if (p == null)
            return;
        edt_payment_id.setText(p.getId() + "");
        edt_payment_name.setText(p.getName());
        edt_payment_description.setText(p.getDescription());
        chk_payment_active.setChecked(p.isActive());
    }
} 