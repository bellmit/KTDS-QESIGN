package com.ktds.esign.common.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;


/**
 * RequestBody JSON XSS Filtergi
 */
@Slf4j
public class RequestBodyFilter implements Filter {

    private static final List<String> SKIP_URLS = Collections.unmodifiableList(Arrays.asList(
            "/editor/save"
    ));
    
    @Override
    public void destroy() {
        // filter destroy
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        boolean isJson = isJsonContentType(request);
        String path = ((HttpServletRequest) request).getRequestURI();

        try {
            for (String skipUrl : SKIP_URLS) {
                if (!path.contains(skipUrl) && isJson) {
                    RequestBodyWrapper reqWrapper = new RequestBodyWrapper((HttpServletRequest) request);
                    chain.doFilter(reqWrapper, response);
                } else {
                    chain.doFilter(request, response);
                }
            }
        } catch (IOException e) {
            chain.doFilter(request, response);
        }

    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.error("{}", filterConfig);
    }
    
    private boolean isJsonContentType(ServletRequest request) {
        String contentType = request.getContentType();
        return (contentType != null && contentType.toLowerCase().contains("json"));
    }
    
    private boolean isAjaxRequest(HttpServletRequest req){
        return "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
    }

}
