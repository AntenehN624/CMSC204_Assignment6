
public class Road implements Comparable<Road>{

	private Town A;
	private Town B;
	private String roadName;
	private int distance;

	/**
	 * Constructor
	 * @param source, destination, degrees, name
	 */
	public Road(Town source, Town destination, int degrees, String name)
	{
		A = source;
		B = destination;
		distance = degrees;
		roadName = name;
	}

	/**
	 * Constructor with weight preset to 1
	 * @param source, destination, name
	 */
	 public Road(Town source, Town destination, String name)
	 {
		 A = source;
		 B = destination;
		 distance = 1;
		 roadName = name;
	 }

	 /**
	  * this method returns 0 if the road names are the same, a positive or negative number if the road names are not the same
	  * @param o
	  * @return 0 if the road names are the same, a positive or negative number if the road names are not the same
	  */
	 @Override
	 public int compareTo(Road o)
	 {
		 return this.roadName.compareTo(o.roadName);
	 }

	 /**
	  * this method returns true only if the edge is connected to the given vertex
	  * @param town
	  * @return true only if the edge is connected to the given vertex
	  */
	 public boolean contains(Town town)
	 {
		 return A.getName().equals(town.getName()) || B.getName().equals(town.getName());
	 }

	 /**
	  * this method returns true if each of the ends of the road r is the 
	  * same as the ends of this road. Remember that a road that goes from point A to point B is 
	  * the same as a road that goes from point B to point A.
	  * @param r
	  * @return true if each of the ends of the road r is the 
	  * same as the ends of this road. Remember that a road that goes from point A to point B is 
	  * the same as a road that goes from point B to point A.
	  */
	 public boolean equals(Object r)
	 {
		 if(r == this)
		 {
			 return true;
		 }
		 if(r == null)
		 {
			 return false;
		 }
		 if(!(r instanceof Road))
		 {
			 return false;
		 }
		 Road road = (Road) r;
		 return (this.A.equals(road.A) && this.B.equals(road.B)) || (this.A.equals(road.B) && this.B.equals(road.A));
	 }
	 
	 /**
	  * This method is a basic to string method.
	  * @return a String representation of the object.
	  */
	 @Override
	 public String toString()
	 {
		 return A.getName() + " via " + roadName + " to " + B.getName() + " " + distance + " mi";
	 }
	 
	 /**
	  * This method returns the source
	  * @return A
	  */
	 public Town getSource()
	 {
		 return A;
	 }
	 
	 /**
	  * This method returns the destination
	  * @return B
	  */
	 public Town getDestination()
	 {
		 return B;
	 }
	 
	 /**
	  * This method returns the weight
	  * @return distance
	  */
	 public int getWeight()
	 {
		 return distance;
	 }
	 
	 /**
	  * This method returns the name
	  * @return roadName
	  */
	 public String getName()
	 {
		 return roadName;
	 }
}
