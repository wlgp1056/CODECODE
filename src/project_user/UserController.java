package project_user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project_controller.Controller2;


public class UserController implements Controller2 {
	UserDAO dao = UserDAO.getInstance();

	@Override
	public String run(HttpServletRequest req, HttpServletResponse resp) {
		String filePath = (String)req.getAttribute("filePath");
		String method=req.getMethod();
		String ctxPath = req.getContextPath();
		HttpSession session = req.getSession();
		String viewName="/WEB-INF/views/user/login.jsp";
		
		//POST
		if("POST".equals(method)) {
			if("/login.do".equals(filePath)) {
				String id = req.getParameter("userID");
				String password = req.getParameter("password");
				int logr = dao.login(id, password);
				if(logr==1) {
					req.setAttribute("logr", logr);
					session.setAttribute("sessionID", id);
					session.setAttribute("password", password);
					viewName="redirect:"+ctxPath+"/CODECODE/board/list.do";
				}else {
					req.setAttribute("logr", logr);
					viewName="/WEB-INF/views/user/login.jsp";
				}
				return viewName;
			} //login.do
			else if("/joinus.do".equals(filePath)) {
				String id=req.getParameter("userID");
				String password = req.getParameter("password");
				String name=req.getParameter("name");
				String gender=req.getParameter("gender");
				String email=req.getParameter("email");
				UserDTO dto = new UserDTO(id,password,name,gender,email);
				int joinr = dao.join(dto);
				if(joinr == 1) {
					req.setAttribute("joinr", joinr);
					viewName="redirect:"+ctxPath+"/CODECODE/user/login.do";
				} else {
					req.setAttribute("joinr", joinr);
					viewName="/WEB-INF/views/user/join.jsp";
				}
				return viewName;
			}
			else if("/idfind.do".equals(filePath)) {
				String name=req.getParameter("name");
				String email=req.getParameter("email");
				String id = dao.idfind(name, email);
				int idr=0;
				if(id!=null) {
					idr=1;
					req.setAttribute("id", id);
					viewName = "/WEB-INF/views/user/idfindr.jsp";
				}else {
					idr=0;
					req.setAttribute("idr", idr);
					viewName = "/WEB-INF/views/user/idfind.jsp";
				}
				//return viewName;
			} else if("/pwfind.do".equals(filePath)) {
				String name=req.getParameter("name");
				String id=req.getParameter("id");
				String password = dao.pwfind(name, id);
				int pwr=0;
				if(password!=null) {
					pwr=1;
					req.setAttribute("password", password);
					viewName = "/WEB-INF/views/user/pwfindr.jsp";
				}else {
					pwr=0;
					req.setAttribute("pwr", pwr);
					viewName = "/WEB-INF/views/user/pwfind.jsp";
				}
				//return viewName;
			}
			return viewName;
		}
		
		
		// GET
		if("/joinus.do".equals(filePath)) {
			viewName = "/WEB-INF/views/user/join.jsp";
		} else if("/login.do".equals(filePath)) {
			viewName = "/WEB-INF/views/user/login.jsp";
		} else if("/idfind.do".equals(filePath)) {
			viewName = "/WEB-INF/views/user/idfind.jsp";
		} else if("/pwfind.do".equals(filePath)) {
			viewName = "/WEB-INF/views/user/pwfind.jsp";
		}else if("/logout.do".equals(filePath)) {
			session.invalidate();
			System.out.println("로그아웃");
			viewName = "redirect:"+ctxPath+"/CODECODE/";
		} 
		return viewName;
	}
}

