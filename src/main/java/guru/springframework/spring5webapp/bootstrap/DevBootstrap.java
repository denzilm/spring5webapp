package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {
        // Eric
        Author eric = new Author("Eric", "Evans");
        Publisher harper = new Publisher("Harper Collins", "somewhere");
        Book ddd = new Book("Domain Driven Design", "1234", harper);
        ddd.setPublisher(harper);
        eric.getBooks().add(ddd);
        publisherRepository.save(harper);
       bookRepository.save(ddd);
        authorRepository.save(eric);


        // Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher worx = new Publisher("Worx", "Somewhere");
        Book noEJB = new Book("J2EE Development without EJB", "1234", worx);
        noEJB.setPublisher(worx);
        rod.getBooks().add(noEJB);

        publisherRepository.save(worx);
        bookRepository.save(noEJB);
        authorRepository.save(rod);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }
}
