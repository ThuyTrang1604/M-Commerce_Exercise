package Models;

public class Task {
    private final int id;
    private final String title;
    private final String dateAssigned;
    private final int employeeId;
    private final boolean isCompleted;

    public Task(int id, String title, String dateAssigned, int employeeId, boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.dateAssigned = dateAssigned;
        this.employeeId = employeeId;
        this.isCompleted = isCompleted;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDateAssigned() {
        return dateAssigned;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
} 