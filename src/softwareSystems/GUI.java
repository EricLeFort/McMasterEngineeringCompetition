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
	private JPanel infoPanel, engineControlPanel, flapControlPanel, engineControlBoxes[], engineBtnPanels[],
		engineLblPanels[], lPitchBtnPanel, rPitchBtnPanel;
	private JButton engineUpBtns[], engineDownBtns[], lPitchUp, lPitchDown, rPitchUp, rPitchDown;
	private JLabel altitudeLbl, directionLbl, latLbl, lonLbl, speedLbl, pitchLbl, engineMaxRPMLbls[],
		engineCurrentRPMLbls[], lPitchLbl, rPitchLbl;

	public GUI(){
		main = new JFrame();									//Initializing main Panels/frame.
		infoPanel = new JPanel();
		engineControlPanel = new JPanel();
		flapControlPanel = new JPanel();
		rPitchBtnPanel = new JPanel();
		lPitchBtnPanel = new JPanel();
		
		main.setLayout(new GridLayout(3, 1));					//Applying layouts to frame and panels.
		main.setSize(1000, 650);
		main.setResizable(false);
		infoPanel.setLayout(new GridLayout(3, 2));
		engineControlPanel.setLayout(new GridLayout(1, 4));
		flapControlPanel.setLayout(new GridLayout(2, 2));
		
		altitudeLbl = new JLabel("Altitude: " + 0);				//Initializing and adding upper info labels.
		altitudeLbl.setHorizontalAlignment(JLabel.CENTER);
		infoPanel.add(altitudeLbl);
		directionLbl = new JLabel("Direction: " + 0);
		directionLbl.setHorizontalAlignment(JLabel.CENTER);
		infoPanel.add(directionLbl);
		pitchLbl = new JLabel("Pitch: " + 0);
		pitchLbl.setHorizontalAlignment(JLabel.CENTER);
		infoPanel.add(pitchLbl);
		latLbl = new JLabel("Lat(X): " + 0);
		latLbl.setHorizontalAlignment(JLabel.CENTER);
		infoPanel.add(latLbl);
		lonLbl = new JLabel("Lat(Y): " + 0);
		lonLbl.setHorizontalAlignment(JLabel.CENTER);
		infoPanel.add(lonLbl);
		speedLbl = new JLabel("Speed: " + 0);
		speedLbl.setHorizontalAlignment(JLabel.CENTER);
		infoPanel.add(speedLbl);
		
		
		engineControlBoxes = new JPanel[4];						//Initializing engine panel components.
		engineBtnPanels = new JPanel[4];
		engineLblPanels = new JPanel[4];
		engineUpBtns = new JButton[4];
		engineDownBtns = new JButton[4];
		
		engineMaxRPMLbls = new JLabel[4];
		engineCurrentRPMLbls = new JLabel[4];
		for(int i = 0; i < 4; i++){
			engineMaxRPMLbls[i] = new JLabel("Max RPM: " + 0);
			engineCurrentRPMLbls[i] = new JLabel("Current RPM: " + 0);
			engineControlBoxes[i] = new JPanel();
			engineControlBoxes[i].setLayout(new GridLayout(2, 1));
			engineBtnPanels[i] = new JPanel();
			engineBtnPanels[i].setLayout(new GridLayout(1, 2));
			engineLblPanels[i] = new JPanel();
			engineLblPanels[i].setLayout(new GridLayout(1, 2));
			engineUpBtns[i] = new JButton("+100");
			engineDownBtns[i] = new JButton("-100");
			
			engineLblPanels[i].add(engineMaxRPMLbls[i]);
			engineLblPanels[i].add(engineCurrentRPMLbls[i]);
			
			engineBtnPanels[i].add(engineUpBtns[i]);
			engineBtnPanels[i].add(engineDownBtns[i]);
			
			engineControlBoxes[i].add(engineLblPanels[i]);
			engineControlBoxes[i].add(engineBtnPanels[i]);
			
			engineControlPanel.add(engineControlBoxes[i]);
		}
		
		lPitchLbl = new JLabel("Left pitch: " + 0);				//Initializing flap control components.
		lPitchLbl.setHorizontalAlignment(JLabel.CENTER);
		lPitchUp = new JButton("+");
		lPitchDown = new JButton("-");
		
		rPitchLbl = new JLabel("Right Pitch: " + 0);
		rPitchLbl.setHorizontalAlignment(JLabel.CENTER);
		rPitchUp = new JButton("+");
		rPitchDown = new JButton("-");
		
		lPitchBtnPanel.setLayout(new GridLayout(2, 1));
		lPitchBtnPanel.add(lPitchUp);
		lPitchBtnPanel.add(lPitchDown);
		rPitchBtnPanel.setLayout(new GridLayout(2, 1));
		rPitchBtnPanel.add(rPitchUp);
		rPitchBtnPanel.add(rPitchDown);
		
		flapControlPanel.add(lPitchLbl);
		flapControlPanel.add(rPitchLbl);
		flapControlPanel.add(lPitchBtnPanel);
		flapControlPanel.add(rPitchBtnPanel);


		main.add(infoPanel);
		main.add(engineControlPanel);
		main.add(flapControlPanel);
		
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setTitle("Aircraft Controller");
		infoPanel.setVisible(true);
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
	
	public void updateLbls(double altitude, double direction, double pitch, double lat, double lon, double speed, 
			double engine1RPM, double engine2RPM, double engine3RPM, double engine4RPM){
		altitudeLbl.setText("Altitude: " + altitude);
		directionLbl.setText("Direction: " + direction);
		pitchLbl.setText("Pitch: " + pitch);
		lonLbl.setText("Lat(X): " + lat);
		latLbl.setText("Lon(Y): " + lon);
		speedLbl.setText("Speed: " + speed);
	}//updateLbls()
}//GUI

