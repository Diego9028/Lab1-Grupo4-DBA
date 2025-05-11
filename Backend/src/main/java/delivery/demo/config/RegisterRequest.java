package delivery.demo.config;

public record RegisterRequest(
        String name,
        String direccion,
        String email,
        String password
) { }