package om.foss.demo.persistence.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import om.foss.demo.persistence.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByName(String name);

}
