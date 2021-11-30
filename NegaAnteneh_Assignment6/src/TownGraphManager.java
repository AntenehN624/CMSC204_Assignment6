import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TownGraphManager implements TownGraphManagerInterface{
	
	private Graph graph = new Graph();

	/**
	 * this method Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) 
	{
		if(graph.addEdge(new Town(town1), new Town(town2), weight, roadName) != null)
		{
			return true;
		}
		return false;
	}

	/**
	 * this method Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	@Override
	public String getRoad(String town1, String town2) 
	{
		return graph.getEdge(new Town(town1), new Town(town2)).getName();
	}

	/**
	 * this method Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	@Override
	public boolean addTown(String v) 
	{
		return graph.addVertex(new Town(v));
	}

	/**
	 * this method Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	@Override
	public Town getTown(String name) 
	{
		return graph.vertexSet().stream().filter(town -> town.getName().equals(name)).findAny().orElse(null);
	}

	/**
	 * this method Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	@Override
	public boolean containsTown(String v) 
	{
		return graph.containsVertex(new Town(v));
	}

	/**
	 * this method Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) 
	{
		return graph.containsEdge(new Town(town1), new Town(town2));
	}

	/**
	 * this method Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	@Override
	public ArrayList<String> allRoads() 
	{
		return graph.edgeSet().stream().map(Road :: getName) .sorted().collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * this method Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) 
	{
		return graph.removeEdge(new Town(town1), new Town(town2), 0, road) != null;
	}

	/**
	 * this method Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	@Override
	public boolean deleteTown(String v) 
	{
		return graph.removeVertex(new Town(v));
	}

	/**
	 * this method Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	@Override
	public ArrayList<String> allTowns() 
	{
		return graph.vertexSet().stream().map(Town::getName).sorted().collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * this method Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) 
	{
		return graph.shortestPath(new Town(town1), new Town(town2));
	}
	
	/**
	 * This method Reads input from a file to build a graph
	 * @param file  file that contains the data to build graph
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void populateTownGraph(File file) throws FileNotFoundException, IOException
	{
		InputStream is;
		is = new FileInputStream(file);
		BufferedReader buf;
		buf = new BufferedReader(new InputStreamReader(is));
		
		buf.lines()
		.map(s -> s.split(";|\\,"))
		.forEach(ar ->{
			addTown(ar[2]);
			addTown(ar[3]);
			addRoad(ar[2], ar[3], Integer.parseInt(ar[1]), ar[0]);
		});
		buf.close();
	}

}
