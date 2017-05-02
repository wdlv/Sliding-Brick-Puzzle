import java.io.*;
import java.util.*;

/** 
 * Puzzle.java
 * CS510 HW2: Sliding Brick Puzzle
 * @author Hongwei Liu
 */


public class Puzzle {

 public  int width;  // The width of the matrix 
 public  int height; // The height of the matrix
 
 // Initial state
  public Node initial;
  // To record how many steps
  int stepNum = 0;
 
 int capacity = 100;
 
 public PriorityQueue<Node> open = new PriorityQueue<Node>(capacity, new Comparator<Node>() {
	 
	 public int compare(Node a, Node b) {
		 if (a.f == b.f) { 
		        return b.g - a.g;
		      }
		      else {
		        return a.f - b.f;
		      }
	 }
 }   );
 
 // closed is to store explored nodes.
	HashSet<Node> closed = new HashSet<Node>();
	List<String> path = new ArrayList<String>();
 
  // list to store the matrix(state)
 public  ArrayList<String> list = new ArrayList<String>();
 
// Astar algorithm
 public void operate() {
	 
	 int nodeNum = 0;
	 
	 open.clear();
	  open.add(this.initial);
	 
	  long startTime = System.currentTimeMillis();
	  
	  while(!open.isEmpty()) {
		  
		  Node currentNode = open.poll();	  
		
		  nodeNum++;
		
		  if (closed.contains(normalizeState(clone(currentNode)))) {
			  continue;
		  }
		  if (gameStateSolved(currentNode.currentState)) {
			  
			  System.out.println("Game solved");
			  
			  for (Node p = currentNode; p != null; p = p.parent )
				  if (p.step != null) {
				  path.add(p.step); 
				  stepNum++;}
			  
			  int index = path.size() -1;
			  for (int i = 0; i < path.size(); i++) {
				  System.out.println("[" + path.get(index) + "]");
				  index--; }
			  
			  outputGameState(currentNode.currentState);
			  
			  long endTime = System.currentTimeMillis();
			  long time = endTime - startTime;
			  
			  System.out.println("The time spent is " + time + " ms ; the num of node visited is " + nodeNum );
			  System.out.println("Step num is " + stepNum);
			
			  return;
		  }

            closed.add(normalizeState(clone(currentNode)));
		
		   getChildren(currentNode);
		  
	  }
	  System.out.println("No children, " + "The num of nodes visited is " + nodeNum);
 }


// Expand more nodes from a given node
 public void getChildren(Node n) {
	 
		Move move = new Move();
		
		move.allMoves(n.currentState);
		
		int num = move.alllist.size();	 
		
		
		for (int i = 0; i< num; i++) {
						
			String[] mid = move.alllist.get(i).split(",");
					
			Node child = new Node();		
		
			child.currentState = move.applyMoveCloning(n.currentState, Integer.parseInt(mid[0]), mid[1]);

			
			if (closed.contains(normalizeState(clone(child)))) {
				continue;}
			
			child.parent = n;
			child.g = n.g +1;
			child.h = getHeuristic(child.currentState);
			
			
			child.f = child.g + child.h;
			child.step = move.alllist.get(i);
			open.add(child);
			
		}	
				
	}
 
 public static int getHeuristic (int[][] state) {
	 // Goal -1's coordinate
		int goalx =0;
		int goaly = 0;
		// Master block 2's coordinate
		int masterx = 0;
		int mastery = 0;
		int heu = 0;
		boolean flag = false;
		boolean goal = true;
		//System.out.println("really" + state.length);
		
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				if (state[i][j] == -1) {
					goal = false;
				}
			}
		}
		
		
		if (goal) {
			heu = 0;
			return heu;
		}
		// Find goalx and goaly
		for (int i = 0; i < state.length && !flag; i++) {
			for (int j = 0; j < state[0].length; j++) {
				if (state[i][j] == -1) {
					goalx = j;
					goaly = i;
					flag = true;
					break;}
			}
		}
		// Determine which area the goal is in
		if (goalx == 0 || goaly == 0) {
			
			flag = false;
			
			for (int i = 0; i < state.length && !flag; i++) {
				for (int j = 0; j < state[0].length; j++) {
					if (state[i][j] == 2) {
						masterx = j;
						mastery = i;
						flag = true;
						break;}
				}
			}
			heu = Math.abs(masterx - goalx) + Math.abs(mastery - goaly);
			return heu;
		} 
		
		flag = false;
		// If not in the above area, then the goal should be in this area
		for (int i = state.length - 1; i>=0 && !flag; i--) {
			for (int j = state[0].length - 1; j >= 0; j--) {
				if (state[i][j] == -1) {
					goalx = j;
					goaly = i;
					flag = true;
					break;}
			}
		}

		flag = false;
		
			for (int i = state.length - 1; i >= 0 && !flag ; i--) {
				for (int j = state[0].length -1; j >= 0; j--) {
					if (state[i][j] == 2) {
						masterx = j;
						mastery = i;
						flag = true;
						break;}
				}
			}
			
			heu = Math.abs(masterx - goalx) + Math.abs(mastery - goaly);
			return heu;
		
	}
  
  public int getWidth() {
	return width;
}

public void setWidth(int width) {
	this.width = width;
}

public int getHeight() {
	return height;
}

public void setHeight(int height) {
	this.height = height;
}

public ArrayList<String> getList() {
	return list;
}


//part 2.A
public void loadGameState(String filePath) {
     int index = 0;
        BufferedReader br = null;
        String str = null;

        try {
           br  = new BufferedReader(new FileReader(new File(filePath)));
            /* read the first line to get the array's width 
           and height so as to create and assign an array later. */
            str = br.readLine();
          
            String [] temp = str.split(",");
            
            width = Integer.parseInt(temp[0]);
            height = Integer.parseInt(temp[1]);

          }  catch (FileNotFoundException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
           
            e.printStackTrace();
        }

        
            String[][] matrixArray = new String [height][];

       try {      

            while((str=br.readLine())!=null){
             
                matrixArray[index] = str.split(",");
                index++;
            }
            // Copy matrixArray's data to give it to list.
            for (int i=0; i<matrixArray.length; i++) {
              for (int j = 0; j < matrixArray[i].length; j++) {
                list.add(matrixArray[i][j]);
              }
            }         

           } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
          
            e.printStackTrace();

        }        
      }

//part 2.A
   public void outputGameState(int[][] matrixArray) {
    System.out.println("The game state is printed as follows:");
    System.out.println(width + "," + height + ",");
       for (int i = 0; i<height; i++) {
        for (int j = 0; j<width ;j++ ) {
          System.out.print(matrixArray[i][j] + ",");
          
        }
        System.out.println();
       }
   
           }  
   
//part 2.A
  public static Node clone( Node n) {
	// System.out.println("A clone request has been received.");
	  Node nodeTemp = new Node();
    nodeTemp.currentState = new int[n.currentState.length][];
    for (int i = 0; i < n.currentState.length; i ++) {
      nodeTemp.currentState[i] = n.currentState[i].clone();
    }
     return nodeTemp;
  }   
  
  
  public static int[][] clone( int[][] array) {
		// System.out.println("A clone request has been received.");
	    int[][] clone = new int[array.length][];
	    for (int i = 0; i < array.length; i ++) {
	      clone[i] = array[i].clone();
	    }
	     return clone;
	  }   
  
  //part2.B
  public boolean gameStateSolved(int[][] state) {
    boolean isSolved = true;

    int h = state.length;
    int w = state[0].length;
    boolean flag = true;
    for (int i = 0; i < h && flag; i++) {
    	for (int j = 0; j < w; j++) {
    		if (state[i][j] == -1) 
    		{flag = false;
    		isSolved = false;
    		break;} 		
    	}
    }
    
   //  System.out.println("Is this game solved?");
  //  System.out.println(isSolved);
    return isSolved;

  }
  
  
  //2.E Normalization
  public Node normalizeState(Node n) {
	 
	  int h = n.currentState.length;
	  int w = n.currentState[0].length;
	  int nextIdx = 3;
	  for (int i = 0; i < h; i++) {
		  for (int j = 0; j<w; j++) {
			  if (n.currentState[i][j] == nextIdx) {
				  nextIdx++;
			  } else if (n.currentState[i][j] > nextIdx) {
				  swapIdx(nextIdx, n.currentState[i][j], n.currentState);
				  nextIdx++;
			  }
		  }
	  }
	  //System.out.println("After normalization, the new state we get is:");
	  return n;
  }
  
  public void swapIdx(int idx1, int idx2, int[][] state) {
	  int h = state.length;
	  int w = state[0].length;
	  for (int i = 0; i < h; i++) {
		  for (int j = 0; j < w; j++) {
			  if (state[i][j] == idx1) {
				  state[i][j] = idx2;
			  } else if (state[i][j] == idx2) {
				  state[i][j] = idx1;
			  }
		  }
	  }
	}
  
  


public void initialize(int[][] array) {
	   this.initial = new Node(array);
		 }
   
   
  public Puzzle() {
	// TODO Auto-generated constructor stub
}
public static void main (String args[]){

    int index = 0;
	  
	  Puzzle puzzle=new Puzzle();
	  
	  String filePath="SBP-bricks-level1.txt";
	  String filePath1="SBP-bricks-level2.txt";
	  String filePath2="SBP-bricks-level3.txt";
	  String filePath3="SBP-bricks-level4.txt";
	  String filePath4="SBP-bricks-level5.txt";
	  String filePath5="SBP-bricks-level6.txt";
	  String filePath6="SBP-bricks-level7.txt";
	  String filePath7="SBP-level0.txt";
	  String filePath8="SBP-level1.txt";
	  String filePath9="SBP-level2.txt";
	  String filePath10="SBP-level3.txt";
	  
	  
	  puzzle.loadGameState(filePath6);


    int[][] matrixArray = new int[puzzle.getHeight()][puzzle.getWidth()];
    for (int i= 0; i<puzzle.getHeight() ; i++) {
      for (int j = 0; j<puzzle.getWidth() ; j++) {
        matrixArray[i][j] = Integer.parseInt(puzzle.getList() .get(index));
        index++;
      }
    }

  
	  puzzle.outputGameState(matrixArray);
	  
	  puzzle.initialize(matrixArray);
	  
	  puzzle.operate();
	  
  }
}
