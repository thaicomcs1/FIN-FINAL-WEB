
package com.mfu.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

import com.mfu.dao.PackingListFacade;
import com.mfu.entity.PackingList;

public class FindPackingListByTripKeyServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PackingListFacade facade = new PackingListFacade();

		String tripKey = req.getParameter("tripKey");
		List<PackingList> packingLists = facade.findPackingListByTripKey(tripKey);
		facade.closeEntityManager();

		try {
			System.out.println("get result size: " + packingLists.size());
			ObjectMapper mapper = new ObjectMapper();
			resp.addHeader("Access-Control-Allow-Origin", "*");
			resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
			resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
			resp.addHeader("Access-Control-Max-Age", "1728000");
			resp.setContentType("application/json");
			mapper.writeValue(resp.getOutputStream(), packingLists);
			resp.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
