package koroler.spring.project1.DAOs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.Nullable;
import koroler.spring.project1.models.Book;
import koroler.spring.project1.models.Person;

@Component
public class BooksDAO {
	private final JdbcTemplate temp;
	private String database_name = null;
	
	@Autowired //check if this one is even needed. If not, use NoArgs
	public BooksDAO(JdbcTemplate temp, @Value("${database.books}") String database_name) {
		this.temp = temp;
		this.database_name = database_name;
	}
	
	@SuppressWarnings("deprecation")
	public List <Book> getBooksByPerson(Integer person_id)
	{
		return temp.query("SELECT * FROM " + database_name + " WHERE book_owner = ?", new Object[]{person_id}, new BeanPropertyRowMapper<>(Book.class));
	}
	
	public List <Book> getBooks()
	{
		return temp.query("SELECT * FROM " + database_name, new BeanPropertyRowMapper<>(Book.class));
	}

	@SuppressWarnings("deprecation")
	public Book getBook(Integer id)
	{
		return temp.query("SELECT * FROM " + database_name + " WHERE book_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
	}
	
	public void addOwner(@Nullable Integer person_id, Integer book_id)
	{
		temp.update("UPDATE "+ database_name +" SET book_owner = ? WHERE book_id = ?", person_id, book_id);
	}
	
	public void addBook(Book book)
	{
		temp.update("INSERT INTO "+ database_name +" (book_name, book_author) VALUES (?, ?)", book.getBook_name(), book.getBook_author());
	}
	
	public void updateBook(Book book, Integer id)
	{
		temp.update("UPDATE "+ database_name +" SET book_name = ?, book_author = ? WHERE book_id = ?", book.getBook_name(), book.getBook_author(), id);
	}
	
}
