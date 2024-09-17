package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookCollectionService {

    @Autowired
    private BookCollectionRepository bookRepository;

    public List<BookCollection> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<BookCollection> getBookByIsbn(String isbn) {
        return bookRepository.findById(isbn);
    }

    public String addBook(BookCollection book) {
        bookRepository.save(book);
        return "Book is added successfully!";
    }

    public String updateBook(String isbn, BookCollection updatedBook) {
        return bookRepository.findById(isbn).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPublicationYear(updatedBook.getPublicationYear());
            bookRepository.save(book);
            return "Book with ISBN " + isbn + " is updated successfully!";
        }).orElseGet(() -> {
            updatedBook.setIsbn(isbn);
            bookRepository.save(updatedBook);
            return "Book with ISBN " + isbn + " is added successfully since it was not found!";
        });
    }

    public String deleteBook(String isbn) {
        if (bookRepository.existsById(isbn)) {
            bookRepository.deleteById(isbn);
            return "Book with ISBN " + isbn + " is deleted successfully!";
        } else {
            return "Book with ISBN " + isbn + " was not found!";
        }
    }
}
