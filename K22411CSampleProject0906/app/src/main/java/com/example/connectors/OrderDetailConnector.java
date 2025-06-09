package com.example.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.models.OrderDetails;

import java.util.ArrayList;

public class OrderDetailConnector {
    private static final String TAG = "OrderDetailConnector";

    public ArrayList<OrderDetails> getOrderDetails(SQLiteDatabase database, int orderId) {
        ArrayList<OrderDetails> details = new ArrayList<>();
        
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT od.*, p.Name as ProductName ");
            builder.append("FROM OrderDetails od ");
            builder.append("JOIN Product p ON od.ProductId = p.Id ");
            builder.append("WHERE od.OrderId = ?");

            String sql = builder.toString();
            Log.d(TAG, "Executing query: " + sql + " with OrderId: " + orderId);
            
            Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(orderId)});
            
            if (cursor == null) {
                Log.e(TAG, "Query returned null cursor");
                return details;
            }

            while(cursor.moveToNext()) {
                try {
                    OrderDetails detail = new OrderDetails();
                    detail.setID(cursor.getInt(0));
                    detail.setOrderId(cursor.getInt(1));
                    detail.setProductId(cursor.getInt(2));
                    detail.setQuantity(cursor.getInt(3));
                    detail.setPrice(cursor.getDouble(4));
                    detail.setDiscount(cursor.getDouble(5));
                    detail.setVAT(cursor.getDouble(6));
                    detail.setTotalValue(cursor.getDouble(7));
                    detail.setProductName(cursor.getString(8));
                    
                    details.add(detail);
                    Log.d(TAG, "Added order detail for product: " + detail.getProductName());
                } catch (Exception e) {
                    Log.e(TAG, "Error processing row: " + e.getMessage());
                }
            }
            cursor.close();
            
            Log.d(TAG, "Total order details loaded: " + details.size());
            
        } catch (Exception e) {
            Log.e(TAG, "Error in getOrderDetails: " + e.getMessage());
        }
        
        return details;
    }
} 