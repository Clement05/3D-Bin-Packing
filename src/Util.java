import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.LinkedList;

public class Util {

	//Function to generate list of parts from file input 
	//Format in file:
	//X1,Y1,Z1,X2,Y2,Z2
	//Where previous value define bound box
	public static LinkedList<Part> GeneratePartFromFileInput(String filePath){
		LinkedList<Part> res = new LinkedList<Part>();
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(new File(filePath));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		 BufferedReader br = new BufferedReader(fileReader);

		 String line = null;
		 // if no more lines the readLine() returns null
		 try {
			int id = 1;
			while ((line = br.readLine()) != null) {
				String[] split = line.split(",");
				Part p = new Part(id, Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), 
						Integer.parseInt(split[3]), Integer.parseInt(split[4]), Integer.parseInt(split[5]));
				res.add(p);
				id++;
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return res;
	}
	
	//Write console line in FreeCAD script
	public static void WriteFile(LinkedList<String> console){
		try{
		    PrintWriter writer = new PrintWriter("C:/Users/girarcle/AppData/Roaming/FreeCAD/Macro/Instance.FCMacro", "UTF-8");
		    for (String string : console) {
		    	writer.println(string);
			}
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
}
