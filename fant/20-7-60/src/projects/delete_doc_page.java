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
import project_db.DocumentManager;
import project_db.User;

/**
 * Servlet implementation class delete_doc_page
 */
@WebServlet("/delete_doc_page")
public class delete_doc_page extends HttpServlet {
	String change_page;
	String from_page;
	User current_user;
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public delete_doc_page() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in delete_doc_page get");


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
	
			// go to fn same as sent fn (sent by post go to post )
			RequestDispatcher dispatcher = request.getRequestDispatcher("delete_doc_ui.jsp");
			dispatcher.forward(request, response);
			ConnectionDB.disconnect();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in delete_doc_page post");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		ConnectionDB.connect();
		DocumentManager doc = new DocumentManager(current_user);


		String bt = request.getParameter("bt");
		if (bt == null) {
		    //no button has been selected
			System.out.println("no bt was press");
			change_page = "delete_doc_page";
			from_page = "delete_doc_page";

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
			//go to get fn
			response.sendRedirect("UI_Manager");



		} else if (bt.equals("History Page")) {
		    //Login button was pressed
			System.out.println("Upload bt was press");
			change_page = "history_page";
			from_page = "add_doc_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			request.getSession().setAttribute("current_user", current_user);

			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");



		} else if (bt.equals("Add Page")) {
				//Login button was pressed
			System.out.println("adde page was press");
			change_page = "add_doc_page";
			from_page = "delete_doc_page";

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
			from_page = "add_doc_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			request.getSession().setAttribute("current_user", current_user);

			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");



		} else if (bt.equals("Delete")) {
		    //Login button was pressed
			System.out.println("Delete bt was press");


			String id_group = request.getParameter("id_group");
			String search_input = request.getParameter("search_input");


			System.out.println("search_input: "+search_input);

			PrintWriter out = response.getWriter();
			request.getSession().setAttribute("current_user", current_user);
			request.getSession().setAttribute("from_page", "delete_doc_page");
			request.getSession().setAttribute("change_page", "delete_doc_page");

			
			if(search_input==null || search_input=="")
			{
				System.out.println("change_page: "+ change_page);
				//go to get fn
				response.sendRedirect("UI_Manager");
			}
			else
			{
				int search_int = Integer.valueOf(search_input);
				System.out.println("search_int: "+search_int);
				if(id_group.equals("doc_code"))
				{
					
					doc.deleteHeader(search_int);
					out.println("<script type=\"text/javascript\">");
					out.println("alert('\\nYour Document ID : "+search_int+" was successfully delete.') " );
					out.println("location='delete_doc_page';");
					out.println("</script>");
				}
				else if(id_group.equals("file_id"))
				{
					doc.deleteFile(search_int);
					out.println("<script type=\"text/javascript\">");
					out.println("alert('\\nYour File ID : "+search_int+" was successfully delete.') " );
					out.println("location='delete_doc_page';");
					out.println("</script>");
				}
			}
			

		} else {
		    //someone has altered the HTML and sent a different value!

			System.out.println("? bt was press");
			change_page = "delete_doc_page";
			from_page = "delete_doc_page";

		}



		ConnectionDB.disconnect();

	}

}
