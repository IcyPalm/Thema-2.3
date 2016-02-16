package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import multiformat.Calculator;

public class OperandView extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7646575643791035409L;
	
	
	private Calculator calc;
	private JTextField operand1;
	private JTextField operand2;
	private JTextField input;
	
	public OperandView() {
		operand1 = new JTextField(50);
		operand2 = new JTextField(50);
		input = new JTextField(50);
		add(operand1);
		add(operand2);
		add(input);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Trigger");
		if(e.getSource() instanceof Calculator){
			calc = (Calculator) e.getSource();
			operand1.setText(calc.firstOperand());
			operand2.setText(calc.secondOperand());
			System.out.println("Cool "+calc.getInput());
			input.setText(calc.getInput());
		}

	}

}
