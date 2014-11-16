package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
@NamedQuery(name="Book.all", query="select b from Book b")
})
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer numberOfAvailable;
	private String author;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumberOfAvailable() {
		return numberOfAvailable;
	}
	public void setNumberOfAvailable(Integer numberOfAvailable) {
		this.numberOfAvailable = numberOfAvailable;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime
				* result
				+ ((numberOfAvailable == null) ? 0 : numberOfAvailable
						.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numberOfAvailable == null) {
			if (other.numberOfAvailable != null)
				return false;
		} else if (!numberOfAvailable.equals(other.numberOfAvailable))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", numberOfAvailable="
				+ numberOfAvailable + ", author=" + author + "]";
	}
	
	
}
