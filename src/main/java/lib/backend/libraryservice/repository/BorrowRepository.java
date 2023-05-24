package lib.backend.libraryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lib.backend.libraryservice.Entity.Borrow;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

}
