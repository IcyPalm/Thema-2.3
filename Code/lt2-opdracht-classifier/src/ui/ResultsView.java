package ui;

import classifier.Feature;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ResultsView extends JPanel {
  public ResultsView(Feature[] features, String category) {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    this.add(new JLabel("Resultaat: "));
    for (Feature f : features) {
      this.add(new JLabel(f.toString()));
    }
    this.add(new JLabel(category));
  }
}
