
import model.Assignment;
import model.Student;

import java.math.BigDecimal;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Gradebook
 */
@WebServlet("/Gradebook")
public class Gradebook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gradebook() {
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
		switch(request.getParameter("action")) {
		case "add": GradebookDB.addGrade((Student)session.getAttribute("studentuser"), 
				(long)Integer.parseInt(request.getParameter("aid")), request.getParameter("atype"), 
				BigDecimal.valueOf(Integer.parseInt(request.getParameter("grade"))));
		break;
		case "update": GradebookDB.updateGrade((Student)session.getAttribute("studentuser"), 
				(long)Integer.parseInt(request.getParameter("aid")), request.getParameter("atype"), 
				BigDecimal.valueOf(Integer.parseInt(request.getParameter("grade"))));
			break;
		case "view": break;
		default: break;
		}
		List<Assignment> graded = GradebookDB.getGrades((Student)session.getAttribute("studentuser"));
		request.setAttribute("assignments", graded);
		request.getRequestDispatcher("view.jsp").forward(request, response);
	}

}
