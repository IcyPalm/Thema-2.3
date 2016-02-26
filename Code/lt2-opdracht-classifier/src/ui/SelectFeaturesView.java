package ui;

import classifier.ItemBuilder;
import classifier.Feature;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class SelectFeaturesView extends JPanel {
  private ItemBuilder builder;

  public SelectFeaturesView(ItemBuilder builder) {
    this.builder = builder;

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    builder.addActionListener(e -> this.update());
    this.paint();
  }

	private void update() {
		this.paint();
		this.repaint();
	}

  private void paint() {
    this.removeAll();
    if (!this.builder.isFinished()) {
      Feature f = this.builder.getCurrentFeature();
      this.add(new FeatureView(f));
      this.add(new FeatureController(this.builder, f.type()));
    }
  }
}
