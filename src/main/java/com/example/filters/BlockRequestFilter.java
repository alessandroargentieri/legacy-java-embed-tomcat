package com.example.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class BlockRequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("BlockRequestFilter is initialized!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("BlockRequestFilter is filtering the request!");
        if (request.getParameter("block") != null) {
            System.out.println("BlockRequestFilter is blocking the request!");
            response.getWriter().println("Sorry, you've been blocked!");
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("BlockRequestFilter is destroyed!");
    }
}
