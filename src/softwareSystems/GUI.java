package softwareSystems;
/**
 * @author Eric Le Fort, Christopher McDonald, Spencer Deevy
 * @version 1.0
 */
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI{
	private JFrame main;
	private JPanel mainPanel, infoPanel, engineControlPanel, flapControlPanel, engineControlBoxes[], engineButtonPanels[];
	private JButton engineUpBtns[], engineDownBtns[], lPitchUp, lPitchDown, rPitchUp, rPitchDown;
	private JLabel altitudeLbl, directionLbl, latLbl, lonLbl, speedLbl, pitchLbl, engineMaxRPMLbls[], engineCurrentRPMLbls[], lPitchLbl, rPitchLbl;

	public GUI(){
		main = new JFrame();									//Initializing main Panels/frame.
		mainPanel = new JPanel();
		infoPanel = new JPanel();
		engineControlPanel = new JPanel();
		flapControlPanel = new JPanel();
		
		altitudeLbl = new JLabel("Altitude: " + 0);				//Initializing upper info labels.
		directionLbl = new JLabel("Direction: " + 0);
		pitchLbl = new JLabel("Pitch: " + 0);
		latLbl = new JLabel("Lat(X): " + 0);
		lonLbl = new JLabel("Lat(Y): " + 0);
		speedLbl = new JLabel("Speed: " + 0);
		
		engineControlBoxes = new JPanel[4];						//Initializing engine panel components
		engineButtonPanels = new JPanel[4];
		engineUpBtns = new JButton[4];
		engineDownBtns = new JButton[4];
		for(int i = 0; i < 4; i++){
			engineMaxRPMLbls[i] = new JLabel("Max RPM: " + 0);
			engineCurrentRPMLbls[i] = new JLabel("Current RPM: " + 0);
			engineControlBoxes[i] = new JPanel();
			engineUpBtns[i] = new JButton("+100");
			engineDownBtns[i] = new JButton("-100");
		}
		
		lPitchLbl = new JLabel("Left pitch: " + 0);
		lPitchUp = new JButton("+");
		lPitchDown = new JButton("-");
		rPitchLbl = new JLabel("Right Pitch: " + 0);
		rPitchUp = new JButton("+");
		rPitchDown = new JButton("-");

		mainPanel.setLayout(new GridLayout(3, 1));
		infoPanel.setLayout(new GridLayout(3, 2));
		engineControlPanel.setLayout(new GridLayout(1, 4));
		flapControlPanel.setLayout(new GridLayout(2, 2));

		main.setLayout(new GridLayout(3, 1));
		main.setSize(1000, 650);
		main.setResizable(false);

		boardPanel.setLayout(new GridLayout(3, 3));

		for(int i = 0; i < 4; i++){
			boardBtns[i] = new JButton();
			boardBtns[i].addActionListener(addBoardButtonListener(i, j));
			boardPanel.add(boardBtns[i]);
		}
		for(int i = 0; i < 4; i++){
			boardBtns[i] = new JButton();
			boardBtns[i].addActionListener(addBoardButtonListener(i, j));
			boardPanel.add(boardBtns[i]);
		}
		playerLbl.setHorizontalAlignment(JLabel.CENTER);
		infoLbl.setHorizontalAlignment(JLabel.CENTER);
		movesLbl.setHorizontalAlignment(JLabel.CENTER);

		infoPanel.add(playerLbl);
		infoPanel.add(movesLbl);
		infoPanel.add(infoLbl);

		main.add(infoPanel);
		main.add(engineControlPanel);
		main.add(flapControlPanel);
		
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setTitle("Aircraft Controller");
		boardPanel.setVisible(true);
		main.setVisible(true);
	}//Constructor

	private ActionListener addBoardButtonListener(int x, int y){
		ActionListener listener = new ActionListener(){
			public synchronized void actionPerformed(ActionEvent e){
				JButton btn = (JButton) e.getSource();
				String text = btn.getText();
				btn.setText("pressed.");
						
			}//actionPerformed()
		};//new ActionListener()
		return listener;
	}//addBoardButtonListener()
}//GUI

