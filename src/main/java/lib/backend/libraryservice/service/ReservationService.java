package lib.backend.libraryservice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.Query;
import jakarta.persistence.EntityManager;
import lib.backend.libraryservice.Entity.Reservation;
import lib.backend.libraryservice.repository.ReservationRepository;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> findByID(Integer user_num) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM reservation WHERE user_num=");
        queryBuilder.append(user_num);
        String query = queryBuilder.toString();

        List<Reservation> reservationLists = executeQuery(query);

        return reservationLists;
    }

    @Autowired
    private EntityManager entityManager;

    public List<Reservation> executeQuery(String query) {
        Query nativeQuery = entityManager.createNativeQuery(query, Reservation.class);
        List<Reservation> borrows = nativeQuery.getResultList();
        return borrows;
    }
}
