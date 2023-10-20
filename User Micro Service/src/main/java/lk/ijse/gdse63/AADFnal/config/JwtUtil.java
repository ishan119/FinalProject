package lk.ijse.gdse63.AADFnal.config;


import lk.ijse.gdse63.AADFnal.dto.UserDTO;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private final String secret_key = "mesecretkey";

    private long accessTokenValidity = 60*60*1000;

    private final JwtParser jwtParser;

    private final String TOKEN_HEADER = "Authorization";

    private final String TOKEN_PERFIX = "Bearer";

    public JwtUtil(){
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }


    private Claims parseJwtClaims(String token){
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public Claims resolveClaims(HttpServletRequest req){
        try {
            String token = resolveToken(req);
            if (token != null){
                return parseJwtClaims(token);
            }
            return null;
        }catch (ExpiredJwtException ex){
            req.setAttribute("expired",ex.getMessage());
            throw ex;
        }catch (Exception ex){
            req.setAttribute("invalid",ex.getMessage());
            throw ex;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PERFIX)){
            return bearerToken.substring(TOKEN_PERFIX.length());
        }
        return null;
    }


    public boolean validateClaims(Claims claims)throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        }catch (Exception e){
            throw e;
        }
    }
    public String getEmail(Claims claims){
        return claims.getSubject();
    }

    private List<String> getRoles(Claims claims){
        return (List<String>) claims.get("roles");
    }

    public String createToken(UserDTO user){
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("UserName", user.getUsername());
        claims.put("UserPassword", user.getPassword());
        claims.put("roles", user.getRoles());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256,secret_key)
                .compact();
    }

}
