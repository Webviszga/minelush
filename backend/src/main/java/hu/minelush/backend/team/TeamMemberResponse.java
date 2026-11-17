package hu.minelush.backend.team;

public record TeamMemberResponse(
    Long id,
    String name,
    String role,
    String description,
    String headUrl,
    String renderUrl
    ) {
    static TeamMemberResponse from(TeamMember member) {
        return new TeamMemberResponse(
            member.getId(),
            member.getName(),
            member.getRole(),
            member.getDescription(),
            member.getHeadUrl(),
            member.getRenderUrl()
        );
    }
}