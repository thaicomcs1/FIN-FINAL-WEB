
package com.mfu.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

import com.mfu.dao.SuggestedTodoFacade;
import com.mfu.dao.TodoFacade;
import com.mfu.entity.SuggestedTodo;
import com.mfu.entity.Todo;

public class FindSuggestedToDoBySuggestTripKeyServlet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		SuggestedTodoFacade facade = new SuggestedTodoFacade();

		String tripKey = req.getParameter("suggestTripKey");
		List<SuggestedTodo> todos = facade.findSuggestedToDoBySuggestTripKey(tripKey);
		facade.closeEntityManager();

		try {
			System.out.println("FindSuggestedToDoBySuggestTripKeyServlet get result size: " + todos.size());
			ObjectMapper mapper = new ObjectMapper();
			resp.addHeader("Access-Control-Allow-Origin", "*");
			resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
			resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
			resp.addHeader("Access-Control-Max-Age", "1728000");
			resp.setContentType("application/json");
			mapper.writeValue(resp.getOutputStream(), todos);
			resp.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
