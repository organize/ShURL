package com.ag.shurl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main servlet to handle /shorten and /{id} requests.
 * @author A. Wallin
 * @date 8/2/15
 */

public class ShURL extends HttpServlet {
	
	/**
	 * The cache instance where the application will store shortened URL indexes. 
	 */
	
	private ShURLCache cache = new ShURLCache();
	
	/**
	 * Handles any GET requests, excluding requests that contain ".html" in the url.
	 * Returns a 404 if the index specified in the request url cannot be found from the cache,
	 * 		otherwise writes a redirect to the corresponding url from the given index.
	 * 
	 * @param req, the GET request for this servlet.
	 * @param resp, the servlet response.
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String href = cache.get(req.getRequestURI().replaceAll("/ShURL/", ""));
		if(href == null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} else {
			resp.sendRedirect(resp.encodeRedirectURL(href));
		}
	}
	
	/**
	 * Handles the POST request from /shorten (specified in WEB-INF/web.xml).
	 * The parameter {@code link} contains an url to shorten. The url is stored inside a cache, where
	 * 		it will be assigned a key which is generated from current system time and overall cache size. This key can
	 * 		then be used to return the aforementioned url from the cache. 
	 * @param req, the POST request for this servlet.
	 * @param resp, the servlet response.
	 */
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String href = req.getParameter("link");
		resp.setContentType("text/plain; charset=ISO-8859-2");
		resp.getWriter().write(cache.put(href));
	}

}
