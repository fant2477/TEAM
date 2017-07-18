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
import project_db.DocumentDetail;
import project_db.DocumentHeader;
import project_db.DocumentManager;
import project_db.User;
import project_db.View;

/**
 * Servlet implementation class header_page
 */
@WebServlet("/header_page")
public class header_page extends HttpServlet {
	String change_page;
	String from_page;
	String head_id;
	String pg;
	String search_input;
	User current_user;

	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public header_page() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in header_page get");


		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		ConnectionDB.connect();

		if(current_user==null)
		{
		current_user = (User) request.getSession().getAttribute("current_user");
		}
		head_id= (String)( request.getSession().getAttribute("head_id"));
		pg = (String) request.getSession().getAttribute("pg");
		search_input = (String) request.getSession().getAttribute("search_input");
		request.getSession(false).invalidate();


		System.out.println("header_page current_user: "+ current_user);
		System.out.println("header_page head_id: "+ head_id);
		System.out.println("pg: "+ pg);
		System.out.println("search_input: "+ search_input);


		if(pg==null || pg=="")
		{
			pg = "1";
			System.out.println("pg if == nul: "+ pg);
		}

		List<DocumentDetail> doclist;
		int max_rpg = 5;

		if(search_input!= null)
		{
			doclist = View.toListofDocDetail(Integer.valueOf(pg) , max_rpg , Integer.valueOf(head_id) ,search_input , "Doc_ID");
		}
		else
		{
			doclist = View.toListofDocDetail(Integer.valueOf(pg) , max_rpg , Integer.valueOf(head_id) );
		}
		int page_total = View.getMaximumPageNo();


		if(Integer.valueOf(pg) > page_total)
		{
			pg= String.valueOf(page_total);
		}


		if(search_input!= null)
		{
			doclist = View.toListofDocDetail(Integer.valueOf(pg) , max_rpg , Integer.valueOf(head_id) ,search_input , "Doc_ID");
		}
		else
		{
			doclist = View.toListofDocDetail(Integer.valueOf(pg) , max_rpg, Integer.valueOf(head_id) );
		}

		int start_pg = (Integer.valueOf(pg)*max_rpg)-(max_rpg-1);

		request.getSession().setAttribute("doclist", doclist);
		request.getSession().setAttribute("page_total", page_total);
		request.getSession().setAttribute("pg", pg);
		request.getSession().setAttribute("head_id", head_id);
		request.getSession().setAttribute("search_input", search_input);
		request.getSession().setAttribute("start_pg", start_pg);

		RequestDispatcher dispatcher = request.getRequestDispatcher("header_ui.jsp");
		dispatcher.forward(request, response);
		ConnectionDB.disconnect();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in header_page post");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		ConnectionDB.connect();


		DocumentManager doc = new DocumentManager(current_user);

		doc.setCurrentHeader(doc.getHeader(Integer.valueOf(head_id)));


		String filee =request.getParameter("file-6[]");


		search_input = (String)request.getParameter("search_input");
		String pg_go = request.getParameter("pg_go");
		pg = request.getParameter("pg");
		String bt = request.getParameter("bt");

		if (pg_go != null) {
		    //no button has been selected
			System.out.println(pg+" pg was press");

			request.getSession().setAttribute("current_user", current_user);
			request.getSession().setAttribute("head_id", head_id);
			request.getSession().setAttribute("pg", pg);
			request.getSession().setAttribute("search_input", search_input);

			System.out.println("pg_page: "+ pg);
			//go to get fn
			response.sendRedirect("header_page");
			System.out.println("go to header_page again");

//		}else if (bt == null) {
//		    //no button has been selected
//			System.out.println("no bt was press");
//			change_page = "main_page";
//			from_page = "header_page";
//
//			request.getSession().setAttribute("change_page", change_page);
//			request.getSession().setAttribute("from_page", from_page);
//			request.getSession().setAttribute("current_user", current_user);
//
//			System.out.println("change_page: "+ change_page);
//			//go to get fn
//			response.sendRedirect("UI_Manager");


		} else if (bt.equals("Main Page")) {
			//Register button was pressed
			System.out.println("Main page was press");

			change_page = "main_page";
			from_page = "header_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			request.getSession().setAttribute("current_user", current_user);

			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");


		} else if (bt.equals("Add Page")) {
		    //Login button was pressed
			System.out.println("Add new document bt was press");
			change_page = "add_doc_page";
			from_page = "header_page";

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
			from_page = "header_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			request.getSession().setAttribute("current_user", current_user);

			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");

		} else if (bt.equals("History Page")) {
		    //Register button was pressed
			System.out.println("History bt was press");

			change_page = "history_page";
			from_page = "header_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			request.getSession().setAttribute("current_user", current_user);

			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");


		} else if (bt.equals("User_info")) {
			//Login button was pressed
			System.out.println("User_info was press");
			change_page = "user_info_page";
			from_page = "header_page";

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
			from_page = "header_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			request.getSession().setAttribute("current_user", current_user);

			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");

		} else if (bt.equals("search_bt")) {
			//Login button was pressed
			System.out.println("search_bt was press");


			System.out.println("search_input"+search_input);

			if(search_input!= null)
			{
				request.getSession().setAttribute("current_user", current_user);
				request.getSession().setAttribute("head_id", head_id);
				request.getSession().setAttribute("pg", pg);
				request.getSession().setAttribute("search_input", search_input);

				//go to get fn
//				response.sendRedirect("register_ui.jsp");
				response.sendRedirect("header_page");
				System.out.println("go to header_page again");
			}




		} else if (bt.equals("Add files")) {
			//Login button was pressed
			System.out.println("Add files was press");

			doc.createFile(filee);

			request.getSession().setAttribute("current_user", current_user);
			request.getSession().setAttribute("head_id", head_id);
			PrintWriter out = response.getWriter();

			out.println("<script type=\"text/javascript\">");
			out.println("alert('your file was successfully add. \\n\\nfile is " +filee+ ". \\n\\nIn your document id :" +head_id+ ".') " );
			out.println("location='header_page';");
			out.println("</script>");

			System.out.println("go to header_ui.jsp again");


		} else if (bt.equals("Delete Document")) {
			//Login button was pressed
			System.out.println("Delete Document was press");

			int head_del = Integer.valueOf(head_id);

			doc.deleteHeader(Integer.valueOf(head_id));

			request.getSession().setAttribute("current_user", current_user);
			PrintWriter out = response.getWriter();

			out.println("<script type=\"text/javascript\">");
			out.println("alert('your Document was successfully delete. \\n\\nFrom Document code: " +head_del+ ".') " );
			out.println("location='main_page';");
			out.println("</script>");

			System.out.println("go to main_ui.jsp");


		} else {
		    //someone has altered the HTML and sent a different value!

			System.out.println(bt+" bt was press");
			change_page = "detail_page";
			from_page = "header_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			request.getSession().setAttribute("doc_id", bt);
			request.getSession().setAttribute("current_user", current_user);

			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");

		}
		ConnectionDB.disconnect();

	}

}
