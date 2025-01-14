package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(
        AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        
        Publisher awPress = new Publisher("Addison & Wesley", "Long Road 496, 10383 LA");
        publisherRepository.save(awPress);

        // Sample 1
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(awPress);
        awPress.getBooks().add(ddd);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        // Sample 2
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        authorRepository.save(rod);
        bookRepository.save(noEJB);

        publisherRepository.save(awPress);

        System.out.println("Started in Bootstrap");
        
        System.out.println("Number of Books: " + bookRepository.count());
        bookRepository.findAll().forEach(System.out::println);

        System.out.println("Number of Authors: " + authorRepository.count());
        authorRepository.findAll().forEach(System.out::println);

        publisherRepository.findAll().forEach(System.out::println);
    }
    
}
