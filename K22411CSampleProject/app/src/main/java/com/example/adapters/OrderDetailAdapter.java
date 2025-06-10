package com.example.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.k22411csampleproject.R;
import com.example.models.OrderDetails;

import java.text.NumberFormat;
import java.util.Locale;

public class OrderDetailAdapter extends ArrayAdapter<OrderDetails> {
    Activity context;
    int resource;
    NumberFormat currencyFormat;

    public OrderDetailAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);

        TextView txtProductName = item.findViewById(R.id.txtProductName);
        TextView txtQuantity = item.findViewById(R.id.txtQuantity);
        TextView txtPrice = item.findViewById(R.id.txtPrice);
        TextView txtDiscount = item.findViewById(R.id.txtDiscount);
        TextView txtVAT = item.findViewById(R.id.txtVAT);
        TextView txtTotalValue = item.findViewById(R.id.txtTotalValue);

        OrderDetails detail = getItem(position);
        if (detail != null) {
            txtProductName.setText(detail.getProductName());
            txtQuantity.setText(String.valueOf(detail.getQuantity()));
            txtPrice.setText(currencyFormat.format(detail.getPrice()));
            txtDiscount.setText(detail.getDiscount() + "%");
            txtVAT.setText(detail.getVAT() + "%");
            
            // Tính tổng tiền cho từng sản phẩm
            double subtotal = detail.getQuantity() * detail.getPrice();
            double discountAmount = subtotal * (detail.getDiscount() / 100.0);
            double afterDiscount = subtotal - discountAmount;
            double vatAmount = afterDiscount * (detail.getVAT() / 100.0);
            double total = afterDiscount + vatAmount;
            
            txtTotalValue.setText(currencyFormat.format(total));
        }

        return item;
    }
} 