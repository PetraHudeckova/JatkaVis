package sessionBeans;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import model.Employee;


@Local
public interface EmployeeSBRemote {
//	public Player createPlayer(String name);
	public Employee saveEmployee(Employee e);
	public List<Employee> getAllEmployees();
	public Employee getEmployee(int employeeId);
	public void deleteEmployee(Employee r);
	public void deleteEmployee(int employeeId);
}
