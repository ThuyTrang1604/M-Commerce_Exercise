package com.example.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Khi có tin nhắn SMS tới sẽ tự động nhảy vào đây
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] arrMessages = (Object[]) bundle.get("pdus");
            if (arrMessages != null) {
                String phone, time, content;
                Date date;
                byte[] bytes;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                
                for (int i = 0; i < arrMessages.length; i++) {
                    bytes = (byte[]) arrMessages[i];
                    SmsMessage message;
                    
                    // Xử lý cho các phiên bản Android khác nhau
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String format = bundle.getString("format");
                        message = SmsMessage.createFromPdu(bytes, format);
                    } else {
                        message = SmsMessage.createFromPdu(bytes);
                    }
                    
                    phone = message.getDisplayOriginatingAddress();
                    date = new Date(message.getTimestampMillis());
                    content = message.getMessageBody();
                    time = sdf.format(date);
                    
                    String info = "SMS từ: " + phone + "\nThời gian: " + time + "\nNội dung: " + content;
                    // Ta sẽ gửi lên Server của ta sau
                    Toast.makeText(context, info, Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}

