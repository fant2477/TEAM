package projects;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project_db.ConnectionDB;
import project_db.User;

/**
 * Servlet implementation class UI_Manager
 */
@WebServlet("/UI_Manager")
public class UI_Manager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User current_user;
	String current_page;
	String change_page;
	String head_id;
	String from_page = null;
	String doc_id;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UI_Manager() {
//    	current_user = "fant";
		current_page = "login_page";
		change_page = "login_page";


		System.out.println("in ui con");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		change_page = (String) request.getSession().getAttribute("change_page");
		from_page = (String) request.getSession().getAttribute("from_page");
		current_user = (User) request.getSession().getAttribute("current_user");
		head_id = (String) request.getSession().getAttribute("head_id");
		doc_id = (String) request.getSession().getAttribute("doc_id");
		System.out.println("UI current_user: "+ current_user);
		request.getSession(false).invalidate();

		System.out.println("ui change_page2: "+change_page);
		String sstate ="";
		if(from_page=="login_page")
		{
		System.out.println("i get from_page: "+from_page);
		}


		if(change_page == null)
		{change_page = "login_page";
		from_page = "firsttime";
//		ConnectionDB.disconnect();
		}


		System.out.println("in ui get");
		System.out.println("change_page = "+ change_page);



		if(change_page == "login_page")
		{
			current_page = "login_page";
			System.out.println("change = login");

			response.sendRedirect("login_page");
			//RequestDispatcher dispatcher = request.getRequestDispatcher("login_page");
			//dispatcher.forward(request, response);
		}


		else if(change_page == "register_page")
		{
			System.out.println("change = register");
			current_page = "register_page";

			response.sendRedirect("register_page");
		}


		else if(change_page == "main_page")
		{
			System.out.println("change = main");
			current_page = "main_page";

			request.getSession().setAttribute("current_user", current_user);
			response.sendRedirect("main_page");
		}


		else if(change_page == "add_doc_page")
		{
			System.out.println("change = add_doc");
			current_page = "add_doc_page";

			request.getSession().setAttribute("current_user", current_user);
			response.sendRedirect("add_doc_page");
		}


		else if(change_page == "delete_doc_page")
		{
			System.out.println("change = delete_doc");
			current_page = "delete_doc_page";

			request.getSession().setAttribute("current_user", current_user);
			response.sendRedirect("delete_doc_page");
		}

		else if(change_page == "history_page")
		{
			System.out.println("change = history");
			current_page = "history_page";

			request.getSession().setAttribute("current_user", current_user);
			response.sendRedirect("history_page");
		}


		else if(change_page == "header_page")
		{
			System.out.println("change = header");
			current_page = "header_page";

			request.getSession().setAttribute("current_user", current_user);
			request.getSession().setAttribute("head_id", head_id);
			response.sendRedirect("header_page");
		}


		else if(change_page == "detail_page")
		{
			System.out.println("change = detail");
			current_page = "detail_page";

			request.getSession().setAttribute("current_user", current_user);
			request.getSession().setAttribute("doc_id", doc_id);
			response.sendRedirect("detail_page");
		}

		else if(change_page == "user_info_page")
		{
			System.out.println("change = user_info");
			current_page = "user_info_page";

			request.getSession().setAttribute("current_user", current_user);
			response.sendRedirect("user_info_page");
		}

		else if(change_page == "user_history_page")
		{
			System.out.println("change = user_history");
			current_page = "user_history_page";

			request.getSession().setAttribute("current_user", current_user);
			response.sendRedirect("user_history_page");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in ui post");



	}


}
