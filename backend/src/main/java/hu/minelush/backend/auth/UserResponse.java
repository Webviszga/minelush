package hu.minelush.backend.auth;

public record UserResponse(
    String username,
    String role
    ) {
}