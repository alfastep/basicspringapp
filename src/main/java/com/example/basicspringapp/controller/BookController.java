package com.example.basicspringapp.controller;

import com.example.basicspringapp.model.Author;
import com.example.basicspringapp.model.Book;
import com.example.basicspringapp.repository.AuthorRepository;
import com.example.basicspringapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @RequestMapping("")
    public String getBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id).orElse(null);
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/new-book")
    public String showCreateForm(Model model) {
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authors);
        return "create-book";
    }

    @PostMapping("")
    public String createBook(@ModelAttribute("book") Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id).orElse(null);
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        return "update-book";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable("id") Long id, @RequestBody Book updatedBook) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            Author author = authorRepository.findById(updatedBook.getAuthor().getId()).orElse(null);
            if (author != null) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(author);
                bookRepository.save(book);
            }
        }
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}
