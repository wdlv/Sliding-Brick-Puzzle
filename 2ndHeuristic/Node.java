import java.util.Arrays;

public class Node {
	
	// Costs so far
	int g;
	//heuristic cost to goal
	int h;
	// Total cost = g + h
	int f;
	
	String step;
	
	int[][] currentState;
	
	public Node parent;
		
	public Node() {
		// TODO Auto-generated constructor stub
	}
   
	public Node(int[][] array) {
		
		this.currentState = Puzzle.clone(array);
	
		this.g= 0;
		this.h= Puzzle.getHeuristic(this.currentState);
		this.f = this.g + this.h;
		this.parent = null;
		
	}
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(this.currentState);

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (!Arrays.deepEquals(this.currentState, other.currentState))
			return false;
		
		return true;
	}
	
}