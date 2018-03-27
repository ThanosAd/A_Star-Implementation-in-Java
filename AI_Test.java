import java.util.ArrayList;

public class AI_Test {
	public static void main(String[] args){
		Maze_Creator Maze = new Maze_Creator(15,80);
		Maze.CreateMaze();
		System.out.println(Maze.toString());
		PathFinder PathG1 = new PathFinder(0,0,13,1,Maze);
		PathG1.ManchattanDistanceCalculator();
		PathG1.GCalculator();
		ArrayList<Node> PathToG1 = PathG1.printPath();
		PathFinder PathG2 = new PathFinder(0,0,2,12,Maze);
		PathG2.ManchattanDistanceCalculator();
		PathG2.GCalculator();
		ArrayList<Node> PathToG2 = PathG2.printPath();
		ArrayList<Node> shortestPath = PathG1.shortest(PathToG1, PathToG2);
		ArrayList<Node> longestPath = PathG1.longest(PathToG1, PathToG2);
		if(shortestPath!=null){
			PathFinder PathFromTo = new PathFinder(shortestPath.get(0).getCoordinates().get(0),shortestPath.get(0).getCoordinates().get(1),longestPath.get(0).getCoordinates().get(0),longestPath.get(0).getCoordinates().get(1),Maze);
			PathFromTo.ManchattanDistanceCalculator();
			PathFromTo.GCalculator();
			ArrayList<Node> PathToFrom = PathFromTo.printPath();
			PathG1.printHolePath(shortestPath, PathToFrom);
		}
		
		
		//System.out.println(Path.toString());*/
	}
}
