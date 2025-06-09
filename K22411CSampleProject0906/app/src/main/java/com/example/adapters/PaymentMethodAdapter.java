package com.example.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.k22411csampleproject.R;
import com.example.models.PaymentMethod;

public class PaymentMethodAdapter extends ArrayAdapter<PaymentMethod> {
    TextView txtPaymentMethodName;
    TextView txtPaymentMethodDescription;
    Activity context;
    int resource;
    Typeface typeface;
    public PaymentMethodAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
        typeface = Typeface.createFromAsset(this.context.getAssets(),"fonts/TMC-Ong Do.TTF");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View item=inflater.inflate(this.resource,null);
        TextView txtPaymentMethodName=item.findViewById(R.id.txtPaymentMethodName);
        TextView txtPaymentMethodDescription=item.findViewById(R.id.txtPaymentMethodDescription);

        PaymentMethod pm=getItem(position);
        txtPaymentMethodName.setText(pm.getName());
        txtPaymentMethodDescription.setText(pm.getDescription());

        txtPaymentMethodName.setTypeface(this.typeface);
        return item;
    }
}