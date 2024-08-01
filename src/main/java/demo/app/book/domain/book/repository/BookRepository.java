package demo.app.book.domain.book.repository;

import demo.app.book.domain.book.entity.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class BookRepository {
  @Inject
  private EntityManager entityManager;

  @Transactional
  public List<Book> retrieveAllBooks() {
    return entityManager.createQuery("select book FROM Book book", Book.class).getResultList();
  }

  @Transactional
  public Book findBookById(Long bookId) {
    return entityManager.find(Book.class, bookId);
  }

  @Transactional
  public Book save(Book book) {
    entityManager.persist(book);
    return book;
  }

  public Long countRegisteredBooksForThisReference(Book book) {
    return entityManager.createQuery("SELECT count(book) FROM Book book WHERE book.isbn=:isbn", Long.class)
            .setParameter("isbn", book.getIsbn())
            .getSingleResult();
  }
}
