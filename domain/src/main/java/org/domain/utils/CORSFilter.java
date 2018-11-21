package org.domain.utils;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CORSFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
            throws IOException, ServletException {
        HttpServletResponse http = (HttpServletResponse) response;
        http.setHeader("Access-Control-Allow-Origin", "*");
        http.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        http.setHeader("Access-Control-Max-Age", "3600");
        http.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        filter.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) {

    }

    public void destroy() {

    }
}
