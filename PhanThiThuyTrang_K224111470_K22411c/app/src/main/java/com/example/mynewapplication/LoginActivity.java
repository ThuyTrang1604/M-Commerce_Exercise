package com.example.mynewapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUserName, edtPassword;
    private Button btnLogin;
    private CheckBox chkSaveLogin;
    private List<Account> accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUserName = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        chkSaveLogin = findViewById(R.id.chkSaveInfo);
        accounts = SimulationData.getAccounts();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUserName.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                boolean found = false;
                for (Account acc : accounts) {
                    if (acc.getUsername().equals(username) && acc.getPassword().equals(password)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void saveLoginInformation() {
        SharedPreferences preferences = getSharedPreferences("LOGIN_INFORMATION", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String usr = edtUserName.getText().toString();
        String pwd = edtPassword.getText().toString();
        boolean isSave = chkSaveLogin.isChecked();
        editor.putString("USERNAME", usr);
        editor.putString("PASSWORD", pwd);
        editor.putBoolean("SAVED", isSave);
        editor.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveLoginInformation();
    }

    public void restoreLoginInformation() {
        SharedPreferences preferences = getSharedPreferences("LOGIN_INFORMATION", MODE_PRIVATE);
        String usr = preferences.getString("USERNAME", "");
        String pwd = preferences.getString("PASSWORD", "");
        Boolean isSave = preferences.getBoolean("SAVED", true);
        if (isSave) {
            edtUserName.setText(usr);
            edtPassword.setText(pwd);
            chkSaveLogin.setChecked(isSave);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreLoginInformation();
    }
}

