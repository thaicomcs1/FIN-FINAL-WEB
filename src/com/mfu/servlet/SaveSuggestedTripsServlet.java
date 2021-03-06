
package com.mfu.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.mfu.dao.SuggestedTripsFacade;
import com.mfu.entity.SuggestedTrips;


public class SaveSuggestedTripsServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
//		StringBuffer jb = new StringBuffer();
//		 String line = null;
//		 try {
//			    BufferedReader reader = req.getReader();
//			    while ((line = reader.readLine()) != null)
//			      jb.append(line);
//			  } catch (Exception e) { /*report an error*/ }
//		 System.out.println("#"+jb+"#");
		try {
			ObjectMapper mapper = new ObjectMapper();
			SuggestedTrips suggestedTrips = mapper.readValue(req.getReader(), SuggestedTrips.class);
			System.out.println(suggestedTrips.getKey());
		
			SuggestedTripsFacade facade = new SuggestedTripsFacade();
	
			if(suggestedTrips!=null)
				facade.saveSuggestedTrips(suggestedTrips);
			
			facade.closeEntityManager();
			resp.addHeader("Access-Control-Allow-Origin", "*");
			resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
			resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
			resp.addHeader("Access-Control-Max-Age", "1728000");
			
			resp.setContentType("application/json");
			resp.getWriter().print(1);
			resp.getWriter().flush();
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
		
	}
}
