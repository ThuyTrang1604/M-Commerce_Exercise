package com.example.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adapters.PaymentMethodAdapter;
import com.example.connectors.PaymentMethodConnector;
import com.example.connectors.SQLiteConnector;
import com.example.models.ListPaymentMethod;
import com.example.models.PaymentMethod;

public class PaymentMethodActivity extends AppCompatActivity {
    ListView lvPaymentMethod;
    PaymentMethodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        lvPaymentMethod = findViewById(R.id.lvPaymentMethod);

        adapter = new PaymentMethodAdapter(this, R.layout.item_paymentmethod);
        PaymentMethodConnector connector = new PaymentMethodConnector();
        ListPaymentMethod list = connector.getAll(new SQLiteConnector(this).openDatabase());

        adapter.addAll(list.getMethods());
        lvPaymentMethod.setAdapter(adapter);

        lvPaymentMethod.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PaymentMethod selected = adapter.getItem(position);
                Intent intent = new Intent();
                intent.putExtra("SELECTED_PAYMENT", selected);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
