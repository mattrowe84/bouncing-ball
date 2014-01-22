import java.util.LinkedList;
import javax.swing.JFrame;

public class BallThreadList {
	private LinkedList<BallThread> ballList;

	public BallThreadList() {
		ballList = new LinkedList<BallThread>();
	}
	
	public void addBall(JFrame f) {
		ballList.add(new BallThread(new Ball("ball-" + (ballList.size() + 1))));
		f.add(ballList.getLast().getBall());
		ballList.getLast().start();
	}
	
	public void addBall(JFrame f, int numberOfBalls) {
		for (int i = 0; i < numberOfBalls; i++) {
			ballList.add(new BallThread(new Ball("ball-" + (ballList.size() + 1))));
			f.add(ballList.getLast().getBall());
			ballList.getLast().start();
		}
	}
	
	public void removeBall(JFrame f) {
		getList().getLast().interrupt();
		f.remove(getList().getLast().getBall());
		getList().removeLast();
		f.repaint();
	}
	
	public void stopBalls(JFrame f) {
		
		for (int i = ballList.size() - 1; i >= 0; i--) {
			ballList.getLast().interrupt();
			f.remove(ballList.getLast().getBall());
			ballList.removeLast();
		}
		
	}
	
	public void pauseBalls(JFrame f) {
		LinkedList<BallThread> bL = new LinkedList<BallThread>();
		
		for (int i = 0; i < ballList.size(); i++) {
			bL.add(new BallThread(new Ball("ball-" + (i + 1), 
					ballList.get(i).getBall().getPosX(),
					ballList.get(i).getBall().getPosY(),
					ballList.get(i).getBall().getVelocityX(),
					ballList.get(i).getBall().getVelocityY(),
					ballList.get(i).getBall().getColorR(),
					ballList.get(i).getBall().getColorG(),
					ballList.get(i).getBall().getColorB(),
					ballList.get(i).getBall().getDirection())));
		}
		
		for (int i = 0; i < ballList.size(); i++) {
			f.remove(ballList.get(i).getBall());
		}
		
		ballList = bL;
	}
	
	public LinkedList<BallThread> getList() {
		return ballList;
	}
	
	public void startLoadedBalls(JFrame f) {
		f.repaint();
		
		for (int i = 0; i < ballList.size(); i++) {
			ballList.get(i).start();
		}
	}
	
	public void loadBalls(JFrame f) {
		f.repaint();
		
		for (int i = 0; i < ballList.size(); i++) {
			f.add(ballList.get(i).getBall());
		}
	}
	
	public void setBallList(LinkedList<BallThread> bL) {
		ballList = bL;
	}
}
