package lib.backend.libraryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lib.backend.libraryservice.Entity.Borrow;
import lib.backend.libraryservice.repository.BorrowRepository;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;

    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }
}