
package com.mfu.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.mfu.dao.ItemFacade;
import com.mfu.entity.Item;

public class FindItemByKeyServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ItemFacade facade = new ItemFacade();
		Item item = facade.findItemByKey(req.getParameter("key"));
		System.out.println("find item by key: " + req.getParameter("key"));
		facade.closeEntityManager();

		try {
			ObjectMapper mapper = new ObjectMapper();

			resp.addHeader("Access-Control-Allow-Origin", "*");
			resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
			resp.addHeader("Access-Control-Allow-Headers",
					"X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
			resp.addHeader("Access-Control-Max-Age", "1728000");
			resp.setContentType("application/json");

			mapper.writeValue(resp.getOutputStream(), item);
			resp.getOutputStream().flush();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
