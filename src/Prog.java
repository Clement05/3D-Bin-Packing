import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Prog { 

	public static void main(String[] args) {
		//list of part to fill in chamber
		LinkedList<Part> allParts;
		//Python line for FCMacro script
		LinkedList<String> res;
		//Define optimization var
		boolean optimize = true;
		
		for (String str : args) {
			String[] split = str.split("=");
			switch (split[0]) {
			case "optimize":
				optimize = Boolean.parseBoolean(split[1]);
				break;
			}
		}
		
		/*
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to optimize? Y/n:");
		String str = sc.nextLine();

		if(str.equals("Y")){
			optimize = true;
		} else if(str.equals("n")){
			optimize = false;
		} else{
			optimize = false;
		}
		*/
		
		//Populate list of part to fill in chamber from text file
		allParts = Util.GeneratePartFromFileInput("C:/Users/girarcle/Desktop/instance2.txt");
		//Define a new chamber
		Chamber myChamber = new Chamber(0,0,0,0,10,10,10);
		
		//Get Python line after filling
		res = myChamber.Fill(allParts);
		//Generate Python script
		Util.WriteFile(res);
		//Display order of part in Chamber
		myChamber.DisplayStringResult(allParts.size());
		
		//If oprimize == true
		if(optimize == true){
			//Set var number of parts in chamber
			int numberOfParts = myChamber.allParts.size();
			
			//while numberOfParts are below the number of part to fill
			while (numberOfParts < allParts.size()) {
				//Clear the list of already filled parts in Chamber
				myChamber.allParts.clear();
				//Shuffle the list of parts to fill
				Collections.shuffle(allParts);
				
				//Get Python line after filling
				res = myChamber.Fill(allParts);
				
				//Get the number of parts filled in Chamber
				numberOfParts = myChamber.allParts.size();
				//If the number of part filled in Chamber equals to number of part to fil
				if(numberOfParts == allParts.size()){
					//Generate Python script
					Util.WriteFile(res);
				}
				//Display order of part in Chamber
				myChamber.DisplayStringResult(allParts.size());	
			}
		}
	}
}
