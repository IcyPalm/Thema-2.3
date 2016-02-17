package ui;
import multiformat.*;

import java.awt.FlowLayout;

import javax.swing.JFrame;

/**
 * De main-klasse die leest en schrijft mbv een GUI
 *
 */
public class GUI extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3387354141337032818L;
	Calculator calc=new Calculator();
  

    public static void main(String[] args) {
        new GUI().init();
    }

    public void init(){
    	this.setSize(500, 500);
    	setDefaultLookAndFeelDecorated(true);
    	setVisible(true);
    	this.setLayout(new FlowLayout());
        NumberController numbers = new NumberController(calc);
        this.getContentPane().add(numbers);
        OperandView opView = new OperandView();
        this.add(opView);
        calc.addActionListener(opView);
        repaint();
    }
}
