package com.example.phanthithuytrang_k224111470_k22411c;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import Adapter.CallAdapter;
import Models.CallDetail;

public class EmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listViewTasks);
        dbHelper = new DatabaseHelper(this);

        int userId = getIntent().getIntExtra("USER_ID", -1);
        this.userId = userId;

        todayTaskId = dbHelper.getTodayTaskId(userId);
        if (todayTaskId == -1) {
            Toast.makeText(this, "No task for today", Toast.LENGTH_LONG).show();
            return;
        }

        listView.setOnItemClickListener((parent, view, position, id) -> {
            CallDetail detail = adapter.getItem(position);
            if (detail != null) {
                boolean newState = !detail.isCalled();
                dbHelper.setDetailCalled(todayTaskId, detail.getCustomerId(), newState);

                // Update task completion state
                boolean allCalled = dbHelper.areAllDetailsCalled(todayTaskId);
                dbHelper.updateTaskCompleted(todayTaskId, allCalled);

                Toast.makeText(this,
                        newState ? "Marked called" : "Marked not called",
                        Toast.LENGTH_SHORT).show();

                loadCallDetails();
            }
        });
    }

    private DatabaseHelper dbHelper;
    private ListView listView;
    private CallAdapter adapter;
    private int userId;
    private int todayTaskId = -1;

    private void loadCallDetails() {
        if (todayTaskId == -1) return;
        List<CallDetail> details = dbHelper.getCallDetails(todayTaskId);
        adapter = new CallAdapter(this, details);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCallDetails();
    }
}