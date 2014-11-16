package sessionBeans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Book;

/**
 * Session Bean implementation class BookSB
 */
@Stateless
@LocalBean
public class BookSB implements BookSBRemote {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public BookSB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Book saveBook(Book b) {
		if(b.getId() != null){
			em.merge(b);
		} else {
			em.persist(b);
		}
		em.flush();
		return b;
	}

	@Override
	public List<Book> getAllBooks() {
		return em.createNamedQuery("Book.all").getResultList();
	}

	@Override
	public Book getBook(int bookId) {
		return em.find(Book.class, bookId);
	}

	@Override
	public void deleteBook(Book b) {
		em.remove(em.find(Book.class, b.getId()));
		
	}

	@Override
	public void deleteBook(int bookId) {
		em.remove(em.find(Book.class, bookId));	
		
	}
    
    

}
