package ui;

import java.util.Collection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;

import classifier.ItemBuilder;
import classifier.FeatureType;

public class FeatureController extends JPanel implements ActionListener {
  private ItemBuilder builder;
  private FeatureType type;

  public FeatureController(ItemBuilder builder, FeatureType type) {
    this.builder = builder;
    this.type = type;

    for (String value : type.allowedValues()) {
      JButton button = new JButton(value);
      button.addActionListener(this);
      this.add(button);
    }
  }

	@Override
  public void actionPerformed(ActionEvent e) {
    JButton source = (JButton) e.getSource();
    Collection<String> values = this.type.allowedValues();

    System.out.println(source.getText());

    if (values.contains(source.getText())) {
      this.builder.configureFeature(source.getText());
    }
  }
}
