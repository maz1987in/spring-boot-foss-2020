package om.foss.demo.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import om.foss.demo.persistence.model.Book;
import om.foss.demo.persistence.repo.BookRepository;

@Controller
public class BookController {

	@Value("${spring.application.name}")
    String appName;
	
	 @Autowired
	 private BookRepository bookRepository;
	 
	@GetMapping("/book")
    public String showBookForm(Book book) {
		
        return "add-book";
    }
     
    @PostMapping("/addbook")
    public String addBook(@Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-book";
        }
         
        bookRepository.save(book);
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("book", book);
        return "update-book";
    }
    
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	book.setId(id);
            return "update-book";
        }
        
        bookRepository.save(book);
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        bookRepository.delete(book);
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }
}
