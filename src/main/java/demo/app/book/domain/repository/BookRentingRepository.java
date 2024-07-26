package demo.app.book.domain.repository;

import demo.app.book.domain.entity.BookRenting;
import demo.app.book.exception.BookIsRentedException;
import demo.app.book.exception.InvalidBookRequestException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.time.LocalDate;

@ApplicationScoped
public class BookRentingRepository {
  @Inject
  private EntityManager entityManager;


  @Transactional
  public void saveForNewRenting(BookRenting bookRenting) throws BookIsRentedException {
    if(anyBookIsRentedBySameBorrowerAtTheMoment(bookRenting.getBookId(), bookRenting.getBorrowerId()))
      throw new BookIsRentedException(true);
    else if(anyBookIsRentedByOtherBorrowerAtTheMoment(bookRenting.getBookId(), bookRenting.getBorrowerId()))
      throw new BookIsRentedException(false);
    else
      entityManager.persist(bookRenting);
  }

  @Transactional
  public void saveForReturningBook(Long bookId, Long borrowerId) throws InvalidBookRequestException {
    var bookRenting = findRentingFor(bookId, borrowerId);
    bookRenting.setStatus("RETURN");
    entityManager.persist(bookRenting);
  }

  private BookRenting findRentingFor(Long bookId, Long borrowerId) throws InvalidBookRequestException {
    return entityManager.createQuery("SELECT bookRenting FROM BookRenting bookRenting WHERE bookRenting.bookId=:bookId AND bookRenting.borrowerId=:borrowerId AND (bookRenting.fromDate<=:currentDate AND bookRenting.toDate>=:currentDate) AND bookRenting.status='RENT'", BookRenting.class)
            .setParameter("bookId",bookId)
            .setParameter("borrowerId",borrowerId)
            .setParameter("currentDate", LocalDate.now()).getResultList().stream().findFirst().orElseThrow(InvalidBookRequestException::new);
  }

  private boolean anyBookIsRentedBySameBorrowerAtTheMoment(Long bookId, Long borrowerId) {
    return entityManager.createQuery("SELECT COUNT(bookRenting) FROM BookRenting bookRenting WHERE bookRenting.bookId=:bookId AND bookRenting.borrowerId=:borrowerId AND (bookRenting.fromDate<=:currentDate AND bookRenting.toDate>=:currentDate) AND bookRenting.status='RENT'", Long.class)
            .setParameter("bookId",bookId)
            .setParameter("borrowerId",borrowerId)
            .setParameter("currentDate", LocalDate.now()).getSingleResult()>0;
  }

  private boolean anyBookIsRentedByOtherBorrowerAtTheMoment(Long bookId, Long borrowerId) {
    return entityManager.createQuery("SELECT COUNT(bookRenting) FROM BookRenting bookRenting WHERE bookRenting.bookId=:bookId AND bookRenting.borrowerId<>:borrowerId AND (bookRenting.fromDate<=:currentDate AND bookRenting.toDate>=:currentDate) AND bookRenting.status='RENT'", Long.class)
            .setParameter("bookId",bookId)
            .setParameter("borrowerId",borrowerId)
            .setParameter("currentDate", LocalDate.now()).getSingleResult()>0;
  }
}
