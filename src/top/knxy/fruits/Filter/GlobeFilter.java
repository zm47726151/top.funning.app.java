package top.knxy.fruits.Filter;

import javax.servlet.annotation.WebFilter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter( filterName = "GlobeFilter",urlPatterns = "/*")
public class GlobeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


        String requestURI = request.getRequestURI();

        int index = requestURI.lastIndexOf('.');
        if (index > -1 && ".jsp".equals(requestURI.substring(index))) {
            response.sendRedirect("/");
            return;
        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
