package com.supinbank.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/25/12
 * Time: 7:03 PM
 * To change this template use File | Settings | File Templates.
 */
@WebFilter(filterName = "FlashFilter", urlPatterns = "/*")
public class FlashFilter implements Filter
{
    private static final String flashKey = "flashScope";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException
    {
        if(request instanceof HttpServletRequest)
        {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpSession session = httpRequest.getSession(false);
            if(session != null)
            {
                Map<String, Object> flashMap = (Map) session.getAttribute(flashKey);
                if(flashMap != null)
                {
                    for(Map.Entry<String, Object> flashData : flashMap.entrySet())
                    {
                        request.setAttribute(flashData.getKey(), flashData.getValue());
                    }
                    session.removeAttribute(flashKey);
                }
            }
        }

        filterChain.doFilter(request, response);

        if(request instanceof HttpServletRequest)
        {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            Map<String, Object> flashMap = new HashMap<String, Object>();
            Enumeration<String> attributesName = httpRequest.getAttributeNames();
            while (attributesName.hasMoreElements())
            {
                String attributeName = attributesName.nextElement();
                if(attributeName.startsWith("[FLASH]"))
                {
                    Object value = httpRequest.getAttribute(attributeName);
                    String key  = attributeName.replace("[FLASH]", "");
                    flashMap.put(key, value);
                }
            }
            if(!flashMap.isEmpty())
            {
                httpRequest.getSession(false).setAttribute(flashKey, flashMap);
            }
        }

    }

    @Override
    public void destroy()
    {
    }
}
