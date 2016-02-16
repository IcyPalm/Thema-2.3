package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JPanel;

import multiformat.Base;
import multiformat.BinaryBase;
import multiformat.Calculator;
import multiformat.DecimalBase;
import multiformat.FixedPointFormat;
import multiformat.FloatingPointFormat;
import multiformat.Format;
import multiformat.FormatException;
import multiformat.HexBase;
import multiformat.OctalBase;
import multiformat.RationalFormat;

public class NumberController extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4201238921273473328L;


	private Calculator calc;
	private ArrayList<CalculatorButton> buttons;
	
	public NumberController(Calculator calc){
		this.calc = calc;
		buttons = new ArrayList<CalculatorButton>();
		
		buttons.add(new CalculatorButton("0", CalculatorButton.Type.VALUE));
		buttons.add(new CalculatorButton("1", CalculatorButton.Type.VALUE));
		buttons.add(new CalculatorButton("2", CalculatorButton.Type.VALUE));
		buttons.add(new CalculatorButton("3", CalculatorButton.Type.VALUE));
		buttons.add(new CalculatorButton("4", CalculatorButton.Type.VALUE));
		buttons.add(new CalculatorButton("5", CalculatorButton.Type.VALUE));
		buttons.add(new CalculatorButton("6", CalculatorButton.Type.VALUE));
		buttons.add(new CalculatorButton("7", CalculatorButton.Type.VALUE));
		buttons.add(new CalculatorButton("8", CalculatorButton.Type.VALUE));
		buttons.add(new CalculatorButton("9", CalculatorButton.Type.VALUE));
		
		buttons.add(new CalculatorButton("A", CalculatorButton.Type.VALUE));
		buttons.add(new CalculatorButton("B", CalculatorButton.Type.VALUE));
		buttons.add(new CalculatorButton("C", CalculatorButton.Type.VALUE));
		buttons.add(new CalculatorButton("D", CalculatorButton.Type.VALUE));
		buttons.add(new CalculatorButton("E", CalculatorButton.Type.VALUE));
		buttons.add(new CalculatorButton("F", CalculatorButton.Type.VALUE));
		
		buttons.add(new CalculatorButton("+", CalculatorButton.Type.OPERATOR));
		buttons.add(new CalculatorButton("-", CalculatorButton.Type.OPERATOR));
		buttons.add(new CalculatorButton("*", CalculatorButton.Type.OPERATOR));
		buttons.add(new CalculatorButton("/", CalculatorButton.Type.OPERATOR));
		buttons.add(new CalculatorButton(".", CalculatorButton.Type.OPERATOR));
		buttons.add(new CalculatorButton("=", CalculatorButton.Type.OPERATOR));
		
		buttons.add(new CalculatorButton("dec", CalculatorButton.Type.BASE));
		buttons.add(new CalculatorButton("bin", CalculatorButton.Type.BASE));
		buttons.add(new CalculatorButton("oct", CalculatorButton.Type.BASE));
		buttons.add(new CalculatorButton("hex", CalculatorButton.Type.BASE));
		
		buttons.add(new CalculatorButton("fixed", CalculatorButton.Type.FORMAT));
		buttons.add(new CalculatorButton("float", CalculatorButton.Type.FORMAT));
		buttons.add(new CalculatorButton("rat", CalculatorButton.Type.FORMAT));
		
		
		for(CalculatorButton button : buttons) {
            button.addInPanel(this);
        }
		
		calc.setBase(new DecimalBase());
		calc.setFormat(new FixedPointFormat() );
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton actionSourceButton = (JButton) e.getSource();
		CalculatorButton source = buttons.get(0);
		for(CalculatorButton button:buttons){
			if(button.button.equals(actionSourceButton)){
				source = button;
			}
		}
		
		Base base;
		
		if(source.getText().equals("dec")){
			base = new DecimalBase();
			calc.setBase(base);
		}
		if(source.getText().equals("bin")){
			base = new BinaryBase();
			calc.setBase(base);
			
		}
		if(source.getText().equals("oct")){
			base = new OctalBase();
			calc.setBase(base);
		}
		if(source.getText().equals("hex")){
			base = new HexBase();
			calc.setBase(base);
		}
		
		Format format;
		if(source.getText().equals("fixed")){
			format = new FixedPointFormat();
			calc.setFormat(format);
		}
		if(source.getText().equals("float")){
			format = new FloatingPointFormat();
			calc.setFormat(format);
		}
		if(source.getText().equals("rat")){
			format = new RationalFormat();
			calc.setFormat(format);
		}
		
		if(source.type == CalculatorButton.Type.VALUE || source.getText().equals(".")) {
            String input = calc.getInput();
            input += source.getText();
            calc.setInput(input);
            System.out.println("Jaj: "+calc.getInput());
        }

		if(source.getText().equals("=")){
			operandInput();
		}
		
		if(source.getText().equals("*")){
			operandInput();
			calc.multiply();
		}
		if(source.getText().equals("/")){
			operandInput();
			calc.divide();//TODO: divide by zero?
		}
		if(source.getText().equals("+")){
			operandInput();
			calc.add();
		}
		if(source.getText().equals("-")){
			operandInput();
			calc.subtract();
		}
	}


	private void operandInput() {
		if(calc.getInput().length() == 0)return;
		try {
			calc.addOperand(calc.getInput());
			calc.setInput("");
		} catch (FormatException e) {
			System.err.println("Something went wrong: " + e.getMessage());
		}
		
	}

	public Dimension getPreferredSize()
	{
	    return new Dimension(100,1000);
	} 
}
