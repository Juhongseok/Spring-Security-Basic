package com.jhs.springsecuritybasic.chap09;

import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String requestId = request.getHeader("Request-Id");
        if (StringUtils.hasText(requestId)) {
            chain.doFilter(request, response);
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
