package hu.minelush.backend.gameserver;

public record GameServerResponse(
    Long id,
    String name,
    String description,
    String versionLabel,
    String imageUrl
    ) {
    static GameServerResponse from(GameServer server) {
        return new GameServerResponse(
            server.getId(),
            server.getName(),
            server.getDescription(),
            server.getVersionLabel(),
            server.getImageUrl()
        );
    }
}
