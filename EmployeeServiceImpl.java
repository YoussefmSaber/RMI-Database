import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl extends UnicastRemoteObject implements EmployeeService {

    private List<Employee> employees;

    public EmployeeServiceImpl() throws RemoteException {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) throws RemoteException {
        employees.add(employee);
    }

    public void updateEmployee(Employee employee) throws RemoteException {
        for (Employee e : employees) {
            if (e.getId() == employee.getId()) {
                e.setName(employee.getName());
                e.setEmail(employee.getEmail());
            }
        }
    }

    public void deleteEmployee(Employee employee) throws RemoteException {
        employees.remove(employee);
    }

    public Employee getEmployee(int id) throws RemoteException {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public void deleteAllEmployees() throws RemoteException {
        employees.clear();
    }

    public List<Employee> getAllEmployees() throws RemoteException {
        return employees;
    }
}
