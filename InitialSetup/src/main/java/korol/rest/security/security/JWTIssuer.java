package korol.rest.security.security;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;

/*
 * 	This class creates a JWT token for user
 */

@Component
@RequiredArgsConstructor
public class JWTIssuer {
	private final JWTProperties properties;
	
	public String issue(long userId, String username, List<String> authorities)
	{
		return JWT.create().
				withSubject(String.valueOf(userId)).
				withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS))).
				withClaim("un", username).
				withClaim("roles", authorities).
				sign(Algorithm.HMAC256(properties.getSecretKey()));
	}
}
