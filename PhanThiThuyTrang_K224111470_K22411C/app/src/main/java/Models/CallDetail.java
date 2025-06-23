package Models;

public class CallDetail {
    private final int customerId;
    private final String name;
    private final String phone;
    private final boolean isCalled;

    public CallDetail(int customerId, String name, String phone, boolean isCalled) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.isCalled = isCalled;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isCalled() {
        return isCalled;
    }

    @Override
    public String toString() {
        return phone + " - " + name;
    }
} 