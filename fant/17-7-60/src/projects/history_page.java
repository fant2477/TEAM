package projects;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project_db.ConnectionDB;
import project_db.DocumentHeader;
import project_db.Log;
import project_db.User;
import project_db.View;

/**
 * Servlet implementation class history_page
 */
@WebServlet("/history_page")
public class history_page extends HttpServlet {
	String change_page;
	String from_page;
	String pg;
	String search_input;
	User current_user;
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public history_page() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in history_page get");


		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		
		System.out.println("before main current_user: "+ current_user);
		System.out.println("before pg: "+ pg);
		
		if(current_user==null)
		{
		current_user = (User) request.getSession().getAttribute("current_user");
		}

		pg = (String) request.getSession().getAttribute("pg");
		search_input = (String) request.getSession().getAttribute("search_input");
		request.getSession(false).invalidate();
		
		System.out.println("main current_user: "+ current_user);
		System.out.println("pg: "+ pg);
		System.out.println("search_input get: "+ search_input);
		

		ConnectionDB.connect();

		if(pg==null || pg=="")
		{
			pg = "1";
			System.out.println("pg if == nul: "+ pg);
		}

		List<String[]> lg;
		
		

		if(search_input!= null)
		{
			lg = Log.getLog(Integer.valueOf(pg),25,search_input,"Time");
		}
		else{
			lg = Log.getLog(Integer.valueOf(pg),25);
		}
		int page_total = Log.getMaximumPageNo();


		if(Integer.valueOf(pg) > page_total)
		{
			pg= String.valueOf(page_total);
		}


		if(search_input!= null)
		{
			lg = Log.getLog(Integer.valueOf(pg),25,search_input,"Time");
		}
		else{
			lg = Log.getLog(Integer.valueOf(pg),25);
		}
		
		
		int start_pg = (Integer.valueOf(pg)*25)-(25-1);
		System.out.println("start_pg"+start_pg);
		// go to fn same as sent fn (sent by post go to post )
		request.getSession().setAttribute("lg", lg);
		request.getSession().setAttribute("page_total", page_total);
		request.getSession().setAttribute("pg", pg);
		request.getSession().setAttribute("search_input", search_input);





		// go to fn same as sent fn (sent by post go to post )
		RequestDispatcher dispatcher = request.getRequestDispatcher("history_ui.jsp");
		dispatcher.forward(request, response);
		ConnectionDB.disconnect();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in history_page post");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		ConnectionDB.connect();

		
		search_input = (String)request.getParameter("search_input");

		String pg_go = request.getParameter("pg_go");
		pg = request.getParameter("pg");
		String bt = request.getParameter("bt");
		
		System.out.println("search_input: "+search_input);
		System.out.println("pg: "+pg);
		System.out.println("bt: "+bt);
		if (pg_go != null) {
		    //no button has been selected
			System.out.println("no bt was press");

			request.getSession().setAttribute("current_user", current_user);
			request.getSession().setAttribute("pg", pg);
			request.getSession().setAttribute("search_input", search_input);

			System.out.println("pg_page: "+ pg);
			//go to get fn
			response.sendRedirect("history_page");
			System.out.println("go to history_page again");



		} else if (bt.equals("Main Page")) {
		    //Register button was pressed
			System.out.println("Back bt was press");

			change_page = "main_page";
			from_page = "history_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			request.getSession().setAttribute("current_user", current_user);

			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");


		} else if (bt.equals("Add Page")) {
				//Register button was pressed
			System.out.println("Add page was press");

			change_page = "add_doc_page";
			from_page = "history_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			request.getSession().setAttribute("current_user", current_user);

			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");


		} else if (bt.equals("Delete Page")) {
				//Register button was pressed
			System.out.println("Delete Page was press");

			change_page = "delete_doc_page";
			from_page = "history_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			request.getSession().setAttribute("current_user", current_user);

			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");



		} else if (bt.equals("User_info")) {
			//Login button was pressed
			System.out.println("User_info was press");
			change_page = "user_info_page";
			from_page = "history_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			request.getSession().setAttribute("current_user", current_user);

			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");



		} else if (bt.equals("Log Out")) {
			//Login button was pressed
			System.out.println("logout was press");
			change_page = "login_page";
			from_page = "history_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			request.getSession().setAttribute("current_user", current_user);

			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");


		} else if (bt.equals("search_bt")) {
			//Login button was pressed
			System.out.println("search_bt was press");


//			List<String[]> lg = Log.getLog();
//
//			request.getSession().setAttribute("lg", lg);
			System.out.println("search_input search_bt: "+search_input);

			if(search_input!= null)
			{
//				List<String[]> lg = Log.getLog(1,1000,search_input,"Time");
//				request.getSession().setAttribute("lg", lg);
//				System.out.println("search input!=null: " +search_input);
//				System.out.println("search doclist " +lg);
				//go to get fn
//				response.sendRedirect("register_ui.jsp");
				
				
				request.getSession().setAttribute("current_user", current_user);
				request.getSession().setAttribute("pg", pg);
				request.getSession().setAttribute("search_input", search_input);


				response.sendRedirect("history_page");
				System.out.println("go to history_page again");


			}

		} else {
		    //someone has altered the HTML and sent a different value!

			System.out.println("? bt was press");
			change_page = "history_page";
			from_page = "history_page";

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
