package com.example.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.k22411csampleproject.R;
import com.example.k22411csampleproject.SendSMSActivity;
import com.example.k22411csampleproject.TelephonyActivity;
import com.example.models.TelephonyInfor;

public class TelephonyInforAdapter extends ArrayAdapter<TelephonyInfor> {
    Activity context;
    int resource;

    public TelephonyInforAdapter(Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);
        TextView txtTelephonyName = item.findViewById(R.id.txtTelephonyName);
        TextView txtTelephonyNumber = item.findViewById(R.id.txtTelephonyNumber);
        ImageView imgDirectCall = item.findViewById(R.id.imgDirectCall);
        ImageView imgDialup = item.findViewById(R.id.imgDialup);
        ImageView imgSms = item.findViewById(R.id.imgSendSms);

        TelephonyInfor ti = this.getItem(position);
        txtTelephonyName.setText(ti.getName());
        
        // Hiển thị số điện thoại và nhà mạng
        String phoneWithCarrier = ti.getPhone() + " (" + getCarrierName(ti.getPhone()) + ")";
        txtTelephonyNumber.setText(phoneWithCarrier);

        // Các sự kiện making telephony làm sau
        imgDirectCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TelephonyActivity) context).directCall(ti);
            }
        });
        imgDialup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TelephonyActivity) context).dialupCall(ti);
            }
        });
        imgSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SendSMSActivity.class);
                intent.putExtra("TI", ti);
                context.startActivity(intent);
            }
        });
        return item;
    }

    private String getCarrierName(String phone) {
        String cleanPhone = phone.replaceAll("\\s+", "");
        if (cleanPhone.length() >= 3) {
            String prefix = cleanPhone.substring(0, 3);
            
            if (isViettel(prefix)) return "Viettel";
            if (isMobifone(prefix)) return "MobiFone";
            if (isVinaphone(prefix)) return "Vinaphone";
            if (isVietnamobile(prefix)) return "Vietnamobile";
            if (isGmobile(prefix)) return "Gmobile";
            if (isItelecom(prefix)) return "Itelecom";
        }
        return "Không xác định";
    }

    private boolean isViettel(String prefix) {
        return prefix.matches("086|096|097|098|032|033|034|035|036|037|038|039");
    }

    private boolean isMobifone(String prefix) {
        return prefix.matches("089|090|093|070|071|072|073|074|075|076|077|078|079");
    }

    private boolean isVinaphone(String prefix) {
        return prefix.matches("088|091|094|081|082|083|084|085|087");
    }

    private boolean isVietnamobile(String prefix) {
        return prefix.matches("092|056|058");
    }

    private boolean isGmobile(String prefix) {
        return prefix.matches("099|059");
    }

    private boolean isItelecom(String prefix) {
        return prefix.matches("087");
    }
}

