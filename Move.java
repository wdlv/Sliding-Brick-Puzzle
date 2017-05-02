import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Move {
	// alllist is to store all the steps possible when given a state
   ArrayList<String> alllist = new ArrayList<String>();
    
	
	public void moveDir (int piece, int[][] state) {
		
		ArrayList<String> list = new ArrayList<String>();
		
		
		boolean canUp = true;
		boolean canLeft = true;
		boolean canDown = true;
		boolean canRight = true;
		boolean flag = false;
		int temp = 0;
		int localh = state.length;
		int localw = state[0].length;
		
	//HL Only master brick (2) can move to goal(-1).	
		if (piece == 2) {
			// HL To see if the piece can move up		
			
			for (int i = 0; i < localh && !flag; i++) {
				for ( int j = 0; j<localw ; j++) {
					if (state[i][j] == piece)
						{ flag = true;   
						if (state[i-1][j] == 0 || state[i-1][j] == -1) 
						{continue;} 
						else {canUp = false;
								break;}
				}
			}
			}

					if (canUp)
						{
						list.add( piece + ",up");	
					
						}
					
	// HL To see if the piece can move down
					
					flag = false;  // HL To make sure the initial value of flag for next move is false
					
					for (int i = localh - 1; i >= 0  && !flag; i--) {
						for ( int j = localw -1; j>=0 ; j--) {
							if (state[i][j] == piece)
								{ flag = true;  
								if (state[i +1][j] == 0 || state[i +1][j] == -1) 
								{continue;} else 
								{canDown = false;
								break;}
						}
					}
				}
									
					if (canDown)
						{
						list.add(piece + ",down");
					
						}
		
	// HL To see if the piece can move left and save the piece's edge's coordinate j to temp
			flag = false;   
						
			for (int i = 0; i < localh && !flag; i++) {
				for ( int j = 0; j<localw ; j++) {
					if (state[i][j] == piece)
						{ flag = true;  
						temp = j;
						break;}					
				}
			}
		
			for (int i = 0; i< localh; i++) {
				if (state[i][temp] ==piece) {
					if (state[i][temp-1] == 0 || state[i][temp-1] == -1) 
					{continue;} else 
					{canLeft = false;
					break;  }
				}
			}
			
			
			
			if (canLeft)
				{
				list.add(piece + ",left");
		       
				}
			
	// HL To see if the piece can move right
			flag = false;
			
			for (int i = localh - 1; i >= 0  && !flag; i--) {
				for ( int j = localw -1; j>=0 ; j--) {
					if (state[i][j] == piece)
						{   flag = true;
						temp = j; 
						break;}
			}
		}
			
			for (int i = 0; i<localh; i++) {
				if (state[i][temp] == piece) {
					if (state[i][temp + 1] == 0 || state[i][temp + 1] == -1 ) 
					{continue;} else 
					{canRight = false;
					break;}
				}
			}	
			
			
			
			if (canRight)
				{
				list.add(piece + ",right");
			
				}
	/*	int index = 0;
			for(int i = 0; i< list.size(); i++) {
				System.out.println("["+list.get(index) + "]");
				index++;
			}      */
			alllist.addAll(list);
			
			return;
		}
		
// HL To see if the other pieces except "2" can move up		
		
		for (int i = 0; i < localh && !flag; i++) {
			for ( int j = 0; j<localw ; j++) {
				if (state[i][j] == piece)
					{ flag = true;   
					if (state[i-1][j] == 0) 
					{continue;} 
					else {canUp = false;
							break;}
			}
		}
		}

				if (canUp)
					{
					list.add( piece + ",up");	
				
					}
				
// HL To see if the piece can move down
				
				flag = false;  // HL To make sure the initial value of flag for next move is false
				
				for (int i = localh - 1; i >= 0  && !flag; i--) {
					for ( int j = localw -1; j>=0 ; j--) {
						if (state[i][j] == piece)
							{ flag = true;  
							if (state[i +1][j] == 0 ) 
							{continue;} else 
							{canDown = false;
							break;}
					}
				}
			}
								
				if (canDown)
					{
					list.add(piece + ",down");
				
					}
	
// HL To see if the piece can move left and save the piece's edge's coordinate j to temp
		flag = false;   
					
		for (int i = 0; i < localh && !flag; i++) {
			for ( int j = 0; j<localw ; j++) {
				if (state[i][j] == piece)
					{ flag = true;  
					temp = j;
					break;}					
			}
		}
	
		for (int i = 0; i< localh; i++) {
			if (state[i][temp] ==piece) {
				if (state[i][temp-1] == 0 ) 
				{continue;} else 
				{canLeft = false;
				break;  }
			}
		}
		
		
		
		if (canLeft)
			{
			list.add(piece + ",left");
	      
			}
		
// HL To see if the piece can move right
		flag = false;
		
		for (int i = localh - 1; i >= 0  && !flag; i--) {
			for ( int j = localw -1; j>=0 ; j--) {
				if (state[i][j] == piece)
					{   flag = true;
					temp = j; 
					break;}
		}
	}
		
		for (int i = 0; i<localh; i++) {
			if (state[i][temp] == piece) {
				if (state[i][temp + 1] == 0 ) 
				{continue;} else 
				{canRight = false;
				break;}
			}
		}	
		
		
		
		if (canRight)
			{
			list.add(piece + ",right");
		
			}
	/*	int index = 0;
		for(int i = 0; i< list.size(); i++) {
			System.out.println("["+list.get(index) + "]");
			index++;
		}     */
		alllist.addAll(list);
		return;
}

	
// 2.C      allMoves
	public void allMoves(int[][] state) {
		ArrayList<String> allpieces = new ArrayList<String>();
		
		int localh = state.length;
		int localw = state[0].length;
		
	//	System.out.println("All the moves in this state can be made are as follows:");
		
		allpieces.add("2");
		
		for (int i = 0; i< localh; i++) {
			for (int j = 0; j < localw; j++) {
				if (state[i][j] > 2) {
					
				
			//HL Search if allpieces has the element, if not, add the element to allpieces.
					if (allpieces.indexOf(state[i][j] + "") == -1)
					{    allpieces.add(state[i][j] + "");   }
				}
			}
		}
		
		//Collections.sort(allpieces); 
		
		
		for ( String one: allpieces)
			moveDir(Integer.parseInt(one), state);		
	}
	
	
	// 2.C applyMove
	public int[][] applyMove(int[][] state, int piece, String direction) {
		//System.out.println("An applyMove request has been received.");
		int localh = state.length;
		int localw = state[0].length;
		int temp = 0;
		boolean flag = false;
		String dir = direction;
		
		
		switch (dir) {
		case "up": 
			
			/* HL Find out the the coordinate of the piece's upside and give the piece value to cells
			directly above the piece's upside.     */
			for (int i = 0; i < localh && !flag; i++) {
				for ( int j = 0; j<localw ; j++) {
					if (state[i][j] == piece)
						{ state[i-1][j] = piece;   
						flag = true;
						}
				}
			}
			
			flag = false;  
			/* HL Search the piece's downside and keep its coordinate and
			 change the piece's downside to 0 so as to complete the move.
			 */
			for (int i = localh - 1; i >= 0  && !flag; i--) {
				for ( int j = localw -1; j>=0 ; j--) {
					if (state[i][j] == piece)
						{ state[i][j] = 0;   
						flag = true;
						}
				}
			}	
			
			break;
			
		case "down": 
			
			/* HL Find out the the coordinate of the piece's downside and
			 * The cells directly below the piece's downside are given the value of piece.
			 */
			for (int i = localh - 1; i >= 0  && !flag; i--) {
				for ( int j = localw -1; j>=0 ; j--) {
					if (state[i][j] == piece)
						{ flag = true;
						state[i+1][j] = piece;	}
				}
			}
				
			flag = false; 
			// HL Search the piece's upside and change the piece's upside to 0.
			for (int i = 0; i < localh && !flag; i++) {
				for ( int j = 0; j<localw ; j++) {
					if (state[i][j] == piece)
						{ flag = true;
						state[i][j] = 0;}
				}
			}
			
		          break;
		          
		case "left": 
			
			/* HL Find out the the first coordinate of the piece's leftside and
			cells directly at the piece's leftside are given the value of piece.  */
			for (int i = 0; i < localh && !flag; i++) {
				for ( int j = 0; j<localw ; j++) {
					if (state[i][j] == piece)
						{ flag = true;
						temp = j;					
						break;}
				}
			}
			
			for (int i = 0; i< localh; i++) {
				if (state[i][temp] == piece) {
					state[i][temp-1] = piece;
				}
			}
				
			flag = false; 
			// HL Search the piece's rightside and keep its coordinate then change them to 0.
			for (int i = localh - 1; i >= 0  && !flag; i--) {
				for ( int j = localw -1; j>=0 ; j--) {
					if (state[i][j] == piece)
						{ flag = true;
						temp = j;					
						break;}
				}
			}
			
			for (int i = 0; i<localh; i++ ) {
				if (state[i][temp] == piece) {
					state[i][temp] = 0;
				}
			}
		
		          break;
		          
		case "right": 
			
			/* HL Find out the the coordinate of the piece's rightside and save i or j to temp.
			 * cells directly at the the piece's rightside are given the value of piece.
			 */
						for (int i = localh - 1; i >= 0  && !flag; i--) {
							for ( int j = localw -1; j>=0 ; j--) {
								if (state[i][j] == piece)
									{ flag = true;
									temp = j;
									break;}
							}
						}
						
						for (int i = 0; i<localh; i++) {
							if (state[i][temp] == piece) {
								state[i][temp+1] = piece;
							}
						}
							
						flag = false; 
						// HL Search the piece's leftside and change the piece's leftside to 0.
						for (int i = 0; i < localh && !flag; i++) {
							for ( int j = 0; j<localw ; j++) {
								if (state[i][j] == piece)
									{ flag = true;
									temp = j;
									break;}
							}
						}
						
						for (int i = 0; i<localh; i++) {
							if (state[i][temp] == piece) {
								state[i][temp] = 0;
							}
						}
			
		           break;
		default: 
			break;
			}
		return state;
		}
	
	
	// 2.C applyMoveCloning
	public int[][] applyMoveCloning( int[][] state, int piece, String direction) {
		//System.out.println("An applyMoveCloning request has been received.");
		int[][] clone = new int[state.length][];
		
		for (int i = 0; i < state.length; i++) {
			clone[i] = state[i].clone();
		}
				
		applyMove(clone, piece, direction);
		
		return clone;
	}
}

