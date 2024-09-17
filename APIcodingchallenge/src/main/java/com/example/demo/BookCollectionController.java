package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/books")
public class BookCollectionController {

	    @Autowired
	    private BookCollectionService bookService;

	    @GetMapping("/showAll")
	    public List<BookCollection> getAllBooks() {
	        return bookService.getAllBooks();
	    }

	    @GetMapping("/getBook/{isbn}")
	    public BookCollection getBookByIsbn(@PathVariable String isbn) {
	        Optional<BookCollection> book = bookService.getBookByIsbn(isbn);
	        return book.orElse(null); 
	    }

	    @PostMapping("/addBook")
	    public String addBook(@RequestBody BookCollection book) {
	        return bookService.addBook(book);
	    }

	    @PutMapping("/updateBook/{isbn}")
	    public String updateBook(@PathVariable String isbn, @RequestBody BookCollection updatedBook) {
	        return bookService.updateBook(isbn, updatedBook);
	    }

	    @DeleteMapping("/deleteBook/{isbn}")
	    public String deleteBook(@PathVariable String isbn) {
	        return bookService.deleteBook(isbn);
	    }
	}

