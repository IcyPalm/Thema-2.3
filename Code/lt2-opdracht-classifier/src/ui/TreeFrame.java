package ui;


import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

import classifier.DecisionTree;
import classifier.FileReader;

public class TreeFrame {

	private static DecisionTree tree;
	private static TreeView treeView;

	public static void main(String s[]) {

		JFrame frame = new JFrame("JFrame Example");
		FileReader fileReader = new FileReader("trainingSet.txt");
		fileReader.generateTree();
        tree = fileReader.getTree();

        treeView = new TreeView(tree);

        JScrollPane mainView = new JScrollPane(treeView);
        mainView.setPreferredSize(new Dimension(800, 600));
        frame.add(mainView);
		frame.setSize(2000, 1100);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
