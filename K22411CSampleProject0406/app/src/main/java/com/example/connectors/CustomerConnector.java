package com.example.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.models.Customer;
import com.example.models.ListCustomer;

import java.util.ArrayList;

public class CustomerConnector {
    private static final String TAG = "CustomerConnector";
    ListCustomer listCustomer;

    public CustomerConnector() {
        listCustomer = new ListCustomer();
    }

    public ArrayList<Customer> get_all_customers() {
        return listCustomer.getCustomers();
    }

    public ArrayList<Customer> get_customers_by_provider(String provider) {
        ArrayList<Customer> results = new ArrayList<>();
        for(Customer c : listCustomer.getCustomers()) {
            if(c.getPhone().startsWith(provider)) {
                results.add(c);
            }
        }
        return results;
    }

    public boolean isExist(Customer c) {
        return listCustomer.isExist(c);
    }

    public void addCustomer(Customer c) {
        listCustomer.addCustomer(c);
    }

    /**
     * Đây là hàm truy vấn toàn bộ dữ liệu Khách hàng
     * sau đó mô hình hóa hướng đối tượng
     * @param database
     * @return trả về ListCustomer
     */
    public ListCustomer getAllCustomers(SQLiteDatabase database) {
        listCustomer = new ListCustomer();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM Customer", null);
            while(cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String phone = cursor.getString(3);
                String username = cursor.getString(4);
                String password = cursor.getString(5);
                Customer c = new Customer();
                c.setId(id);
                c.setName(name);
                c.setEmail(email);
                c.setPhone(phone);
                c.setUsername(username);
                c.setPassword(password);
                listCustomer.addCustomer(c);
            }
            cursor.close();
        } catch (Exception e) {
            Log.e(TAG, "Error getting customers: " + e.getMessage());
        }
        return listCustomer;
    }
}
