package hu.minelush.backend.auth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

// Ideiglenes fix admin belépés
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    public static final String SESSION_USER = "username";

    private final String adminUsername;
    private final String adminPassword;

    public AuthController(
        @Value("${minelush.admin.username}") String adminUsername,
        @Value("${minelush.admin.password}") String adminPassword) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    @PostMapping("/login")
    public UserResponse login(@Valid @RequestBody LoginRequest request, HttpServletRequest httpRequest) {
        boolean ok = matches(request.identifier().trim(), adminUsername)
            & matches(request.password(), adminPassword);
        if (!ok) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Hibás felhasználónév vagy jelszó.");
        }

        // régi session eldobása hogy belépéskor új session id legyen
        HttpSession old = httpRequest.getSession(false);
        if (old != null) {
            old.invalidate();
        }
        httpRequest.getSession(true).setAttribute(SESSION_USER, adminUsername);
        return new UserResponse(adminUsername, "ADMIN");
    }

    @GetMapping("/me")
    public UserResponse me(HttpSession session) {
        if (!(session.getAttribute(SESSION_USER) instanceof String username)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Nincs bejelentkezve.");
        }
        return new UserResponse(username, "ADMIN");
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpSession session) {
        session.invalidate();
    }

    // időben állandó összehasonlítás hogy a válaszidőből ne lehessen találgatni
    private static boolean matches(String given, String expected) {
        return MessageDigest.isEqual(
            given.getBytes(StandardCharsets.UTF_8),
            expected.getBytes(StandardCharsets.UTF_8));
    }
}