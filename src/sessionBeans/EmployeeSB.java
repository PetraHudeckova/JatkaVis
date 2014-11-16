package sessionBeans;

import java.util.Arrays;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Employee;

/**
 * Session Bean implementation class Employee
 */
@Stateless
@LocalBean
public class EmployeeSB implements EmployeeSBRemote {
	
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public EmployeeSB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Employee saveEmployee(Employee e) {
		if(e.getId() != null){
			em.merge(e);
		} else {
			em.persist(e);
		}
		em.flush();
		return e;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return em.createNamedQuery("User.all").setParameter("classes", 
                Arrays.asList(Employee.class)).getResultList();
	}

	@Override
	public Employee getEmployee(int employeeId) {
		return em.find(Employee.class, employeeId);
	}

	@Override
	public void deleteEmployee(Employee e) {
		em.remove(em.find(Employee.class, e.getId()));
		
	}

	@Override
	public void deleteEmployee(int employeeId) {
		em.remove(em.find(Employee.class, employeeId));	
		
	}



}
