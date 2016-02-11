import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
    
public class HistoryView extends JPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4919945376128409188L;
    DobbelsteenModel d;
    ArrayList<Integer> tellers;
    int worpen = 0;
    TextArea veld;
    
	public HistoryView()
	{
	    this.setLayout(new GridLayout());
	    tellers = new ArrayList<>();
	    for (int i = 0; i < 6; i++) {
			tellers.add(new Integer(0));
		}
	    veld = new TextArea(7, 12);
	    this.add(veld);
	}
	
	public void actionPerformed( ActionEvent e )
	{
		worpen++;
	    d = (DobbelsteenModel) e.getSource();
	    tellers.set(d.getWaarde()-1, tellers.get(d.getWaarde()-1)+1);
	    String tekst = "Aantal worpen: "+worpen+"\n";
	    for (int i = 0; i < 6; i++) {
			 tekst = tekst + i + ": " + tellers.get(i).toString()+"\n";
		}
	    veld.setText(tekst);
	    
	}
	
	public Dimension getPreferredSize()
	{
	    return new Dimension(200,200);
	} 
}