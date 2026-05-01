    package com.example.biblio.security;

    import jakarta.servlet.FilterChain;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.stereotype.Component;
    import org.springframework.web.filter.OncePerRequestFilter;

    import java.io.IOException;
    import java.util.List;

    @Component
    public class JwtFilter extends OncePerRequestFilter {

        private final JwtUtils jwtUtils;

        public JwtFilter(JwtUtils jwtUtils) {
            this.jwtUtils = jwtUtils;
        }

        @Override
        protected void doFilterInternal(
                HttpServletRequest request,
                HttpServletResponse response,
                FilterChain filterChain
        ) throws ServletException, IOException {
            String path = request.getServletPath();

            if (path.startsWith("/auth/")) {
                filterChain.doFilter(request, response);
                return;
            }
            System.out.println("\n===== JWT FILTER START =====");
            System.out.println("REQUEST = " + request.getRequestURI());

            String authHeader = request.getHeader("Authorization");

            System.out.println("AUTH HEADER = " + authHeader);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = authHeader.substring(7);

            String username = jwtUtils.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                if (jwtUtils.isTokenExpired(token)) {
                    filterChain.doFilter(request, response);
                    return;
                }

                String role = jwtUtils.extractRole(token);

                if (role == null) {
                    filterChain.doFilter(request, response);
                    return;
                }

                // ROLE normalization
                if (!role.startsWith("ROLE_")) {
                    role = "ROLE_" + role;
                }

                System.out.println("ROLE FINAL = " + role);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                List.of(new SimpleGrantedAuthority(role))
                        );

                SecurityContextHolder.getContext().setAuthentication(auth);

                System.out.println("AUTH SET SUCCESSFULLY");
            }

            System.out.println("===== JWT FILTER END =====\n");

            filterChain.doFilter(request, response);
        }
    }