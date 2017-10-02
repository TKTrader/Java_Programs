import javax.swing.JFrame;

public class SierpinskiTriangle {
	//******************************************************************************************
	//  SierpinskiTriangle.java  Author: Thomson Kneeland  Sect: CS112_11SU17   
	//  Program uses recursion to generate Sierpinski Triangle of n levels
	//  user inputs number of levels through slider in GUI
	//  frame (and image) is expandable, however I was unable to increase slider maximum
	//  and may not have internet for the rest of the weekend, so I am submitting while I have access.
	//  The program performs expected graphic output however there is an inefficiency/bug in the calculation
	//  that I have not found the source of logically (level 1 seems to disappear from recursive function)
	//  therefore I added it through brute force. A work in progress!
	//*********************************************************************************************
	public static void main(String[] args) {
		JFrame frame = new JFrame("Sierpinski Triangle");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setResizable(true);
	    TrianglePanel panel = new TrianglePanel();
		frame.getContentPane().add(panel);//add content
	    frame.pack();
	    frame.setVisible(true);
	}
}
