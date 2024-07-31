package demo.app.book.domain.renting.repository;

import demo.app.book.domain.renting.entity.Renting;
import demo.app.book.domain.renting.exception.BookIsRentedException;
import demo.app.book.domain.renting.exception.InvalidBookRentingRequestException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@ApplicationScoped
public class RentingRepository {
  @Inject
  private EntityManager entityManager;


  @Transactional
  public void saveForNewRenting(Renting bookRenting) throws BookIsRentedException {
    if(anyBookIsRentedBySameBorrowerAtTheMoment(bookRenting.getBookId(), bookRenting.getBorrowerId()))
      throw new BookIsRentedException(true);
    else if(anyBookIsRentedByOtherBorrowerAtTheMoment(bookRenting.getBookId(), bookRenting.getBorrowerId()))
      throw new BookIsRentedException(false);
    else
      entityManager.persist(bookRenting);
  }

  @Transactional
  public void saveForReturningBook(Long bookId, Long borrowerId) throws InvalidBookRentingRequestException {
    var bookRenting = findRentingFor(bookId, borrowerId);
    bookRenting.setStatus("RETURN");
    entityManager.persist(bookRenting);
  }

  private Renting findRentingFor(Long bookId, Long borrowerId) throws InvalidBookRentingRequestException {
    return entityManager.createQuery("SELECT renting FROM Renting renting WHERE renting.bookId=:bookId AND renting.borrowerId=:borrowerId AND (renting.fromDate<=:currentDate AND renting.toDate>=:currentDate) AND renting.status='RENT'", Renting.class)
            .setParameter("bookId",bookId)
            .setParameter("borrowerId",borrowerId)
            .setParameter("currentDate", OffsetDateTime.now(ZoneId.systemDefault())).getResultList().stream().findFirst().orElseThrow(InvalidBookRentingRequestException::new);
  }

  private boolean anyBookIsRentedBySameBorrowerAtTheMoment(Long bookId, Long borrowerId) {
    return entityManager.createQuery("SELECT COUNT(renting) FROM Renting renting WHERE renting.bookId=:bookId AND renting.borrowerId=:borrowerId AND (renting.fromDate<=:currentDate AND renting.toDate>=:currentDate) AND renting.status='RENT'", Long.class)
            .setParameter("bookId",bookId)
            .setParameter("borrowerId",borrowerId)
            .setParameter("currentDate", OffsetDateTime.now(ZoneId.systemDefault())).getSingleResult()>0;
  }

  private boolean anyBookIsRentedByOtherBorrowerAtTheMoment(Long bookId, Long borrowerId) {
    return entityManager.createQuery("SELECT COUNT(renting) FROM Renting renting WHERE renting.bookId=:bookId AND renting.borrowerId<>:borrowerId AND (renting.fromDate<=:currentDate AND bookRenting.toDate>=:currentDate) AND bookRenting.status='RENT'", Long.class)
            .setParameter("bookId",bookId)
            .setParameter("borrowerId",borrowerId)
            .setParameter("currentDate", OffsetDateTime.now(ZoneId.systemDefault())).getSingleResult()>0;
  }
}
