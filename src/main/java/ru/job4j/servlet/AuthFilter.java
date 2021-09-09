package ru.job4j.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private static final Set<String> ALLOWED_PATHS = Set.of(
            "auth.html", "reg.html", "auth.do", "reg.do");
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        boolean isLoggedIn = req.getSession().getAttribute("user") != null;

        String[] parts = req.getRequestURI().split("/");
        String uriPath = parts[parts.length - 1];

        if (ALLOWED_PATHS.contains(uriPath) && !isLoggedIn) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (!ALLOWED_PATHS.contains(uriPath) && !isLoggedIn) {
            resp.sendRedirect(req.getContextPath() + "/auth.html");
            return;
        }

        if(isLoggedIn && ALLOWED_PATHS.contains(uriPath)) {
            resp.sendRedirect(".");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
