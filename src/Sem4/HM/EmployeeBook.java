package Sem4.HM;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeBook {
    private static final List<Employee> employees = new ArrayList<>();

    public void add(Employee e) {
        employees.add(e);
    }

    public List<Employee> getEmployeeByExperience(int experience) {
        return employees.stream().filter(e -> e.getExperience() == experience).collect(Collectors.toList());
    }

    public List<String> getEmployeeByName(String name) {
        return employees.stream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .map(e -> String.format("name: %s, phone: %s", e.getName(), e.getPhoneNumber()))
                .collect(Collectors.toList());
    }

    public Employee getEmployeeByID(int id) {
        return employees.stream().filter(e -> e.getEmployeeID() == id).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return employees.toString();
    }
}
