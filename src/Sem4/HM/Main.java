package Sem4.HM;

public class Main {
    public static void main(String[] args) {

        EmployeeBook employeeBook = new EmployeeBook();
        employeeBook.add(new Employee(1, "Vitea", "111111", 1));
        employeeBook.add(new Employee(2, "Katea", "222222", 2));
        employeeBook.add(new Employee(3, "Sasha", "333333", 6));
        employeeBook.add(new Employee(4, "Oleg", "333333", 1));

        System.out.println(employeeBook);
        System.out.println(employeeBook.getEmployeeByID(4));
        System.out.println(employeeBook.getEmployeeByExperience(1));
        System.out.println(employeeBook.getEmployeeByName("Sasha"));

    }
}
