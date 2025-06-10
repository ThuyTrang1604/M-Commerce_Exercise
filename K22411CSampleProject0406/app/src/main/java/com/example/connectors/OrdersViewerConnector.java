package com.example.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.models.Customer;
import com.example.models.OrdersViewer;

import java.util.ArrayList;

public class OrdersViewerConnector {
    private static final String TAG = "OrdersViewerConnector";

    public ArrayList<OrdersViewer> getAllOrdersViewer(SQLiteDatabase database) {
        ArrayList<OrdersViewer> datasets = new ArrayList<>();
        
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(" SELECT ");
            builder.append(" o.Id AS OrderId, ");
            builder.append(" o.Code AS OrderCode, ");
            builder.append(" o.OrderDate, ");
            builder.append(" e.Name AS EmployeeName, ");
            builder.append(" c.Name AS CustomerName, ");
            builder.append(" SUM((od.Quantity * od.Price - od.Discount / 100.0 * od.Quantity * od.Price) * (1 + od.VAT / 100.0)) AS TotalOrderValue ");
            builder.append(" FROM Orders o ");
            builder.append(" JOIN Employee e ON o.EmployeeId = e.Id ");
            builder.append(" JOIN Customer c ON o.CustomerId = c.Id ");
            builder.append(" JOIN OrderDetails od ON o.Id = od.OrderId ");
            builder.append(" GROUP BY o.Id, o.Code, o.OrderDate, e.Name, c.Name ");

            String sql = builder.toString();
            Log.d(TAG, "Executing query: " + sql);
            
            Cursor cursor = database.rawQuery(sql, null);
            
            if (cursor == null) {
                Log.e(TAG, "Query returned null cursor");
                return datasets;
            }

            while(cursor.moveToNext()) {
                try {
                    int id = cursor.getInt(0);
                    String code = cursor.getString(1);
                    String orderdate = cursor.getString(2);
                    String employeeName = cursor.getString(3);
                    String customerName = cursor.getString(4);
                    double totalvalue = cursor.getDouble(5);

                    OrdersViewer ov = new OrdersViewer();
                    ov.setId(id);
                    ov.setCode(code);
                    ov.setOrderDate(orderdate);
                    ov.setEmployeeName(employeeName);
                    ov.setCustomerName(customerName);
                    ov.setTotalOrderValue(totalvalue);
                    datasets.add(ov);
                    
                    Log.d(TAG, "Added order: " + code);
                } catch (Exception e) {
                    Log.e(TAG, "Error processing row: " + e.getMessage());
                }
            }
            cursor.close();
            
            Log.d(TAG, "Total orders loaded: " + datasets.size());
            
        } catch (Exception e) {
            Log.e(TAG, "Error in getAllOrdersViewer: " + e.getMessage());
        }
        
        return datasets;
    }
}
