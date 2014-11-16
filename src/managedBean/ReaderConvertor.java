package managedBean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.Book;
import model.Reader;
import sessionBeans.ReaderSBRemote;

@FacesConverter(forClass=Reader.class)
public class ReaderConvertor implements Converter {

	@EJB
	private ReaderSBRemote readerSB;
	
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
		return readerSB.getReader(id);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		if(value == null){
			return "null";
		}
		if(value instanceof Reader){
			return Integer.toString(((Reader)value).getId());
		}
		context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nelze pøevést readera.", "Tento konvertor je urèen pouze ke konverzi objektù typu Reader."));
		throw new ConverterException("Nelze provést konverzi!");
	}

}
