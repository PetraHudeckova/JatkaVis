package managedBean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.Book;
import sessionBeans.BookSBRemote;

@FacesConverter(forClass=Book.class)
public class BookConvertor implements Converter{
	
	@EJB
	private BookSBRemote bookSB;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		if("null".equals(value)){
			return null;
		}
		Integer id = 0;
		try{
			id = Integer.parseInt(value);
		} catch(NumberFormatException ex){
			context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nelze p�ev�st string na ��slo ID.", "Koncvertor nem��e p�ev�st string na ��slo. " + ex.getMessage()));
			throw new ConverterException("Nelze p�ev�st string na ��slo ID.", ex);
		}
		System.out.println("Vratila se kniha.");
		return bookSB.getBook(id);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		if(value == null){
			return "blbla";
		}
		if(value instanceof Book){
			return Integer.toString(((Book)value).getId());
		}
		context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nelze p�ev�st knihu.", "Tento konvertor je ur�en pouze ke konverzi objekt� typu Book."));
		throw new ConverterException("Nelze prov�st konverzi!");
	}

}
