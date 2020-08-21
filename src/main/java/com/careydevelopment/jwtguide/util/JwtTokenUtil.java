package com.careydevelopment.jwtguide.util;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import com.careydevelopment.jwtguide.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -8274549945478145377L;

	private static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60;
	public static final String SECRET = "mysecret";
	
	private String token = null;
	
	public JwtTokenUtil(String jwtToken) {
		this.token = jwtToken;
	}
		
	
	public String getUsernameFromToken() {
		return getClaimFromToken(Claims::getSubject);
	}

	
	public Date getExpirationDateFromToken() {
		return getClaimFromToken(Claims::getExpiration);
	}

	
	public <T> T getClaimFromToken(Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken();
		return claimsResolver.apply(claims);
	}
	
	
	public String getClaimFromTokenByName(String name) {
		final Claims claims = getAllClaimsFromToken();
		return (String)claims.get(name);
	}
	
	
	private Claims getAllClaimsFromToken() {
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
	}

	
	private Boolean isTokenExpired() {
		final Date expiration = getExpirationDateFromToken();
		return expiration.before(new Date());
	}

	
	public static String generateToken(User user) {
		Map<String, Object> claims = addClaims(user); 
		return doGenerateToken(claims, user.getUsername());
	}

	
	private static Map<String, Object> addClaims(User user) {
		Map<String, Object> claims = MapUtils.toKeyValuePairs(user);		
		return claims;
	}	

	
	private static String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}

	
	public Boolean validateToken(UserDetails userDetails) {
		final String username = getUsernameFromToken();
		return (username.equals(userDetails.getUsername()) && !isTokenExpired());
	}
	
	public Boolean validateToken() {
		return (!isTokenExpired());
	}
}
