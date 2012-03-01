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
 * Filter that implements a flash scope function. It puts tagged values in session for one request time
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
                //get flash object map
                Map<String, Object> flashMap = (Map) session.getAttribute(flashKey);
                //if it exists, put all objects in the current request
                if(flashMap != null)
                {
                    for(Map.Entry<String, Object> flashData : flashMap.entrySet())
                    {
                        request.setAttribute(flashData.getKey(), flashData.getValue());
                    }
                    // clean the session
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
            // scan request attributes for flash tagged values
            while (attributesName.hasMoreElements())
            {
                String attributeName = attributesName.nextElement();
                //if the value is flash tagged, put it in the flash scope map
                if(attributeName.startsWith("[FLASH]"))
                {
                    Object value = httpRequest.getAttribute(attributeName);
                    String key  = attributeName.replace("[FLASH]", "");
                    flashMap.put(key, value);
                }
            }
            if(!flashMap.isEmpty())
            {
                // if there is any flash scope attribute, persist them in the session
                httpRequest.getSession(false).setAttribute(flashKey, flashMap);
            }
        }

    }

    @Override
    public void destroy()
    {
    }
}
