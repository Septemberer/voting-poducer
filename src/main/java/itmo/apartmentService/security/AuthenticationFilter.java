package itmo.apartmentService.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {


	public static final String BASIC = "Basic";
	public static final String REPLACEMENT = "";

	AuthenticationFilter(final RequestMatcher requiresAuth) {
		super(requiresAuth);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
												HttpServletResponse httpServletResponse)
			throws AuthenticationException {

		String token = !Objects.equals(httpServletRequest.getHeader(AUTHORIZATION), REPLACEMENT) ?
				httpServletRequest.getHeader(AUTHORIZATION) : REPLACEMENT;
		token = token.replaceAll(BASIC, REPLACEMENT).trim();
		Authentication requestAuthentication = new UsernamePasswordAuthenticationToken(token, token);
		return getAuthenticationManager().authenticate(requestAuthentication);

	}

	@Override
	protected void successfulAuthentication(final HttpServletRequest request,
											final HttpServletResponse response,
											final FilterChain chain,
											final Authentication authResult)
			throws IOException, ServletException {
		SecurityContextHolder.getContext().setAuthentication(authResult);
		chain.doFilter(request, response);
	}
}
