package delivery.demo.config;

public record RegisterRequest (
        String name,
        String email,
        String password
) { }