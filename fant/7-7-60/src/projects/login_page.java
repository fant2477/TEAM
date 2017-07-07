package projects;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project_db.ConnectionDB;
import project_db.DocumentManager;
import project_db.User;
import project_db.UserManager;
import project_db.UserValidation;

/**
 * Servlet implementation class login_page
 */
@WebServlet("/login_page")

public class login_page extends HttpServlet {
	String change_page,from_page;
	User current_user;
	static String bt;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login_page() {
        super();
        System.out.println("in login_page constructor");
    }	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in login_page get");
		

		response.setContentType("text/html");

		// go to fn same as sent fn (sent by post go to post )
		RequestDispatcher dispatcher = request.getRequestDispatcher("login_ui.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in login_page post");
		
		response.setContentType("text/html");
		ConnectionDB.connect();
		
		//get parameter from ui
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		//----------- set , send----------		
		//request.setAttribute("username", username);
		//request.setAttribute("password", password);
		
		
		String bt = request.getParameter("bt");
		if (bt == null) {
		    //no button has been selected
			System.out.println("no bt was press");
			change_page = "login_page";
			from_page = "login_page";
			ConnectionDB.disconnect();
			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			
			System.out.println("log change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");
			
		} else if (bt.equals("Login")) {
		    //Login button was pressed
			System.out.println("login bt was press");
			
			UserValidation user_va = new UserValidation();
			UserManager user = new UserManager();
			
			if(user_va.validLogin(username,password)==true)
			{
				
				current_user = user.getUser(username);
//				DocumentManager doc = new DocumentManager(current_user);
				change_page = "main_page";
				from_page = "login_page";
				request.getSession().setAttribute("change_page", change_page);
				request.getSession().setAttribute("from_page", from_page);
				request.getSession().setAttribute("current_user", current_user);
				System.out.println("login set current_user: "+ current_user);
				
				System.out.println("log change_page: "+ change_page);
				//go to get fn
				response.sendRedirect("UI_Manager");
			}
			else{
				String username_check = user_va.validUsernameLogin(username);
				String password_check = user_va.validPasswordLogin(username,password);
				

				request.setAttribute("username_check",username_check);
				request.setAttribute("password_check",password_check);

				
				request.setAttribute("username",username);
				request.setAttribute("password",password);



				//go to get fn
//				response.sendRedirect("register_ui.jsp");
				request.getRequestDispatcher("login_ui.jsp").forward(request, response);
				System.out.println("go to login.jsp again");}
			ConnectionDB.disconnect();
			
		} else if (bt.equals("Register")) {
		    //Register button was pressed
			System.out.println("register bt was press");
			ConnectionDB.disconnect();
			change_page = "register_page";
			from_page = "login_page";
			
			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			
			System.out.println("log change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
		} else {
		    //someone has altered the HTML and sent a different value!
			ConnectionDB.disconnect();
			System.out.println("? bt was press");
			change_page = "login_page";
			from_page = "login_page";
			
			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			
			System.out.println("log change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");
		}
		
		
	}
}
