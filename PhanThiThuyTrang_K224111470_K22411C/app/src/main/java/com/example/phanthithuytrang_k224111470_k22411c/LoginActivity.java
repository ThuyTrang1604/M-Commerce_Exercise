package com.example.phanthithuytrang_k224111470_k22411c;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUserName;
    private EditText edtPassword;
    private CheckBox chkSaveLoginInfor;

    private static final String PREFS_NAME = "login_prefs";
    private static final String PREF_USERNAME = "username";
    private static final String PREF_PASSWORD = "password";
    private static final String PREF_REMEMBER = "remember";
    private SharedPreferences preferences;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        chkSaveLoginInfor = findViewById(R.id.chkSaveLoginInfor);

        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        dbHelper = new DatabaseHelper(this);

        // Restore saved login info if available
        boolean remember = preferences.getBoolean(PREF_REMEMBER, false);
        if (remember) {
            edtUserName.setText(preferences.getString(PREF_USERNAME, ""));
            edtPassword.setText(preferences.getString(PREF_PASSWORD, ""));
            chkSaveLoginInfor.setChecked(true);
        }

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
    }

    /**
     * Called from the XML layout using android:onClick="do_login".
     */
    public void do_login(View view) {
        String username = edtUserName.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, R.string.err_empty_credentials, Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT ID, TypeOfAccount FROM Account WHERE Username=? AND Password=? LIMIT 1";
        int userId=-1;
        int type=-1;
        try (Cursor cursor = db.rawQuery(sql, new String[]{username, password})) {
            if (cursor.moveToFirst()) {
                userId = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
                type = cursor.getInt(cursor.getColumnIndexOrThrow("TypeOfAccount"));
            }
        }
        if (type != -1) {
            handleRememberMe(username, password);

            Intent intent;
            if (type == 1) {
                intent = new Intent(this, AdminActivity.class);
            } else {
                intent = new Intent(this, EmployeeActivity.class);
                intent.putExtra("USER_ID", userId);
            }
            startActivity(intent);
            finish();
            return;
        }
        Toast.makeText(this, R.string.err_invalid_credentials, Toast.LENGTH_SHORT).show();
    }

    /**
     * Called from the XML layout using android:onClick="do_exit".
     */
    public void do_exit(View view) {
        finishAffinity();
    }

    /**
     * Stores or clears login information based on the "remember me" checkbox.
     */
    private void handleRememberMe(String username, String password) {
        SharedPreferences.Editor editor = preferences.edit();
        if (chkSaveLoginInfor.isChecked()) {
            editor.putBoolean(PREF_REMEMBER, true);
            editor.putString(PREF_USERNAME, username);
            editor.putString(PREF_PASSWORD, password);
        } else {
            editor.clear();
        }
        editor.apply();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option_menu_about,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menu_about)
        {
            Intent intent=new Intent(LoginActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}