package productsecurity.model.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
