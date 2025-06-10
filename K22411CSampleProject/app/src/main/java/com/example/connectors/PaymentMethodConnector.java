package com.example.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.models.ListPaymentMethod;
import com.example.models.PaymentMethod;

public class PaymentMethodConnector {
    private static final String TAG = "PaymentMethodConnector";
    ListPaymentMethod list;

    public PaymentMethodConnector() {
        list = new ListPaymentMethod();
    }

    public ListPaymentMethod getAll(SQLiteDatabase database) {
        list = new ListPaymentMethod();
        Cursor cursor = database.rawQuery("SELECT * FROM PaymentMethod", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String methodName = cursor.getString(1);
            String description = cursor.getString(2);
            PaymentMethod method = new PaymentMethod(id, methodName, description);
            list.add(method);
        }
        cursor.close();
        return list;
    }

    public boolean isExist(PaymentMethod method) {
        return list.isExist(method);
    }

    public void addPaymentMethod(PaymentMethod method) {
        list.add(method);
    }
}
