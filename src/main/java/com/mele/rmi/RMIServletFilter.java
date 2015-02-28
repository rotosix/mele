package com.mele.rmi;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

public class RMIServletFilter extends UrlRewriteFilter
  implements Filter
{
 
  public void init(FilterConfig filterConfig)
    throws ServletException {
      super.init(filterConfig);
  }

  public void destroy()
  {
      destroyActual();
  }

  
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {

      String uri = ((HttpServletRequest) request).getRequestURI();
	  if(uri.contains("/rmi/")) {
	      String actionName = uri.substring(uri.lastIndexOf("/")+1);

	      RequestDispatcher dis = request.getRequestDispatcher(actionName);
		  dis.forward(request, response);
	  } else {
	      super.doFilter(request, response, chain);
	  }
  }

}