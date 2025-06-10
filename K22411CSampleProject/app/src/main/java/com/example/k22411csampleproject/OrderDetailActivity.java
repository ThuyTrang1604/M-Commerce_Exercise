package com.example.k22411csampleproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapters.OrderDetailAdapter;
import com.example.connectors.OrderDetailConnector;
import com.example.connectors.SQLiteConnector;
import com.example.models.OrderDetails;
import com.example.models.OrdersViewer;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class OrderDetailActivity extends AppCompatActivity {
    private static final String TAG = "OrderDetailActivity";
    private ListView lvOrderDetails;
    private OrderDetailAdapter adapter;
    private TextView txtOrderCode, txtOrderDate, txtCustomerName, txtEmployeeName, txtTotalValue;
    private NumberFormat currencyFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        addViews();
        loadOrderDetails();
    }

    private void addViews() {
        lvOrderDetails = findViewById(R.id.lvOrderDetails);
        txtOrderCode = findViewById(R.id.txtOrderCode);
        txtOrderDate = findViewById(R.id.txtOrderDate);
        txtCustomerName = findViewById(R.id.txtCustomerName);
        txtEmployeeName = findViewById(R.id.txtEmployeeName);
        txtTotalValue = findViewById(R.id.txtTotalValue);

        adapter = new OrderDetailAdapter(this, R.layout.item_order_detail);
        lvOrderDetails.setAdapter(adapter);
    }

    private void loadOrderDetails() {
        try {
            OrdersViewer order = (OrdersViewer) getIntent().getSerializableExtra("SELECTED_ORDER");
            if (order == null) {
                Toast.makeText(this, "Không tìm thấy thông tin đơn hàng", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            // Hiển thị thông tin đơn hàng
            txtOrderCode.setText("Mã đơn hàng: " + order.getCode());
            txtOrderDate.setText("Ngày đặt: " + order.getOrderDate());
            txtCustomerName.setText("Khách hàng: " + order.getCustomerName());
            txtEmployeeName.setText("Nhân viên: " + order.getEmployeeName());

            // Lấy chi tiết đơn hàng từ database
            SQLiteConnector connector = new SQLiteConnector(this);
            OrderDetailConnector odc = new OrderDetailConnector();
            ArrayList<OrderDetails> details = odc.getOrderDetails(connector.openDatabase(), order.getId());

            if (details == null || details.isEmpty()) {
                Toast.makeText(this, "Không có chi tiết đơn hàng", Toast.LENGTH_SHORT).show();
                return;
            }

            adapter.addAll(details);
            adapter.notifyDataSetChanged();

            // Tính tổng tiền bao gồm VAT
            double totalAmount = 0;
            for (OrderDetails detail : details) {
                double subtotal = detail.getQuantity() * detail.getPrice();
                double discountAmount = subtotal * (detail.getDiscount() / 100.0);
                double afterDiscount = subtotal - discountAmount;
                double vatAmount = afterDiscount * (detail.getVAT() / 100.0);
                totalAmount += afterDiscount + vatAmount;
            }

            txtTotalValue.setText("Tổng tiền (bao gồm VAT): " + currencyFormat.format(totalAmount));

        } catch (Exception e) {
            Log.e(TAG, "Error loading order details: " + e.getMessage());
            Toast.makeText(this, "Lỗi khi tải chi tiết đơn hàng: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
} 