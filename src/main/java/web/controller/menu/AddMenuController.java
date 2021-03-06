package web.controller.menu;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.Controller;
import domain.menu.service.MenuService;
import domain.menu.serviceImpl.MenuServiceImpl;
import domain.menu.vo.MenuVO;

public class AddMenuController implements Controller {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ServletContext context = request.getServletContext();
		String uploadPath = context.getRealPath("/asset/img");
		// new MultipartRequest("요청정보","저장위치","maxSize","encoding","rename정책");
		try {
			MultipartRequest multi = new MultipartRequest(request,uploadPath,1024*1024*5,"utf-8",new DefaultFileRenamePolicy());
			MenuVO vo = new MenuVO();
			long restaurantId = Long.parseLong(multi.getParameter("restaurantId"));
			vo.setRestaurantId(restaurantId);
			vo.setName(multi.getParameter("name"));
			vo.setCost(Integer.parseInt(multi.getParameter("cost")));
			vo.setPicture(multi.getFilesystemName("picture"));
			MenuService menuDAO = new MenuServiceImpl();
			menuDAO.menuInsert(vo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "main.do";
	}

}
