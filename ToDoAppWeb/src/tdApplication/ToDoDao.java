package tdApplication;

import java.sql.SQLException;
import java.util.List;

public interface ToDoDao {

	void insertToDo(ToDo todo) throws SQLException;
	
	ToDo selectToDo(int todoID);
	
	List<ToDo> selectAllToDos();
	
	boolean deleteToDo(int id) throws SQLException;
	
	boolean updateToDo(ToDo todo) throws SQLException;
	
}
