//package com.mxgraph.examples.swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Scanner;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.lang.*;

public class JGraphXRBT extends JFrame
{

	/**
	 *
	 */
	//private static final long serialVersionUID = -2707712944901661771L;
	private RedBlackTree<Integer, String> tree = new RedBlackTree<Integer, String>();
	private Boolean run = true;

	public JGraphXRBT()
	{
		super("Red Black Tree");

		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();
		// try
		// {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter your root:");
		int ans = Integer.parseInt(scan.nextLine());

    while(run){
			tree.put(ans, "");
			System.out.println("Do you want to add more nodes?");
			String input = scan.nextLine();
			if(input.equals("n")){
        run = false;
			}else{
				System.out.println("Please enter your nodes:");
				ans = Integer.parseInt(scan.nextLine());
			}
    }
		// setVisible(true);
		// tree.getRoot().mx(graph, 400, 20);
		//setVisible(true);
		// setVisible(false);
		//Deletion strats

			run = true;
			while(run){

				System.out.println("Would you like to delete node?(y/n)");
				if(scan.nextLine().equals("n")){
					run = false;
				}
				else{
					System.out.println("Enter node value");
					int input2 = Integer.parseInt(scan.nextLine());
					tree.delete(input2);
				}
			}
			setVisible(true);
			tree.getRoot().mx(graph, 400, 20);
					// 	setVisible(true);
					// }

		// }
		// finally
		// {
			graph.getModel().endUpdate();

		// }
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
		pack();
	}

	public static void main(String[] args)
	{
		JGraphXRBT frame = new JGraphXRBT();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 640);
		frame.setVisible(true);
	}

}
