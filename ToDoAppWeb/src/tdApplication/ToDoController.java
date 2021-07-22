package tdApplication;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class ToDoController extends HttpServlet {

	private static final long serialVersionUID = 1;
	private ToDoDao tdDAO;
	
	public void init()
	{
		tdDAO = new TDDaoImplement();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		String action = request.getServletPath();
		
		try
		{
			switch (action)
			{
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertToDo(request, response);
				break;
			case "/delete":
				deleteToDo(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateToDo(request, response);
				break;
			case "/list":
				listToDo(request, response);
				break;
			default:
				RequestDispatcher dispatcher = request.getRequestDispatcher("todo-list.jsp");
				dispatcher.forward(request,  response);
				break;
			}
		}
		catch (SQLException e)
		{
			throw new ServletException(e);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		doGet(request, response);
	}
	
	private void listToDo(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException, ServletException
	{
		List<ToDo> listToDo = tdDAO.selectAllToDos();
		request.setAttribute("listToDo", listToDo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, ServletException, IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));
		ToDo existingToDo = tdDAO.selectToDo(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		request.setAttribute("todo",  existingToDo);
		dispatcher.forward(request, response);
	}
	
	private void insertToDo(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException
	{
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		boolean isDone = Boolean.valueOf(request.getParameter("is_done"));
		ToDo newToDo = new ToDo(title, description, isDone);
		tdDAO.insertToDo(newToDo);
		response.sendRedirect("list");
	}
	
	private void updateToDo(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));
		
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
		
		ToDo updateToDo = new ToDo(id, title, description, isDone);
		
		tdDAO.updateToDo(updateToDo);
		
		response.sendRedirect("list");
	}
	
	private void deleteToDo(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));
		tdDAO.deleteToDo(id);
		response.sendRedirect("list");
	}
	
}
