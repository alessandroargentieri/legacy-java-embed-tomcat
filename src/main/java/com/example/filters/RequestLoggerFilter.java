package com.example.filters;

import javax.servlet.*;
import java.io.IOException;

public class RequestLoggerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("RequestLoggerFilter is initialized!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("RequestLoggerFilter is filtering the request!");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("RequestLoggerFilter is destroyed!");
    }
}