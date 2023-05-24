package lib.backend.libraryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lib.backend.libraryservice.Entity.Reservation;;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
