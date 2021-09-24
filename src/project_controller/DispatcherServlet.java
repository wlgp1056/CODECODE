package project_controller;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project_home.HomeController;

public class DispatcherServlet extends HttpServlet {
	HandlerMapping mapping = new HandlerMapping();
	Controller2 ctrl = null;
	
	
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ctrl = mapping.getController(req,resp);
		String viewName = ctrl.run(req,resp);
		
		StringTokenizer st = new StringTokenizer(viewName, ":");
		if(st.countTokens()>=2) {
			String cmd = st.nextToken();
			if("redirect".equals(cmd)) {
				resp.sendRedirect(st.nextToken());
			}
			return;
		}
		RequestDispatcher view = req.getRequestDispatcher(viewName);
		view.forward(req, resp);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

}
