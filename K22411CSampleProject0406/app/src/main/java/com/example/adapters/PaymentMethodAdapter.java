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
import com.example.models.PaymentMethod;

import java.util.List;

public class PaymentMethodAdapter extends ArrayAdapter<PaymentMethod> {
    Activity context;
    int resource;

    public PaymentMethodAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);
        
        TextView txtPaymentMethodName = item.findViewById(R.id.txtPaymentMethodName);
        TextView txtPaymentMethodDescription = item.findViewById(R.id.txtPaymentMethodDescription);

        PaymentMethod pm = getItem(position);
        if (pm != null) {
            txtPaymentMethodName.setText(pm.getMethodName());
            txtPaymentMethodDescription.setText(pm.getDescription());
        }
        
        return item;
    }
}