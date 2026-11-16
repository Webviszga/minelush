package hu.minelush.backend.gameserver;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/api/servers")
public class GameServerController {
    private final GameServerRepository repository;

    public GameServerController(GameServerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<GameServerResponse> listServers() {
        return repository.findAllByOrderByIdAsc().stream()
            .map(GameServerResponse::from)
            .toList();
    }
}
