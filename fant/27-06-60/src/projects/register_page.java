package projects;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class register_page
 */
@WebServlet("/register_page")
public class register_page extends HttpServlet {
	String change_page;
	String from_page;
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public register_page() {
        super();
		System.out.println("in register con ");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in register_page get");


		response.setContentType("text/html");


		RequestDispatcher dispatcher = request.getRequestDispatcher("register_ui.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in register_page post");

		response.setContentType("text/html");


		String firstname = request.getParameter("firstname");// ("inside this") get by name
		String lastname = request.getParameter("lastname");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");


		String bt = request.getParameter("bt");
		if (bt == null) {
		    //no button has been selected
			System.out.println("no bt was press");
			change_page = "register_page";
			from_page = "register_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("log change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");

		} else if (bt.equals("Register")) {
		    //Login button was pressed
			System.out.println("Register bt was press");
			change_page = "register_page";
			from_page = "register_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("log change_page: "+ change_page);
			System.out.println("register done");
			//go to get fn
			response.sendRedirect("register_page");

		} else if (bt.equals("Login")) {
		    //Register button was pressed
			System.out.println("Back bt was press");

			change_page = "login_page";
			from_page = "register_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("log change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
		} else {
		    //someone has altered the HTML and sent a different value!

			System.out.println("? bt was press");
			change_page = "register_page";
			from_page = "register_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("log change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");
		}
	}

}
