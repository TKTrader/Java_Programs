import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TrianglePanel extends JPanel{
	//******************************************************************************************
	//  TrianglePanel.java  Author: Thomson Kneeland  Sect: CS112_11SU17  
	//  see main method class for comments: SierpinskiTriangle.java
	//******************************************************************************************
	
	   private final int MIN_VALUE = 0;
	   private int MAX_VALUE = 10;
	   private final int INIT_VALUE = 0;
	   private final int TICK_SPACING = 1;
	   private final int TRIANGLE_BORDER = 170;
	   private final static int npoints = 3;//number of sides of triangle for fillpolygon()
	   private static int number;//number of levels to display on Sierpinski Triangle
	   
	
	public TrianglePanel(){
		//set up display
		setBackground(new Color(153,204,255));//set background color to light blue
		setName("Sierpinski Triangle");//title
		setBorder(new EmptyBorder(5, 5, 5, 5));//border
		setPreferredSize(new Dimension(1000,1000));
		setLayout(null);//absolute layout
		
		//set slider title
		JLabel title = new JLabel("Sierpinski Triangle");
		title.setFont(new Font("Arial", Font.BOLD, 30));//font text
		title.setBounds(10, 150, 400, 100);
		this.add(title);
		
		//set slider title
		JLabel sliderTitle = new JLabel("# of Sierpinski levels");
		sliderTitle.setBounds(10, 175, 200, 200);
		this.add(sliderTitle);
		
		//set up slider
		JSlider slider = new JSlider(JSlider.VERTICAL, MIN_VALUE, MAX_VALUE, INIT_VALUE);
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    slider.setMajorTickSpacing(TICK_SPACING);
	    slider.setSnapToTicks(true);
	    slider.setBounds(10, 300, 100, 200);
	    this.add(slider);
	    SliderListener listener = new SliderListener();//create and add listener
	    slider.addChangeListener(listener);
	    
	    //for console input, uncomment
//	    Scanner input = new Scanner(System.in);
//	    System.out.println("Please select how many levels of the Sierpinski triangle you want to view:");
//	    number = input.nextInt();
	  
	}
	//Prints Graphics
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//get bounds of screen for resizing
	    Rectangle r = super.getBounds();
	    //create starting dimensions for triangle, expandable
	    int x = TRIANGLE_BORDER;
	    int x1 = r.width-TRIANGLE_BORDER;
	    int y = 100;
	    int y1 = triangle(y,x,x1);
	    
	    //last attempt to change Max value of slider with size of screen width. FAIL!
//	    MAX_VALUE=(int)r.width;
	    
	   //draw initial triangle
		int initXPoints[] = {midpoint(x,x1),x,x1};
		int initYPoints[] = {y,y1,y1};
		g.setColor(Color.black);
		g.fillPolygon(initXPoints,initYPoints,npoints);
		
		//draw recursive Sierpinski Triangles with "number" levels
		drawSierpinski(x,x1,y,y1,g,number);
	}
	//******************************************************************************************
	//given x coordinates of triangle, computes x coordinates where new triangle will be printed
	//******************************************************************************************
	public int[] SierpinskiX(int x, int x1) {
		int[] xpoints2 = { midpoint(x, x1), (3 * x + x1) / 4, (x + 3 * x1) / 4 };
		return xpoints2;
	}
	//******************************************************************************************
	//given x and y coordinates of triangle, computes y coordinates where new triangle will be printed
	//******************************************************************************************
	public int[] SierpinskiY(int y, int y1) {
		int[] ypoints2 = { y1, midpoint(y, y1), midpoint(y, y1) };
		return ypoints2;
	}

	//*******************************************************************************************
	// drawSierpinski method: draws levels of Sierpinski Triangle recursively
	//******************************************************************************************
	public void drawSierpinski(int x, int x1, int y, int y1, Graphics pane, int number) {
		//all necessary variables that store coordinates of next iteration of Triangle
		int x01 = x;
		int x02 = midpoint(x, midpoint(x, x1));
		int x03 = midpoint(x, x1);
		int x04 = midpoint(midpoint(x, x1), x1);
		int x05 = x1;
		int y01 = y;
		int y02 = midpoint(y1, y);
		int y03 = y1;
		pane.setColor(new Color(51,51,225));
		if (number==0){//base case
			return;
		}
		if (number == 1) {//2nd base case, iteratess on 1 triangle only
			pane.fillPolygon(SierpinskiX(x, x1), SierpinskiY(y, y1), npoints);
			number-=1;
		} else {//subsequent cases iterate on 3 triangles
			pane.fillPolygon(SierpinskiX(x, x1), SierpinskiY(y, y1), npoints);
			pane.fillPolygon(SierpinskiX(x01, x03), SierpinskiY(y02, y03), npoints);
			pane.fillPolygon(SierpinskiX(x02, x04), SierpinskiY(y01, y02), npoints);
			pane.fillPolygon(SierpinskiX(x03, x05), SierpinskiY(y02, y03), npoints);
			number-=1;
			drawSierpinski(x01, x03, y02, y03, pane, number);
			drawSierpinski(x02, x04, y01, y02, pane, number);
			drawSierpinski(x03, x05, y02, y03, pane, number);
		}
	}
			
	//******************************************************************************
	//triangle method : calculates height needed to make triangle equilateral 
	//*****************************************************************************
	public int triangle(int y, int x, int x1){
		return (int)(Math.round((double)y+((double)x1-(double)x)*Math.sqrt(3.0)/2));
	}
	//******************************************************************************
	//midpoint method : finds midpoint of any two x or y coordinates  
	//*****************************************************************************
	public int midpoint(int p, int q){
		return (p+q)/2;
	}
	//*****************************************************************************
	//SliderListener to retrieve number of levels of Sierpinski Triangle
	//*****************************************************************************
	private class SliderListener implements ChangeListener{
		public void stateChanged(ChangeEvent event){
			JSlider source = (JSlider)event.getSource();
			if(!source.getValueIsAdjusting()){
				return;
			}
			else{
				number = (int)source.getValue();
				repaint();
			}
		}
	}
}
