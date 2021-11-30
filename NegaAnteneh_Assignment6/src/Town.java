import java.util.ArrayList;

public class Town implements Comparable<Town>{
	
	private String townName;
	private ArrayList<Town> adjTowns;
	
	/**
	 * Constructor
	 * @param name - Town's name
	 */
	public Town(String name)
	{
		townName = name;
	}
	
	/**
	 * Copy Constructor
	 * @param templateTown - instance of town
	 */
	public Town(Town templateTown)
	{
		townName = templateTown.townName;
	}

	/**
	 * this method returns 0 if names are equal, a positive or negative number if the names are not equal
	 * @param o
	 * @return 0 if names are equal, a positive or negative number if the names are not equal
	 */
	@Override
	public int compareTo(Town o) 
	{
		return townName.compareTo(o.townName);
	}
	
	/**
	 *This method returns true if the town names are equal, false if not 
	 * @param obj
	 * @return true if the town names are equal, false if not
	 */
	public boolean equals(Object obj)
	{
		Town town = (Town) obj;
		if(obj == this)
		{
			return true;
		}
		if(obj == null)
		{
			return false;
		}
		if(!(obj instanceof Town))
		{
			return false;
		}
		
		
		return this.townName.equals(town.townName);
	}
	
	/**
	 * This method returns the town name
	 * @return townName - town's name
	 */
	public String getName()
	{
		return townName;
	}
	
	/**
	 * This method returns the hashcode for the name of the town
	 * @return the hashcode for the name of the town
	 */
	public int hashCode()
	{
		return townName.hashCode();
	}
	
	/**
	 * This method is a basic to string method.
	 * @return townName - town's name
	 */
	public String toString()
	{
		return townName;
	}

}
