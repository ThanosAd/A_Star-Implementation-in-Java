// Project for MYY602, Artificial Intelligence, CSE, University of Ioannina
//Thanasis Antamis, Spring 2017


import java.util.ArrayList;

public class Node {
	double F;
	double G;
	double H;
	Node Father;
	ArrayList<Integer> Coordinates = new ArrayList<Integer>();
	boolean Free;
	
	public Node(ArrayList<Integer> sCoordinates){
		//F = aF;
		//G = aG;
		//H = aH;
		Coordinates = sCoordinates;
	}
	
	public double getF(){
		return F;
	}
	
	public double getG(){
		return G;
	}
	
	public double getH(){
		return H;
	}
	
	public ArrayList<Integer> getCoordinates(){
		return Coordinates;
	}
	
	public boolean isFree(){
		return Free;
	}
	
	public Node getFather(){
		return Father;
	}
	
	public void setF(double aF){
		F = aF;
	}
	
	public void setG(double aG){
		G = aG;
	}
	
	public void setH(double aH){
		H = aH;
	}
	
	public void setFather(Node aFather){
		Father = aFather;
	}
	
	public void setFree(boolean FreeOrNot){
		Free = FreeOrNot;
	}
}
