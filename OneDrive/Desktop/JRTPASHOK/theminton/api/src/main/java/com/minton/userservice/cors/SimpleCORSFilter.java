//package com.minton.userservice.cors;
//
//
//import java.io.IOException;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.minton.userservice.exception.handler.ApplicationConstants;
//
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class SimpleCORSFilter implements Filter {
//
//  @Autowired
//  private ApplicationConstants appContsants;
//
//  @Override
//  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//      throws IOException, ServletException {
//    HttpServletResponse response = (HttpServletResponse) res;
//    HttpServletRequest request = (HttpServletRequest) req;
//    response.addHeader("Access-Control-Allow-Origin", "*");
//    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PATCH, PUT");
//    response.setHeader("Access-Control-Max-Age", "3600");
//    response.setHeader("Access-Control-Allow-Headers",
//        "x-requested-with, content-type, Authorization, Cache-Control, X-XSRF-TOKEN, X-FORWARDED-FOR, clientId, X-TEMP-PASSWORD, X-RE-CAPTCHA-TOKEN");
//    response.setHeader("Access-Control-Allow-Credentials", "true");
//    response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
//    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//      response.setStatus(HttpServletResponse.SC_OK);
//    } else {
//      chain.doFilter(req, res);
//    }
//  }
//
//  @Override
//  public void init(FilterConfig filterConfig) {
//    // No Implementation Needed
//  }
//
//  @Override
//  public void destroy() {
//    // No Implementation Needed
//  }
//
//}