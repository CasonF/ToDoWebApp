package tdApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TDDaoImplement implements ToDoDao {

	private static final String INSERT_TODOS_SQL = "INSERT INTO todos" +
			" (title, description, is_done) VALUES " + " (?, ?, ?);";
	
	private static final String SELECT_TODO_BY_ID = "select id, title, description, is_done from todos where id = ?;";
	private static final String SELECT_ALL_TODOS = "select * from todos;";
	private static final String DELETE_TODO_BY_ID = "delete from todos where id = ?;";
	private static final String UPDATE_TODO = "update todos set title = ?, description = ?, is_done = ? where id = ?;";
	
	public TDDaoImplement() {}
	
	@Override
	public void insertToDo(ToDo todo) throws SQLException
	{
		System.out.println(INSERT_TODOS_SQL);
		try (Connection conn = JDBCconnect.getConnection(); PreparedStatement ppdStatement = conn.prepareStatement(INSERT_TODOS_SQL))
		{
			ppdStatement.setString(1, todo.getTitle());
			ppdStatement.setString(2, todo.getDescription());
			ppdStatement.setBoolean(3, todo.getStatus());
			System.out.println(ppdStatement);
			ppdStatement.executeUpdate();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Override
	public ToDo selectToDo(int todoID)
	{
		ToDo todo = null;
		
		try (Connection conn = JDBCconnect.getConnection();
			PreparedStatement ppdStatement = conn.prepareStatement(SELECT_TODO_BY_ID);)
		{
			ppdStatement.setInt(1,  todoID);
			System.out.println(ppdStatement);
			ResultSet rs = ppdStatement.executeQuery();
			
			while (rs.next())
			{
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				boolean isDone = rs.getBoolean("is_done");
				todo = new ToDo(id, title, description, isDone);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return todo;
	}
	
	@Override
	public List<ToDo> selectAllToDos()
	{
		List<ToDo> todos = new ArrayList<>();
		
		try (Connection conn = JDBCconnect.getConnection();
			PreparedStatement ppdStatement = conn.prepareStatement(SELECT_ALL_TODOS);)
		{
			System.out.println(ppdStatement);
			ResultSet rs = ppdStatement.executeQuery();
			
			while (rs.next())
			{
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				boolean isDone = rs.getBoolean("is_done");
				todos.add(new ToDo(id, title, description, isDone));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return todos;
	}
	
	@Override
	public boolean deleteToDo(int id) throws SQLException
	{
		boolean rowDeleted;
		try (Connection conn = JDBCconnect.getConnection();
			PreparedStatement statement = conn.prepareStatement(DELETE_TODO_BY_ID);)
		{
			statement.setInt(1,  id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	@Override
	public boolean updateToDo(ToDo todo) throws SQLException
	{
		boolean rowUpdated;
		try (Connection conn = JDBCconnect.getConnection();
			PreparedStatement statement = conn.prepareStatement(UPDATE_TODO);)
		{
			statement.setString(1,  todo.getTitle());
			statement.setString(2, todo.getDescription());
			statement.setBoolean(3, todo.getStatus());
			statement.setInt(4, todo.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
}
