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

/**
 * Servlet implementation class header_page
 */
@WebServlet("/header_page")
public class header_page extends HttpServlet {
	String change_page;
	String from_page;
	int head_id;
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


		response.setContentType("text/html");
		ConnectionDB.connect();
		current_user = (User) request.getSession().getAttribute("current_user");
		head_id = Integer.valueOf((String)( request.getSession().getAttribute("head_id")));
		System.out.println("header_page current_user: "+ current_user);
		System.out.println("header_page head_id: "+ head_id);


		List<DocumentDetail> doclist = DocumentDetail.toListofDocDetail(head_id);

		request.getSession().setAttribute("doclist", doclist);

		RequestDispatcher dispatcher = request.getRequestDispatcher("header_ui.jsp");
		dispatcher.forward(request, response);
		ConnectionDB.disconnect();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in header_page post");

		response.setContentType("text/html");
		ConnectionDB.connect();


		String filee =request.getParameter("file-6[]");


		String bt = request.getParameter("bt");
		if (bt == null) {
		    //no button has been selected
			System.out.println("no bt was press");
			change_page = "main_page";
			from_page = "header_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");
			ConnectionDB.disconnect();
			
			
		} else if (bt.equals("Main Page")) {
			//Register button was pressed
			System.out.println("Main page was press");

			change_page = "main_page";
			from_page = "header_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
			ConnectionDB.disconnect();
			

		} else if (bt.equals("Add Page")) {
		    //Login button was pressed
			System.out.println("Add new document bt was press");
			change_page = "add_doc_page";
			from_page = "header_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");
			ConnectionDB.disconnect();

		} else if (bt.equals("Delete Page")) {
		    //Register button was pressed
			System.out.println("Delete document bt was press");

			change_page = "delete_doc_page";
			from_page = "header_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
			ConnectionDB.disconnect();

		} else if (bt.equals("History Page")) {
		    //Register button was pressed
			System.out.println("History bt was press");

			change_page = "history_page";
			from_page = "header_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
			ConnectionDB.disconnect();


		} else if (bt.equals("User_info")) {
			//Login button was pressed
			System.out.println("User_info was press");
			change_page = "user_info_page";
			from_page = "header_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");
			ConnectionDB.disconnect();


		} else if (bt.equals("Log Out")) {
			//Login button was pressed
			System.out.println("logout was press");
			change_page = "login_page";
			from_page = "header_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");
			ConnectionDB.disconnect();

		} else if (bt.equals("search_bt")) {
			//Login button was pressed
			System.out.println("search_bt was press");


//			List<String[]> lg = Log.getLog();
//
//			request.getSession().setAttribute("lg", lg);


			//go to get fn
//			response.sendRedirect("register_ui.jsp");
			request.getRequestDispatcher("main_ui.jsp").forward(request, response);
			System.out.println("go to main.jsp again");
			ConnectionDB.disconnect();


		} else if (bt.equals("Add files")) {
			//Login button was pressed
			System.out.println("Add files was press");
			DocumentManager doc = new DocumentManager(current_user);

			doc.setCurrentHeader(doc.getHeader(head_id));

			doc.createFile(filee);


			PrintWriter out = response.getWriter();

			out.println("<script type=\"text/javascript\">");
			out.println("alert('your file was successfully add. \\n\\nfile is " +filee+ ". \\n\\nIn your document id :" +head_id+ ".') " );
			out.println("location='header_page';");
			out.println("</script>");



			System.out.println("go to header_ui.jsp again");
			ConnectionDB.disconnect();



		} else {
		    //someone has altered the HTML and sent a different value!

			System.out.println(bt+" bt was press");
			change_page = "detail_page";
			from_page = "header_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			request.getSession().setAttribute("doc_id", bt);

			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");
			ConnectionDB.disconnect();
		}


	}

}
