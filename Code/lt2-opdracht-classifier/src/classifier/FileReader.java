package classifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileReader {

	private String filename;
	private DecisionTree tree;
	private FeatureType types;
	
	public FileReader(String filename){
		this.filename = filename;
		types = new FeatureType("YesNo",new String[]{"nee","ja"});
	}
	
	
	
	
	public DecisionTree generateTree(){
		Map<Item, String> trainingSet = new HashMap<Item, String>();
		Map<String, FeatureType> featureSet = new HashMap<String, FeatureType>();
		
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
        			featureSet.clear();
        			for (int i = 0; i < featuresAmount; i++) {
						featureSet.put(featureList.get(i), types);
					}
        		}
        		
        		else if(parts[0].equals("Items")){
        			itemsAmount = Integer.parseInt(parts[1]);
        			if(itemsAmount<=0){
        				throw new Exception("There should be more than 0 items");
        			}
        		}
        		
        		
        		else{
        			if(parts.length != featuresAmount+2){
        				throw new Exception("Ivalid amount of features in line:\""+current+"\"");
        			}
        			
        			Feature[] featureArray = new Feature[featuresAmount];
        			
        			for (int i = 1; i < parts.length-1; i++) {
						String name = featureList.get(i-1);
						String value = parts[i];
						if(value.equals("0"))value = "nee";
						if(value.equals("1"))value = "ja";
						
						
						featureArray[i-1] = new Feature(name, value, types);
					}
        			Item item = new Item(parts[0], featureArray);
        			trainingSet.put(item, parts[parts.length-1]);
        		}
        	}
        }catch(Exception e){
        	System.err.println(e);
        }finally {
			scanner.close();
		}
        for (Item item : trainingSet.keySet()) {
			System.out.println(trainingSet.get(item));
		}
        
        tree = new DecisionTree(trainingSet, featureSet);
		return tree;
	}
	
	public DecisionTree getTree() {
		return tree;
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
	
	
	
	//TODO: read amount items and validate if correct, maybe loop different over file
}
