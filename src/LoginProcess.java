
import model.Student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class LoginProcess
 */
@WebServlet("/LoginProcess")
public class LoginProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginProcess() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String choice = "";
		if(request.getParameter("choice").equalsIgnoreCase("login")) {
			String first = "";
			if(!request.getParameter("firstname").isEmpty()) {
				first = request.getParameter("firstname");
			}
			String last = "";
			if(!request.getParameter("lastname").isEmpty()) {
				last = request.getParameter("lastname");
			}
			long studentID = 0;
			if(!request.getParameter("studentid").isEmpty()) {
				studentID = (long)Integer.parseInt(request.getParameter("studentid"));
			}
			if(GradebookDB.studentExists(first, last, studentID)) {
				Student studentUser = GradebookDB.findStudent(first, last, studentID);
				session.setAttribute("studentuser", studentUser);
				session.setAttribute("loggedin", true);
				choice = "choice.jsp";
			} else {
				session.setAttribute("loggedin", false);
				choice = "view.jsp";
				request.setAttribute("assignments", GradebookDB.getAllGrades());;
			}
		} else {
			session.setAttribute("loggedin", false);
			choice = "view.jsp";
			request.setAttribute("assignments", GradebookDB.getAllGrades());
		}

		request.getRequestDispatcher(choice).forward(request, response);
	}

	

}
