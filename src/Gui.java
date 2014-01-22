import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JPanel {
	private JFrame frame;
	private JButton addButton;
	private JTextField numberOfBallsForAdding;
	private JButton addWithNumber;
	private JButton removeButton;
	private JTextField numberOfBallsForRemoving;
	private JButton removeWithNumber;
	private JButton removeAllButton;
	private JButton stopButton;
	private JButton savePositions;
	private JButton loadPositions;
	private JButton playButton;
	private JButton pauseButton;
	private JFileChooser saveFile;
	private JFileChooser loadFile;
	private BallThreadList lbt;
	private ButtonHandlerClass handler;
	
	public Gui() {
		handler = new ButtonHandlerClass();
		lbt = new BallThreadList();
		frame = new JFrame();
		frame.setLayout(null);
		
		addButton = new JButton("Add Random Ball");
		addButton.addActionListener(handler);
		addButton.setBounds(320, 10, 160, 25);
		
		numberOfBallsForAdding = new JTextField("# of Balls");
		numberOfBallsForAdding.setBounds(320, 37, 70, 26);
		
		addWithNumber = new JButton("Add");
		addWithNumber.addActionListener(handler);
		addWithNumber.setBounds(390, 37, 90, 25);
		
		removeButton = new JButton("Remove Last Ball");
		removeButton.addActionListener(handler);
		removeButton.setBounds(320, 64, 160, 25);
		
		numberOfBallsForRemoving = new JTextField("# of Balls");
		numberOfBallsForRemoving.setBounds(320, 91, 70, 26);
		
		removeWithNumber = new JButton("Remove");
		removeWithNumber.addActionListener(handler);
		removeWithNumber.setBounds(390, 91, 90, 25);
		
		removeAllButton = new JButton("Remove All Ball(s)");
		removeAllButton.addActionListener(handler);
		removeAllButton.setBounds(320, 118, 160, 25);
		
		playButton = new JButton("Play");
		playButton.addActionListener(handler);
		playButton.setBounds(320, 145, 160, 25);
		playButton.setVisible(false);
		
		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(handler);
		pauseButton.setBounds(320, 145, 160, 25);
		
		stopButton = new JButton("Stop");
		stopButton.addActionListener(handler);
		stopButton.setBounds(320, 172, 160, 25);
		
		savePositions = new JButton("Save");
		savePositions.addActionListener(handler);
		savePositions.setBounds(320, 199, 160, 25);
		
		saveFile = new JFileChooser();
		saveFile.setFileFilter(new FileNameExtensionFilter("XML File", "xml"));

		loadPositions = new JButton("Load");
		loadPositions.addActionListener(handler);
		loadPositions.setBounds(320, 226, 160, 25);
		
		loadFile = new JFileChooser();
		loadFile.setFileFilter(new FileNameExtensionFilter("XML File", "xml"));
		
		frame.add(addButton);
		frame.add(numberOfBallsForAdding);
		frame.add(addWithNumber);
		frame.add(removeButton);
		frame.add(numberOfBallsForRemoving);
		frame.add(removeWithNumber);
		frame.add(removeAllButton);
		frame.add(playButton);
		frame.add(pauseButton);
		frame.add(stopButton);
		frame.add(savePositions);
		frame.add(loadPositions);
		
		frame.setSize(500, 310);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.drawRect(0, 0, 302, 282);
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	private class ButtonHandlerClass implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			/*To add one ball*/
			if (e.getSource() == addButton) {
				frame.repaint();
				lbt.addBall(frame);
			}
			
			/*To add entered number of balls*/
			else if (e.getSource() == addWithNumber) {
				int x = Integer.parseInt(numberOfBallsForAdding.getText());
				
				
				if (x < 51) {
					lbt.addBall(frame, x);
				}
				else if (x < 101) {
					int choice = 0;
					
					JPanel panel = new JPanel(new GridLayout(2, 1));
					JLabel label = new JLabel("Are you sure? Adding " + x + " balls may reduce performance.");

					String[] options = {"Yes", "No"};
					panel.add(label);

					choice = JOptionPane.showOptionDialog(null, panel, "Warning",
					     JOptionPane.YES_NO_OPTION,
					     JOptionPane.QUESTION_MESSAGE, null, options, "");
					
					if (choice == 0) {
						lbt.addBall(frame, x);
					}
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Sorry, you can not add more than 100 balls!");
				}
			}
			
			/*To remove last added ball*/
			else if (e.getSource() == removeButton) {
				if (!lbt.getList().isEmpty()) {
					lbt.removeBall(frame);
				}
			}
			
			/*To remove entered number of balls*/
			else if (e.getSource() == removeWithNumber) {
				int x = Integer.parseInt(numberOfBallsForRemoving.getText());
				
				if (x <= lbt.getList().size()) {
					for (int i = 0; i < x; i++) {
						lbt.removeBall(frame);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "There are " + lbt.getList().size() + " ball(s), but you want to remove " + x + " ball(s).");
				}
			}
			
			/*To remove all balls*/
			else if (e.getSource() == removeAllButton) {
				while (!lbt.getList().isEmpty()) {
					lbt.removeBall(frame);
				}
			}
			
			/*To play paused balls*/
			else if (e.getSource() == playButton) {
				lbt.loadBalls(frame);
				
				lbt.startLoadedBalls(frame);
				
				pauseButton.setVisible(true);
				playButton.setVisible(false);
			}
			
			/*To pause balls*/
			else if (e.getSource() == pauseButton) {
				for (int i = 0; i < lbt.getList().size(); i++) {
					lbt.getList().get(i).interrupt();
				}
				lbt.pauseBalls(frame);
				pauseButton.setVisible(false);
				playButton.setVisible(true);
			}
			
			/*To stop balls*/
			else if (e.getSource() == stopButton) {
				lbt.stopBalls(frame);
			}
			
			/*To save balls into an xml file*/
			else if (e.getSource() == savePositions) {
				saveFile.showSaveDialog(null);
				
				String s;
				
				if (!saveFile.getSelectedFile().getAbsolutePath().endsWith(".xml")) {
					s = saveFile.getSelectedFile().getAbsolutePath() + ".xml";
				}
				else {
					s = saveFile.getSelectedFile().getAbsolutePath();
				}
				
				XmlProcessor xW = new XmlProcessor(lbt.getList());
				xW.writeXML(s);
			}
			
			/*To load balls from an xml file*/
			else if (e.getSource() == loadPositions) {
				loadFile.showOpenDialog(null);
				
				String s = loadFile.getSelectedFile().getAbsolutePath();
				JOptionPane.showMessageDialog(null, "Loaded: " + s);
				
				XmlProcessor xW = new XmlProcessor(lbt.getList());
				
				while (!lbt.getList().isEmpty()) {
					lbt.removeBall(frame);
				}
				
				lbt.setBallList(xW.readXML(s));
				lbt.loadBalls(frame);
				pauseButton.setVisible(false);
				playButton.setVisible(true);
			}
		}
	}
}
