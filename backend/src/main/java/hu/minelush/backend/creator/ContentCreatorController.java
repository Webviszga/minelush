package hu.minelush.backend.creator;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/api/creators")
public class ContentCreatorController {
    private final ContentCreatorRepository repository;

    public ContentCreatorController(ContentCreatorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ContentCreatorResponse> listCreators() {
        return repository.findAllByOrderBySubscriberCountDescIdAsc().stream()
            .map(ContentCreatorResponse::from)
            .toList();
    }
}
