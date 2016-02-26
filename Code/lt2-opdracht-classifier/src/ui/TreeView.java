package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import classifier.DecisionTree;
import classifier.Node;

public class TreeView extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1844263639817047518L;
	private DecisionTree tree;

	public TreeView(DecisionTree tree){
		this.tree = tree;
		this.setPreferredSize(new Dimension(1920, 1080));
		this.setLayout(null);
		drawTree();
	}
	
	public void repaint(){
	}

	private void drawTree(){
		Node root = tree.getRoot();
		drawNode(root,(int)this.getPreferredSize().getWidth()/2,50,0,1);
		System.out.println();
	}
	
	private void drawNode(Node node, int x, int y, int xOffset, int depth) {
        JLabel label = new JLabel(node.getLabel());

        label.setBounds(x - 50, y, 100, 20);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(Color.cyan);
        label.setOpaque(true);
        
        if(node.isLeaf()){
        	label.setBackground(Color.green);
        }

        if(!node.isLeaf()) {
            Map<String, Node> arcs = node.getArcs();
            int children = arcs.size();
            int minX = (x - xOffset) / children;
            int stepX = (x - xOffset) / (children - 1);
            int step = 0;

            for (Iterator<String> iter = arcs.keySet().iterator(); iter.hasNext();) {
                String arcLabel = iter.next();
                Node dest = arcs.get(arcLabel);

                int newX = minX + (stepX * step);
                drawNode(dest, newX, y + 40, (stepX * step * depth), depth + 1);

                step++;
            }
        }

        this.add(label);
    }
}
