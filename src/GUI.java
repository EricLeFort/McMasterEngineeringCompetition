/**
 * @author Eric Le Fort, Christopher McDonald, Spencer Deevy
 *
 */
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI{
	private JFrame main;
	private JPanel boardPanel, infoPanel;
	private JButton boardBtns[][];
	private JLabel infoLbl, playerLbl, movesLbl;

	/**
	 * Initializes the components of the GUI as well as any styles used in their display.
	 */
	public GUI(){
		main = new JFrame();
		boardPanel = new JPanel();
		infoPanel = new JPanel();
		boardBtns = new JButton[3][3];
		infoLbl = new JLabel();
		playerLbl = new JLabel();
		movesLbl = new JLabel();


		infoPanel.setLayout(new GridLayout(3, 1));

		main.setLayout(new GridLayout(2, 1));
		main.setSize(500, 650);
		main.setResizable(false);

		boardPanel.setLayout(new GridLayout(3, 3));

		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				boardBtns[i][j] = new JButton();
				boardBtns[i][j].addActionListener(addBoardButtonListener(i, j));
				boardPanel.add(boardBtns[i][j]);
			}
		}
		playerLbl.setHorizontalAlignment(JLabel.CENTER);
		infoLbl.setHorizontalAlignment(JLabel.CENTER);
		movesLbl.setHorizontalAlignment(JLabel.CENTER);

		infoPanel.add(playerLbl);
		infoPanel.add(movesLbl);
		infoPanel.add(infoLbl);

		main.add(infoPanel);
		main.add(boardPanel);
		
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setTitle("INSERT TITLE HERE");
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

