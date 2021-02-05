package br.com.zup.mercadolivre.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private TokenManager tokenManager;
	private UsersService usersService;
	
	public JwtAuthenticationFilter(TokenManager tokenManager, UsersService usersService) {
		this.tokenManager = tokenManager;
		this.usersService = usersService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String possibleToken = getTokenFromRequest(request);
		
        if (possibleToken!= null && tokenManager.isValid(possibleToken)) {
            
        	String userName = tokenManager.getUserName(possibleToken);
            UserDetails userDetails = usersService.loadUserByUsername(userName);
            
            UsernamePasswordAuthenticationToken authentication = 
            			new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        chain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization");
		
		if (authToken==null || authToken.isEmpty() || !authToken.startsWith("Bearer ")) {
			return null;
		}
		return (authToken.substring(7));
	}

}
