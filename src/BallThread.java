
public class BallThread extends Thread {
	private Ball ball;
	
	public BallThread(Ball b) {
		ball = b;
	}
	
	public void run(){
		try {
			while (!Thread.currentThread().isInterrupted()) {
					ball.moveBall();
					ball.repaint();
					Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			
		}
	}
	
	public void setBall(Ball b){
		ball = b;
	}
	
	public Ball getBall(){
		return ball;
	}
}
