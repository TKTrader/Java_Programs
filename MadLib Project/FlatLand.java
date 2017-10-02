package MadLib;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class FlatLand extends JFrame {
	//---------------------------------------------------------------------------------
	// Program that writes a letter and story for a young child that is sick.  Asks for
	// user input and outputs a letter and story about Flatland using that input 
    //---------------------------------------------------------------------------------
	private JPanel contentPane;//instantiate panels and components and set to private
	private JTextPane textPane;
	private JLabel label;
	private JLabel label1;private JLabel label2;private JLabel label3;private JLabel label4;private JLabel label5;
	private JLabel label6;private JLabel label7;private JLabel label8;private JLabel label9;private JLabel label10;
	private JLabel label11;private JLabel label12;private JLabel label13;
	private JTextField input1;private JTextField input2;private JTextField input3;private JTextField input4;
	private JTextField input5;private JTextField input6;private JTextField input7;private JTextField input8;
	private JTextField input9;private JTextField input10;private JTextField input11;private JTextField input12;
	private JTextField input13;
	private JButton btnMadLibIt;
	private JLabel warning;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {//main method
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlatLand frame = new FlatLand();
					frame.setVisible(true);//make frame visible
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FlatLand() {
		setTitle("FlatLand");//title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//create first panel
		setBounds(50, 10, 1200, 980);//dimensions of entire pane
		contentPane = new JPanel();//create JPanel
		contentPane.setBackground(new Color(153,204,255));//set background color to light blue
		contentPane.setName("Flatland");//title
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//border
		setContentPane(contentPane);//add content
		contentPane.setLayout(null);//absolute layout
		
		//Title label
		label = new JLabel("");//Add output label for first name
		label.setForeground(Color.BLACK);//color of label
		label.setFont(new Font("Arial", Font.BOLD, 30));//font text
		label.setBounds(450, 20, 300, 25);//position of title
		label.setText("Flatland Mad Lib");//add title
		contentPane.add(label);//add to panel
		
		//First Name label
		label1 = new JLabel("");//Add output label for first name
		label1.setForeground(Color.BLACK);//color of label
		label1.setFont(new Font("Arial", Font.BOLD, 18));//font text
		label1.setBounds(150, 60, 220, 25);//position of label
		label1.setText("What is your first name?");//add initial instructions
		contentPane.add(label1);//add to panel
		//First Name text field
		input1=new JTextField();//First Name text input
		input1.setFont(new Font("Arial", Font.BOLD, 18));//font
		input1.setBounds(650, 60, 300, 25);//size
		contentPane.add(input1);//add to panel
		
		//Pet Type label
		label2 = new JLabel("");//Add output label for pet type
		label2.setForeground(Color.BLACK);//color of label
		label2.setFont(new Font("Arial", Font.BOLD, 18));//font text
		label2.setBounds(150, 110, 300, 25);//position of label
		label2.setText("What is your favorite animal?");//add initial instructions
		contentPane.add(label2);//add to panel
		//pet Type text field		
		input2=new JTextField();//Pet type text input
		input2.setFont(new Font("Arial", Font.BOLD, 18));//font
		input2.setBounds(650, 110, 300, 25);//size
		contentPane.add(input2);//add to panel
		
		//Pet Name label
		label3 = new JLabel("");//Add output label for pet name
		label3.setForeground(Color.BLACK);//color of label
		label3.setFont(new Font("Arial", Font.BOLD, 18));//font text
		label3.setBounds(150, 160, 350, 25);//position of label
		label3.setText("What would you name your pet animal?");//add initial instructions
		contentPane.add(label3);//add to panel
		//Pet Name textfield				
		input3=new JTextField();//Pet name text input
		input3.setFont(new Font("Arial", Font.BOLD, 18));//font
		input3.setBounds(650, 160, 300, 25);//size
		contentPane.add(input3);//add to panel
		
		//Favorite Color label
		label4 = new JLabel("");//Add output label for Favorite Color
		label4.setForeground(Color.BLACK);//color of label
		label4.setFont(new Font("Arial", Font.BOLD, 18));//font text
		label4.setBounds(150, 210, 350, 25);//position of label
		label4.setText("What is your favorite color?");//add initial instructions
		contentPane.add(label4);//add to panel
		//Favorite color textfield						
		input4=new JTextField();//Favorite Color text input
		input4.setFont(new Font("Arial", Font.BOLD, 18));//font
		input4.setBounds(650, 210, 300, 25);//size
		contentPane.add(input4);//add to panel
		
		//Least Favorite color label
		label5 = new JLabel("");//Add output label for Least Favorite color
		label5.setForeground(Color.BLACK);//color of label
		label5.setFont(new Font("Arial", Font.BOLD, 18));//font text
		label5.setBounds(150, 260, 350, 25);//position of label
		label5.setText("What is your least favorite color?");//add initial instructions
		contentPane.add(label5);//add to panel
		//least favorite color textfield								
		input5=new JTextField();//Least Favorite color text input
		input5.setFont(new Font("Arial", Font.BOLD, 18));//font
		input5.setBounds(650, 260, 300, 25);//size
		contentPane.add(input5);//add to panel
		
		//Favorite fruit label
		label6 = new JLabel("");//Add output label for favorite frui
		label6.setForeground(Color.BLACK);//color of label
		label6.setFont(new Font("Arial", Font.BOLD, 18));//font text
		label6.setBounds(150, 310, 350, 25);//position of label
		label6.setText("What is your favorite fruit?");//add initial instructions
		contentPane.add(label6);//add to panel
		//favorite fruit text field										
		input6=new JTextField();//favorite fruit text input
		input6.setFont(new Font("Arial", Font.BOLD, 18));//font
		input6.setBounds(650, 310, 300, 25);//size
		contentPane.add(input6);//add to panel
		
		//worst food label
		label7 = new JLabel("");//Add output label for worst food
		label7.setForeground(Color.BLACK);//color of label
		label7.setFont(new Font("Arial", Font.BOLD, 18));//font text
		label7.setBounds(150, 360, 350, 25);//position of label
		label7.setText("What is the worst tasting vegetable?");//add initial instructions
		contentPane.add(label7);//add to panel
		//worst food textfield
		input7=new JTextField();//worst food text input
		input7.setFont(new Font("Arial", Font.BOLD, 18));//font
		input7.setBounds(650, 360, 300, 25);//size
		contentPane.add(input7);//add to panel
		
		//shape label
		label8 = new JLabel("");//Add output label for shape
		label8.setForeground(Color.BLACK);//color of label
		label8.setFont(new Font("Arial", Font.BOLD, 18));//font text
		label8.setBounds(150, 410, 380, 25);//position of label
		label8.setText("Choose a shape! (Ex. circle,square)");//add initial instructions
		contentPane.add(label8);//add to panel
		//shape textfield												
		input8=new JTextField();//shape input
		input8.setFont(new Font("Arial", Font.BOLD, 18));//font
		input8.setBounds(650, 410, 300, 25);//size
		contentPane.add(input8);//add to panel
		
		//Second shape label
		label9 = new JLabel("");//Add output label for second shape
		label9.setForeground(Color.BLACK);//color of label
		label9.setFont(new Font("Arial", Font.BOLD, 18));//font text
		label9.setBounds(150, 460, 350, 25);//position of label
		label9.setText("Choose a second shape");//add initial instructions
		contentPane.add(label9);//add to panel
		//second shape textfield														
		input9=new JTextField();//second shape text input
		input9.setFont(new Font("Arial", Font.BOLD, 18));//font
		input9.setBounds(650, 460, 300, 25);//size
		contentPane.add(input9);//add to panel
		
		//adjective label
		label10 = new JLabel("");//Add output label for adjective
		label10.setForeground(Color.BLACK);//color of label
		label10.setFont(new Font("Arial", Font.BOLD, 18));//font text
		label10.setBounds(150, 510, 400, 25);//position of label
		label10.setText("Pick a positive adjective (Ex. happy,bouncy) ");//add initial instructions
		contentPane.add(label10);//add to panel
		//adjective text field																
		input10=new JTextField();//adjective text input
		input10.setFont(new Font("Arial", Font.BOLD, 18));//font
		input10.setBounds(650, 510, 300, 25);//size
		contentPane.add(input10);//add to panel
		
		//bad adjective label
		label11 = new JLabel("");//Add output label for bad adjective
		label11.setForeground(Color.BLACK);//color of label
		label11.setFont(new Font("Arial", Font.BOLD, 18));//font text
		label11.setBounds(150, 560, 400, 25);//position of label
		label11.setText("Pick a negative adjective (Ex. stinky,angry) ");//add initial instructions
		contentPane.add(label11);//add to panel
		//bad adjective text field																		
		input11=new JTextField();//Pet type text input
		input11.setFont(new Font("Arial", Font.BOLD, 18));//font
		input11.setBounds(650, 560, 300, 25);//size
		contentPane.add(input11);//add to panel
		
		//good smell label
		label12 = new JLabel("");//Add output label for good smell
		label12.setForeground(Color.BLACK);//color of label
		label12.setFont(new Font("Arial", Font.BOLD, 18));//font text
		label12.setBounds(150, 610, 350, 25);//position and size
		label12.setText("What is something that smells good?");//add initial instructions
		contentPane.add(label12);//add to panel
		//good smell text field																				
		input12=new JTextField();//Good smell text input
		input12.setFont(new Font("Arial", Font.BOLD, 18));//font text
		input12.setBounds(650, 610, 300, 25);//position and size
		contentPane.add(input12);//add to panel
		
		//bad smell label
		label13 = new JLabel("");//Add output label for bad smell
		label13.setForeground(Color.BLACK);//color of label
		label13.setFont(new Font("Arial", Font.BOLD, 18));//font text
		label13.setBounds(150, 660, 350, 25);//position and size
		label13.setText("What is something that smells bad?");//add initial instructions
		contentPane.add(label13);//add to panel
		//bad smell text field																						
		input13=new JTextField();//Bad smell text input
		input13.setFont(new Font("Arial", Font.BOLD, 18));//font text
		input13.setBounds(650, 660, 300, 25);//position and size
		contentPane.add(input13);//add to panel
		
		//create Mad Lib Button to output story
		btnMadLibIt = new JButton("Mad Lib it!");
		btnMadLibIt.setFont(new Font("Arial", Font.BOLD, 22));//font text
		btnMadLibIt.setBorder(BorderFactory.createRaisedBevelBorder());//add border
		btnMadLibIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//outputs story in html format
				
				//create second JPanel to overlay on frame
				textPane = new JTextPane();//create text panel for html text
				textPane.setBackground(new Color(222,184,135));//background color tan
				textPane.setBounds(0, 0, 1200, 940);//size
				textPane.setBorder(new EmptyBorder(5, 5, 5, 5));//border
				textPane.setEditorKit(new HTMLEditorKit());//add html capability
				textPane.setContentType("text/html");//format for html
				
				//Store user inputs for ease of writing story
			    String name = input1.getText(); String pet = input2.getText();String petName = input3.getText();
				String col1 = input4.getText();String col2 = input5.getText(); String fruit = input6.getText();
				String veg = input7.getText(); String shape1 = input8.getText();String shape2 = input9.getText(); 
				String adj1 = input10.getText();String adj2 = input11.getText(); String smell = input12.getText();
				String smell2 = input13.getText();//store user input
				//format strings for story
				name = name.substring(0,1).toUpperCase() +name.substring(1).toLowerCase();//make Proper name
				petName = petName.substring(0,1).toUpperCase() +petName.substring(1).toLowerCase();//make Proper name
				shape1 = shape1.substring(0,1).toUpperCase() +shape1.substring(1).toLowerCase();//make Proper Name	
				shape2 = shape2.substring(0,1).toUpperCase() +shape2.substring(1).toLowerCase();//make proper name
				//format some entries to make plural
				if (fruit.charAt(fruit.length() - 1) !='s'){//make fruit plural
					fruit=fruit.concat("s");
				}
				if (veg.charAt(veg.length() - 1) !='s'){//make vegetable plural
					veg = veg.concat("s");
				}
				if (smell.charAt(smell.length() - 1)!='s'){//make smell plural
					smell=smell.concat("s");
				}
				if (smell2.charAt(smell2.length() - 1)!='s'){//make bad smell plural
					smell2=smell2.concat("s");
				}
				
				//letter and story in html format
				textPane.setText("<hr><h1>To : "+name+"@gmail.com</h1>"+"<p><h1>From: "+shape1+"@flatland.com</h1></p><hr/>"
						+"<h2>SUBJECT: FLATLAND (TOP SECRET)<h2>"
						+"<h2>Dear "+name+",</h2>"+"<p><h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I heard you weren't feeling well, and I was surprised because I "
						+"have another friend named "+name+ " who also didn't feel well this morning! Let me tell you about the "
						+"strange world "+name+ " lives in. There is a hidden world called Flatland where two different kinds of "
						+"shapes live: the "+shape1+"s and the "+shape2+"s. These shapes are people just like us, except they are "
						+ "flat because Flatland is as thin as a piece of paper! The shapes live in two main cities: "+shape1+"town, and "+shape2+"ville. "
						+"Each shape is a specific color too. "+shape1+"s are "+col1+" because they like to eat "+col1+" "+fruit+", and "
						+shape2+"s are "+col2+" because they eat lots of "+col2+" "+veg+". Shapes also like to keep pets who have a "
						+ "special smell when you scratch them. The "+shape1+"s pride themselves on their "+adj1+" "+pet+"s who smell like "+smell+" while the "
						+shape2+"s keep "+adj2+" monkeys which smell like "+smell2+".</h2></p><p><h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;In "+shape1+"town there lives a young "
						+col1+" "+shape1+" named "+name+" who has a pet "+pet+" named "+petName+". This morning a strange thing happened! When "+name
						+" woke up, "+petName+" was not "+col1+" anymore, but had turned "+col2+"!! \"Oh, no!\" "+name+" said to "+petName
						+", \"How did this happen?\" Even worse, "+petName+" now smelled like moldy "+veg+"!! This was horrible! Just then, "
						+name+" had an idea. \"Maybe "+petName+" ate some bad food while I was asleep!\" Running straight to the"
						+" kitchen, "+name+" almost slipped on the evidence...a pile of half eaten moldy "+veg+" were sitting right there"
						+" on the kitchen floor. Luckily, "+name+" had just made a special trip to the store yesterday to stock up on "+col1+" "
						+fruit+". And "+petName+" jumped up on the counter and ate them without even asking!"
						+" In no time, "+petName+" turned "+col1+" again and smelt like "+smell+" again so they were both very happy! </p>"
						+"<p><h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I hope you enjoyed my story, "+name+", feel better!</h2></p><p><h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
						+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Your friend, Mr. "+shape1+"</h2></p>");
				
				setContentPane(textPane);//overlay new panel with button click
				
				//following commented code was used in failed attempt to create new panel, saved for future implementation
				//MadLibOutput screen = new MadLibOutput();
				//screen.NewPanel();
			}
		});
		btnMadLibIt.setBounds(475, 750, 200, 40);//position MadLib button
		contentPane.add(btnMadLibIt);//add MadLib Button
		
		//Title label
		warning = new JLabel("");//Add output label for first name
		warning.setForeground(Color.BLACK);//color of label
		warning.setFont(new Font("Arial", Font.BOLD, 18));//font text
		warning.setBounds(300, 800, 700, 25);//position of title
		warning.setText("Make sure you fill in all your answers before you click this button!");//add title
		contentPane.add(warning);//add to panel
	}
}
