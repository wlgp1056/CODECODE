package project_board;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import project_comment.CommentDAO;
import project_comment.CommentDTO;
import project_controller.Controller2;

public class BoardController implements Controller2 {
	BbsDAO dao = BbsDAO.getInstance();

	@Override
	public String run(HttpServletRequest req, HttpServletResponse resp ) throws IOException , ServletException {
		String filePath = (String)req.getAttribute("filePath");
		String method=req.getMethod();
		String ctxPath = req.getContextPath();
		String viewName = "/WEB-INF/views/bbs/list.jsp";
		HttpSession session = req.getSession();
		
		// POST
		if("POST".equals(method)) {
			if("/write.do".equals(filePath)) {
				MultipartRequest multi = null;
				 
				 int sizeLimit = 10 * 1024 * 1024;
				 String savePath = req.getRealPath("/upload");
				 try{
						multi = new MultipartRequest(req, savePath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
				 } catch(Exception e){
					 e.printStackTrace();
				 }
				 
				BbsDTO dto = new BbsDTO();
		
				String bbsCategory = multi.getParameter("bbsCategory");
				String bbsTitle = multi.getParameter("bbsTitle");
				String bbsContent = multi.getParameter("bbsContent");
				String id = (String) session.getAttribute("sessionID");
				String b_fname = multi.getFilesystemName("b_fname");
				
				dao.write(bbsCategory, bbsTitle, bbsContent, id, b_fname);
				viewName="redirect:"+ctxPath+"/CODECODE/board/list.do";	
				
			} else if("/bbsupdate.do".equals(filePath)) {
				
				BbsDTO dto = new BbsDTO();
					
				String bbsId = req.getParameter("bbsId");
				String bbsCategory = req.getParameter("bbsCategory");
				String bbsTitle = req.getParameter("bbsTitle");
				String bbsContent = req.getParameter("bbsContent");
			
				dto.setBbsid(Integer.parseInt(bbsId));
				dto.setBbscategory(bbsCategory);
				dto.setBbstitle(bbsTitle);
				dto.setBbscontent(bbsContent);
	
				dao.update(dto);
				
				viewName="redirect:"+ctxPath+"/CODECODE/board/detail.do?bbsId="+bbsId;
				
			} else if("/detail.do".equals(filePath)) {
				String bbsId = req.getParameter("bbsId");
				String id = req.getParameter("id");
				String commtext = req.getParameter("commentText");
				CommentDAO dao = new CommentDAO();
				dao.write(Integer.parseInt(bbsId), id, commtext);
				viewName="redirect:"+ctxPath+"/CODECODE/board/detail.do?bbsId="+bbsId;
			}
			return viewName;
		}
		
		//GET
	    if("/write.do".equals(filePath)) {
			viewName="/WEB-INF/views/bbs/write.jsp";
		} else if("/list.do".equals(filePath)) {
			String KeyField = req.getParameter("keyField");
			String KeyWord = req.getParameter("keyWord");
			List<BbsDTO> list = new ArrayList<BbsDTO>();
			list = dao.selectAll2(KeyField, KeyWord);
			//list = dao.selectAll(pageNumber);
			req.setAttribute("list", list);
			viewName="/WEB-INF/views/bbs/list.jsp";
			
		} else if("/detail.do".equals(filePath)) {
			String bbsId = req.getParameter("bbsId");
			dao.hitUpdate(Integer.parseInt(bbsId));
			BbsDTO dto = new BbsDTO();
			dto = dao.selectId(Integer.parseInt(bbsId));
			req.setAttribute("detail", dto);
			
			CommentDAO dao = new CommentDAO();
			List<CommentDTO> list = new ArrayList<CommentDTO>();
			list = dao.selectAll(Integer.parseInt(bbsId));
			req.setAttribute("list", list);
			
			viewName="/WEB-INF/views/bbs/detail.jsp";
			
		} else if("/bbsdelete.do".equals(filePath)) {
			String bbsId = req.getParameter("bbsId");
			dao.delete(Integer.parseInt(bbsId));
			viewName="redirect:"+ctxPath+"/CODECODE/board/list.do";
			
		} else if("/comdelete.do".equals(filePath)) {
			String bbsId = req.getParameter("bbsId");
			String commid = req.getParameter("commid");
			CommentDAO dao = new CommentDAO();
			dao.delete(Integer.parseInt(commid));
			viewName="redirect:"+ctxPath+"/CODECODE/board/detail.do?bbsId="+bbsId;
			
		} else if("/bbsupdate.do".equals(filePath)) {
			String bbsId = req.getParameter("bbsId");
			BbsDTO dto = new BbsDTO();
			dto = dao.selectId(Integer.parseInt(bbsId));
			req.setAttribute("update", dto);
			viewName="/WEB-INF/views/bbs/update.jsp";
			
		}else if("/download.do".equals(filePath)) {
			//viewName="/WEB-INF/views/bbs/FileDownload.jsp";
			String bbsId = req.getParameter("bbsId");
			BbsDAO dao = BbsDAO.getInstance();
			BbsDTO board = dao.selectId(Integer.parseInt(bbsId));
			String b_fname = board.getB_fname();
			
			String uploadFileName = req.getRealPath("/upload"+"/"+b_fname);
			
			File downFile = new File(uploadFileName);
			
			if(downFile.exists() && downFile.isFile())
			{
				try{
					long filesize = downFile.length();
					resp.setContentType("application/x-msdownload");
					resp.setContentLength((int)filesize);
					
					String strClient = req.getHeader("user-agent");
					
					if(strClient.indexOf("MSIE 5.5") != -1){
						resp.setHeader("content-Disposition", "filename="+b_fname+";");
					}
					else 
					{
						b_fname = URLEncoder.encode(b_fname, "UTF-8").replaceAll("\\+", "%20"); //인코딩 변경과 '+'문자 깨짐 방지
						resp.setHeader("content-Disposition","attachment;filename="+b_fname+";");
					}
					resp.setHeader("Content-Length", String.valueOf(filesize));
					resp.setHeader("Content-Transfer-Encoding", "binary");
					resp.setHeader("Pragma", "no-cache");
					resp.setHeader("Cache-Control","private");
					
					byte b[] = new byte[1024];
					BufferedInputStream fin = new BufferedInputStream(new FileInputStream(downFile));
					BufferedOutputStream outs = new BufferedOutputStream(resp.getOutputStream());
					int read = 0 ;
					while((read = fin.read(b))!= -1){
						outs.write(b,0,read);
					}
					outs.flush();
					outs.close();
					fin.close();
					
				}catch(Exception e){
					System.out.println("Download Exception : " + e.getMessage());
				}
			}
			else
			{
				System.out.println("Download Error : downFile Error [" + downFile + "]");

			}
			return null;

		}
		return viewName;
	}

}
