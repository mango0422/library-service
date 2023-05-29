package lib.backend.libraryservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lib.backend.libraryservice.repository.*;
import lib.backend.libraryservice.Entity.*;

@Service
public class CommunityService {
    private JdbcTemplate jdbcTemplate;
    private CommunityRepository communityRepository;

    @Autowired
    public CommunityService(CommunityRepository communityRepository, JdbcTemplate jdbcTemplate) {
        this.communityRepository = communityRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Community> getCommunities() {
        return communityRepository.getCommunnites();
    }

    public Community getCommunityById(Integer Id) {
        return communityRepository.getCommunityById(Id);
    }
}