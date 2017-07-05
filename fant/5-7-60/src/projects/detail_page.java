package projects;

import java.io.IOException;

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
import project_db.Time;
import project_db.User;
import project_db.UserManager;

/**
 * Servlet implementation class detail_page
 */
@WebServlet("/detail_page")
public class detail_page extends HttpServlet {
	String change_page;
	String from_page;
	String doc_id;
	int head_id;
	User current_user;
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public detail_page() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in detail_page get");


		response.setContentType("text/html");
		current_user = (User) request.getSession().getAttribute("current_user");
		doc_id = (String) request.getSession().getAttribute("doc_id");
		
		System.out.println("file id: "+doc_id);
		

		ConnectionDB.connect();
		DocumentManager doc = new DocumentManager(current_user);

		
		
		
		DocumentDetail detail = doc.getGenneralFile(Integer.valueOf(doc_id));
		
		head_id = detail.getDoc_header_ID();
		
		DocumentHeader header = doc.getHeader(head_id);
		
		
		String head_name = header.getDoc_header_subject();
		String descriptions = header.getDoc_header_description();
		

		request.getSession().setAttribute("file_name", detail.getDoc_name());
		request.getSession().setAttribute("file_id", detail.getDoc_ID());
		request.getSession().setAttribute("file_size", detail.getSizetoString());
		
		request.getSession().setAttribute("head_name", head_name);  ///////////////////
		request.getSession().setAttribute("head_id", head_id);
		request.getSession().setAttribute("add_date", Time.datetoReadableString(detail.getDate_created()));
		request.getSession().setAttribute("add_by", UserManager.getUsername(detail.getUser_ID_created()));
		request.getSession().setAttribute("last_edit", Time.datetoReadableString(detail.getDate_modified()));
		request.getSession().setAttribute("last_edit_by", UserManager.getUsername(detail.getUser_ID_modified()));
		request.getSession().setAttribute("descriptions", descriptions);  ///////////////////
		
        System.out.println("head_id: "+head_id);
        
        
        
		
		
		// go to fn same as sent fn (sent by post go to post )
		RequestDispatcher dispatcher = request.getRequestDispatcher("detail_ui.jsp");
		dispatcher.forward(request, response);
		ConnectionDB.disconnect();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in detail_page post");

		response.setContentType("text/html");
		ConnectionDB.connect();
		DocumentManager doc = new DocumentManager(current_user);
		DocumentDetail detail = doc.getGenneralFile(Integer.valueOf(doc_id));
		DocumentHeader header = doc.getHeader(head_id);
		
		

//		String file_name = detail.getDoc_name();
//		int file_id = detail.getDoc_ID();
//		String file_size = detail.getSizetoString();
//		
//		String head_name = header.getDoc_header_subject();;
//		String add_date = Time.datetoReadableString(detail.getDate_created());
//		String add_by = UserManager.getUsername(detail.getUser_ID_created());
//		String last_edit = Time.datetoReadableString(detail.getDate_modified());
//		String last_edit_by = UserManager.getUsername(detail.getUser_ID_modified());
//		String descriptions = header.getDoc_header_description();
//		
//		
		


		String bt = request.getParameter("bt");
		if (bt == null) {
		    //no button has been selected
			System.out.println("no bt was press");
			change_page = "detail_page";
			from_page = "detail_page";

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
			from_page = "add_doc_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
			ConnectionDB.disconnect();


		} else if (bt.equals("Add Page")) {
		    //Login button was pressed
			System.out.println("Add new document bt was press");
			change_page = "add_doc_page";
			from_page = "user_info_page";

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
			from_page = "user_info_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
			ConnectionDB.disconnect();

		} else if (bt.equals("History Page")) {
		    //Register button was pressed
			System.out.println("History bt was press");

			change_page = "history_page";
			from_page = "user_info_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
			ConnectionDB.disconnect();


		} else if (bt.equals("User_info")) {
			//Login button was pressed
			System.out.println("User_info was press");
			change_page = "user_info_page";
			from_page = "history_page";

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
			from_page = "user_info_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");
			ConnectionDB.disconnect();

			
			
		} else if (bt.equals("Back")) {
		    //Register button was pressed
			System.out.println("Back bt was press");

			
			
	        System.out.println("head_id: "+head_id);
//	        System.out.print(detail.getDoc_ID());
//	        System.out.print('\t');
//	        System.out.print(detail.getDoc_header_ID());
//	        System.out.print('\t');
//	        System.out.print(detail.getDoc_name());
//	        System.out.print('\t');
//	        System.out.print(Time.datetoReadableString(detail.getDate_created()));
//	        System.out.print('\t');
//	        System.out.print(Time.datetoReadableString(detail.getDate_modified()));
//	        System.out.print('\t');
//	        System.out.print(UserManager.getUsername(detail.getUser_ID_created()));
//	        System.out.print('\t');
//	        System.out.print(UserManager.getUsername(detail.getUser_ID_modified()));
//	        System.out.print('\t');
//	        System.out.print(detail.getSizetoString());
	        
	        
			
			String head_str = String.valueOf(head_id);
			
			
			change_page = "header_page";
			from_page = "detail_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			request.getSession().setAttribute("head_id", head_str);

			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
			ConnectionDB.disconnect();
			
			
		} else if (bt.equals("Save")) {
		    //Register button was pressed
			System.out.println("Save bt was press");

			change_page = "detail_page";
			from_page = "detail_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
			ConnectionDB.disconnect();


		} else if (bt.equals("Delete")) {
		    //Register button was pressed
			System.out.println("Delete bt was press");

			change_page = "detail_page";
			from_page = "detail_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
			ConnectionDB.disconnect();


		} else if (bt.equals("Download")) {
		    //Register button was pressed
			System.out.println("Download bt was press");

			change_page = "detail_page";
			from_page = "detail_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
			ConnectionDB.disconnect();

		} else {
		    //someone has altered the HTML and sent a different value!

			System.out.println("? bt was press");
			change_page = "detail_page";
			from_page = "detail_page";

			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);

			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");
			ConnectionDB.disconnect();
		}

	}

}
