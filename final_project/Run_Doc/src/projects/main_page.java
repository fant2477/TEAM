package projects;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project_db.ConnectionDB;
import project_db.DocumentHeader;
import project_db.DocumentManager;
import project_db.Log;
import project_db.User;
import project_db.UserManager;
import project_db.UserValidation;
import project_db.View;

/**
 * Servlet implementation class main_page
 */
@WebServlet("/main_page")
public class main_page extends HttpServlet {
	String change_page;
	String from_page;
	String pg;
	String search_input;
	User current_user;

	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public main_page() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in main_page get");


		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		ConnectionDB.connect();

		System.out.println("before main current_user: "+ current_user);
		System.out.println("before pg: "+ pg);
		
		
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
			pg = (String) request.getSession().getAttribute("pg");
			search_input = (String) request.getSession().getAttribute("search_input");
			
			request.getSession(false).invalidate();
			response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
			 response.setHeader("Pragma","no-cache"); //HTTP 1.0 
			 response.setDateHeader ("Expires", 0); //prevents caching at the proxy server 
	
			System.out.println("main current_user: "+ current_user);
			System.out.println("pg: "+ pg);
			System.out.println("search_input: "+ search_input);
	
	
			if(pg==null || pg=="")
			{
				pg = "1";
				System.out.println("pg if == nul: "+ pg);
			}
	
			List<DocumentHeader> doclist;
			int max_rpg = 10;
	
			if(search_input!= null)
			{
				doclist = View.toListofDocHeader(Integer.valueOf(pg),max_rpg,search_input,"Doc_header_ID");
			}
			else{
				doclist = View.toListofDocHeader(Integer.valueOf(pg),max_rpg);
			}
			int page_total = View.getMaximumPageNo();
	
	
			if(Integer.valueOf(pg) > page_total)
			{
				pg= String.valueOf(page_total);
			}
	
	
			if(search_input!= null)
			{
				doclist = View.toListofDocHeader(Integer.valueOf(pg),max_rpg,search_input,"Doc_header_ID");
			}
			else{
				doclist = View.toListofDocHeader(Integer.valueOf(pg),max_rpg);
			}
	
	
			int start_pg = (Integer.valueOf(pg)*max_rpg)-(max_rpg-1);
	
			// go to fn same as sent fn (sent by post go to post )
			request.getSession().setAttribute("doclist", doclist);
			request.getSession().setAttribute("page_total", page_total);
			request.getSession().setAttribute("pg", pg);
			request.getSession().setAttribute("search_input", search_input);
			request.getSession().setAttribute("start_pg", start_pg);
	
			RequestDispatcher dispatcher = request.getRequestDispatcher("main_ui.jsp");
			dispatcher.forward(request, response);
			ConnectionDB.disconnect();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in main_page post");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		ConnectionDB.connect();
		
		DocumentManager doc = new DocumentManager(current_user);

		
		search_input = (String)request.getParameter("search_input");

		String pg_go = request.getParameter("pg_go");
		pg = request.getParameter("pg");
		String bt = request.getParameter("bt");
		System.out.println("pg: "+pg);
		System.out.println("bt: "+bt);
		if (pg_go != null) {
		    //no button has been selected
			System.out.println(pg_go +" pg_go  was press");

			request.getSession().setAttribute("current_user", current_user);
			request.getSession().setAttribute("pg", pg);
			request.getSession().setAttribute("search_input", search_input);
			request.getSession().setAttribute("from_page", "main_page");

			System.out.println("pg_page: "+ pg);
			//go to get fn
			response.sendRedirect("main_page");
			System.out.println("go to main_page again");


//
//		String bt = request.getParameter("bt");
//		if (bt == null) {
//		    //no button has been selected
//			System.out.println("no bt was press");
//			change_page = "main_page";
//			from_page = "main_page";
//
//			request.getSession().setAttribute("change_page", change_page);
//			request.getSession().setAttribute("from_page", from_page);
//			request.getSession().setAttribute("current_user", current_user);
//
//			System.out.println("change_page: "+ change_page);
//			//go to get fn
//			response.sendRedirect("UI_Manager");

		} else if (bt.equals("Add Page")) {
		    //Login button was pressed
			System.out.println("Add new document bt was press");
			change_page = "add_doc_page";
			from_page = "main_page";

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
			from_page = "main_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			request.getSession().setAttribute("current_user", current_user);

			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");

		} else if (bt.equals("History Page")) {
		    //Register button was pressed
			System.out.println("History bt was press");

			change_page = "history_page";
			from_page = "main_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			request.getSession().setAttribute("current_user", current_user);

			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");


		} else if (bt.equals("User_info")) {
			//Login button was pressed
			System.out.println("User_info was press");
			change_page = "user_info_page";
			from_page = "main_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			request.getSession().setAttribute("current_user", current_user);

			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");


		} else if (bt.equals("Log Out")) {
			//Login button was pressed
			System.out.println("logout was press");
			
			UserValidation.logout(doc);
			
			change_page = "login_page";
			from_page = "main_page";

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

			System.out.println("search_input"+search_input);

			if(search_input!= null)
			{
				request.getSession().setAttribute("current_user", current_user);
				request.getSession().setAttribute("pg", pg);
				request.getSession().setAttribute("search_input", search_input);
				request.getSession().setAttribute("from_page", "main_page");

				//go to get fn
//				response.sendRedirect("register_ui.jsp");
				response.sendRedirect("main_page");
				System.out.println("go to main_page again");
			}
			else{
				request.getSession().setAttribute("current_user", current_user);
				request.getSession().setAttribute("pg", pg);
				request.getSession().setAttribute("from_page", "main_page");
	
				//go to get fn
	//			response.sendRedirect("register_ui.jsp");
				response.sendRedirect("main_page");
				System.out.println("go to main_page again");}

		} else if (bt.equals("Delete")) {
			//Login button was pressed
			System.out.println("Delete was press");

			
			String[] head_cb =request.getParameterValues("head_cb");
			
			
			if(head_cb.length >=1)
			{
				String code_del = "";
				
				for (int i = 0; i < head_cb.length; i++) {
				    System.out.println("head_cb[i]: "+head_cb[i]);
				    if(i==0)
				    {
				    	code_del = head_cb[i];
				    }
				    else
				    {
				    	code_del = code_del+" , "+head_cb[i];
				    }
	
				    System.out.println("code_del: "+code_del);
				    
				    doc.setCurrentHeader(doc.getHeader(Integer.valueOf(head_cb[i])));
				    doc.deleteHeader(Integer.valueOf(head_cb[i]));
				}
	
	
				request.getSession().setAttribute("current_user", current_user);
				request.getSession().setAttribute("from_page", "main_page");
				PrintWriter out = response.getWriter();
	
				out.println("<script type=\"text/javascript\">");
				out.println("alert('your Document "+code_del+" was successfully delete.') " );
				out.println("location='main_page';");
				out.println("</script>");
	
				System.out.println("go to main_ui.jsp");
			}
			else
			{
			
				change_page = "main_page";
				from_page = "main_page";
	
				request.getSession().setAttribute("change_page", change_page);
				request.getSession().setAttribute("from_page", from_page);
				request.getSession().setAttribute("current_user", current_user);
	
				System.out.println("change_page: "+ change_page);
				//go to get fn
				response.sendRedirect("UI_Manager");
			}


		} else {
		    //someone has altered the HTML and sent a different value!

			System.out.println(bt+" bt was press");
			change_page = "header_page";
			from_page = "main_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			request.getSession().setAttribute("head_id", bt);
			request.getSession().setAttribute("current_user", current_user);

			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");
		}

		ConnectionDB.disconnect();
	}

}
