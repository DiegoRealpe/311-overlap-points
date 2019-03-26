/**
 * Represents the Position of a specific end point for an interval
 * either its start or its end
 * @author diegort
 *
 */
public class Endpoint {
	
	//private static final int START = -1, END = 1;
	private int position;
	private int ID;
	private int direction;
	
	public Endpoint(int ab, int ID, int direction) {
		position = ab;
		this.ID = ID;
		this.direction = direction;
	}
	
	/**
	 * returns the value of the line at this endpoint.
	 * a.k.a the position of the endpoint in the line
	 * @return endpoint position
	 */
	public int getValue() {
		return position;
	}
	
	/**
	 * returns the associated interval ID with this endpoint
	 * @return ID
	 */
	protected int getID() {
		return ID;
	}
	
	/**
	 * returns if this endpoint is a start or and end
	 * @return +1 || -1
	 */
	protected int getDir() {
		return direction;
	}
}
