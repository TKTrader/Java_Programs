package FinalProject;

import java.awt.*;//import for awt
import java.awt.event.*;
import javax.swing.*;//import for swing components
import javax.swing.border.*;//import for borders
import java.util.*;//import for ArrayList/Collections

public class SlidePuzzle extends JFrame {
	//-------------------------------------------------------------------------------------------
	//SlidePuzzle 
	//Author: Thomson Kneeland
	//This program creates a 3x3 slide puzzle which starts from a random shuffled position
	//History Button displays game moves
	//New Game starts new game and reshuffles board
	//when solved, dialog box displays to user that puzzle is solved
	//puzzle is implemented with a set of 9 tiles (buttons) on a gameboard (panel) set into a 2D array
	//program checks order of buttons for solution after each moved using checksolved()
	// Note: assignment requests "title" be larger than default, but not possible to change titlebar
	// It seemed redundant to add a second title below the default title, plus doing so would make 
	// visual aesthetics too cluttered
	//---------------------------------------------------------------------------------------------
	
	//initialize variables
	private JPanel contentPane;
	private JPanel gamePanel;//Button Panel
	private JButton[] solutionArray;//solution in one dimension for shuffle
	private JButton[][] gameArray;//game configuration
	private Border blackline;//border for Buttons
	private int counter = 0; //counter for number of moves
	private String labelText = "";//text for number of moves
	private JLabel label;//label for text counter
	private String historyText = "";//text for history
	private JTextArea text2;//textpane for history
	private final int BTNWIDTH=100;//button width
	private final int BTNHEIGHT=100;//button height
	private final int ROWS=3;//game rows
	private final int COLS=3;//game columns

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SlidePuzzle frame = new SlidePuzzle();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//method checkSolved() :  check if board solved
	public boolean checkSolved(){
		boolean solved = false;//boolean to assess if puzzle solved
		String s="";
		//put present game configuration into string format
		for (int i=0; i<3;i++){
	    	for (int j=0; j<3;j++){
	    		s=s+(gameArray[i][j].getText());
	    	}
	    }	
		//System.out.println(s); //to print present configuration to console
		
		//check if present game configuration is solution
		if (s.equals("12345678 ")){
			solved = true;
			
			//create dialog box to display puzzle solved
			JLabel text = new JLabel("you've solved the puzzle!");
		    text.setFont(new Font("Arial", Font.BOLD, 15));//font text
		    //create JOptionPane for dialog box
			JOptionPane box = new JOptionPane(text,JOptionPane.INFORMATION_MESSAGE);
			//create dialog box for setting location/size
			JDialog dialog = box.createDialog(null, "Solved!");
		    dialog.setLocation(100,200);
		    dialog.setSize(300,300);
		    dialog.setVisible(true);
		}
		return solved;
	}

	/**
	 * Create the frame.
	 */
	public SlidePuzzle() {

		//create JPanel for game
		setTitle("Slide Puzzle");//title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);//boundaries
		contentPane = new JPanel();//create JPanel for background
		contentPane.setBackground(new Color(153,204,255));//set background color to light blue
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);//null layout
		
		//create new panel/game board to hold buttons/tiles
		gamePanel = new JPanel();
		gamePanel.setBackground(Color.BLACK);
		gamePanel.setBounds(250, 25, 300, 300);//position of label
		gamePanel.setLayout(null);
		contentPane.add(gamePanel);
		
		//border for JButtons
		blackline = BorderFactory.createLineBorder(Color.black,5);
		
		//create history button
		JButton historyBtn = new JButton("Move History");
		historyBtn.setFont(new Font("Arial", Font.BOLD, 16));//font text
		historyBtn.setBounds(40, 260, 150, 30);
		historyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//create text Area for display
				text2 = new JTextArea(historyText);
			    text2.setFont(new Font("Arial", Font.BOLD, 15));//font text
			    text2.setBackground(new Color(153,204,255));//blue background
			    //create JOptionPane for dialog box
			    JScrollPane scrollPane = new JScrollPane(text2);
			    JOptionPane.showMessageDialog(contentPane, scrollPane,"Move History", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		contentPane.add(historyBtn);
		
		//create label for move counter
		label = new JLabel(labelText);
		label.setBounds(55, 165, 200, 20);
		label.setFont(new Font("Arial", Font.BOLD, 18));//font text
		contentPane.add(label);
		
		//create button for new game
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Arial", Font.BOLD, 20));//font text
		btnNewGame.setBounds(40, 60, 150, 30);
		btnNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {		
				counter = 0; //reset counter
				label.setText("");//reset counter text
				historyText = "";//reset history text
				
				//Shuffle:  represent each button position with integer in ArrayList, create unique shuffle order
				ArrayList<Integer> order = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8));
				Collections.shuffle(order);
				
				//create 3x3 array in starting format for game, shuffled order
				gameArray = new JButton[][]{{solutionArray[order.get(0)],solutionArray[order.get(1)],solutionArray[order.get(2)]},
					{solutionArray[order.get(3)],solutionArray[order.get(4)],solutionArray[order.get(5)]},
					{solutionArray[order.get(6)],solutionArray[order.get(7)],solutionArray[order.get(8)]}};
					
				//add shuffled buttons to game array
				for (int i=0; i<ROWS;i++){
					for (int j=0;j<COLS;j++){
						gameArray[i][j].setBounds(j*BTNWIDTH,i*BTNHEIGHT,BTNWIDTH,BTNHEIGHT);
						gameArray[i][j].setFont(new Font("Arial", Font.BOLD, 40));//font text
						gameArray[i][j].setBorder(blackline);//add border
						gamePanel.add(gameArray[i][j]);	
					}	
				}
			}
		});
		contentPane.add(btnNewGame);
		
		//Create Buttons with Numbers for Display
		JButton btn1 = new JButton("1");
		JButton btn2 = new JButton("2");
		JButton btn3 = new JButton("3");
		JButton btn4 = new JButton("4");
		JButton btn5 = new JButton("5");
		JButton btn6 = new JButton("6");
		JButton btn7 = new JButton("7");
		JButton btn8 = new JButton("8");
		JButton btn9 = new JButton(" ");
		btn9.setBackground(Color.BLACK);//make last button"empty"(black)
		
		//create array of buttons in solution order
		solutionArray = new JButton[]{btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9};
		
		//Shuffle:  represent each button position with integer in ArrayList, create unique shuffle order
		ArrayList<Integer> order = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8));
		Collections.shuffle(order);
		
		//create 3x3 array in starting format for game, using shuffled order
		gameArray = new JButton[][]{{solutionArray[order.get(0)],solutionArray[order.get(1)],solutionArray[order.get(2)]},
			{solutionArray[order.get(3)],solutionArray[order.get(4)],solutionArray[order.get(5)]},
			{solutionArray[order.get(6)],solutionArray[order.get(7)],solutionArray[order.get(8)]}};
			
		//add shuffled buttons to game array
		for (int i=0; i<ROWS;i++){
			for (int j=0;j<COLS;j++){
				gameArray[i][j].setBounds(j*BTNWIDTH,i*BTNHEIGHT,BTNWIDTH,BTNHEIGHT);
				gameArray[i][j].setFont(new Font("Arial", Font.BOLD, 40));//font text
				gameArray[i][j].setBorder(blackline);
				gamePanel.add(gameArray[i][j]);	
			}	
		}
//		//Print initial game array
//		System.out.println("gameArray is:");
//		for (int i=0; i<3;i++){
//    		for (int j=0; j<3;j++){
//    			System.out.print(gameArray[i][j].getText());
//    		}
//    	}
//		System.out.println();
		
		//create Listener for btn1
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int xPos=0,yPos=0,emptyXPos=0,emptyYPos=0;//variables for tile location
				boolean adjacent=false;//variable to store if tiles adjacent
			    for(int i = 0; i<3; i++){
			        for(int j = 0; j<3; j++){
			        	//get position of btn1
			            if(gameArray[i][j].equals(btn1)) {
			            	xPos = i;
			            	yPos = j;
			            }
			            //get position of empty button
			            if(gameArray[i][j].equals(btn9)) {
				    		emptyXPos = i;
				    		emptyYPos = j;
			            }
			        }
			    }    
			    //Test if empty button and button clicked are adjacent
			    if ((xPos==emptyXPos)&&((yPos==emptyYPos+1)||(yPos==emptyYPos-1))){
			    	adjacent=true;
			    }
			    if ((yPos==emptyYPos)&&((xPos==emptyXPos+1)||(xPos==emptyXPos-1))){
			    	adjacent=true;
			    }
			    //if buttons are adjacent, swap buttons
			    if (adjacent==true){
			    	counter +=1;//update move counter
			    	label.setText("# of Moves: "+counter);//update text of counter
			    	historyText += counter + ".  1 moved ("+xPos+","+yPos+") to ("+emptyXPos+","+emptyYPos+")\n";//update history text
			    	gameArray[emptyXPos][emptyYPos]=btn1;//move tile to new position
			    	gameArray[xPos][yPos]=btn9;//new empty tile position
			        //Redraw JPanel with new button layout
			    	for (int i=0; i<ROWS;i++){
						for (int j=0;j<COLS;j++){
							gameArray[i][j].setBounds(j*BTNWIDTH,i*BTNHEIGHT,BTNWIDTH,BTNHEIGHT);
							gameArray[i][j].setFont(new Font("Arial", Font.BOLD, 40));//font text
							gameArray[i][j].setBorder(blackline);
							gamePanel.add(gameArray[i][j]);	
						}	
					}
			    }
				checkSolved();	//check if solution found
			}
		});
		
		//create Listener for btn2
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int xPos=0, yPos=0, emptyXPos=0,emptyYPos=0;
				boolean adjacent = false;
			    for(int i = 0; i<3; i++){
			        for(int j = 0; j<3; j++){
			        	//get position of btn1
			            if(gameArray[i][j].equals(btn2)) {
			            	xPos = i;
			            	yPos = j;
			            }
			            //get position of empty button
			            if(gameArray[i][j].equals(btn9)) {
				    		emptyXPos = i;
				    		emptyYPos = j;
			            }
			        }
			    }    
			    //Test if empty button and button clicked are adjacent
			    if ((xPos==emptyXPos)&&((yPos==emptyYPos+1)||(yPos==emptyYPos-1))){
			    	adjacent=true;
			    }
			    if ((yPos==emptyYPos)&&((xPos==emptyXPos+1)||(xPos==emptyXPos-1))){
			    	adjacent=true;
			    }
			    //if buttons are adjacent, swap buttons
			    if (adjacent==true){
			    	counter +=1;//update move counter
			    	label.setText("# of Moves: "+counter);//update text of counter
			    	historyText += counter + ".  2 moved ("+xPos+","+yPos+") to ("+emptyXPos+","+emptyYPos+")\n";//update history text
			    	gameArray[emptyXPos][emptyYPos]=btn2;
			    	gameArray[xPos][yPos]=btn9;
			        //Redraw JPanel with new button layout
			    	for (int i=0; i<ROWS;i++){
						for (int j=0;j<COLS;j++){
							gameArray[i][j].setBounds(j*BTNWIDTH,i*BTNHEIGHT,BTNWIDTH,BTNHEIGHT);
							gameArray[i][j].setFont(new Font("Arial", Font.BOLD, 40));//font text
							gameArray[i][j].setBorder(blackline);
							gamePanel.add(gameArray[i][j]);	
						}	
					}
			    }
				checkSolved();//check if solution found
			}
		});
		
		//create Listener for btn3
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int xPos=0, yPos=0, emptyXPos=0,emptyYPos=0;
				boolean adjacent = false;
			    for(int i = 0; i<3; i++){
			        for(int j = 0; j<3; j++){
			        	//get position of btn3
			            if(gameArray[i][j].equals(btn3)) {
			            	xPos = i;
			            	yPos = j;
			            }
			            //get position of empty button
			            if(gameArray[i][j].equals(btn9)) {
				    		emptyXPos = i;
				    		emptyYPos = j;
			            }
			        }
			    }    
			    //Test if empty button and button clicked are adjacent
			    if ((xPos==emptyXPos)&&((yPos==emptyYPos+1)||(yPos==emptyYPos-1))){
			    	adjacent=true;
			    }
			    if ((yPos==emptyYPos)&&((xPos==emptyXPos+1)||(xPos==emptyXPos-1))){
			    	adjacent=true;
			    }
			    //if buttons are adjacent, swap buttons  WORKS
			    if (adjacent==true){
			    	counter +=1;//update move counter
			    	label.setText("# of Moves: "+counter);//update text of counter
			    	historyText += counter + ".  3 moved ("+xPos+","+yPos+") to ("+emptyXPos+","+emptyYPos+")\n";//update history text
			    	gameArray[emptyXPos][emptyYPos]=btn3;
			    	gameArray[xPos][yPos]=btn9;
			        //Redraw JPanel with new button layout
			    	for (int i=0; i<ROWS;i++){
						for (int j=0;j<COLS;j++){
							gameArray[i][j].setBounds(j*BTNWIDTH,i*BTNHEIGHT,BTNWIDTH,BTNHEIGHT);
							gameArray[i][j].setFont(new Font("Arial", Font.BOLD, 40));//font text
							gameArray[i][j].setBorder(blackline);
							gamePanel.add(gameArray[i][j]);	
						}	
					}
			    }
				checkSolved();	//check if solution found
			}
		});
		
		//create Listener for btn4
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int xPos=0, yPos=0, emptyXPos=0,emptyYPos=0;
				boolean adjacent = false;
			    for(int i = 0; i<3; i++){
			        for(int j = 0; j<3; j++){
			        	//get position of btn4
			            if(gameArray[i][j].equals(btn4)) {
			            	xPos = i;
			            	yPos = j;
			            }
			            //get position of empty button
			            if(gameArray[i][j].equals(btn9)) {
				    		emptyXPos = i;
				    		emptyYPos = j;
			            }
			        }
			    }    
			    //Test if empty button and button clicked are adjacent
			    if ((xPos==emptyXPos)&&((yPos==emptyYPos+1)||(yPos==emptyYPos-1))){
			    	adjacent=true;
			    }
			    if ((yPos==emptyYPos)&&((xPos==emptyXPos+1)||(xPos==emptyXPos-1))){
			    	adjacent=true;
			    }
			    //if buttons are adjacent, swap buttons
			    if (adjacent==true){
			    	counter +=1;//update move counter
			    	label.setText("# of Moves: "+counter);//update text of counter
			    	historyText += counter + ".  4 moved ("+xPos+","+yPos+") to ("+emptyXPos+","+emptyYPos+")\n";//update history text
			    	gameArray[emptyXPos][emptyYPos]=btn4;
			    	gameArray[xPos][yPos]=btn9;
			        //Redraw JPanel with new button layout
			    	for (int i=0; i<ROWS;i++){
						for (int j=0;j<COLS;j++){
							gameArray[i][j].setBounds(j*BTNWIDTH,i*BTNHEIGHT,BTNWIDTH,BTNHEIGHT);
							gameArray[i][j].setFont(new Font("Arial", Font.BOLD, 40));//font text
							gameArray[i][j].setBorder(blackline);
							gamePanel.add(gameArray[i][j]);	
						}	
					}
			    }
				checkSolved();//check if solution found
			}
		});

		//create Listener for btn5
		btn5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int xPos=0, yPos=0, emptyXPos=0,emptyYPos=0;
				boolean adjacent = false;
			    for(int i = 0; i<3; i++){
			        for(int j = 0; j<3; j++){
			        	//get position of btn5
			            if(gameArray[i][j].equals(btn5)) {
			            	xPos = i;
			            	yPos = j;
			            }
			            //get position of empty button
			            if(gameArray[i][j].equals(btn9)) {
				    		emptyXPos = i;
				    		emptyYPos = j;
			            }
			        }
			    }    
			    //Test if empty button and button clicked are adjacent
			    if ((xPos==emptyXPos)&&((yPos==emptyYPos+1)||(yPos==emptyYPos-1))){
			    	adjacent=true;
			    }
			    if ((yPos==emptyYPos)&&((xPos==emptyXPos+1)||(xPos==emptyXPos-1))){
			    	adjacent=true;
			    }
			    //if buttons are adjacent, swap buttons
			    if (adjacent==true){
			    	counter +=1;//update move counter
			    	label.setText("# of Moves: "+counter);//update text of counter
			    	historyText += counter + ".  4 moved ("+xPos+","+yPos+") to ("+emptyXPos+","+emptyYPos+")\n";//update history text
			    	gameArray[emptyXPos][emptyYPos]=btn5;
			    	gameArray[xPos][yPos]=btn9;
			        //Redraw JPanel with new button layout
			    	for (int i=0; i<ROWS;i++){
						for (int j=0;j<COLS;j++){
							gameArray[i][j].setBounds(j*BTNWIDTH,i*BTNHEIGHT,BTNWIDTH,BTNHEIGHT);
							gameArray[i][j].setFont(new Font("Arial", Font.BOLD, 40));//font text
							gameArray[i][j].setBorder(blackline);
							gamePanel.add(gameArray[i][j]);	
						}	
					}
			    }
				checkSolved();	//check if solution found
			}
		});
		
		//create Listener for btn6
		btn6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int xPos=0, yPos=0, emptyXPos=0,emptyYPos=0;
				boolean adjacent = false;
			    for(int i = 0; i<3; i++){
			        for(int j = 0; j<3; j++){
			        	//get position of btn6
			            if(gameArray[i][j].equals(btn6)) {
			            	xPos = i;
			            	yPos = j;
			            }
			            //get position of empty button
			            if(gameArray[i][j].equals(btn9)) {
				    		emptyXPos = i;
				    		emptyYPos = j;
			            }
			        }
			    }    
			    //Test if empty button and button clicked are adjacent
			    if ((xPos==emptyXPos)&&((yPos==emptyYPos+1)||(yPos==emptyYPos-1))){
			    	adjacent=true;
			    }
			    if ((yPos==emptyYPos)&&((xPos==emptyXPos+1)||(xPos==emptyXPos-1))){
			    	adjacent=true;
			    }
			    //if buttons are adjacent, swap buttons
			    if (adjacent==true){
			    	counter +=1;//update move counter
			    	label.setText("# of Moves: "+counter);//update text of counter
			    	historyText += counter + ".  6 moved ("+xPos+","+yPos+") to ("+emptyXPos+","+emptyYPos+")\n";//update history text
			    	gameArray[emptyXPos][emptyYPos]=btn6;
			    	gameArray[xPos][yPos]=btn9;
			        //Redraw JPanel with new button layout
			    	for (int i=0; i<ROWS;i++){
						for (int j=0;j<COLS;j++){
							gameArray[i][j].setBounds(j*BTNWIDTH,i*BTNHEIGHT,BTNWIDTH,BTNHEIGHT);
							gameArray[i][j].setFont(new Font("Arial", Font.BOLD, 40));//font text
							gameArray[i][j].setBorder(blackline);
							gamePanel.add(gameArray[i][j]);	
						}	
					}
			    }
				checkSolved();	//check if solution found
			}
		});
		
		//create Listener for btn7
		btn7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int xPos=0, yPos=0, emptyXPos=0,emptyYPos=0;
				boolean adjacent = false;
			    for(int i = 0; i<3; i++){
			        for(int j = 0; j<3; j++){
			        	//get position of btn7
			            if(gameArray[i][j].equals(btn7)) {
			            	xPos = i;
			            	yPos = j;
			            }
			            //get position of empty button
			            if(gameArray[i][j].equals(btn9)) {
				    		emptyXPos = i;
				    		emptyYPos = j;
			            }
			        }
			    }    
			    //Test if empty button and button clicked are adjacent
			    if ((xPos==emptyXPos)&&((yPos==emptyYPos+1)||(yPos==emptyYPos-1))){
			    	adjacent=true;
			    }
			    if ((yPos==emptyYPos)&&((xPos==emptyXPos+1)||(xPos==emptyXPos-1))){
			    	adjacent=true;
			    }
			    //if buttons are adjacent, swap buttons
			    if (adjacent==true){
			    	counter +=1;//update move counter
			    	label.setText("# of Moves: "+counter);//update text of counter
			    	historyText += counter + ".  7 moved ("+xPos+","+yPos+") to ("+emptyXPos+","+emptyYPos+")\n";//update history text
			    	gameArray[emptyXPos][emptyYPos]=btn7;
			    	gameArray[xPos][yPos]=btn9;
			        //Redraw JPanel with new button layout
			    	for (int i=0; i<ROWS;i++){
						for (int j=0;j<COLS;j++){
							gameArray[i][j].setBounds(j*BTNWIDTH,i*BTNHEIGHT,BTNWIDTH,BTNHEIGHT);
							gameArray[i][j].setFont(new Font("Arial", Font.BOLD, 40));//font text
							gameArray[i][j].setBorder(blackline);
							gamePanel.add(gameArray[i][j]);	
						}	
					}
			    }
				checkSolved();	//check if solution found
			}
		});
		
		//create Listener for btn8
		btn8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int xPos=0, yPos=0, emptyXPos=0,emptyYPos=0;
				boolean adjacent = false;
			    for(int i = 0; i<3; i++){
			        for(int j = 0; j<3; j++){
			        	//get position of btn8
			            if(gameArray[i][j].equals(btn8)) {
			            	xPos = i;
			            	yPos = j;
			            }
			            //get position of empty button
			            if(gameArray[i][j].equals(btn9)) {
				    		emptyXPos = i;
				    		emptyYPos = j;
			            }
			        }
			    }    
			    //Test if empty button and button clicked are adjacent
			    if ((xPos==emptyXPos)&&((yPos==emptyYPos+1)||(yPos==emptyYPos-1))){
			    	adjacent=true;
			    }
			    if ((yPos==emptyYPos)&&((xPos==emptyXPos+1)||(xPos==emptyXPos-1))){
			    	adjacent=true;
			    }
			    //if buttons are adjacent, swap buttons
			    if (adjacent==true){
			    	counter +=1;//update move counter
			    	label.setText("# of Moves: "+counter);//update text of counter
			    	historyText += counter + ".  8 moved ("+xPos+","+yPos+") to ("+emptyXPos+","+emptyYPos+")\n";//update history text
			    	gameArray[emptyXPos][emptyYPos]=btn8;
			    	gameArray[xPos][yPos]=btn9;
			        //Redraw JPanel with new button layout
			    	for (int i=0; i<ROWS;i++){
						for (int j=0;j<COLS;j++){
							gameArray[i][j].setBounds(j*BTNWIDTH,i*BTNHEIGHT,BTNWIDTH,BTNHEIGHT);
							gameArray[i][j].setFont(new Font("Arial", Font.BOLD, 40));//font text
							gameArray[i][j].setBorder(blackline);
							gamePanel.add(gameArray[i][j]);	
						}	
					}
			    }
				checkSolved();	//check if solution found
			}
		});
	}
}