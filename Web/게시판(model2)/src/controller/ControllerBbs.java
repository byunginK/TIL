package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.BbsDao;
import dto.BbsDto;
import dto.MemberDto;

@WebServlet("/conBbs")
public class ControllerBbs extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String work = req.getParameter("work");
		BbsDao dao = BbsDao.getInstance();
		//System.out.println(work);
		if(work.equals("bbslist")) {
			List<BbsDto> list = dao.getBbsList();		
			
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8"); 
			String gson = new Gson().toJson(list);
			//System.out.println(gson);
			resp.getWriter().write(gson);
		}else if(work.equals("write")) {
			resp.sendRedirect("bbswrite.jsp");
		}else if(work.equals("back")) {
			resp.sendRedirect("bbslist.jsp");
		}else if(work.equals("addcontent")) {
			String id = req.getParameter("id");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			BbsDto dto = new BbsDto(id, title, content);
			boolean isS = dao.writeBbs(dto);
			if(isS) {
				String check = "success";
				String gson = new Gson().toJson(check);
				resp.getWriter().write(gson);
			}else {
				String check = "fail";
				String gson = new Gson().toJson(check);
				resp.getWriter().write(gson);
			}
		}else if(work.equals("detail")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			BbsDto dto = dao.getBbs(seq);
			req.setAttribute("BbsDto", dto);
			forward("bbsdetail.jsp", req, resp);
		}else if(work.equals("deletecon")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			boolean isS = dao.bbsDelete(seq);
			isS(isS, req, resp);
		}else if(work.equals("answer")) {
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String id = req.getParameter("id");
			int seq = Integer.parseInt(req.getParameter("seq"));
			BbsDto dto = new BbsDto(id, title, content);
			boolean isS = dao.answer(seq, dto);
			req.setAttribute("isS", isS);
			forward("answer.jsp", req, resp);
			
		}
	}

	public void forward(String linkname, HttpServletRequest req, HttpServletResponse resp) {
		RequestDispatcher dispatcher = req.getRequestDispatcher(linkname);
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void isS(boolean isS,HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		if(isS) {
			String check = "success";
			String gson = new Gson().toJson(check);
			resp.getWriter().write(gson);
		}else {
			String check = "fail";
			String gson = new Gson().toJson(check);
			resp.getWriter().write(gson);
		}
	}
}
