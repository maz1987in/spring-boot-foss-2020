package om.foss.demo.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import om.foss.demo.persistence.model.Book;
import om.foss.demo.persistence.repo.BookRepository;

@RestController
@RequestMapping("api")
public class BookRESTController {

	 @Autowired
	    private BookRepository repository;

	    // Find
	    @GetMapping("/books")
	    List<Book> findAll() {
	        return repository.findAll();
	    }

	    // Save
	    //return 201 instead of 200
	    @ResponseStatus(HttpStatus.CREATED)
	    @PostMapping("/books")
	    Book newBook(@RequestBody Book newBook) {
	        return repository.save(newBook);
	    }

	    // Find
	    @GetMapping("/books/{id}")
	    Book findOne(@PathVariable Long id) {
	        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    }

	    // Save or update
	    @PutMapping("/books/{id}")
	    Book saveOrUpdate(@RequestBody Book newBook, @PathVariable Long id) {

	        return repository.findById(id)
	                .map(x -> {
	                    x.setName(newBook.getName());
	                    x.setAuthor(newBook.getAuthor());
	                    return repository.save(x);
	                })
	                .orElseGet(() -> {
	                    newBook.setId(id);
	                    return repository.save(newBook);
	                });
	    }

	    @DeleteMapping("/books/{id}")
	    void deleteBook(@PathVariable Long id) {
	        repository.deleteById(id);
	    }

}
