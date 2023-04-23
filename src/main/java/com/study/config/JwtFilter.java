//package com.study.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import org.springframework.web.filter.GenericFilterBean;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtFilter extends GenericFilterBean{
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        final HttpServletRequest request = (HttpServletRequest) servletRequest;
//        final HttpServletResponse response = (HttpServletResponse) servletResponse;
//        final String authHeader = request.getHeader("authorization");
//        if ("OPTIONS".equals(request.getMethod())) {
//            response.setStatus(HttpServletResponse.SC_OK);
//            filterChain.doFilter((ServletRequest) request, (ServletResponse) response);
//        } else {
//            if(authHeader == null || !authHeader.startsWith("Bearer ")){
//                throw new ServletException("An exception occurred");
//            }
//        }
//        final String token = authHeader.substring(7);
//        Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
//        request.setAttribute("claims", claims);
//        request.setAttribute("blog", servletRequest.getParameter("id"));
//        filterChain.doFilter((ServletRequest) request, (ServletResponse) response);
//    }
//}
