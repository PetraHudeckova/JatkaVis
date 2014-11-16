package sessionBeans;

import java.util.Arrays;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Employee;
import model.Reader;

/**
 * Session Bean implementation class ReaderSB
 */
@Stateless
@LocalBean
public class ReaderSB implements ReaderSBRemote {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ReaderSB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Reader saveReader(Reader r) {
		if(r.getId() != null){
			em.merge(r);
		} else {
			em.persist(r);
		}
		em.flush();
		return r;
	}

	@Override
	public List<Reader> getAllReaders() {
		return em.createNamedQuery("User.all").setParameter("classes", 
                Arrays.asList(Reader.class)).getResultList();
	}

	@Override
	public Reader getReader(int readerId) {
		return em.find(Reader.class, readerId);
	}

	@Override
	public void deleteReader(Reader r) {
		em.remove(em.find(Reader.class, r.getId()));
	}

	@Override
	public void deleteReader(int readerId) {
		// TODO Auto-generated method stub
		
	}

}
