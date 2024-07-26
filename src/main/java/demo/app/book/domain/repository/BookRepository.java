package demo.app.book.domain.repository;

import demo.app.book.domain.entity.Book;
import demo.app.book.exception.BookDuplicateEntryException;
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
  public Book newEntry(Book book) throws BookDuplicateEntryException {
    if(anyRegisteredBooksForThisReference(book))
      throw new BookDuplicateEntryException(book.getIsbn());
    else {
      entityManager.persist(book);
      return book;
    }
  }

  private boolean anyRegisteredBooksForThisReference(Book book) {
    return entityManager.createQuery("SELECT count(book) FROM Book book WHERE book.isbn=:isbn AND book.title=:title AND book.author=:author",Long.class)
            .setParameter("isbn", book.getIsbn())
            .setParameter("title", book.getTitle())
            .setParameter("author", book.getAuthor())
            .getSingleResult()>1;
  }
}
