package managedBean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Book;
import sessionBeans.BookSBRemote;

@ManagedBean(name="bookMB")
@SessionScoped
public class BookMB {

	@EJB
	private BookSBRemote bookSB;
	
	private Book editedBook;
	
//	public String createPlayer(){
//		playerSB.createPlayer("David");
//		return "";
//	}

	public List<Book> getAllBooks(){
		return bookSB.getAllBooks();
	}
	
	public void setBookSB(BookSBRemote bookSB) {
		this.bookSB = bookSB;
	}

	public Book getEditedBook() {
		return editedBook;
	}


	public void setEditedBoo(Book editedBook) {
		this.editedBook = editedBook;
	}

	public String createBook(){
		System.out.println("Vytvareni knihy.");
		return editBook(new Book());
	}
	
	public String editBook(Book b){
		editedBook = b;
		System.out.println("Editace knihy");
		return "editBook";
	}
	
	public String editBook() {
		return "editBook";
	}
	
	public String saveBook(){
		try {
			bookSB.saveBook(editedBook);
			System.out.println("Ulozeni knihy s id: " + editedBook.getId());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Nepodaøilo se uložit data do databáze.", 
					"Nepodaøilo se uložit data do databáze. Pøíèina: " + e.getMessage() + editedBook.toString()
					));
			e.printStackTrace();
			return "index";
		}
		return "index";
	}

	public String cancelBookEdit(){
		editedBook = null;
		return "index";
	}
	
	public String deleteBook(Book b){
		bookSB.deleteBook(b);
		return "index";
	}	
}
