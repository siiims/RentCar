package com.sims.rentcar.service;

import com.sims.rentcar.auth.AccountCredentials;
import com.sims.rentcar.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;


public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "Eq1mSDk0YnxERtPP4fvBGSEfhMOuimWcM55noo8jcmZLDAVLEnTWmDmIntnCTSIC";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";
    public static final String CONTACT_ID = "contactID";

    public TokenAuthenticationService() {
        throw new IllegalStateException("TokenAuthenticationService");
    }


    static void addAuthentication(HttpServletResponse res, User contact) {

        String jwt = getTokenString(contact);
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);
    }

    public static String getTokenString(User contact) {
        Map<String,Object> claims=new HashMap<>();
        List<String> auths=contact.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        claims.put(CONTACT_ID,contact.getId());
        if (auths.size() == 0) {
            auths = Arrays.asList("user", "user", "user");
        }
        claims.put("auth", auths);

        return Jwts.builder()
                .setSubject(contact.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .addClaims(claims)
                .compact();
    }


    /**
     *
     * @param token
     * @return extract contactID from the token
     */
    public static long extractContactid(String token){
        return (int) Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .get(CONTACT_ID);
    }

    public static Authentication getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.



            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            int contactID=(int) Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .get(CONTACT_ID);

            Collection<?> authCont=(Collection<?>) Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .get("auth");

            List<SimpleGrantedAuthority> authorities;

            if (authCont.isEmpty())
                authorities = emptyList();
            else
                authorities = authCont.stream().map(strAuth -> new SimpleGrantedAuthority(strAuth.toString())).collect(Collectors.toList());


            return user != null ?
                    new UsernamePasswordAuthenticationToken(user,new AccountCredentials(user, Long.valueOf(contactID)), authorities) :
                    null;


        }
        return null;
    }



}