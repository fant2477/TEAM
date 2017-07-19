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
import project_db.DocumentHeader;
import project_db.DocumentManager;
import project_db.User;

/**
 * Servlet implementation class add_doc_page
 */
@WebServlet("/add_doc_page")
public class add_doc_page extends HttpServlet {
	String change_page;
	String from_page = "";
	User current_user;
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public add_doc_page() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in add_doc_page get");

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
			RequestDispatcher dispatcher = request.getRequestDispatcher("add_doc_ui.jsp");
			dispatcher.forward(request, response);
			ConnectionDB.disconnect();
		}
		System.out.println("from_page: "+request.getSession().getAttribute("from_page"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in add_doc_page post");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		ConnectionDB.connect();


		String subject = request.getParameter("subject");// ("inside this") get by name
		String tag = request.getParameter("tag");
		String descriptions = request.getParameter("descriptions");
		String[] filee =request.getParameterValues("file-6[]");



		String bt = request.getParameter("bt");
		if (bt == null) {
		    //no button has been selected
			System.out.println("no bt was press");
			change_page = "add_doc_page";
			from_page = "add_doc_page";

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

		} else if (bt.equals("Delete Page")) {
				//Login button was pressed
			System.out.println("delete page was press");
			change_page = "delete_doc_page";
			from_page = "add_doc_page";

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


		} else if (bt.equals("Save")) {
		    //Register button was pressed
			System.out.println("Save bt was press");
			DocumentManager doc = new DocumentManager(current_user);


			//filee = filee.replace('\\', '/');
			System.out.println("subject : "+subject);
			System.out.println("tag : "+tag);
			System.out.println("descriptions : "+descriptions);
			System.out.println("filee : "+filee);


			DocumentHeader doc_head = doc.createHeader(subject,descriptions);
			doc.setCurrentHeader(doc_head);
			
			String name_file ="";
			for (int i = 0; i < filee.length; i++) {
			    System.out.println("filee: "+filee[i]);
			    if(i==0)
			    {
			    	name_file = filee[i];
			    }
			    else
			    {
			    	name_file = name_file+" , "+filee[i];
			    }

			    System.out.println("name_file: "+name_file);

			    doc.createFile(filee[i]);
			}
			


			int head_id = doc_head.getDoc_header_ID();
//			System.out.println("header_id : "+header_id);
//			request.getSession().setAttribute("head_id", String.valueOf(header_id));
			request.getSession().setAttribute("current_user", current_user);
//
			PrintWriter out = response.getWriter();

			out.println("<script type=\"text/javascript\">");
			if(filee.length>1)
			{
				out.println("alert('your files was successfully add. \\n\\nfiles are " +name_file+ ". \\n\\nIn your document id :" +head_id+ ".') " );
			}
			else{
			out.println("alert('your file was successfully add. \\n\\nfile is " +name_file+ ". \\n\\nIn your document id :" +head_id+ ".') " );
			}
			out.println("location='add_doc_page';");
			out.println("</script>");


//			request.getRequestDispatcher("add_doc_ui.jsp").forward(request, response);
			System.out.println("go to add_doc_ui.jsp again");




		} else {
		    //someone has altered the HTML and sent a different value!

			System.out.println("? bt was press");
			change_page = "add_doc_page";
			from_page = "add_doc_page";

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
