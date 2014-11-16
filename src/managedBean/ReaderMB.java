package managedBean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Reader;
import sessionBeans.ReaderSBRemote;

@SessionScoped
public class ReaderMB {

	@EJB
	private ReaderSBRemote readerSB;
	
	
	private Reader edited;
	
//	public String createPlayer(){
//		playerSB.createPlayer("David");
//		return "";
//	}
	
	public List<Reader> getAllReaders(){
		return readerSB.getAllReaders();
	}

	public Reader getEdited() {
		return edited;
	}

	public void setEdited(Reader edited) {
		this.edited = edited;
	}
	
	public String createReader(){
		return editReader(new Reader());
	}
	
	public String editReader(Reader r){
		edited = r;
		return "editReader";
	}
	
	public String saveReader(){
		try {
			readerSB.saveReader(edited);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodaøilo se uložit data do databáze.", 
					"Nepodaøilo se uložit data do databáze. Pøíèina: " + e.getMessage()));
			e.printStackTrace();
			return "";
		}
		return "index";
	}

	public String cancelReaderEdit(){
		edited = null;
		return "index";
	}
	
	public String deleteReader(Reader p){
		readerSB.deleteReader(p);
		return "index";
	}	
}
