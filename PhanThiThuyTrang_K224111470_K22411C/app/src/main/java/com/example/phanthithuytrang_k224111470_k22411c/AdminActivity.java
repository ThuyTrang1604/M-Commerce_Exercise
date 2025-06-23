package com.example.phanthithuytrang_k224111470_k22411c;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import Adapter.TaskAdapter;
import Models.Task;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listViewTasks);
        Button btnCreate = findViewById(R.id.btnCreateTask);

        dbHelper = new DatabaseHelper(this);

        btnCreate.setOnClickListener(v -> {
            startActivity(new Intent(AdminActivity.this, CreateTaskActivity.class));
        });
    }

    private DatabaseHelper dbHelper;
    private ListView listView;

    private void loadTasks() {
        List<Task> tasks = dbHelper.getAllTasks();
        TaskAdapter adapter = new TaskAdapter(this, tasks);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dbHelper != null) {
            loadTasks();
        }
    }
}