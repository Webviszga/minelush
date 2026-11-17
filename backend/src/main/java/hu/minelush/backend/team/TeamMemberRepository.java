package hu.minelush.backend.team;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface TeamMemberRepository extends Repository<TeamMember, Long> {
    List<TeamMember> findAllByOrderByIdAsc();
    
}
