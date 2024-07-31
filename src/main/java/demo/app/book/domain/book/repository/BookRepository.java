package demo.app.book.domain.book.repository;

import demo.app.book.domain.book.entry.Book;
import demo.app.book.domain.book.exception.DuplicateBookEntryException;
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
  public Book newEntry(Book book) throws DuplicateBookEntryException {
    if(anyRegisteredBooksForThisReference(book))
      throw new DuplicateBookEntryException(book.getIsbn());
    else {
      entityManager.persist(book);
      return book;
    }
  }

  private boolean anyRegisteredBooksForThisReference(Book book) {
    return ((Long)entityManager.createNativeQuery("SELECT count(1) FROM book WHERE book.isbn=:isbn")
            .setParameter("isbn", book.getIsbn())
            .getSingleResult()).longValue()>0;
  }
}
