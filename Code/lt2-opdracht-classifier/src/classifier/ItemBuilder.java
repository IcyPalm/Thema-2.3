package classifier;

import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemBuilder {
  private Item item;
  private Feature[] features;
  private int currentFeature = 0;

  public ItemBuilder(String name, Feature[] features) {
    this(new Item(name, features), features);
  }
  public ItemBuilder(Item item, Feature[] features) {
    this.item = item;
    this.features = features;
  }

  public Item getItem() {
    return this.item;
  }

  public boolean isFinished() {
    return this.currentFeature >= this.features.length;
  }

  public Feature getCurrentFeature() {
    if (this.isFinished()) {
      return null;
    }
    return this.features[this.currentFeature];
  }

  public void nextFeature() {
    this.currentFeature++;
  }

  public void configureFeature(String value) {
    if (this.isFinished()) {
      return;
    }
    String name = this.getCurrentFeature().getName();
    this.item.setFeatureValue(name, value);
    this.nextFeature();

    this.notifyListeners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
  }

  private List<ActionListener> listeners = new ArrayList<>();
  public void addActionListener(ActionListener l) {
    this.listeners.add(l);
  }
  public void removeActionListener(ActionListener l) {
    this.listeners.remove(l);
  }
  public List<ActionListener> getActionListeners() {
    return this.listeners;
  }
  private void notifyListeners(ActionEvent e) {
    for (ActionListener l : this.listeners) {
      l.actionPerformed(e);
    }
  }
}
