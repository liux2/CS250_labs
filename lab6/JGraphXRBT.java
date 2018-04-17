//package com.mxgraph.examples.swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Scanner;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class JGraphXRBT extends JFrame
{

	/**
	 *
	 */
	//private static final long serialVersionUID = -2707712944901661771L;
	private RedBlack<Integer, String> tree = new RedBlack<Integer, String>();
	private Boolean run = true;

	public JGraphXRBT()
	{
		super("Red Black Tree");

		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		getContentPane().add(new JLabel(tree));
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(false);

		Scanner scan = new Scanner(System.in);

		graph.getModel().beginUpdate();
		try
		{
			System.out.println("Add root node");
        int input1 =scan.nextInt();

        while(run){

            tree.add(input1, "");
            System.out.println("Would you like to add another node?(y/n)");
            if(scan.next().equals("n")){
              run = false;
            }
            else{
              System.out.println("Enter node value");
              input1 = scan.nextInt();
            }
        }
        setVisible(true);

        //Deletion strats
        /*System.out.println("Would you like to delete a node?(y/n)");
        if(scan.next().equals("n")){}

        else{
          System.out.println("Enter node value");
          int input2 = scan.nextInt();

          while(run){
            tree.delete(input2, "");
            System.out.println("Would you like to delete another node?(y/n)");
            if(scan.next().equals("n")){
              run = false;
            }
            else{
              System.out.println("Enter node value");
              input2 = scan.nextInt();
            }
          }
        }*/

		}
		finally
		{
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
	}

	public static void main(String[] args)
	{
		/*JGraphXRBT frame =*/ new JGraphXRBT();
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setSize(400, 320);
		//frame.setVisible(true);
	}

}
