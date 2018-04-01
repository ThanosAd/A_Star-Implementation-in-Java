// Project for MYY602, Artificial Intelligence, CSE, University of Ioannina
//Thanasis Antamis, Spring 2017

import java.util.Random;
import java.util.ArrayList;

public class Maze_Creator {
	int Dimension;
	double Probability;
	Random rand = new Random();
	int  n = rand.nextInt(100) + 0;
	ArrayList<ArrayList<Integer>> MazeRows = new ArrayList<ArrayList<Integer>>();
	ArrayList<Integer> MazeColumns ;
	//ArrayList<Integer> 
	public Maze_Creator(int N, double P){
		Dimension = N;
		Probability = P;
		
	}
	
	public void CreateMaze(){
		for(int i=0;i<Dimension;i++){
			MazeColumns = new ArrayList<Integer>();
			MazeRows.add(MazeColumns);
			for(int j=0;j<Dimension;j++){
				if(n<=Probability){
					MazeRows.get(i).add(0);
				}else{
					MazeRows.get(i).add(1); 
				}
				n = rand.nextInt(100) + 1;
			}
		}
	}
	
	public ArrayList<ArrayList<Integer>> getMaze(){
		return MazeRows;
	}
	
	public int getDimension(){
		return Dimension;
	}
	
	public String toString(){
		String M ="";
		for(int i=0;i<Dimension;i++){
			for(int j=0;j<Dimension;j++){
				
				M = M + MazeRows.get(i).get(j)+"  ";
			}
			M = M + "\n";
		}
		return M;
	}
	
	public void setMaze(ArrayList<ArrayList<Integer>> BinMaze){
		MazeRows = BinMaze;
	}
}
