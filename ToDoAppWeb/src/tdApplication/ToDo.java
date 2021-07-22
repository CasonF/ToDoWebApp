package tdApplication;

public class ToDo {

	private int id;
	private String title, description;
	private boolean status;
	
	protected ToDo() {}
	
	public ToDo(int id, String title, String desc, boolean isDone)
	{
		super();
		this.id = id;
		this.title = title;
		this.description = desc;
		this.status = isDone;
	}
	
	public ToDo(String title, String desc, boolean isDone)
	{
		super();
		this.title = title;
		this.description = desc;
		this.status = isDone;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String desc)
	{
		this.description = desc;
	}
	
	public boolean getStatus()
	{
		return status;
	}
	
	public void setStatus(boolean status)
	{
		this.status = status;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int)(id ^ (id >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToDo other = (ToDo) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
