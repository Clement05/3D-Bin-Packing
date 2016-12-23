import java.util.Collections;
import java.util.LinkedList;

public class Prog { 

	public static void main(String[] args) {
		LinkedList<Part> allParts;
		
		allParts = Util.GeneratePartFromFileInput("C:/Users/girarcle/Desktop/instance1.txt");
		Chamber myChamber = new Chamber(0,0,0,0,10,10,10);
		
		myChamber.Fill(allParts);
		myChamber.DisplayIDofPartsInList();
		System.out.println(myChamber.allParts.size());
		
		
		int numberOfParts = myChamber.allParts.size();
		while (numberOfParts < 5) {
			myChamber.allParts.clear();
			Collections.shuffle(allParts);
			myChamber.Fill(allParts);
			
			myChamber.DisplayIDofPartsInList();
			
			System.out.println(myChamber.allParts.size());
			numberOfParts = myChamber.allParts.size();
		}
	}
	

}
