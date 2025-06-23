package com.example.phanthithuytrang_k224111470_k22411c;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateTaskActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText edtTitle;
    private Spinner spinnerEmployee;
    private List<Models.Customer> selectedCustomers = new ArrayList<>();
    private ArrayAdapter<Models.Customer> customerAdapter;
    private ListView listViewCustomers;
    private TextView txtCustomerPhone;

    private List<Integer> employeeIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_task);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DatabaseHelper(this);

        edtTitle = findViewById(R.id.edtTaskTitle);
        spinnerEmployee = findViewById(R.id.spinnerEmployee);
        Button btnSave = findViewById(R.id.btnSaveTask);
        listViewCustomers = findViewById(R.id.listViewCustomers);
        customerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedCustomers);
        listViewCustomers.setAdapter(customerAdapter);

        loadEmployees();

        Button btnSelectCustomer = findViewById(R.id.btnSelectCustomer);
        btnSelectCustomer.setOnClickListener(v -> {
            selectedCustomers.clear();
            selectedCustomers.addAll(dbHelper.getRandomCustomers(5));
            customerAdapter.notifyDataSetChanged();
        });

        txtCustomerPhone = findViewById(R.id.txtCustomerPhone);
        listViewCustomers.setOnItemClickListener((parent, view, position, id) -> {
            Models.Customer c = customerAdapter.getItem(position);
            if (c != null) {
                txtCustomerPhone.setText(c.getPhone());
            }
        });

        btnSave.setOnClickListener(v -> {
            String title = edtTitle.getText().toString().trim();
            int selectedPos = spinnerEmployee.getSelectedItemPosition();
            if (title.isEmpty() || selectedPos == Spinner.INVALID_POSITION) {
                Toast.makeText(this, "Please enter task title and select employee", Toast.LENGTH_SHORT).show();
                return;
            }
            int empId = employeeIds.get(selectedPos);
            long id = dbHelper.insertTask(title, empId);
            if (id != -1) {
                java.util.List<Integer> cids = new java.util.ArrayList<>();
                for (Models.Customer c : selectedCustomers) cids.add(c.getId());
                dbHelper.insertTaskDetails((int) id, cids);
                Toast.makeText(this, "Task created", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error creating task", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadEmployees() {
        Map<Integer, String> map = dbHelper.getEmployees();
        employeeIds.clear();
        List<String> names = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            employeeIds.add(entry.getKey());
            names.add(entry.getValue());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmployee.setAdapter(adapter);
    }
} 