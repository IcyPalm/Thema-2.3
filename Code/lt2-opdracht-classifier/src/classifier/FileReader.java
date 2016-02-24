package classifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FileReader {
	
	
	public static void main(String[] args) {
		new FileReader("TrainingSet.txt").generateTrainingsSet();
	}
	
	
	private String filename;
	private DecisionTree tree;
	
	public FileReader(String filename){
		this.filename = filename;
	}
	
	public HashMap<Item, String> generateTrainingsSet(){
		
        File file = new File(filename);
        Scanner scanner;
        
        ArrayList<String> featureList;
        
        featureList = readFeatures();
        
        try {
            scanner = new Scanner(file);
            scanner.useDelimiter(";");
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
            return null;
        }
        
        try{
        	int featuresAmount = 0;
        	int itemsAmount = 0;
        	
        	while(scanner.hasNextLine()){
        		String current = scanner.nextLine();        		
        		String[] parts = current.split(";");
        		
        		if(parts[0].equals("Features")){
        			featuresAmount = Integer.parseInt(parts[1]);
        			if(featuresAmount<=0){
        				throw new Exception("Amount of features is invalid, needs to be >0");
        			}
        			if(featuresAmount>featureList.size()){
        				throw new Exception("Given number of features is larger than featurelist");
        			}
        			
        		}
        	}
        }catch(Exception e){
        	System.err.println(e);
        }finally {
			scanner.close();
		}
        	
		return null;
	}

	private ArrayList<String> readFeatures() {
		File file = new File("features.txt");
        Scanner scanner = null;
        ArrayList<String> features = new ArrayList<>();
		try {
			scanner = new Scanner(file);
			while(scanner.hasNextLine()){
				features.add(new String(scanner.nextLine()));
			}
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
            return null;
        }finally {
			scanner.close();
		}
		return features;
	}
	
	
	
	//TODO: read amount items
	
	//TODO: Read items
}
