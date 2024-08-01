package demo.app.book.domain.rent.repository;

import demo.app.book.domain.rent.entity.Rent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.time.OffsetDateTime;
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
  public void save(Rent renting) {
    entityManager.persist(renting);
  }

  public boolean anyBookIsOccupiedBySameBorrowerAtTheMoment(Long bookId, Long borrowerId) {
    return entityManager.createQuery("SELECT COUNT(renting) FROM Renting renting WHERE renting.bookId=:bookId AND renting.borrowerId=:borrowerId AND (renting.fromDate<=:currentDate AND renting.toDate>=:currentDate) AND renting.status='RENT'", Long.class)
            .setParameter("bookId",bookId)
            .setParameter("borrowerId",borrowerId)
            .setParameter("currentDate", OffsetDateTime.now(ZoneId.systemDefault())).getSingleResult()>0;
  }

  public boolean anyBookIsOccupiedByOtherBorrowerAtTheMoment(Long bookId, Long borrowerId) {
    return entityManager.createQuery("SELECT COUNT(renting) FROM Renting renting WHERE renting.bookId=:bookId AND renting.borrowerId<>:borrowerId AND (renting.fromDate<=:currentDate AND bookRenting.toDate>=:currentDate) AND bookRenting.status='RENT'", Long.class)
            .setParameter("bookId",bookId)
            .setParameter("borrowerId",borrowerId)
            .setParameter("currentDate", OffsetDateTime.now(ZoneId.systemDefault())).getSingleResult()>0;
  }
}
