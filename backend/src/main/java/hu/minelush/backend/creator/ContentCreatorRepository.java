package hu.minelush.backend.creator;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface ContentCreatorRepository extends Repository<ContentCreator, Long> {
    List<ContentCreator> findAllByOrderBySubscriberCountDescIdAsc();
    
}
