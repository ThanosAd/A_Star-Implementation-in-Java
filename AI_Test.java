import java.util.ArrayList;

public class AI_Test {
	public static void main(String[] args){
		Maze_Creator Maze = new Maze_Creator(15,80);                // Set the Maze's dimensions
		Maze.CreateMaze();                                          // Create the Maze
		System.out.println(Maze.toString());                        // Print the Maze
		PathFinder PathG1 = new PathFinder(0,0,13,10,Maze);         // Set the Staring Point to (0,0) and the Destination to (13,10)
		PathG1.MachattanDistanceCalculator();                       // Calculate The Manhattan Distance of all nodes
		PathG1.GCalculator();                                       // Calculate the G cost and the final path
		ArrayList<Node> PathToG1 = PathG1.printPath();              // Print the shortest path from S to G
	}
}
