package lib.backend.libraryservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.Query;
import jakarta.persistence.EntityManager;
import lib.backend.libraryservice.Entity.Borrow;
import lib.backend.libraryservice.repository.BorrowRepository;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;

    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    public List<Borrow> findByID(Integer user_num) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM borrow WHERE user_num=");
        queryBuilder.append(user_num);
        String query = queryBuilder.toString();

        List<Borrow> borrowLists = executeQuery(query);

        return borrowLists;
    }

    @Autowired
    private EntityManager entityManager;

    public List<Borrow> executeQuery(String query) {
        Query nativeQuery = entityManager.createNativeQuery(query, Borrow.class);
        List<Borrow> borrows = nativeQuery.getResultList();
        return borrows;
    }
}