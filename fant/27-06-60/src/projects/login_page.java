package projects;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login_page
 */
@WebServlet("/login_page")

public class login_page extends HttpServlet {
	String change_page,from_page;
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
			
			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			
			System.out.println("log change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");
			
		} else if (bt.equals("Login")) {
		    //Login button was pressed
			System.out.println("login bt was press");
			change_page = "main_page";
			from_page = "login_page";
			
			
			
			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			
			System.out.println("log change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");
			
		} else if (bt.equals("Register")) {
		    //Register button was pressed
			System.out.println("register bt was press");
			
			change_page = "register_page";
			from_page = "login_page";
			
			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			
			System.out.println("log change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
		} else {
		    //someone has altered the HTML and sent a different value!
			
			System.out.println("? bt was press");
			change_page = "login_page";
			from_page = "login_page";
			
			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			
			System.out.println("log change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");
		}
		
		

		/*String firstname = request.getParameter("firstname");// ("inside this") get by name
		String lastname = request.getParameter("lastname");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		
		
		request.setAttribute("firstname", firstname);
		request.setAttribute("lastname", lastname);
		request.setAttribute("username", username);
		request.setAttribute("password", password);
		request.setAttribute("confirm_password", confirm_password);*/
		
		/*change_page = "register_page";
		from_page = "login_page";
		request.getSession().setAttribute("change_page", change_page);
		request.getSession().setAttribute("from_page", from_page);
		
		System.out.println("log change_page: "+ change_page);
		
		response.sendRedirect("UI_Manager");*/
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("UI_Manager");       go to fn same as sent fn (sent by post go to post )
		//dispatcher.forward(request, response);
		
	}
	
	
	public void login_check(String username, String password)
	{}

}