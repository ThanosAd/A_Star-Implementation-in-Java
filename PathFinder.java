import java.util.ArrayList;


public class PathFinder {
	
	int Sx;
	int Sy;
	int G1x;
	int G1y;
	
	
	ArrayList<Integer> Coordinates;
	Maze_Creator Maze;
	ArrayList<Node> TheMaze = new ArrayList<Node>();
	ArrayList<Node> XY_Coordinates_Close = new ArrayList<Node>();
	ArrayList<Node> XY_Coordinates_Open = new ArrayList<Node>();
	
	
	public PathFinder(int aSx,int aSy ,int aG1x ,int aG1y , Maze_Creator aMaze){
		Sx = aSx;
		Sy = aSy;
		G1x = aG1x;
		G1y = aG1y;
		Maze = aMaze;
	}
	
	public void ManhattanDistanceCalculator(){
		ArrayList<ArrayList<Integer>> BinMaze = Maze.getMaze();
		for(int i =0;i<Maze.getDimension();i++){
			for(int j=0;j<Maze.getDimension();j++){
				double tempHvalue = Math.abs(j-G1x)*0.5 + Math.abs(i-G1y);
				Coordinates = new ArrayList<Integer>();
				int x=j;
				int y=i;
				Coordinates.add(x);
				Coordinates.add(y);
				Node aNode = new Node(Coordinates);
				aNode.setH(tempHvalue);
				int T = (Maze.getDimension()*i)+j;
				if(BinMaze.get(i).get(j)== 0){
					aNode.setFree(true);
				}else{
					aNode.setFree(false);
				}
				TheMaze.add(aNode);
			
			}
		}
	}
	
	public void GCalculator(){
		Node StartNode = null ;
		for(int i=0;i<TheMaze.size();i++){
			ArrayList<Integer> Start = new ArrayList<Integer>();
			Start.add(Sx);
			Start.add(Sy);
			if(TheMaze.get(i).getCoordinates().equals(Start)){
				StartNode = TheMaze.get(i);
			}
		}
		StartNode.setF(0);
		StartNode.setG(0);
		StartNode.setFather(null);
		//XY_Coordinates_Close.add(StartNode);
		XY_Coordinates_Open.add(StartNode);
		boolean flag = true ;
		boolean flag2 = true;
		boolean flag3 = true;
		Node CurrentNode = StartNode;
		while(flag==true){
			double minF = XY_Coordinates_Open.get(0).getF();;
			int position = 0;
			for(int i=0; i < XY_Coordinates_Open.size(); i++){
				if(minF>XY_Coordinates_Open.get(i).getF()){
					minF = XY_Coordinates_Open.get(i).getF();
					position = i;
				}
			}
			//System.out.println(XY_Coordinates_Open.size());
			if(XY_Coordinates_Open.size()>0){
				CurrentNode = XY_Coordinates_Open.get(position);
				XY_Coordinates_Open.remove(XY_Coordinates_Open.get(position));
				XY_Coordinates_Close.add(CurrentNode);
			}
			ArrayList<Integer> CurrentCoordinates = CurrentNode.getCoordinates();
			if(Maze.getDimension()*CurrentCoordinates.get(1)+(CurrentCoordinates.get(0)+1)<Maze.getDimension()*Maze.getDimension() && Maze.getDimension()*CurrentCoordinates.get(1)+(CurrentCoordinates.get(0)+1)>=0 && CurrentCoordinates.get(0)+1 < Maze.getDimension()){
				if(TheMaze.get((Maze.getDimension()*CurrentCoordinates.get(1)+(CurrentCoordinates.get(0)+1))).isFree()){
					//if()
					Node NodeRight = TheMaze.get((Maze.getDimension()*CurrentCoordinates.get(1)+(CurrentCoordinates.get(0)+1)));
					for(int i=0;i<XY_Coordinates_Open.size();i++){
						if(NodeRight.getCoordinates().get(0)== XY_Coordinates_Open.get(i).getCoordinates().get(0)&& NodeRight.getCoordinates().get(1) == XY_Coordinates_Open.get(i).getCoordinates().get(1)){
							flag2 = false;
						}
					}
					for(int i=0;i<XY_Coordinates_Close.size();i++){
						if(NodeRight.getCoordinates().get(0)== XY_Coordinates_Close.get(i).getCoordinates().get(0)&& NodeRight.getCoordinates().get(1) == XY_Coordinates_Close.get(i).getCoordinates().get(1)){
							flag3 = false;
						}
					}
					if(flag2&&flag3){
						NodeRight.setFather(CurrentNode);
						NodeRight.setG(CurrentNode.getG()+0.5);
						double g = NodeRight.getG();
						double aH = NodeRight.getH();
						NodeRight.setF(g+aH);
						XY_Coordinates_Open.add(NodeRight);
						//System.out.println("Right");
					}else if(flag2==false&&flag3){
						if(NodeRight.getG()>CurrentNode.getG()+0.5){
							NodeRight.setFather(CurrentNode);
							NodeRight.setG(CurrentNode.getG()+0.5);
							double g = NodeRight.getG();
							double aH = NodeRight.getH();
							NodeRight.setF(g+aH);
						}
					}	
				}
			}
			
			flag2 = true;
			flag3 = true;
			if(Maze.getDimension()*CurrentCoordinates.get(1)+(CurrentCoordinates.get(0)-1)<Maze.getDimension()*Maze.getDimension() && Maze.getDimension()*CurrentCoordinates.get(1)+(CurrentCoordinates.get(0)-1)>=0 && CurrentCoordinates.get(0)-1 >= 0){
				if(TheMaze.get((Maze.getDimension()*CurrentCoordinates.get(1)+(CurrentCoordinates.get(0)-1))).isFree()){
					
					Node NodeLeft = TheMaze.get((Maze.getDimension()*CurrentCoordinates.get(1)+(CurrentCoordinates.get(0)-1)));
					for(int i=0;i<XY_Coordinates_Open.size();i++){
						if(NodeLeft.getCoordinates().get(0)== XY_Coordinates_Open.get(i).getCoordinates().get(0)&& NodeLeft.getCoordinates().get(1) == XY_Coordinates_Open.get(i).getCoordinates().get(1)){
							flag2 = false;
						}
					}
					for(int i=0;i<XY_Coordinates_Close.size();i++){
						if(NodeLeft.getCoordinates().get(0)== XY_Coordinates_Close.get(i).getCoordinates().get(0)&& NodeLeft.getCoordinates().get(1) == XY_Coordinates_Close.get(i).getCoordinates().get(1)){
							flag3 = false;
						}
					}
					if(flag2&&flag3){
						NodeLeft.setFather(CurrentNode);
						NodeLeft.setG(CurrentNode.getG()+0.5);
						double g = NodeLeft.getG();
						double aH = NodeLeft.getH();
						NodeLeft.setF(g+aH);
						XY_Coordinates_Open.add(NodeLeft);
						//System.out.println("Left");
					}else if(flag2==false&&flag3){
						if(NodeLeft.getG()>CurrentNode.getG()+0.5){
							NodeLeft.setFather(CurrentNode);
							NodeLeft.setG(CurrentNode.getG()+0.5);
							double g = NodeLeft.getG();
							double aH = NodeLeft.getH();
							NodeLeft.setF(g+aH);
						}
						
					}	
				}
			}
			
			flag2 = true;
			flag3 = true;
			if(Maze.getDimension()*(CurrentCoordinates.get(1)+1)+(CurrentCoordinates.get(0))<Maze.getDimension()*Maze.getDimension() && Maze.getDimension()*(CurrentCoordinates.get(1)+1)+(CurrentCoordinates.get(0))>=0 && CurrentCoordinates.get(1)+1 < Maze.getDimension()){
				if(TheMaze.get((Maze.getDimension()*(CurrentCoordinates.get(1)+1)+(CurrentCoordinates.get(0)))).isFree()){
					
					Node NodeUp = TheMaze.get((Maze.getDimension()*(CurrentCoordinates.get(1)+1)+(CurrentCoordinates.get(0))));
					for(int i=0;i<XY_Coordinates_Open.size();i++){
						if(NodeUp.getCoordinates().get(0)== XY_Coordinates_Open.get(i).getCoordinates().get(0)&& NodeUp.getCoordinates().get(1) == XY_Coordinates_Open.get(i).getCoordinates().get(1)){
							flag2 = false;
						}
					}
					for(int i=0;i<XY_Coordinates_Close.size();i++){
						if(NodeUp.getCoordinates().get(0)== XY_Coordinates_Close.get(i).getCoordinates().get(0)&& NodeUp.getCoordinates().get(1) == XY_Coordinates_Close.get(i).getCoordinates().get(1)){
							flag3 = false;
						}
					}
					if(flag2&&flag3){
						NodeUp.setFather(CurrentNode);
						NodeUp.setG(CurrentNode.getG()+1);
						double g = NodeUp.getG();
						double aH = NodeUp.getH();
						NodeUp.setF(g+aH);
						XY_Coordinates_Open.add(NodeUp);
						//System.out.println("Up");
					}else if(flag2==false&&flag3){
						if(NodeUp.getG()>CurrentNode.getG()+1){
							NodeUp.setFather(CurrentNode);
							NodeUp.setG(CurrentNode.getG()+1);
							double g = NodeUp.getG();
							double aH = NodeUp.getH();
							NodeUp.setF(g+aH);
						}
					}	
				}
			}
			
			flag2 = true;
			flag3 = true;
			if(Maze.getDimension()*(CurrentCoordinates.get(1)-1)+(CurrentCoordinates.get(0))<Maze.getDimension()*Maze.getDimension() && Maze.getDimension()*(CurrentCoordinates.get(1)-1)+(CurrentCoordinates.get(0))>=0 && CurrentCoordinates.get(1)-1 >= 0){
				if(TheMaze.get((Maze.getDimension()*(CurrentCoordinates.get(1)-1)+(CurrentCoordinates.get(0)))).isFree()){
					
					Node NodeDown = TheMaze.get((Maze.getDimension()*(CurrentCoordinates.get(1)-1)+(CurrentCoordinates.get(0))));
					for(int i=0;i<XY_Coordinates_Open.size();i++){
						if(NodeDown.getCoordinates().get(0)== XY_Coordinates_Open.get(i).getCoordinates().get(0)&& NodeDown.getCoordinates().get(1) == XY_Coordinates_Open.get(i).getCoordinates().get(1)){
							flag2 = false;
						}
					}
					for(int i=0;i<XY_Coordinates_Close.size();i++){
						if(NodeDown.getCoordinates().get(0)== XY_Coordinates_Close.get(i).getCoordinates().get(0)&& NodeDown.getCoordinates().get(1) == XY_Coordinates_Close.get(i).getCoordinates().get(1)){
							flag3 = false;
						}
					}
					if(flag2&&flag3){
						NodeDown.setFather(CurrentNode);
						NodeDown.setG(CurrentNode.getG()+1);
						double g = NodeDown.getG();
						double aH = NodeDown.getH();
						NodeDown.setF(g+aH);
						XY_Coordinates_Open.add(NodeDown);
						//System.out.println("Down");
					}else if(flag2==false&&flag3){
						if(NodeDown.getG()>CurrentNode.getG()+1){
							NodeDown.setFather(CurrentNode);
							NodeDown.setG(CurrentNode.getG()+1);
							double g = NodeDown.getG();
							double aH = NodeDown.getH();
							NodeDown.setF(g+aH);
							//System.out.println(NodeDown.getF());
						}
					}	
				}
			}
			
			flag2 = true;
			flag3 = true;
			
			/*for(int i=0;i<XY_Coordinates_Open.size();i++){
				System.out.println("$"+XY_Coordinates_Open.get(i).getCoordinates().get(0)+XY_Coordinates_Open.get(i).getCoordinates().get(1)+"$");
			}*/
			for(int i=0;i<XY_Coordinates_Close.size();i++){
				//System.out.println(flag);
				if(G1x == XY_Coordinates_Close.get(i).getCoordinates().get(0)&& G1y == XY_Coordinates_Close.get(i).getCoordinates().get(1) || XY_Coordinates_Open.size() == 0){
					//System.out.println(flag);
					flag = false;
					//System.out.println(flag);
				}
			}
		}	
	}
	
	public ArrayList<Node> printPath(){
		ArrayList<Node> OnlyPathNodes = new ArrayList<Node>();
		if(XY_Coordinates_Open.isEmpty()){
			System.out.println("There is no Path from"+"{"+Sx+","+Sy+"}"+" to "+"{"+G1x+","+G1y+"}");
			OnlyPathNodes = null;
			return OnlyPathNodes;
		}
		//System.out.println("The Solution Path is:\n");
		
		boolean flag4 = true;
		//if(XY_Coordinates_Close.get(XY_Coordinates_Close.size()-1))
		OnlyPathNodes.add(XY_Coordinates_Close.get(XY_Coordinates_Close.size()-1));
		Node PFather = XY_Coordinates_Close.get(XY_Coordinates_Close.size()-1).getFather();
		if(PFather!=null ){
			while(flag4){
				if(PFather.getCoordinates().get(0) == Sx && PFather.getCoordinates().get(1) == Sy){
					flag4 = false;
				}
				OnlyPathNodes.add(PFather);
				PFather = PFather.getFather();	
			}
		}
		String Path = "";
		boolean isIn = false;
		boolean isInOpen =false;
		for(int i=0;i<Maze.getDimension();i++){
			Path = Path + "\n";
			for(int j=0;j<Maze.getDimension();j++){
				isIn = false;
				isInOpen = false;
				for(int k=0;k<OnlyPathNodes.size();k++){
					if(OnlyPathNodes.get(k).getCoordinates().get(0) == j && OnlyPathNodes.get(k).getCoordinates().get(1) == i){
						isIn = true;
					}
				}
				for(int t=0;t<XY_Coordinates_Open.size();t++){
					if(XY_Coordinates_Open.get(t).getCoordinates().get(0) == j && XY_Coordinates_Open.get(t).getCoordinates().get(1) == i){
						isInOpen = true;
					}
				}
				if(isInOpen){
					Path = Path + "# ";
				}else if(j==Sx && i==Sy){
					Path = Path + "S ";
				}else if(j==G1x && i==G1y){
					Path = Path + "G ";
				}else if(isIn){
					Path = Path + "* ";
				}else if(Maze.getMaze().get(i).get(j) == 0){
					Path = Path +"0 ";
				}else{
					Path = Path + "1 ";
				}
			}
		}
		
		System.out.println("\nThe Solution Path is:\n"+Path);
		return OnlyPathNodes;	
	}
	
	public void printHolePath(ArrayList<Node> Path1, ArrayList<Node> Path2){
		String Path = "";
		boolean isIn1 = false;
		boolean isIn2 = false;
		if(Path1==null || Path2==null){
			System.out.println("There is no path");
			
		}else{
			for(int i=0;i<Maze.getDimension();i++){
				Path = Path + "\n";
				for(int j=0;j<Maze.getDimension();j++){
					isIn1 = false;
					isIn2 = false;
					for(int k=0;k<Path1.size();k++){
						if(Path1.get(k).getCoordinates().get(0) == j && Path1.get(k).getCoordinates().get(1) == i){
							isIn1 = true;
						}
					}
					for(int k=0;k<Path2.size();k++){
						if(Path2.get(k).getCoordinates().get(0) == j && Path2.get(k).getCoordinates().get(1) == i){
							isIn2 = true;
						}
					}
					if(Path1.get(Path1.size()-1).getCoordinates().get(0)==j && Path1.get(Path1.size()-1).getCoordinates().get(1)==i){
						Path = Path + "S ";
					}else if((Path1.get(0).getCoordinates().get(0)==j && Path1.get(0).getCoordinates().get(1)==i )|| (Path2.get(0).getCoordinates().get(0)==j && Path2.get(0).getCoordinates().get(1)==i) ){
						Path = Path + "G ";
					}else if(isIn1 || isIn2){
						Path = Path + "* ";
					}else if(Maze.getMaze().get(i).get(j) == 0){
						Path = Path +"0 ";
					}else{
						Path = Path + "1 ";
					}
				}
			}
			
			System.out.println("The Solution Path is:\n"+Path+"\n");
			double SumG1 = 0;
			double SumG2 = 0;
			for(int i=0;i<Path1.size();i++){
				SumG1 = SumG1 + Path1.get(i).getG();
			}
			for(int i=0;i<Path2.size();i++){
				SumG2 = SumG2 + Path2.get(i).getG();
			}
			System.out.println("The Cost of the Path is: " +(SumG1+SumG2));
		}
		
		
	}
	
	public ArrayList<Node> shortest(ArrayList<Node> Path1, ArrayList<Node> Path2){
		if(Path1==null||Path2==null){
			return null;
		}
		double SumG1 = 0;
		double SumG2 = 0;
		for(int i=0;i<Path1.size();i++){
			SumG1 = SumG1 + Path1.get(i).getG();
		}
		for(int i=0;i<Path2.size();i++){
			SumG2 = SumG2 + Path2.get(i).getG();
		}
		if(SumG2<SumG1){
			return Path2;
		}
		return Path1;
	}
	
	public ArrayList<Node> longest(ArrayList<Node> Path1, ArrayList<Node> Path2){
		if(Path1==null||Path2==null){
			return null;
		}
		double SumG1 = 0;
		double SumG2 = 0;
		for(int i=0;i<Path1.size();i++){
			SumG1 = SumG1 + Path1.get(i).getG();
		}
		for(int i=0;i<Path2.size();i++){
			SumG2 = SumG2 + Path2.get(i).getG();
		}
		if(SumG2>SumG1){
			return Path2;
		}
		return Path1;
	}
	
	/*public String toString(){
		String M ="";
		//System.out.println(H_Value.size());
		for(int i=0;i<XY_Coordinates_Close.size();i++){
			
		}
		for(int i=0;i<Maze.getDimension();i++){
			
			for(int j=0;j<Maze.getDimension();j++){
				Coordinates = new ArrayList<Integer>();
				//Coordinates2 = new Integer[2];
				Coordinates.add(j);
				Coordinates.add(i);
				//System.out.println(Coordinates2[0].toString());
				//System.out.println(H_Value.get(Coordinates)+"00000000");
				//M = M + H_Value.get(Coordinates)+"\n";
			}
			
		}
		return M;
	}*/
	
	public ArrayList<Node> getOpen(){
		return XY_Coordinates_Open;
	}
}
