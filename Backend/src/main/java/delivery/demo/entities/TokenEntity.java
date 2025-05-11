package delivery.demo.entities;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity {

    public enum TokenType {
        BEARER
    }

    private Long id;

    private String token;

    private TokenType token_type = TokenType.BEARER;

    private boolean revoked;

    private boolean expired;

    private Long cliente_id;
}
