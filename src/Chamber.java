import java.util.LinkedList;

public class Chamber extends Box{
	
	public LinkedList<Part> allParts = new LinkedList<Part>();
	
	public Chamber(int id, int x1, int y1, int z1, int x2, int y2, int z2) {
		super(id, x1, y1, z1, x2, y2, z2);
	}

	public LinkedList<String> Fill(LinkedList<Part> allParts){
		LinkedList<String> console = new LinkedList<String>();
		//Current values for the local origin (only for displaying)
		int currX = 0;
		int currY = 0;
		int currZ = 0;
		
		//Local maximum value for a row
		int localMaxY = 0;
		//local maximum value for a layer
		int localMaxZ = 0;
		
		//Remaining sapce on axis
		int remainX = this.getX2();
		int remainY = this.getY2();
		int remainZ = this.getZ2();
		
		console.add("doc = App.ActiveDocument");
		
		for (int i = 0; i < allParts.size(); i++){
			
			//If there is space on X
			if(allParts.get(i).getX2() <= remainX){
				if(allParts.get(i).getY2() <= remainY){
					if(allParts.get(i).getZ2() <= remainZ){
						//Set local max Z
						localMaxZ = Math.max(localMaxZ,allParts.get(i).getZ2());
						//Set local max Y
						localMaxY = Math.max(localMaxY,allParts.get(i).getY2());
						
						//Add part to the chamber list
			            this.allParts.add(allParts.get(i));
			            
			            //Decrease the remain X value with the value of the added part
			            remainX -= allParts.get(i).getX2();
			            
			            //Generate Python lines for FreeCAD
			            GeneratePythonLine(allParts.get(i), console, currX, currY, currZ);
			            //Increment the value of current local origin with the length of the added part
			            currX += allParts.get(i).getX2();
			            
			            //Continue
			            continue;
					}
				}
			}
			
			//If there is no space on X
			//Update the remain Y value with the localMaxY value (corresponding to the local maximum of the current row)
			remainY -= localMaxY;
			//Reset current local origin X value to 0 because we are changing row or layer
			currX = 0;
			//If there is space on Y
			if(allParts.get(i).getY2() <= remainY){
				if(allParts.get(i).getZ2() <= remainZ){
					//Set the current local origin Y value with the localMaxY value 
					currY += localMaxY;
					//Continue to set localMaxZ with previous value
					localMaxZ = Math.max(localMaxZ,allParts.get(i).getZ2());
					//Reset the calculation of localMaxY because we are starting a new row
					localMaxY = Math.max(0,allParts.get(i).getY2());
					//Reset remainX value
					remainX = this.getX2();
					
					//Add part to the chamber list
		            this.allParts.add(allParts.get(i)); 
		            //Decrease the remain X value with the value of the added part
		            remainX -= allParts.get(i).getX2();
		            
		            //Generate Python lines for FreeCAD
		            GeneratePythonLine(allParts.get(i), console, currX, currY, currZ);
		            //Increment the value of current local origin with the length of the added part
		            currX += allParts.get(i).getX2();
		            
		            //Continue
		            continue;
				}
			}
			
			//If there is no space on X and Y
			//Update the remain Z value with the localMaxZ value (corresponding to the local maximum of the current layer)
			remainZ -= localMaxZ;
			if(allParts.get(i).getZ2() <= remainZ){
				//Set the current local origin Z value with the localMaxZ value 
				currZ += localMaxZ;
				//Reset current local origin Y value to 0 because we are changing layer
				currY = 0;
				//Reset the calculation of localMaxZ because we are starting a new layer
				localMaxZ = Math.max(0,allParts.get(i).getZ2());
				//Reset the calculation of localMaxY because we are starting a new row
				localMaxY = Math.max(0,allParts.get(i).getY2());
				//Reset remainX value
				remainX = this.getX2();
				//Reset remainY value
				remainY = this.getY2();
				
				//Add part to the chamber list
	            this.allParts.add(allParts.get(i));
	            
	            //Decrease the remain X value with the value of the added part
	            remainX -= allParts.get(i).getX2();
	            
	            //Generate Python lines for FreeCAD
	            GeneratePythonLine(allParts.get(i), console, currX, currY, currZ);
	            //Increment the value of current local origin with the length of the added part
	            currX += allParts.get(i).getX2();
	            
	            //Continue
	            continue;
			}
				
		}
		console.add("doc.recompute()");
		
		return console;
	}

	private void GeneratePythonLine(Part part, LinkedList<String> console, int currX, int currY, int currZ) {
		console.add("box"+part.id+" = doc.addObject('Part::Box','BOX_"+part.id+"')");
		console.add("box"+part.id+".Length = "+part.x2 *10 );
		console.add("box"+part.id+".Width = "+part.y2 * 10);
		console.add("box"+part.id+".Height = "+part.z2 * 10);
		console.add("box"+part.id+".Placement.Base.x = " + currX * 10);
		console.add("box"+part.id+".Placement.Base.y = " + currY * 10);
		console.add("box"+part.id+".Placement.Base.z = " + currZ * 10);	
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
