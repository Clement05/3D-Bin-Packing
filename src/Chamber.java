import java.util.LinkedList;

public class Chamber extends Box{
	
	public LinkedList<Part> allParts = new LinkedList<Part>();
	
	public Chamber(int id, int x1, int y1, int z1, int x2, int y2, int z2) {
		super(id, x1, y1, z1, x2, y2, z2);
	}

	public void Fill(LinkedList<Part> allParts){
		int currX = 0;
		int currY = 0;
		int currZ = 0;
		
		int maxY = 0;
		int maxZ = 0;
		
		for (int i = 0; i < allParts.size(); i++){

			if ( currX + (allParts.get(i).x2 - allParts.get(i).x1) <= 10){
				if ( currY + (allParts.get(i).y2 - allParts.get(i).y1) <= 10){
					if ( currZ + (allParts.get(i).z2 - allParts.get(i).z1) <= 10){
						this.allParts.add(allParts.get(i));
						maxY = Math.max(maxY, allParts.get(i).y2);
						maxZ = Math.max(maxY, allParts.get(i).z2);
						currX += allParts.get(i).x2 - allParts.get(i).x1;	
					}	
				}
				
				else {
					maxY = 0;
					currZ = maxZ;
					currZ += allParts.get(i).z2 - allParts.get(i).z1;
					currY = 0;
					currX = 0;
					this.allParts.add(allParts.get(i));
				}
			
			}	
			
			else {
				currY = maxY;
				currY += allParts.get(i).y2 - allParts.get(i).y1;
				currX = 0;
				this.allParts.add(allParts.get(i));
			}			
		}
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
