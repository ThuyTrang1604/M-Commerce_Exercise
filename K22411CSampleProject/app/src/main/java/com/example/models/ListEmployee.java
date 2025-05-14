package com.example.models;

import java.util.ArrayList;

public class ListEmployee {
    private ArrayList<Employee> employees;

    public ListEmployee() {
        employees=new ArrayList<>();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
    public void gen_dataset()
    {
        Employee e1=new Employee();
        e1.setId(123);
        e1.setName("John");
        e1.setEmail("John123");
        e1.setPhone("01234569");
        e1.setUsername("John123");
        e1.setPassword("John123");
        employees.add(e1);

        Employee e2=new Employee();
        e2.setId(124);
        e2.setName("Peter");
        e2.setEmail("Peter123");
        e2.setPhone("03259872");
        e2.setUsername("Peter789");
        e2.setPassword("789haha");
        employees.add(e2);

        Employee e3=new Employee();
        e3.setId(125);
        e3.setName("Jack");
        e3.setEmail("Jack5tr");
        e3.setPhone("088175236");
        e3.setUsername("Jackonly5tr");
        e3.setPassword("Jack5tr4evever");
        employees.add(e3);
    }
}
