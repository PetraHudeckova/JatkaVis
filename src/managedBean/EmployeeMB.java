package managedBean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Employee;
import sessionBeans.EmployeeSBRemote;

@SessionScoped
public class EmployeeMB {

	@EJB
	private EmployeeSBRemote employeeSB;
	
	
	private Employee edited;
	
//	public String createPlayer(){
//		playerSB.createPlayer("David");
//		return "";
//	}
	
	public List<Employee> getAllEmployees(){
		return employeeSB.getAllEmployees();
	}

	public Employee getEdited() {
		return edited;
	}

	public void setEdited(Employee edited) {
		this.edited = edited;
	}
	
	public String createEmployee(){
		return editEmployee(new Employee());
	}
	
	public String editEmployee(Employee e){
		edited = e;
		return "editEmployee";
	}
	
	public String saveEmployee(){
		try {
			employeeSB.saveEmployee(edited);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodaøilo se uložit data do databáze.", 
					"Nepodaøilo se uložit data do databáze. Pøíèina: " + e.getMessage()));
			e.printStackTrace();
			return "";
		}
		return "index";
	}

	public String cancelEmployeeEdit(){
		edited = null;
		return "index";
	}
	
	public String deleteEmployee(Employee e){
		employeeSB.deleteEmployee(e);
		return "index";
	}	
}
