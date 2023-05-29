package lib.backend.libraryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import lib.backend.libraryservice.Entity.Community;

public interface CommunityRepository extends JpaRepository<Community, String> {
    @Query(value = "SELECT * FROM community WHERE board_num = ?1", nativeQuery = true)
    Community getCommunityById(Integer Id);

    @Modifying
    @Query(value = "SELECT * FROM community", nativeQuery = true)
    List<Community> getCommunnites();
}   