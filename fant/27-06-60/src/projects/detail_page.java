package projects;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class detail_page
 */
@WebServlet("/detail_page")
public class detail_page extends HttpServlet {
	String change_page;
	String from_page;
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

		// go to fn same as sent fn (sent by post go to post )
		RequestDispatcher dispatcher = request.getRequestDispatcher("detail_ui.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in detail_page post");
		
		response.setContentType("text/html");
		
		
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
			
		} else if (bt.equals("Save")) {
		    //Register button was pressed
			System.out.println("Save bt was press");
			
			change_page = "detail_page";
			from_page = "detail_page";
			
			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			
			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
			
			
		} else if (bt.equals("Delete")) {
		    //Register button was pressed
			System.out.println("Delete bt was press");
			
			change_page = "detail_page";
			from_page = "detail_page";
			
			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			
			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
			
	
		} else if (bt.equals("Back")) {
		    //Register button was pressed
			System.out.println("Back bt was press");
			
			change_page = "main_page";
			from_page = "detail_page";
			
			request.getSession().setAttribute("change_page", change_page);
			request.getSession().setAttribute("from_page", from_page);
			
			System.out.println("change_page: "+ change_page);
			response.sendRedirect("UI_Manager");
			
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
		}
		
	}

}
