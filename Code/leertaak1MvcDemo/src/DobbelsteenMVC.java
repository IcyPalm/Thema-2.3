import javax.swing.*;
import java.awt.*;

public class DobbelsteenMVC extends JApplet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8138325578397500407L;
	DobbelsteenModel model;             //het model
	TekstView tekstView;              // view
	DobbelsteenView dobbelsteenView;  // view
	DobbelsteenController controller;             // Controller
	
	public static void main(String[] args) {
		new DobbelsteenMVC().init();
	}
	
	public void init()
	{
		resize(250,200);
        
		// Maak het model
		model = new DobbelsteenModel();
        
        // Maak de controller en geef hem het model
		controller = new DobbelsteenController(model);
        controller.setBackground(Color.cyan);
        getContentPane().add(controller,BorderLayout.NORTH);
        
        // Maak de views
        dobbelsteenView = new DobbelsteenView(Color.red);
        dobbelsteenView.setBackground(Color.black);
        getContentPane().add(dobbelsteenView,BorderLayout.CENTER);
        tekstView = new TekstView();
        tekstView.setBackground(Color.green);
        getContentPane().add(tekstView,BorderLayout.SOUTH);
        
        // Registreer de views bij het model
        model.addActionListener(tekstView);
        model.addActionListener(dobbelsteenView);
        
        // Eerste worp
        model.gooi();
	}
}
