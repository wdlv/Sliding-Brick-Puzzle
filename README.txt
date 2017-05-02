When I was testing on Tux, I find that just typing "make" couldn't find the makefile nor compile the program.
However, typing "make -f makefile.txt" would work. I am wondering if there is something wrong with my makefile or other reasons? 

///////////////////////////////////////////////////////////////////////////////////

The second heuristic is to count how many moves for a master brick to reach the goal.

The principle of this heuristic is to use the goal's coordinate as a base for master block.
The master block has two ways to get close to the goal:
1. Move along horizontal line first and then go vertical to reach the goal.
2. Move along vertical line first and then go horizontal to reach the goal.

There are two heuristic values for the possibile two ways - heu1, heu2. 

This method will detect if there are other sliding bricks on the way. If so, heuristic value will add 2 because you have to move bricks on the way to other places first then you can move master brick one step. If there are spot“0” on the way. Heuristic value will add only 1.

The heuristic value will take the minimum value of heu1 and heu2.
