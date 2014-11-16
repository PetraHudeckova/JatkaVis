package managedBean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.Employee;
import sessionBeans.EmployeeSBRemote;

@FacesConverter(forClass=Employee.class)
public class EmployeeConvertor implements Converter {

	@EJB
	private EmployeeSBRemote employeeSB;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if("null".equals(value)){
			return null;
		}
		int id = 0;
		try{
			id = Integer.parseInt(value);
		} catch(NumberFormatException ex){
			context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nelze pøevést string na èíslo ID.", "Koncvertor nemùže pøevést string na èíslo. " + ex.getMessage()));
			throw new ConverterException("Nelze pøevést string na èíslo ID.", ex);
		}
		return employeeSB.getEmployee(id);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		if(value == null){
			return "null";
		}
		if(value instanceof Employee){
			return Integer.toString(((Employee)value).getId());
		}
		context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nelze pøevést employee.", "Tento konvertor je urèen pouze ke konverzi objektù typu Employee."));
		throw new ConverterException("Nelze provést konverzi!");
	}

}
