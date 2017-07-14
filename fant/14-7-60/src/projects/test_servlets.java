package projects;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class test_servlets
 */

@WebServlet("/test_servlets")
public class test_servlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(test_servlets.class);

	public test_servlets() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in get");

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("z-bootstrap_test.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(
						new DiskFileItemFactory()).parseRequest(request);
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File("C:\\Users\\User\\Desktop\\testdownload\\"+item.getName()).getName();
						System.out.print("name: "+name);
					} else {
						if (item.getFieldName().equalsIgnoreCase("text1")) {
							String name = item.getFieldName();
							String value = item.getString();
							System.out.println("Name= {} Values= {}"+name+"   "+ value);
						}
					}
				}
				request.setAttribute("message", "File Uploaded Successfully");
			} catch (Exception ex) {
				request.setAttribute("message", "File Upload Failed due to "
						+ ex);
			}

		} else {
			request.setAttribute("message",
					"Sorry this Servlet only handles file upload request");
		}

		request.getRequestDispatcher("z-bootstrap_test.jsp").forward(request,
				response);

	}

}
