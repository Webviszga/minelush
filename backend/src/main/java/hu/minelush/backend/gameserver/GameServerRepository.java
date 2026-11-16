package hu.minelush.backend.gameserver;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface GameServerRepository extends Repository<GameServer, Long> {
    List<GameServer> findAllByOrderByIdAsc();
}
