package com.example.phanthithuytrang_k224111470_k22411c;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Helper that ensures the pre-packaged SQLite database shipped inside the assets folder
 * is copied to the app's internal database directory if it does not already exist.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Database.sqlite"; // asset & destination name
    private static final int DATABASE_VERSION = 1; // increment when you need to handle upgrades

    private final Context context;
    private final String destinationPath;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context.getApplicationContext();
        this.destinationPath = this.context.getDatabasePath(DATABASE_NAME).getPath();
        ensureDatabaseCopied();
    }

    /**
     * Called when the database is created for the first time. Since we are using a pre-populated
     * database, no table-creation statements are required here.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // No-op – using pre-packaged DB
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement schema-migration logic if you change DATABASE_VERSION
    }

    /**
     * Ensures the database file exists in the internal storage directory by copying it from assets
     * when necessary.
     */
    private void ensureDatabaseCopied() {
        File dbFile = new File(destinationPath);
        if (dbFile.exists()) {
            // Already copied
            return;
        }

        // Make sure the databases directory exists.
        File parentDir = dbFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            parentDir.mkdirs();
        }

        try {
            copyDatabaseFromAssets(dbFile);
        } catch (IOException e) {
            throw new RuntimeException("Error copying pre-populated database", e);
        }
    }

    private void copyDatabaseFromAssets(File destination) throws IOException {
        try (InputStream is = context.getAssets().open(DATABASE_NAME);
             OutputStream os = new FileOutputStream(destination)) {

            byte[] buffer = new byte[8 * 1024]; // 8k buffer
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
        }
    }

    // --- Convenience methods for Task logic ---
    public java.util.List<Models.Task> getAllTasks() {
        java.util.List<Models.Task> list = new java.util.ArrayList<>();
        try (android.database.Cursor cursor = getReadableDatabase().rawQuery("SELECT ID, TaskTitle, DateAssigned, AccountID, IsCompleted FROM TaskForTeleSales", null)) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String date = cursor.getString(2);
                int empId = cursor.getInt(3);
                boolean completed = cursor.getInt(4) == 1;
                list.add(new Models.Task(id, title, date, empId, completed));
            }
        }
        return list;
    }

    public java.util.List<Models.Task> getTasksForEmployee(int employeeId) {
        java.util.List<Models.Task> list = new java.util.ArrayList<>();
        android.database.Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT ID, TaskTitle, DateAssigned, AccountID, IsCompleted FROM TaskForTeleSales WHERE AccountID=?",
                new String[]{String.valueOf(employeeId)});
        while (cursor.moveToNext()) {
            list.add(new Models.Task(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4) == 1));
        }
        cursor.close();
        return list;
    }

    public long insertTask(String title, int employeeId) {
        android.content.ContentValues cv = new android.content.ContentValues();
        cv.put("TaskTitle", title);
        cv.put("AccountID", employeeId);
        cv.put("DateAssigned", new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(new java.util.Date()));
        cv.put("IsCompleted", 0);
        return getWritableDatabase().insert("TaskForTeleSales", null, cv);
    }

    public void updateTaskCompleted(int taskId, boolean completed) {
        android.content.ContentValues cv = new android.content.ContentValues();
        cv.put("IsCompleted", completed ? 1 : 0);
        getWritableDatabase().update("TaskForTeleSales", cv, "ID=?", new String[]{String.valueOf(taskId)});
    }

    // Get list of employees (type 2)
    public java.util.Map<Integer, String> getEmployees() {
        java.util.Map<Integer, String> map = new java.util.LinkedHashMap<>();
        try (android.database.Cursor c = getReadableDatabase().rawQuery("SELECT ID, Username FROM Account WHERE TypeOfAccount=2", null)) {
            while (c.moveToNext()) {
                map.put(c.getInt(0), c.getString(1));
            }
        }
        return map;
    }

    // --- Customer helpers ---
    public java.util.List<Models.Customer> getRandomCustomers(int limit) {
        java.util.List<Models.Customer> list = new java.util.ArrayList<>();
        String sql = "SELECT ID, Name, Phone FROM Customer ORDER BY RANDOM() LIMIT " + limit;
        try (android.database.Cursor c = getReadableDatabase().rawQuery(sql, null)) {
            while (c.moveToNext()) {
                list.add(new Models.Customer(c.getInt(0), c.getString(1), c.getString(2)));
            }
        }
        return list;
    }

    public void insertTaskDetails(int taskId, java.util.List<Integer> customerIds) {
        android.database.sqlite.SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            for (int cid : customerIds) {
                android.content.ContentValues cv = new android.content.ContentValues();
                cv.put("TaskForTeleSalesID", taskId);
                cv.put("CustomerID", cid);
                cv.put("IsCalled", 0);
                db.insert("TaskForTeleSalesDetails", null, cv);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    // Get today task id for employee
    public int getTodayTaskId(int employeeId) {
        String today = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(new java.util.Date());
        try (android.database.Cursor c = getReadableDatabase().rawQuery(
                "SELECT ID FROM TaskForTeleSales WHERE AccountID=? AND DateAssigned=? LIMIT 1",
                new String[]{String.valueOf(employeeId), today})) {
            if (c.moveToFirst()) return c.getInt(0);
        }
        return -1;
    }

    // Fetch call details for task
    public java.util.List<Models.CallDetail> getCallDetails(int taskId) {
        java.util.List<Models.CallDetail> list = new java.util.ArrayList<>();
        String sql = "SELECT cu.ID, cu.Name, cu.Phone, d.IsCalled FROM TaskForTeleSalesDetails d JOIN Customer cu ON cu.ID = d.CustomerID WHERE d.TaskForTeleSalesID=?";
        try (android.database.Cursor c = getReadableDatabase().rawQuery(sql, new String[]{String.valueOf(taskId)})) {
            while (c.moveToNext()) {
                list.add(new Models.CallDetail(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3)==1));
            }
        }
        return list;
    }

    // Update detail called
    public void setDetailCalled(int taskId,int customerId, boolean called) {
        android.content.ContentValues cv = new android.content.ContentValues();
        cv.put("IsCalled", called?1:0);
        getWritableDatabase().update("TaskForTeleSalesDetails", cv, "TaskForTeleSalesID=? AND CustomerID=?", new String[]{String.valueOf(taskId), String.valueOf(customerId)});
    }

    public boolean areAllDetailsCalled(int taskId) {
        try (android.database.Cursor c = getReadableDatabase().rawQuery(
                "SELECT COUNT(*) FROM TaskForTeleSalesDetails WHERE TaskForTeleSalesID=? AND IsCalled=0",
                new String[]{String.valueOf(taskId)})) {
            if (c.moveToFirst()) {
                return c.getInt(0)==0;
            }
        }
        return false;
    }
} 