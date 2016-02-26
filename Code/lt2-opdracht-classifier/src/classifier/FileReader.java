package classifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileReader {
	public static final int FEATURE_TYPES = 0;
	public static final int FEATURES = 1;

	private String filename;
	private DecisionTree tree;
	private Collection<FeatureBlueprint> features;

	public FileReader(String filename){
		this.filename = filename;
	}

	public void generateTree(){
		Map<Item, String> trainingSet = new HashMap<Item, String>();
		Map<String, FeatureType> featureSet = new HashMap<String, FeatureType>();

        File file = new File(filename);
        Scanner scanner;

        ArrayList<FeatureBlueprint> featureList = readFeatures();

        try {
            scanner = new Scanner(file);
            scanner.useDelimiter(";");
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
            return;
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
						featureSet.put(featureList.get(i).name, featureList.get(i).type);
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
						FeatureBlueprint f = featureList.get(i-1);
						int valueIndex = Integer.parseInt(parts[i], 10);
						String value = f.type.allowedValues().get(valueIndex);

						featureArray[i-1] = new Feature(f.name, value, f.type);
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

    this.tree = new DecisionTree(trainingSet, featureSet);
		this.features = featureList;
	}

	public DecisionTree getTree() {
		return this.tree;
	}
	public Feature[] createFeatures() {
		return this.features.stream()
			.map(f -> new Feature(f.name, f.type.getDefaultValue(), f.type))
			.toArray(Feature[]::new);
	}

	private ArrayList<FeatureBlueprint> readFeatures() {
		File file = new File("features.txt");
        Scanner scanner = null;
	Map<String, FeatureType> featureTypes = new HashMap<>();
        ArrayList<FeatureBlueprint> features = new ArrayList<>();
		int state = FEATURE_TYPES;
		try {
			scanner = new Scanner(file);
			while(scanner.hasNextLine()){
				String line = new String(scanner.nextLine());
				if (line.equals("")) {
					state = FEATURES;
					continue;
				}
				if (state == FEATURE_TYPES) {
					String[] parts = line.split("=");
					String[] values = parts[1].split(";");
					featureTypes.put(parts[0], new FeatureType(parts[0], values));
				} else if (state == FEATURES) {
					String[] parts = line.split("=");
					FeatureType type = featureTypes.get(parts[1]);
					features.add(new FeatureBlueprint(parts[0], type));
				}
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

	private class FeatureBlueprint {
		public String name;
		public FeatureType type;
		public FeatureBlueprint(String name, FeatureType type) {
			this.name = name;
			this.type = type;
		}
	}

	//TODO: read amount items and validate if correct, maybe loop different over file
}
