package project_controller;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project_board.BoardController;
import project_home.HomeController;
import project_user.UserController;

public class HandlerMapping {
	static Map<String, Controller2> map = new Hashtable<String, Controller2>();
	static {
		map.put("/", new HomeController());
		map.put("/user", new UserController());
		map.put("/board", new BoardController());
	}
	public Controller2 getController(HttpServletRequest req, HttpServletResponse resp) {
		String reqUri = req.getRequestURI();
		String ctxPath = req.getContextPath()+"/CODECODE";
		int beginIndex = ctxPath.length();
		
		String path=reqUri.substring(beginIndex);
		String path2="/";
		
		if(path.indexOf("/", 1)!= -1) {
			path2=path.substring(0, path.indexOf("/", 1));
		}
		
		String filePath = path.substring(path.lastIndexOf("/"));
		req.setAttribute("path", path);
		req.setAttribute("path2", path2);
		req.setAttribute("filePath", filePath);
		return map.get(path2);
	}
}
