package ui;

import classifier.Classifier;
import classifier.ItemBuilder;
import classifier.Feature;
import classifier.FeatureType;
import classifier.FileReader;

import javax.swing.JFrame;

public class GUI extends JFrame {
	public static final int SELECTING = 0;
	public static final int RESULTS = 1;

	public static void main(String[] args) {
		String file = "TrainingSet.txt";
		if (args.length > 0) {
			file = args[0];
		}
    new GUI(file).setVisible(true);
	}

  private Feature[] features;
	private Classifier classifier;
  private ItemBuilder builder;
	private int state = -1;

  public GUI(String file) {
    this.setDefaultLookAndFeelDecorated(true);
		this.setSize(300, 150);

    this.readTreeAndFeatures(file);
		System.out.println(this.features.length);
    this.builder = new ItemBuilder("Car", this.features, this.classifier);

		this.builder.addActionListener(e -> this.update());
		this.paint();
  }

	private void update() {
		this.paint();
		this.repaint();
	}

	private void paint() {
		int newState = this.builder.isFinished() ? RESULTS : SELECTING;
		if (newState != this.state) {
			System.out.println("updating");
			if (newState == RESULTS) {
				System.out.println("Results: " + this.builder.getCategory());
				this.add(new ResultsView(this.features, this.builder.getCategory()));
			} else {
		    this.add(new SelectFeaturesView(this.builder));
			}
			this.state = newState;
		}
	}

	private void readTreeAndFeatures(String fileName) {
		FileReader fr = new FileReader(fileName);
		fr.generateTree();
		this.features = fr.createFeatures();
		this.classifier = fr.getTree();
	}

  private Feature[] makeFeatures() {
    FeatureType bool = new FeatureType("Bool", new String[] { "Yes", "No" });
    return new Feature[] {
      new Feature("AC", "Yes", bool),
      new Feature("ABS", "Yes", bool),
      new Feature("WiFi", "Yes", bool)
    };
  }
}
