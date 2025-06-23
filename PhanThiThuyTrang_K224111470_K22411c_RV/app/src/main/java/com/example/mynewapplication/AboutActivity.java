package com.example.mynewapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView txtAbout = findViewById(R.id.txtAbout);
        ImageView imgAvatar = findViewById(R.id.imgAvatar);
        txtAbout.setText("ID: K224111470\nName: Phan Thi Thuy Trang\nEmail: trangptt22411c@uel.edu.vn\nClass: K22411c");
        imgAvatar.setImageResource(R.drawable.avatar);
    }
}
