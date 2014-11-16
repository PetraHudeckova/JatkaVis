package sessionBeans;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import model.Book;

@Local
public interface BookSBRemote {

	public Book saveBook(Book b);
	public List<Book> getAllBooks();
	public Book getBook(int bookId);
	public void deleteBook(Book b);
	public void deleteBook(int bookId);
	
}
