package projects;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project_db.ConnectionDB;
import project_db.UserManager;
import project_db.UserValidation;

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
		ConnectionDB.connect();
		

		String firstname = request.getParameter("firstname");// ("inside this") get by name
		String lastname = request.getParameter("lastname");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		String business_group = request.getParameter("business_group");


		String bt = request.getParameter("bt");
		if (bt == null) {
		    //no button has been selected
			System.out.println("no bt was press");
			change_page = "register_page";
			from_page = "register_page";
			ConnectionDB.disconnect();

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("log change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");

		} else if (bt.equals("Register")) {
		    //Login button was pressed
			System.out.println("Register bt was press");

			UserValidation user_va = new UserValidation();
			UserManager user = new UserManager();
			if(user_va.isValidAll(username,password,confirm_password,firstname,lastname)==true)
			{

				user.createNewUser(username,password,confirm_password,firstname,lastname,business_group);
				

				change_page = "login_page";
				from_page = "register_page";

				request.getSession().setAttribute("change_page", change_page);
				request.getSession().setAttribute("from_page", from_page);

				System.out.println("log change_page: "+ change_page);
				System.out.println("register done");
				//go to get fn
				response.sendRedirect("login_page");

			}
			else{

				String firstname_check = user_va.validName(firstname);
				String lastname_check = user_va.validName(lastname);
				String username_check = user_va.validUsername(username);
				String password_check = user_va.validPassword(password);
				String confirm_password_check = user_va.ValidConfirmpass(password,confirm_password);
				

				System.out.println("business_group: "+business_group);
				request.setAttribute("firstname_check",firstname_check);// ("inside this") get by name
				request.setAttribute("lastname_check",lastname_check);

				request.setAttribute("username_check",username_check);
				request.setAttribute("password_check",password_check);
				request.setAttribute("confirm_password_check",confirm_password_check);



				request.setAttribute("firstname",firstname);// ("inside this") get by name
				request.setAttribute("lastname",lastname);

				request.setAttribute("username",username);
				request.setAttribute("password",password);
				request.setAttribute("confirm_password",confirm_password);
				request.setAttribute("business_group",business_group);



				//go to get fn
//				response.sendRedirect("register_ui.jsp");
				request.getRequestDispatcher("register_ui.jsp").forward(request, response);
				System.out.println("go to register.jsp again");}
			ConnectionDB.disconnect();

		} else if (bt.equals("Login")) {
		    //Register button was pressed
			System.out.println("Back bt was press");

			change_page = "login_page";
			from_page = "register_page";
			ConnectionDB.disconnect();
			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("log change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
		} else {
		    //someone has altered the HTML and sent a different value!
			ConnectionDB.disconnect();
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
