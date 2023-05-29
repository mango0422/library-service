package lib.backend.libraryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lib.backend.libraryservice.service.CommunityService;
import lib.backend.libraryservice.repository.CommunityRepository;
import lib.backend.libraryservice.Entity.Community;

@Controller
public class CommunityController {
    private CommunityService communityService;
    private CommunityRepository communityRepository;

    @Autowired
    public CommunityController(CommunityService communityService, CommunityRepository communityRepository) {
        this.communityService = communityService;
        this.communityRepository = communityRepository;
    }

    @GetMapping("/community")
    public String community(Model model) {
        List<Community> communities = communityService.getCommunities();
        model.addAttribute("communities", communities);
        return "community";
    }

    @GetMapping("/Community/details/{board-id}")
    public String showCommunityDetails(@PathVariable Integer boardId, Model model) {
        Community community = new Community();
        community = communityRepository.getCommunityById(boardId);
        
    }
}