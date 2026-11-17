package hu.minelush.backend.team;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/api/team")
public class TeamMemberController {
    private final TeamMemberRepository repository;

    public TeamMemberController(TeamMemberRepository repository) {
        this.repository = repository;
    }    

    @GetMapping
    public List<TeamMemberResponse> listTeamMembers() {
        return repository.findAllByOrderByIdAsc().stream()
            .map(TeamMemberResponse::from)
            .toList();
    }
}
