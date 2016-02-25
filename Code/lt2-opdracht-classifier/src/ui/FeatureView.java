package ui;

import classifier.Feature;

import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class FeatureView extends JPanel {
  private Feature feature;
  public FeatureView(Feature feature) {
    this.feature = feature;

    this.add(new JLabel(feature.getName()));
  }
}
