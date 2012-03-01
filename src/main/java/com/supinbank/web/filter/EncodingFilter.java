package com.supinbank.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 3/1/12
 * Time: 3:44 PM
 * Filter to change the servlet encoding and avoid encoding problems with non-ascii characters.
 */
@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*")
public class EncodingFilter implements Filter
{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        request.setCharacterEncoding("UTF-8");

        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy()
    {
    }
}
