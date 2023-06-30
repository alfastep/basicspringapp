package com.example.basicspringapp.controller;

import com.example.basicspringapp.model.Author;
import com.example.basicspringapp.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("")
    public String getAuthors(Model model) {
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping("/{id}")
    public String getAuthorById(@PathVariable("id") Long id, Model model) {
        Author author = authorRepository.findById(id).orElse(null);
        model.addAttribute("author", author);
        return "author";
    }

    @GetMapping("/new-author")
    public String showCreateForm(Model model) {
        model.addAttribute("author", new Author());
        return "create-author";
    }

    @PostMapping("")
    public String createAuthor(@ModelAttribute("author") Author author) {
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Author author = authorRepository.findById(id).orElse(null);
        model.addAttribute("author", author);
        return "update-author";
    }

    @PostMapping("/{id}")
    public String updateAuthor(@PathVariable("id") Long id, @ModelAttribute("author") Author updatedAuthor) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author != null) {
            author.setName(updatedAuthor.getName());
            authorRepository.save(author);
        }
        return "redirect:/authors";
    }

    @GetMapping("/{id}/delete")
    public String deleteAuthor(@PathVariable("id") Long id) {
        authorRepository.deleteById(id);
        return "redirect:/authors";
    }
}
