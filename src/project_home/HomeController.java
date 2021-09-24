package project_home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project_controller.Controller2;

public class HomeController implements Controller2{
	
	
	@Override
	public String run(HttpServletRequest req, HttpServletResponse resp) {
		return "/WEB-INF/views/home.jsp";
	}

}
