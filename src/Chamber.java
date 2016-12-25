import java.util.LinkedList;

public class Chamber extends Box{
	
	public LinkedList<Part> allParts = new LinkedList<Part>();
	
	public Chamber(int id, int x1, int y1, int z1, int x2, int y2, int z2) {
		super(id, x1, y1, z1, x2, y2, z2);
	}

	public LinkedList<String> Fill(LinkedList<Part> allParts){
		LinkedList<String> console = new LinkedList<String>();
		int currX = 0;
		int currY = 0;
		int currZ = 0;
		
		int maxX = 0;
		int maxY = 0;
		int maxZ = 0;
		
		int minX = 0;
		int minY = 0;
		int minZ = 0;
		
		int nextX = 0;
		int nextY = 0;
		int nextZ = 0;
		
		int remainX = 10;
		int remainY = 10;
		int remainZ = 10;
		
		console.add("doc = App.ActiveDocument");
		for (int i = 0; i < allParts.size(); i++){
			
			if(IsSpaceOnX(allParts.get(i), remainX)){
				if(IsSpaceOnY(allParts.get(i), remainY)){
					if(IsSpaceOnZ(allParts.get(i), remainZ)){
						
						GeneratePythonLine(allParts.get(i), console);
						this.allParts.add(allParts.get(i));
						GeneratePythonLine(allParts.get(i), console, currX, currY, currZ);
						
						//add on x
						nextX = allParts.get(i).x2;
						nextY = Math.max(nextY, allParts.get(i).y2);
						nextZ = Math.max(nextZ, allParts.get(i).z2);
						
						remainX -= nextX;
						currX += allParts.get(i).x2;
					}
				} else {
					remainY = 10;
					remainZ -= nextZ;
					remainX = 10;
					currY = 0;
					currZ = nextZ;
					currX = 0;
					if(IsSpaceOnZ(allParts.get(i), remainZ)){
						System.out.println("Change layer");
						
						GeneratePythonLine(allParts.get(i), console);
						this.allParts.add(allParts.get(i));
						GeneratePythonLine(allParts.get(i), console, currX, currY, currZ);
						
						//add on x
						nextX = allParts.get(i).x2;
						nextY += allParts.get(i).y2;
						nextZ += allParts.get(i).z2;
						
						remainX -= nextX;
					}
				}
			} else {
				remainY = 10 - nextY;
				remainZ = 10 - nextZ;
				remainX = 10;
				currY = nextY;
				currZ = 0;
				currX = 0;			
				if(IsSpaceOnY(allParts.get(i), remainY)){
					System.out.println("Change row");
					if(IsSpaceOnZ(allParts.get(i), remainZ)){
						
						GeneratePythonLine(allParts.get(i), console);
						this.allParts.add(allParts.get(i));
						GeneratePythonLine(allParts.get(i), console, currX, currY, currZ);
						
						//add on x
						nextX = allParts.get(i).x2;
						nextY += allParts.get(i).y2;
						nextZ = Math.max(nextZ, allParts.get(i).z2);
						
						remainX -= nextX;
						currX = allParts.get(i).x2;
					}
				} else {
					remainY = 10;
					remainZ = 10 - nextZ;
					remainX = 10;
					currY = 0;
					currZ = nextZ;
					currX = 0;
					if(IsSpaceOnZ(allParts.get(i), remainZ)){
						System.out.println("Change layer");
						
						GeneratePythonLine(allParts.get(i), console);
						this.allParts.add(allParts.get(i));
						GeneratePythonLine(allParts.get(i), console, currX, currY, currZ);
						
						//add on x
						nextX = allParts.get(i).x2;
						nextY += allParts.get(i).y2;
						nextZ += allParts.get(i).z2;
						
						remainX -= nextX;
						currX += allParts.get(i).x2;
					}
				}
			}
			/*
			console.add("box"+allParts.get(i).id+" = doc.addObject('Part::Box','BOX_"+allParts.get(i).id+"')");
			console.add("box"+allParts.get(i).id+".Length = "+allParts.get(i).x2 *10 );
			console.add("box"+allParts.get(i).id+".Width = "+allParts.get(i).y2 * 10);
			console.add("box"+allParts.get(i).id+".Height = "+allParts.get(i).z2 * 10);
			
			if ( currX + (allParts.get(i).x2 - allParts.get(i).x1) <= 10){
				if ( currY + (allParts.get(i).y2 - allParts.get(i).y1) <= 10){
					if ( currZ + (allParts.get(i).z2 - allParts.get(i).z1) <= 10){
						this.allParts.add(allParts.get(i));
						maxY = Math.max(maxY, allParts.get(i).y2);
						maxZ = Math.max(maxZ, allParts.get(i).z2);
						console.add("box"+allParts.get(i).id+".Placement.Base.x = " + currX * 10);
						console.add("box"+allParts.get(i).id+".Placement.Base.y = " + currY * 10);
						console.add("box"+allParts.get(i).id+".Placement.Base.z = " + currZ * 10);
						currX += allParts.get(i).x2 - allParts.get(i).x1;	
					}	
				}
				
				else {

					maxY = 0;
					currZ = maxZ;
					currY = 0;
					currX = 0;
					
					console.add("box"+allParts.get(i).id+".Placement.Base.x = " + currX * 10);
					console.add("box"+allParts.get(i).id+".Placement.Base.y = " + currY * 10);
					console.add("box"+allParts.get(i).id+".Placement.Base.z = " + currZ * 10);
					
					currZ += allParts.get(i).z2 - allParts.get(i).z1;
					
					this.allParts.add(allParts.get(i));
				}
			
			}	
			
			else {
				currY = maxY;

				currX = 0;
				console.add("box"+allParts.get(i).id+".Placement.Base.x = " + currX * 10);
				console.add("box"+allParts.get(i).id+".Placement.Base.y = " + currY * 10);
				console.add("box"+allParts.get(i).id+".Placement.Base.z = " + currZ * 10);
				
				currY += allParts.get(i).y2 - allParts.get(i).y1;
				
				this.allParts.add(allParts.get(i));

				
			}	
			*/		
		}
		console.add("doc.recompute()");
		
		return console;
	}

	private void GeneratePythonLine(Part part, LinkedList<String> console, int currX, int currY, int currZ) {
		console.add("box"+part.id+".Placement.Base.x = " + currX * 10);
		console.add("box"+part.id+".Placement.Base.y = " + currY * 10);
		console.add("box"+part.id+".Placement.Base.z = " + currZ * 10);	
	}

	private void GeneratePythonLine(Part part, LinkedList<String> console) {
		console.add("box"+part.id+" = doc.addObject('Part::Box','BOX_"+part.id+"')");
		console.add("box"+part.id+".Length = "+part.x2 *10 );
		console.add("box"+part.id+".Width = "+part.y2 * 10);
		console.add("box"+part.id+".Height = "+part.z2 * 10);
	}

	private boolean IsSpaceOnY(Part part, int remainY) {
		int width = part.y2 - part.y1;
		if(remainY >= width){
			return true;
		}
		return false;
	}

	private boolean IsSpaceOnZ(Part part, int remainZ) {
		int height = part.z2 - part.z1;
		if(remainZ >= height){
			return true;
		}
		return false;
	}

	private boolean IsSpaceOnX(Part part, int remainX) {
		int length = part.x2 - part.x1;
		if(remainX >= length){
			return true;
		}
		return false;
	}

	public void DisplayIDofPartsInList() {
		String res = "";
		for (Part part : allParts) {
			res +=Integer.toString(part.id)+',';
		}
		res = res.substring(0, res.length() - 1);
		System.out.println(res);
	}

}
