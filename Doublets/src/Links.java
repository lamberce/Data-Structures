import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Links class
 * 
 * @author lamberce. Created Feb 5, 2014.
 */
public class Links {
	private HashMap<String, HashSet<String>> link = new HashMap<String, HashSet<String>>();
	
	/**
	 * Contructs an object that relates words
	 *
	 * @param fileName
	 */

	public Links(String fileName) {
		try {
			fillLinks(fileName);
		} catch (FileNotFoundException exception) {
			System.out.println("File Not Found");
		}
	}

	private void fillLinks(String fileName) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(fileName));
		ArrayList<String> keys = new ArrayList<String>();
		while(sc.hasNextLine()){
			keys.add(sc.nextLine());
		}
		for(int j = 0; j < keys.size(); j++){
			HashSet<String> value = new HashSet<String>();
			for(int i = 0; i < keys.size(); i++){
				boolean shouldBeInLink = false;
				if(keys.get(j).length()==keys.get(i).length()){
					for(int k = 0; k < keys.get(j).length(); k++){
						String tempKey = keys.get(j).substring(k,k+1);
						String tempValue = keys.get(i).substring(k,k+1);
						if(!tempKey.equals(tempValue)&&!shouldBeInLink){
							shouldBeInLink = true;
						} else if(shouldBeInLink&&!tempKey.equals(tempValue)) {
							shouldBeInLink = false;
							break;
						}
					}
				}
				if(shouldBeInLink){
					value.add(keys.get(i));
				}
			}
			if(!value.isEmpty()){
				this.link.put(keys.get(j), value);
			}
		}
	}

	public HashSet getCandidates(String initial) {
		return this.link.get(initial);
	}

	public boolean contains(String s) {
		return this.link.containsKey(s);
	}
}
