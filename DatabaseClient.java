import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Random;

public class DatabaseClient {

    public static void main(String[] args) {
        try {

            // Clear screen
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Lookup for EmployeeService in the RMI Registry
            EmployeeService employeeService = (EmployeeService) Naming.lookup("rmi://localhost/EmployeeService");
            System.out.println("Client client is running...");

            Thread.sleep(1200);
            // Clear screen
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Create a BufferedReader for user input
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Random random = new Random();

            while (true) {

                // Display menu
                int choice = menu(reader);

                // Clear screen
                System.out.print("\033[H\033[2J");
                System.out.flush();

                if (choice == 1) {
                    // Call addEmployee()
                    createEntity(employeeService, random, reader);
                } else if (choice == 2) {
                    // Call updateEmployee()
                    updateEmployeeData(employeeService, reader);
                } else if (choice == 3) {
                    // Call getEmployee()
                    getEmployee(employeeService, reader);
                } else if (choice == 4) {
                    // Call getAllEmployees()
                    getAllEmployees(employeeService, reader);
                } else if (choice == 5) {
                    // Call deleteEmployee()
                    deleteEmployee(employeeService, reader);
                } else if (choice == 6) {
                    // Call deleteAllEmployees()
                    deleteAllEmployees(employeeService, reader);
                } else {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    System.out.print("Have a nice day!");
                    Thread.sleep(800);

                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int menu(BufferedReader reader) throws IOException, InterruptedException {
        // Display menu
        System.out.println("==== Welcome Employee Management System using RMI ====");
        Thread.sleep(1200);
        
        System.out.println("1. Add Employee");
        System.out.println("2. Update Employee");
        System.out.println("3. Get Employee");
        System.out.println("4. Get All Employees");
        System.out.println("5. Delete Employee");
        System.out.println("6. Delete All Employees");
        System.out.println("7. Exit");

        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(reader.readLine());
        return choice;
    }

    private static void createEntity(EmployeeService employeeService,
            Random random,
            BufferedReader reader)
            throws IOException, RemoteException, InterruptedException {
        System.out.println("==== Welcome Employee Management System using RMI ====");
        System.out.println("Add Employee Form");

        int id = random.nextInt(10000000) + 1;
        System.out.print("Enter employee name: ");
        String name = reader.readLine();
        System.out.print("Enter employee email: ");
        String email = reader.readLine();
        Employee employee = new Employee(id, name, email);
        employeeService.addEmployee(employee);

        System.out.println("Employee id\t\tEmployee name\t\tEmployee email");

        System.out.print(employee.getId() + "\t\t\t");
        System.out.print(employee.getName() + "\t\t\t");
        System.out.print(employee.getEmail() + "\t\t\t");
        exit(reader);
    }

    private static void updateEmployeeData(EmployeeService employeeService,
            BufferedReader reader)
            throws IOException, RemoteException, InterruptedException {
        System.out.println("==== Welcome Employee Management System using RMI ====");
        System.out.println("Update Employee Form");

        if (employeeService.getAllEmployees().isEmpty()) {
            System.out.println("No employees found!");
            exit(reader);
            return;
        }
        System.out.print("Enter employee id: ");
        int id = Integer.parseInt(reader.readLine());

        Employee employee = employeeService.getEmployee(id);
        while (employee == null) {
            System.out.println("Employee not found!");
            System.out.println("Enter employee id again: ");
            id = Integer.parseInt(reader.readLine());
        }

        System.out.print("Enter employee new name: ");
        String name = reader.readLine();
        System.out.print("Enter employee new email: ");
        String email = reader.readLine();
        employee.setName(name);
        employee.setEmail(email);

        employeeService.updateEmployee(employee);

        System.out.println("Employee updated successfully");
        System.out.println("Employee id\t\tEmployee name\t\tEmployee email");

        System.out.print(employee.getId() + "\t\t\t");
        System.out.print(employee.getName() + "\t\t\t");
        System.out.print(employee.getEmail() + "\t\t\t");
        exit(reader);
    }

    private static void deleteEmployee(EmployeeService employeeService, BufferedReader reader)
            throws IOException, RemoteException, InterruptedException {
        System.out.println("==== Welcome Employee Management System using RMI ====");
        System.out.println("Delete Employee Form");

        if (employeeService.getAllEmployees().isEmpty()) {
            System.out.println("No employees found!");
            exit(reader);
            return;
        }
        System.out.print("Enter employee id: ");
        int id = Integer.parseInt(reader.readLine());

        Employee employee = employeeService.getEmployee(id);
        while (employee == null) {
            System.out.println("Employee not found!");
            System.out.println("Enter employee id again: ");
            id = Integer.parseInt(reader.readLine());
        }

        System.out.println("Employee with id " + employee.getId() + "deleted successfully");
        employeeService.deleteEmployee(employee);
        exit(reader);
    }

    private static void getEmployee(EmployeeService employeeService, BufferedReader reader)
            throws IOException, RemoteException, InterruptedException {

        System.out.println("==== Welcome Employee Management System using RMI ====");
        System.out.println("Get Employee Form");

        if (employeeService.getAllEmployees().isEmpty()) {
            System.out.println("No employees found!");
            exit(reader);
            return;
        }
        System.out.print("Enter employee id: ");
        int id = Integer.parseInt(reader.readLine());

        Employee employee = employeeService.getEmployee(id);
        while (employee == null) {
            System.out.println("Employee not found!");
            System.out.println("Enter employee id again: ");
            id = Integer.parseInt(reader.readLine());
        }

        System.out.println("Employee id\t\tEmployee name\t\tEmployee email");

        System.out.print(employee.getId() + "\t\t\t");
        System.out.print(employee.getName() + "\t\t\t");
        System.out.print(employee.getEmail() + "\t\t\t");
        exit(reader);
    }

    private static void getAllEmployees(EmployeeService employeeService, BufferedReader reader)
            throws RemoteException, IOException, InterruptedException {

        System.out.println("==== Welcome Employee Management System using RMI ====");
        System.out.println("Get All Employees Form");

        List<Employee> allEmployees = employeeService.getAllEmployees();
        if (allEmployees.isEmpty()) {
            System.out.println("No employees found!");
            exit(reader);
            return;
        }
        System.out.println("Employee id\t\tEmployee name\t\tEmployee email");
        for (Employee employee : allEmployees) {
            System.out.print(employee.getId() + "\t\t\t");
            System.out.print(employee.getName() + "\t\t\t");
            System.out.print(employee.getEmail() + "\t\t\t");
            System.out.println();
        }
        System.out.println();

        System.out.println("Total employees: " + allEmployees.size());

        exit(reader);
    }

    private static void deleteAllEmployees(EmployeeService employeeService, BufferedReader reader)
            throws RemoteException, IOException, InterruptedException {

        System.out.println("==== Welcome Employee Management System using RMI ====");
        System.out.println("Delete All Employees Form");

        if (employeeService.getAllEmployees().isEmpty()) {
            System.out.println("No employees found!");
            exit(reader);
            return;
        }
        employeeService.deleteAllEmployees();
        System.out.println("All employees deleted successfully!");
        exit(reader);
    }

    private static void exit(BufferedReader reader) throws IOException, InterruptedException {
        System.out.println();
        System.out.println();
        System.out.print("Do you want to return to the main menu(y/n):");
        String choice = reader.readLine();
        if (choice.equalsIgnoreCase("n")) {
            // Clear screen
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.print("Have a nice day!");
            Thread.sleep(800);

            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.exit(0);
        } else {
            // Clear screen
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }
}