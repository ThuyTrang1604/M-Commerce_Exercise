package com.example.k22411csampleproject;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.k22411csampleproject.R;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.adapters.TelephonyInforAdapter;
import com.example.models.TelephonyInfor;

import android.Manifest;
import android.content.pm.PackageManager;

import java.util.ArrayList;


public class TelephonyActivity extends AppCompatActivity {
    ArrayList<TelephonyInfor> originalList = new ArrayList<>();

    ListView lvTelephony;
    //ArrayAdapter<TelephonyInfor> adapter;
    TelephonyInforAdapter adapter;
    private static final int REQUEST_READ_CONTACTS = 100;
    private static final int REQUEST_CALL_PHONE = 101;
    private static final int REQUEST_SMS_PERMISSIONS = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telephony);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        // Kiểm tra và xin quyền
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS);
        } else {
            getAllContacts();
        }
        
        // Kiểm tra quyền SMS
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, REQUEST_SMS_PERMISSIONS);
        }
        
        addEvents();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getAllContacts();
            }
        } else if (requestCode == REQUEST_CALL_PHONE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền đã được cấp, thực hiện cuộc gọi
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission denied to make phone calls", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getAllContacts() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri,null, null,null, null);

        adapter.clear();
        originalList.clear(); // 💡 Ghi nhớ danh sách gốc

        while (cursor.moveToNext()){
            int nameCol = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int phoneCol = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String name = cursor.getString(nameCol);
            String phone = cursor.getString(phoneCol);
            TelephonyInfor infor = new TelephonyInfor();
            infor.setName(name);
            infor.setPhone(phone);
            adapter.add(infor);
            originalList.add(infor); // ✅ Lưu vào danh sách gốc
        }
        cursor.close();
        
        // Hiển thị thống kê nhà mạng
        showCarrierStatistics();
    }

    private void showCarrierStatistics() {
        int viettelCount = 0, mobifoneCount = 0, vinaphoneCount = 0;
        int vietnamobileCount = 0, gmobileCount = 0, itelecomCount = 0, otherCount = 0;

        for (TelephonyInfor contact : originalList) {
            String phone = contact.getPhone().replaceAll("\\s+", "");
            if (phone.length() >= 3) {
                String prefix = phone.substring(0, 3);
                
                if (isViettel(prefix)) viettelCount++;
                else if (isMobifone(prefix)) mobifoneCount++;
                else if (isVinaphone(prefix)) vinaphoneCount++;
                else if (isVietnamobile(prefix)) vietnamobileCount++;
                else if (isGmobile(prefix)) gmobileCount++;
                else if (isItelecom(prefix)) itelecomCount++;
                else otherCount++;
            }
        }

        String stats = String.format("Thống kê: Viettel(%d) | MobiFone(%d) | Vinaphone(%d) | Vietnamobile(%d) | Gmobile(%d) | Itelecom(%d) | Khác(%d)",
                viettelCount, mobifoneCount, vinaphoneCount, vietnamobileCount, gmobileCount, itelecomCount, otherCount);
        
        Toast.makeText(this, stats, Toast.LENGTH_LONG).show();
    }

    private void addViews() {
        lvTelephony=findViewById(R.id.lvTelephonyInfor);
        //adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        adapter=new TelephonyInforAdapter(this,R.layout.item_telephony_infor);
        lvTelephony.setAdapter(adapter);

    }

    private void addEvents() {
        lvTelephony.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TelephonyInfor infor = adapter.getItem(position);
                assert infor != null;
                makeAPhoneCall(infor);
            }
        });
    }

    private TelephonyInfor currentCallInfo;

    private void makeAPhoneCall(TelephonyInfor infor) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
            // Lưu thông tin cuộc gọi để thực hiện sau khi được cấp quyền
            currentCallInfo = infor;
        } else {
            currentCallInfo = infor; // Lưu thông tin cuộc gọi
            makePhoneCall();
        }
    }
    private void makePhoneCall() {
        if (currentCallInfo != null) {
            Uri uri = Uri.parse("tel:" + currentCallInfo.getPhone());
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(uri);
            try {
                startActivity(intent);
            } catch (SecurityException e) {
                Toast.makeText(this, "Error making phone call: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void directCall(TelephonyInfor ti)
    {
        Uri uri = Uri.parse("tel:" + ti.getPhone());
        Intent intent=new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
    }
    public void dialupCall(TelephonyInfor ti)
    {
        Uri uri = Uri.parse("tel:" + ti.getPhone());
        Intent intent=new Intent(Intent.ACTION_DIAL);
        intent.setData(uri);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_telephony_filter, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_show_all:
                showAllContacts();
                return true;
            case R.id.menu_show_statistics:
                showCarrierStatistics();
                return true;
            case R.id.menu_filter_viettel:
                filterByCarrier("viettel");
                return true;
            case R.id.menu_filter_mobifone:
                filterByCarrier("mobifone");
                return true;
            case R.id.menu_filter_vinaphone:
                filterByCarrier("vinaphone");
                return true;
            case R.id.menu_filter_vietnamobile:
                filterByCarrier("vietnamobile");
                return true;
            case R.id.menu_filter_gmobile:
                filterByCarrier("gmobile");
                return true;
            case R.id.menu_filter_other:
                filterByCarrier("other");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAllContacts() {
        adapter.clear();
        adapter.addAll(originalList);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Hiển thị tất cả danh bạ", Toast.LENGTH_SHORT).show();
    }

    private void filterByCarrier(String type) {
        ArrayList<TelephonyInfor> filteredList = new ArrayList<>();
        String carrierName = "";

        for (TelephonyInfor contact : originalList) {
            String phone = contact.getPhone().replaceAll("\\s+", "");
            if (phone.length() >= 3) {
                String prefix = phone.substring(0, 3);
                boolean shouldAdd = false;
                
                switch (type) {
                    case "viettel":
                        shouldAdd = isViettel(prefix);
                        carrierName = "Viettel";
                        break;
                    case "mobifone":
                        shouldAdd = isMobifone(prefix);
                        carrierName = "MobiFone";
                        break;
                    case "vinaphone":
                        shouldAdd = isVinaphone(prefix);
                        carrierName = "Vinaphone";
                        break;
                    case "vietnamobile":
                        shouldAdd = isVietnamobile(prefix);
                        carrierName = "Vietnamobile";
                        break;
                    case "gmobile":
                        shouldAdd = isGmobile(prefix);
                        carrierName = "Gmobile";
                        break;
                    case "other":
                        shouldAdd = !isViettel(prefix) && !isMobifone(prefix) && 
                                   !isVinaphone(prefix) && !isVietnamobile(prefix) && 
                                   !isGmobile(prefix) && !isItelecom(prefix);
                        carrierName = "Nhà mạng khác";
                        break;
                }
                
                if (shouldAdd) {
                    filteredList.add(contact);
                }
            }
        }

        adapter.clear();
        adapter.addAll(filteredList);
        adapter.notifyDataSetChanged();
        
        String message = "Đã lọc " + filteredList.size() + " liên hệ thuộc " + carrierName;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private boolean isViettel(String prefix) {
        // Viettel: 086, 096, 097, 098, 032, 033, 034, 035, 036, 037, 038, 039
        return prefix.matches("086|096|097|098|032|033|034|035|036|037|038|039");
    }

    private boolean isMobifone(String prefix) {
        // Mobifone: 089, 090, 093, 070, 071, 072, 073, 074, 075, 076, 077, 078, 079
        return prefix.matches("089|090|093|070|071|072|073|074|075|076|077|078|079");
    }

    private boolean isVinaphone(String prefix) {
        // Vinaphone: 088, 091, 094, 081, 082, 083, 084, 085, 087
        return prefix.matches("088|091|094|081|082|083|084|085|087");
    }

    private boolean isVietnamobile(String prefix) {
        // Vietnamobile: 092, 056, 058
        return prefix.matches("092|056|058");
    }

    private boolean isGmobile(String prefix) {
        // Gmobile: 099, 059
        return prefix.matches("099|059");
    }

    private boolean isItelecom(String prefix) {
        // Itelecom: 087
        return prefix.matches("087");
    }
}