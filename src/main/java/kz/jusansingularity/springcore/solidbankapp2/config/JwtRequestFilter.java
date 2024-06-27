package kz.jusansingularity.springcore.solidbankapp2.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.jusansingularity.springcore.solidbankapp2.util.ErrorResponseSender;
import kz.jusansingularity.springcore.solidbankapp2.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils;
    private final ErrorResponseSender errorResponseSender;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                username = jwtTokenUtils.getUsername(jwt);
            } catch (ExpiredJwtException e) {
                errorResponseSender.sendErrorResponse(response, HttpStatus.FORBIDDEN, "JWT token expired");
                return;
            } catch (SignatureException e) {
                errorResponseSender.sendErrorResponse(response, HttpStatus.FORBIDDEN, "Your signature is not right");
            } catch (MalformedJwtException e) {
                errorResponseSender.sendErrorResponse(response, HttpStatus.BAD_REQUEST, "Malformed JWT token");
                return;
            } catch (UnsupportedJwtException e) {
                errorResponseSender.sendErrorResponse(response, HttpStatus.BAD_REQUEST, "Unsupported JWT token");
                return;
            } catch (IllegalArgumentException e) {
                errorResponseSender.sendErrorResponse(response, HttpStatus.BAD_REQUEST, "Invalid JWT token");
                return;
            }
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    jwtTokenUtils.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            );
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }
}
