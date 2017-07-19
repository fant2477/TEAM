package projects;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project_db.ConnectionDB;
import project_db.User;
import project_db.UserManager;
import project_db.UserValidation;

/**
 * Servlet implementation class user_info_page
 */
@WebServlet("/user_info_page")
public class user_info_page extends HttpServlet {
	String change_page;
	String from_page;
	User current_user;
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public user_info_page() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in user_info_page get");


		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		ConnectionDB.connect();
		
		
		if(request.getSession().getAttribute("from_page")==null ||request.getSession().getAttribute("from_page")=="")
		{
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("login_ui.jsp");
			dispatcher.forward(request, response);
			ConnectionDB.disconnect();
		}
			else{
			if((User) request.getSession().getAttribute("current_user")!=null)
			{
			current_user = (User) request.getSession().getAttribute("current_user");
			}
			request.getSession(false).invalidate();
	
	
			System.out.println("current_user: "+current_user);
	
	
			String firstname = current_user.getName();
			String lastname = current_user.getSurname();
			String username = current_user.getUsername();
			String password = current_user.getPassword();
			String business_group = current_user.getBusinessGroup();
	
	//
	//		String firstname_check = (String)request.getSession().getAttribute("firstname_check");
	//		String lastname_check = (String)request.getSession().getAttribute("lastname_check");
	//		String username_check = (String)request.getSession().getAttribute("username_check");
	//		String password_check = (String)request.getSession().getAttribute("password_check");
	
	
	
	
			request.setAttribute("firstname",firstname);
			request.setAttribute("lastname",lastname);
			request.setAttribute("username",username);
			request.setAttribute("password",password);
			request.setAttribute("business_group",business_group);
	
	//
	//		request.setAttribute("firstname_check",firstname_check);
	//		request.setAttribute("lastname_check",lastname_check);
	//		request.setAttribute("username_check",username_check);
	//		request.setAttribute("password_check",password_check);
	
	
	
	
	
			System.out.println("firstname: "+firstname);
			System.out.println("lastname: "+lastname);
			System.out.println("username: "+username);
			System.out.println("password: "+password);
			System.out.println("business_group: "+business_group);
	
			// go to fn same as sent fn (sent by post go to post )
			RequestDispatcher dispatcher = request.getRequestDispatcher("user_info_ui.jsp");
			dispatcher.forward(request, response);
			ConnectionDB.disconnect();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				System.out.println("in user_info_page post");

				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");

				ConnectionDB.connect();


				String bt = request.getParameter("bt");
				if (bt == null) {
				    //no button has been selected
					System.out.println("no bt was press");
					change_page = "user_info_page";
					from_page = "user_info_page";

					request.getSession().setAttribute("change_page", change_page);
					request.getSession().setAttribute("from_page", from_page);
					request.getSession().setAttribute("current_user", current_user);

					System.out.println("change_page: "+ change_page);
					//go to get fn
					response.sendRedirect("UI_Manager");

				} else if (bt.equals("Main Page")) {
					//Register button was pressed
					System.out.println("Main page was press");

					change_page = "main_page";
					from_page = "add_doc_page";

					request.getSession().setAttribute("change_page", change_page);
					request.getSession().setAttribute("from_page", from_page);
					request.getSession().setAttribute("current_user", current_user);

					System.out.println("change_page: "+ change_page);
					response.sendRedirect("UI_Manager");


				} else if (bt.equals("Add Page")) {
				    //Login button was pressed
					System.out.println("Add new document bt was press");
					change_page = "add_doc_page";
					from_page = "user_info_page";

					request.getSession().setAttribute("change_page", change_page);
					request.getSession().setAttribute("from_page", from_page);
					request.getSession().setAttribute("current_user", current_user);

					System.out.println("change_page: "+ change_page);
					//go to get fn
					response.sendRedirect("UI_Manager");

				} else if (bt.equals("Delete Page")) {
				    //Register button was pressed
					System.out.println("Delete document bt was press");

					change_page = "delete_doc_page";
					from_page = "user_info_page";

					request.getSession().setAttribute("change_page", change_page);
					request.getSession().setAttribute("from_page", from_page);
					request.getSession().setAttribute("current_user", current_user);

					System.out.println("change_page: "+ change_page);
					response.sendRedirect("UI_Manager");

				} else if (bt.equals("History Page")) {
				    //Register button was pressed
					System.out.println("History bt was press");

					change_page = "history_page";
					from_page = "user_info_page";

					request.getSession().setAttribute("change_page", change_page);
					request.getSession().setAttribute("from_page", from_page);
					request.getSession().setAttribute("current_user", current_user);

					System.out.println("change_page: "+ change_page);
					response.sendRedirect("UI_Manager");


				} else if (bt.equals("Log Out")) {
					//Login button was pressed
					System.out.println("logout was press");
					change_page = "login_page";
					from_page = "user_info_page";

					request.getSession().setAttribute("change_page", change_page);
					request.getSession().setAttribute("from_page", from_page);
					request.getSession().setAttribute("current_user", current_user);

					System.out.println("change_page: "+ change_page);
					//go to get fn
					response.sendRedirect("UI_Manager");


				} else if (bt.equals("My History")) {
					//Login button was pressed
					System.out.println("My History was press");
					change_page = "user_history_page";
					from_page = "user_info_page";

					request.getSession().setAttribute("change_page", change_page);
					request.getSession().setAttribute("from_page", from_page);
					request.getSession().setAttribute("current_user", current_user);

					System.out.println("change_page: "+ change_page);
					//go to get fn
					response.sendRedirect("UI_Manager");


				} else if (bt.equals("Save")) {
					//Login button was pressed
					System.out.println("Save was press");

					UserValidation user_va = new UserValidation();
					UserManager user = new UserManager();

					String firstname = request.getParameter("firstname");
					String lastname = request.getParameter("lastname");
//					String username = request.getParameter("username");
					String password = request.getParameter("password");
					String business_group = current_user.getBusinessGroup();
					String current_username = current_user.getUsername();
					if(user_va.isUserValidAll(password,firstname,lastname)==true)
//						if(user_va.isUserValidAll(username,password,firstname,lastname,current_username)==true)
					{


						//String business_group = request.getParameter("business_group");

						current_user.setName(firstname);
						current_user.setSurname(lastname);
//						current_user.setUsername(username);
						current_user.setPassword(password);

						UserManager.updateUser(current_user);
						request.getSession().setAttribute("current_user", current_user);



						PrintWriter out = response.getWriter();

						out.println("<script type=\"text/javascript\">");
						out.println("alert('Your profile was sucessfully update. \\n\\nYour username is "+current_username+", Password is "+password+" ') " );
						out.println("location='user_info_page';");
						out.println("</script>");
					}
					else
					{
//						System.out.print("username: "+username);
						System.out.print(" current_username: "+current_username);
						String firstname_check = user_va.validName(firstname);
						String lastname_check = user_va.validName(lastname);
//						String username_check = user_va.UserValidUsername(username,current_username);
						String password_check = user_va.validPassword(password);
//						System.out.print(" username_check: "+username_check);

						request.setAttribute("firstname_check",firstname_check);// ("inside this") get by name
						request.setAttribute("lastname_check",lastname_check);

//						request.setAttribute("username_check",username_check);
						request.setAttribute("password_check",password_check);



						request.setAttribute("firstname",firstname);// ("inside this") get by name
						request.setAttribute("lastname",lastname);

						request.setAttribute("username",current_username);
						request.setAttribute("password",password);
						request.setAttribute("business_group",business_group);


						request.getRequestDispatcher("user_info_ui.jsp").forward(request, response);

					}




				} else {
				    //someone has altered the HTML and sent a different value!

					System.out.println("? bt was press");
					change_page = "user_info_page";
					from_page = "user_info_page";

					request.getSession().setAttribute("change_page", change_page);
					request.getSession().setAttribute("from_page", from_page);
					request.getSession().setAttribute("current_user", current_user);

					System.out.println("change_page: "+ change_page);
					//go to get fn
					response.sendRedirect("UI_Manager");
				}

				ConnectionDB.disconnect();
	}

}
