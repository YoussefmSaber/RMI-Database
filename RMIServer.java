import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIServer {

    public static void main(String[] args) {
        try {
            EmployeeService employeeService = new EmployeeServiceImpl();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("EmployeeService", employeeService);
            // Clear screen
            System.out.print("\033[H\033[2J");
            System.out.flush();
            // Display Server is running
            System.out.println("RMI server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
