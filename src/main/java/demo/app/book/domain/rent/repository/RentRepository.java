package demo.app.book.domain.rent.repository;

import demo.app.book.domain.rent.entity.Rent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;

@ApplicationScoped
public class RentRepository {
  @Inject
  private EntityManager entityManager;

  @Transactional
  public Rent findRentingId(Long rentingId) {
    return entityManager.find(Rent.class, rentingId);
  }

  @Transactional
  public void save(Rent rent) {
    entityManager.persist(rent);
  }

  public boolean anyBookIsOccupiedBySameBorrowerAtTheMoment(Long bookId, Long borrowerId) {
    return entityManager.createQuery("SELECT COUNT(rent) FROM Rent rent WHERE rent.bookId=:bookId AND rent.borrowerId=:borrowerId AND (rent.fromDate<=:currentDate AND rent.toDate>=:currentDate) AND rent.status='RENT'", Long.class)
            .setParameter("bookId",bookId)
            .setParameter("borrowerId",borrowerId)
            .setParameter("currentDate", LocalDate.now(ZoneId.systemDefault())).getSingleResult()>0;
  }

  public boolean anyBookIsOccupiedByOtherBorrowerAtTheMoment(Long bookId, Long borrowerId) {
    return entityManager.createQuery("SELECT COUNT(rent) FROM Rent rent WHERE rent.bookId=:bookId AND rent.borrowerId<>:borrowerId AND (rent.fromDate<=:currentDate AND rent.toDate>=:currentDate) AND rent.status='RENT'", Long.class)
            .setParameter("bookId",bookId)
            .setParameter("borrowerId",borrowerId)
            .setParameter("currentDate", LocalDate.now(ZoneId.systemDefault())).getSingleResult()>0;
  }
}
