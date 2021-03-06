package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.controller.MainController;
import web.controller.reservation.AcceptReservationController;
import web.controller.reservation.CheckAvailableListController;
import web.controller.reservation.RejectReservationController;
import web.controller.reservation.ReserveController;
import web.controller.restaurant.RestaurantDetailController;
import web.controller.restaurant.SearchRestaurantController;
import web.controller.login.LoginCheckController;
import web.controller.login.LoginController;
import web.controller.login.LoginFormController;
import web.controller.login.LogoutController;
import web.controller.manager.ManagerIndexController;
import web.controller.manager.ManagerSignupController;
import web.controller.manager.ManagerSignupFormController;
import web.controller.member.MemberDeleteController;
import web.controller.member.MemberUpdateController;
import web.controller.member.MyPageController;
import web.controller.menu.AddMenuController;
import web.controller.menu.AddMenuFormController;
import web.controller.menu.MenuDeleteController;
import web.controller.menu.MenuListController;
import web.controller.menu.MenuUpdateController;
import web.controller.menu.MyMenuListController;
import web.controller.reservation.ReserveFormController;
import web.controller.restaurant.AddRestaurantController;
import web.controller.restaurant.AddRestaurantFormController;
import web.controller.restaurant.RestaurantDetail;
import web.controller.restaurant.SearchRestaurantController;
import web.controller.restaurant.SelectRestaurantInfoController;
import web.controller.restaurant.UpdateRestaurantController;
import web.controller.restaurant.UpdateRestaurantFormController;
import web.controller.signup.ManagerIdCheckController;
import web.controller.signup.MemberIdCheckController;
import web.controller.signup.SignupController;
import web.controller.signup.SignupFormController;

@WebServlet(urlPatterns = "*.do")
public class FrontController extends HttpServlet {

    private Map<String, Controller> commandMap;

    @Override
    public void init(ServletConfig config) throws ServletException {
        commandMap = new HashMap<>();

        commandMap.put("/cMain.do",new MainController()); // customer ???????????????
        commandMap.put("/logout.do", new LogoutController()); // ???????????? ??????
        commandMap.put("/loginForm.do", new LoginFormController()); // ????????? ?????? ?????????
        commandMap.put("/login.do", new LoginController()); // ????????? ??????
        commandMap.put("/loginCheck.do", new LoginCheckController()); // ???????????? ????????? ???????????? ????????? ?????? ??????
        commandMap.put("/signupForm.do", new SignupFormController()); // ???????????? ?????????
        commandMap.put("/signup.do", new SignupController()); // ???????????? ??????
        commandMap.put("/myPage.do", new MyPageController()); // ???????????????
        commandMap.put("/memberUpdate.do", new MemberUpdateController()); // ???????????? ??????
        commandMap.put("/memberDelete.do", new MemberDeleteController()); // ????????????
        commandMap.put("/memberIdCheck.do", new MemberIdCheckController()); // ?????? ????????? ???????????? ??????

        commandMap.put("/searchRestaurant.do", new SearchRestaurantController());
        commandMap.put("/restaurantDetail.do", new RestaurantDetailController());

        // Manager
        commandMap.put("/main.do", new ManagerIndexController()); // ????????? ?????? ?????????
        commandMap.put("/managerIdCheck.do", new ManagerIdCheckController()); // ????????? ????????? ???????????? ??????
        commandMap.put("/managerSignupForm.do", new ManagerSignupFormController()); // ????????? ???????????? ?????????
        commandMap.put("/managerSignup.do", new ManagerSignupController()); // ????????? ???????????? ??????
        commandMap.put("/addRestaurantForm.do", new AddRestaurantFormController()); // ?????? ?????? ?????????
        commandMap.put("/addRestaurant.do", new AddRestaurantController()); // ?????? ????????????

        commandMap.put("/acceptReservation.do", new AcceptReservationController());
        commandMap.put("/rejectReservation.do", new RejectReservationController());

        // Reservation
        commandMap.put("/reserveForm.do", new ReserveFormController()); // ???????????? ?????????
        commandMap.put("/reserve.do", new ReserveController()); // ??????
        commandMap.put("/checkAvailableList.do", new CheckAvailableListController());

        commandMap.put("/updateRestaurantForm.do", new UpdateRestaurantFormController()); // ?????? ?????? ?????? ?????????
        commandMap.put("/selectRestaurantInfo.do", new SelectRestaurantInfoController()); // ?????? ?????? ????????????
        commandMap.put("/updateRestaurant.do", new UpdateRestaurantController()); // ?????? ?????? ????????????
        commandMap.put("/addMenuForm.do", new AddMenuFormController()); // ???????????????????????? ??????
        commandMap.put("/addMenu.do", new AddMenuController()); // ?????? ?????? ??????
        commandMap.put("/menuList.do", new MenuListController()); // ???????????? ???????????? ??????
        commandMap.put("/myMenuList.do", new MyMenuListController()); // ??? ????????? ?????? ?????? ?????????
        commandMap.put("/menuUpdate.do", new MenuUpdateController()); // ?????? ?????? ????????????
        commandMap.put("/menuDelete.do", new MenuDeleteController()); // ?????? ?????? ??????
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String commandPath = requestURI.substring(contextPath.length());

        if (commandMap.get(commandPath) == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        String filePath = commandMap.get(commandPath).exec(request, response);
        
        if (isRedirect(filePath)) {
            response.sendRedirect(filePath.substring(9));
            return;
        }
        
        if(!filePath.endsWith(".do")) {
			if(filePath.startsWith("ajax:")) {
				// ajax ??????
				PrintWriter out = response.getWriter();
				out.print(filePath.substring(5));
				return;
			} else {
				filePath = filePath + ".tiles";
			}
		}
        System.out.println(filePath);
        request.getRequestDispatcher(filePath).forward(request, response);
    }

    private boolean isRedirect(String filePath) {
        String redirect = "redirect:";  // 9
        return filePath.startsWith(redirect);
    }
}
