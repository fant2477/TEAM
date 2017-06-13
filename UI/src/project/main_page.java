package project;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class main_page
 */
@WebServlet("/main_page")
public class main_page extends HttpServlet {
	String change_page;
	String from_page;
	
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
		

		response.setContentType("text/html");

		// go to fn same as sent fn (sent by post go to post )
		RequestDispatcher dispatcher = request.getRequestDispatcher("main_ui.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in main_page post");
		
		response.setContentType("text/html");
		
		
		String bt = request.getParameter("bt");
		if (bt == null) {
		    //no button has been selected
			System.out.println("no bt was press");
			change_page = "main_page";
			from_page = "main_page";
			
			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			
			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");
			
		} else if (bt.equals("Add new document")) {
		    //Login button was pressed
			System.out.println("Add new document bt was press");
			change_page = "add_doc_page";
			from_page = "main_page";
			
			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			
			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");
			
		} else if (bt.equals("Delete document")) {
		    //Register button was pressed
			System.out.println("Delete document bt was press");
			
			change_page = "delete_doc_page";
			from_page = "main_page";
			
			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			
			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
		
		} else if (bt.equals("History")) {
		    //Register button was pressed
			System.out.println("History bt was press");
			
			change_page = "history_page";
			from_page = "main_page";
			
			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			
			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
		
			
		} else if (bt.equals("Log out")) {
		    //Register button was pressed
			System.out.println("Log out bt was press");
			
			change_page = "login_page";
			from_page = "main_page";
			
			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			
			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
			
		} else {
		    //someone has altered the HTML and sent a different value!
			
			System.out.println("? bt was press");
			change_page = "main_page";
			from_page = "main_page";
			
			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			
			System.out.println("change_page: "+ change_page);
			//go to get fn
			response.sendRedirect("UI_Manager");
		}
		
	}

}
