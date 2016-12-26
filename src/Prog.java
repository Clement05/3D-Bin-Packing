import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

public class Prog { 

	public static void main(String[] args) {
		LinkedList<Part> allParts;
		LinkedList<String> res;
		
		allParts = Util.GeneratePartFromFileInput("C:/Users/girarcle/Desktop/instance2.txt");
		Chamber myChamber = new Chamber(0,0,0,0,10,10,10);
		
		res = myChamber.Fill(allParts);
		myChamber.DisplayIDofPartsInList();
		System.out.println(myChamber.allParts.size());
		Util.WriteFile(res);
		
		
		int numberOfParts = myChamber.allParts.size();
		while (numberOfParts < 11) {
			myChamber.allParts.clear();
			Collections.shuffle(allParts);
			res = myChamber.Fill(allParts);
			
			myChamber.DisplayIDofPartsInList();
			
			System.out.println(myChamber.allParts.size());
			numberOfParts = myChamber.allParts.size();
			
			if(numberOfParts == 11){
				Util.WriteFile(res);
			}
		}
	}
	

}
