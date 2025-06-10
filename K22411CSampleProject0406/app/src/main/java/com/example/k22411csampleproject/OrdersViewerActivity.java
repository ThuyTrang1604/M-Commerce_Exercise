package com.example.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.database.sqlite.SQLiteDatabase;
import com.example.adapters.OrdersViewerAdapter;
import com.example.connectors.OrdersViewerConnector;
import com.example.connectors.SQLiteConnector;
import com.example.models.OrdersViewer;

import java.util.ArrayList;

public class OrdersViewerActivity extends AppCompatActivity {

    private static final String TAG = "OrdersViewerActivity";
    ListView lvOrdersViewer;
    OrdersViewerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orders_viewer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void addEvents() {
        lvOrdersViewer.setOnItemClickListener((parent, view, position, id) -> {
            try {
                OrdersViewer selectedOrder = adapter.getItem(position);
                if (selectedOrder != null) {
                    Intent intent = new Intent(OrdersViewerActivity.this, OrderDetailActivity.class);
                    intent.putExtra("SELECTED_ORDER", selectedOrder);
                    startActivity(intent);
                }
            } catch (Exception e) {
                Log.e(TAG, "Error opening order details: " + e.getMessage());
                Toast.makeText(this, "Lỗi khi mở chi tiết đơn hàng: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addViews() {
        try {
            lvOrdersViewer = findViewById(R.id.lvOrdersViewer);
            adapter = new OrdersViewerAdapter(this, R.layout.item_ordersviewer);
            lvOrdersViewer.setAdapter(adapter);

            SQLiteConnector connector = new SQLiteConnector(this);
            SQLiteDatabase database = connector.openDatabase();
            
            if (database == null) {
                Toast.makeText(this, "Không thể kết nối đến database", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Database connection failed");
                return;
            }

            OrdersViewerConnector ovc = new OrdersViewerConnector();
            ArrayList<OrdersViewer> dataset = ovc.getAllOrdersViewer(database);
            
            if (dataset == null || dataset.isEmpty()) {
                Toast.makeText(this, "Không có dữ liệu đơn hàng", Toast.LENGTH_SHORT).show();
                Log.w(TAG, "No orders data found");
                return;
            }

            adapter.addAll(dataset);
            adapter.notifyDataSetChanged();
            
            Log.d(TAG, "Loaded " + dataset.size() + " orders successfully");
            
        } catch (Exception e) {
            Log.e(TAG, "Error loading orders: " + e.getMessage());
            Toast.makeText(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}