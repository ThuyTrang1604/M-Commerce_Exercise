package com.example.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ListPaymentMethod implements Serializable {
    private ArrayList<PaymentMethod> methods;

    public ListPaymentMethod() {
        methods = new ArrayList<>();
    }

    public ArrayList<PaymentMethod> getMethods() {
        return methods;
    }

    public void setMethods(ArrayList<PaymentMethod> methods) {
        this.methods = methods;
    }

    public void add(PaymentMethod method) {
        methods.add(method);
    }

    public boolean isExist(PaymentMethod method) {
        for (PaymentMethod m : methods) {
            if (m.getId() == method.getId() || 
                m.getMethodName().equalsIgnoreCase(method.getMethodName())) {
                return true;
            }
        }
        return false;
    }
}
