package sessionBeans;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import model.Reader;

@Local
public interface ReaderSBRemote {

	Reader saveReader(Reader r);

	List<Reader> getAllReaders();

	Reader getReader(int readerId);

	void deleteReader(Reader r);

	void deleteReader(int readerId);

}
