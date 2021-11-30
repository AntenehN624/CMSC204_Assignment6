import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph implements GraphInterface<Town,Road>{

	private ArrayList<String> shortPath = new ArrayList<>();
	private Set<Road> roads = new HashSet<>();
	private Set<Town> towns = new HashSet<>();
	private Town B;
	
	/**
     * this method Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @return an edge connecting source vertex to target vertex.
     */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) 
	{
		if(sourceVertex == null || destinationVertex == null)
		{
			return null;
		}
		return roads.stream().filter(road -> road.contains(sourceVertex) && road.contains(destinationVertex)).findAny().orElse(null);
	}

	/**
     * this method Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     * @return The newly created edge if added to the graph, otherwise null.
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) throws IllegalArgumentException, NullPointerException
	{
		if(sourceVertex == null || destinationVertex == null)
		{
			throw new NullPointerException();
		}
		if(!containsVertex(sourceVertex) || !containsVertex(destinationVertex))
		{
			throw new IllegalArgumentException();
		}
		Road road;
		road = new Road(sourceVertex, destinationVertex, weight, description);
		roads.add(road);
		return road;
	}

	 /**
     * this method Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     * @param v vertex to be added to this graph.
     * @return true if this graph did not already contain the specified
     * vertex.
     * @throws NullPointerException if the specified vertex is null.
     */
	@Override
	public boolean addVertex(Town v) throws NullPointerException
	{
		if(!towns.contains(v))
		{
			towns.add(v);
			return true;
		}
		if(v == null)
		{
			throw new NullPointerException();
		}
		return false;
	}

	/**
     * this method Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @return true if this graph contains the specified edge.
     */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) 
	{
		for(Road roads : roads)
		{
			if(roads.contains(sourceVertex) && roads.contains(destinationVertex))
			{
				return true;
			}
		}
		return false;
	}

	/**
     * this method Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     * @param v vertex whose presence in this graph is to be tested.
     * @return true if this graph contains the specified vertex.
     */
	@Override
	public boolean containsVertex(Town v) 
	{
		return towns.contains(v);
	}

	/**
     * this method Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     * @return a set of the edges contained in this graph.
     */
	@Override
	public Set<Road> edgeSet() 
	{
		return roads;
	}

	/**
     * this method Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     * @return a set of all edges touching the specified vertex.
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	@Override
	public Set<Road> edgesOf(Town vertex) 
	{
		if(vertex == null)
		{
			throw new NullPointerException();
		}
		Set<Road> roadS = new HashSet<>();
		for(Road r : roads)
		{
			if(r.contains(vertex))
			{
				roadS.add(r);
			}
		}
		if(roadS.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		return roadS;
	}

	 /**
     * this method Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     * @return The removed edge, or null if no edge removed.
     */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) 
	{
		Road road;
		road = null;
		for(Road r : roads)
		{
			if(r.contains(destinationVertex) && r.contains(sourceVertex) && (weight > -1) && description != null)			{
				road = r;
			}
		}
		if(roads.remove(road))
		{
			return road;
		}
		return null;
	}

	/**
     * this method Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     * @param v vertex to be removed from this graph, if present.
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	@Override
	public boolean removeVertex(Town v) 
	{
		if(v == null)
		{
			return false;
		}
		return towns.remove(v);
	}

	 /**
     * this method Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     * @return a set view of the vertices contained in this graph.
     */
	@Override
	public Set<Town> vertexSet() 
	{
		return towns;
	}

	/**
     * this method Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     */   
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) 
	{
		B = destinationVertex;

		dijkstraShortestPath(sourceVertex);
		ArrayList<Road> rPath;
		rPath = new ArrayList<>();
		
		boolean aSource;
		aSource = roads.stream().anyMatch(r -> r.contains(sourceVertex));
		
		boolean aDest;
		aDest = roads.stream().anyMatch(r -> r.contains(destinationVertex));
		
		if(!aSource || !aDest)
		{
			return new ArrayList<>();
		}
		
		for(int n = 0; n < shortPath.size() - 1; n++)
		{
			Town source;
			source = new Town(shortPath.get(n));
			
			Town destination;
			destination = new Town(shortPath.get(n+1));
			
			Road road = getEdge(source, destination);
			rPath.add(new Road(source, destination, road.getWeight(), road.getName()));
		}
		shortPath.clear();
		
		return rPath.stream().map(Road :: toString).collect(Collectors.toCollection(ArrayList::new));
	}

	/**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) 
	{
		List <Town> vert;
		
		vert = new ArrayList<>(towns);
		
		int[][] adjMatrix;
		
		adjMatrix = new int[towns.size()][towns.size()];
		
		for(int n = 0; n < adjMatrix.length; n++)
		{
			for(int k = 0; k < adjMatrix[n].length; k++)
			{
				if(n == k || !containsEdge(vert.get(n), vert.get(k)))
				{
					adjMatrix[n][k] = 0;
				}
				else
				{
					int dist;
					
					dist = getEdge(vert.get(n), vert.get(k)).getWeight();
					
					adjMatrix[n][k] = adjMatrix[k][n] = dist;
				}
			}
		}
		
		int sTown;
		
		sTown = 0;
		
		for(Town t : vert)
		{
			if(!t.equals(sourceVertex))
			{
				
				sTown++;
				
			}
			else
			{
				break;
			}
		}
		
		int eTown;
		
		eTown=0;
		
		for(Town t : vert)
		{
			if(!t.equals(B))
			{
				eTown++;
			}
			else
			{
				break;
			}
		}
		int nTown;
		nTown = adjMatrix[0].length;
		
		int[] sDist;
		sDist = new int[nTown];
		
		boolean[] visited;
		visited = new boolean[nTown];
		
		for(int t = 0; t < nTown; t++)
		{
			sDist[t] = Integer.MAX_VALUE;
			
			visited[t] = false;
		}
		
		sDist[sTown] = 0;
		
		int[] minimumPathLength = new int [nTown];
		
		minimumPathLength[sTown] = -1;
		
		for(int n = 1; n < nTown; n++)
		{
			int nearTown;
			nearTown = -1;
			
			int mDist;
			mDist = Integer.MAX_VALUE;
			
			for(int tI = 0; tI < nTown; tI++)
			{
				if(!visited[tI] && sDist[tI] < mDist)
				{
					nearTown = tI;
					
					mDist = sDist[tI];
				}
			}
			visited[nearTown] = true;
			for(int tI = 0; tI < nTown; tI++)
			{
				int rDist = adjMatrix[nearTown][tI];
				
				if(rDist > 0 && ((mDist + rDist) < sDist[tI]))
				{
					minimumPathLength[tI] = nearTown;
					
					sDist[tI] = mDist + rDist;
				}
			}
		}
		
		cShortPath(eTown, minimumPathLength);
	}
	
	/**
	 * this method computes the shortest paths of towns and add into arraylist
	 * @param sourceVertex index of town
	 * @param minPathLengths array with towns short distances
	 */
	private void cShortPath(int sourceVertex, int[] minimumPathLength)
	{
		if(sourceVertex == -1)
		{
			return;
		}
		
		cShortPath(minimumPathLength[sourceVertex], minimumPathLength);
		
		int tI;
		
		tI = 0;
	
		for(Town t : towns)
		{
			if(tI == sourceVertex)
			{
				shortPath.add(t.getName());
			}
			tI++;
		}
	}
}


