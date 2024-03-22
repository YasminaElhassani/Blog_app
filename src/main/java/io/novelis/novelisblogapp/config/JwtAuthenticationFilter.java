package io.novelis.novelisblogapp.config;

import io.novelis.novelisblogapp.service.enums.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private JwtService jwtService;

    public JwtAuthenticationFilter() {
        jwtService=new JwtService();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token=getToken(request);
        if(token!=null){
            String userName=jwtService.getUsernameFromToken(token);
            Authentication authentication=new UsernamePasswordAuthenticationToken(userName,null,null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);
    }

    private String getToken(HttpServletRequest request)
    {
        String token=request.getHeader("Authorization");

        if(StringUtils.hasText(token) && token.startsWith("Bearer "))
        {
            return  token.substring(7);
        }
        return null;
    }
}
