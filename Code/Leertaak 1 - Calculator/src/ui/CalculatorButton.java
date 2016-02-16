package ui;

import javax.swing.AbstractButton;
import javax.swing.JButton;

public class CalculatorButton {
	
	protected AbstractButton button;
	protected String value;
	protected Type type;
	
	public enum Type{
		VALUE, OPERATOR, BASE, FORMAT
	}
	
	public CalculatorButton(String label, Type type){
		button = new JButton(label);
		this.value = label;
		this.type = type;
	}
	
	public void addInPanel(NumberController panel){
		panel.add(button);
		button.addActionListener(panel);
	}
	
	public AbstractButton getButton() {
		return button;
	}
	
	public String getText(){
		return value;
	}
}
