import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface EmployeeService extends Remote {

    void addEmployee(Employee employee) throws RemoteException;

    void updateEmployee(Employee employee) throws RemoteException;

    void deleteEmployee(Employee employee) throws RemoteException;

    Employee getEmployee(int id) throws RemoteException;

    void deleteAllEmployees() throws RemoteException;

    List<Employee> getAllEmployees() throws RemoteException;
}
