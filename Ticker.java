package Final;

import java.awt.*;

import javax.swing.JPanel;

public class Ticker extends JPanel implements Runnable {

	int myXStart = 20;
	int myYStart = 50;
	String myForeColor = "Green";
	String myBackColor = "Blue";
	String myMessage = "Boom";
	String moving = "no";
	
	public String getMoving() {
		return moving;
	}

	public void setMoving(String moving) {
		this.moving = moving;
		repaint();
	}

	private Thread thread;
	
	public Ticker() {
		this.thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		while(true) {
			
			if(this.moving.equals("yes")) {
				myXStart = (myXStart + 2)%this.getWidth();
				repaint();
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	


	public int getMyXStart() {
		return myXStart;
	}

	public void setMyXStart(int myXStart) {
		this.myXStart = myXStart;
		repaint();
		
	}

	public int getMyYStart() {
		return myYStart;
	}

	public void setMyYStart(int myYStart) {
		this.myYStart = myYStart;
		repaint();
	}


	public String getMyForeColor() {
		return myForeColor;
	}

	public void setMyForeColor(String myForeColor) {
		this.myForeColor = myForeColor;
		repaint();
	}

	public String getMyBackColor() {
		return myBackColor;
	}

	public void setMyBackColor(String myBackColor) {
		this.myBackColor = myBackColor;
		repaint();
	}
	
	public String getMyMessage() {
		return myMessage;
	}

	public void setMyMessage(String myMessage) {
		this.myMessage = myMessage;
		repaint();
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(myBackColor.equalsIgnoreCase("Green")) {
			this.setBackground(Color.green);
		} else if (myBackColor.equalsIgnoreCase("Blue")) {
			this.setBackground(Color.blue);
		} else {
			this.setBackground(Color.yellow);
		}
		
		if(myForeColor.equalsIgnoreCase("Green")) {
			g.setColor(Color.green);
		} else if (myForeColor.equalsIgnoreCase("Blue")) {
			g.setColor(Color.blue);
		} else {
			g.setColor(Color.yellow);
		}
		g.drawString(myMessage, myXStart, myYStart);
		
	}

}