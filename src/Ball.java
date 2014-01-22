import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Color;

public class Ball extends JPanel {
	private String ballName;
	private int posX;
	private int posY;
	private int velocityX;
	private int velocityY;
	private int rColor, gColor, bColor;
	private boolean randDirection;
	
	public Ball(String s) {
		Random rand = new Random();
		
		ballName = s;
		posX = rand.nextInt(291);
		posY = rand.nextInt(261);
		velocityX = rand.nextInt(3) + 1;
		velocityY = rand.nextInt(3) + 1;
		rColor = rand.nextInt(255);
		gColor = rand.nextInt(255);
		bColor = rand.nextInt(255);
		randDirection = rand.nextBoolean();
		
		if (randDirection) {
			velocityX *= -1;
		}
		else {
			velocityY *= -1;
		}
		
		this.setBounds(1, 1, 301, 281);
		this.setOpaque(false);
		
		System.out.println(ballName + " is created at the point (" + posX + " ," + posY + ")");
	}
	
	public Ball(String s, int pX, int pY, int vX, int vY, int cR, int cG, int cB, boolean d) {
		ballName = s;
		posX = pX;
		posY = pY;
		velocityX = vX;
		velocityY = vY;
		rColor = cR;
		gColor = cG;
		bColor = cB;
		randDirection = d;
		
		this.setBounds(1, 1, 301, 281);
		this.setOpaque(false);
		
		System.out.println(ballName + " is created at the point (" + posX + " ," + posY + ")");
	}
	
	public void moveBall() {
		posX += velocityX;
		posY += velocityY;
		
		if (posX < 0 || posX > 290) {
			velocityX *= -1;
		}
		
		if (posY < 0 || posY > 260) {
			velocityY *= -1;
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(rColor, gColor, bColor));
		g2d.fillOval(posX, posY, 10, 10);
		g2d.setRenderingHint(
	            RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	public void setPosX(int n) {
		posX = n;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public void setPosY(int n) {
		posY = n;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void setVelocityX(int n) {
		velocityX = n;
	}
	
	public int getVelocityX() {
		return velocityX;
	}
	
	public void setVelocityY(int n) {
		velocityY = n;
	}
	
	public int getVelocityY() {
		return velocityY;
	}
	
	public void setBallName(String s) {
		ballName = s;
	}
	
	public String getBallName() {
		return ballName;
	}
	
	public void setColorR(int r) {
		if (r >= 0 && r <= 255) {
			rColor = r;
		}
	}
	
	public int getColorR() {
		return rColor;
	}
	
	public void setColorG(int g) {
		if (g >= 0 && g <= 255) {
			gColor = g;
		}
	}
	
	public int getColorG() {
		return gColor;
	}
	
	public void setColorB(int b) {
		if (b >= 0 && b <= 255) {
			bColor = b;
		}
	}
	
	public int getColorB() {
		return bColor;
	}
	
	public void setDirection(boolean b) {
		randDirection = b;
	}
	
	public boolean getDirection() {
		return randDirection;
	}
}
