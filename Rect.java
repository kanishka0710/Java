package Final;
import java.awt.*;

import javax.swing.JPanel;

public class Rect extends JPanel {

	int myXStart = 20;
	int myYStart = 20;
	int myWidth = 50;
	int myHeight = 50;
	String myForeColor = "Green";
	String myBackColor = "Blue";
	String myFill = "Yes";

	

	public Rect() {

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

	public int getMyWidth() {
		return myWidth;
	}

	public void setMyWidth(int myWidth) {
		this.myWidth = myWidth;
		repaint();
	}

	public int getMyHeight() {
		return myHeight;
	}

	public void setMyHeight(int myHeight) {
		this.myHeight = myHeight;
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

	public String getMyFill() {
		return myFill;
	}

	public void setMyFill(String myFill) {
		this.myFill = myFill;
		repaint();
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(myBackColor.equalsIgnoreCase("Green")) {
			this.setBackground(Color.green);
		} else if (myBackColor.equalsIgnoreCase("Blue")) {
			this.setBackground(Color.blue);
		} else if (myBackColor.equalsIgnoreCase("Red")){
			this.setBackground(Color.red);
		} else if (myBackColor.equalsIgnoreCase("Yellow")){
			this.setBackground(Color.yellow);
		}
		
		if(myForeColor.equalsIgnoreCase("Green")) {
			g.setColor(Color.green);
		} else if (myForeColor.equalsIgnoreCase("Blue")) {
			g.setColor(Color.blue);
		} else if (myForeColor.equalsIgnoreCase("Red")){
			g.setColor(Color.red);
		} else if (myForeColor.equalsIgnoreCase("Yellow")) {
			g.setColor(Color.yellow);
		}
		
		if(myFill.equalsIgnoreCase("Yes")) {
			g.fillRect(myXStart, myYStart, myWidth, myHeight);
		} else {
			g.drawRect(myXStart, myYStart, myWidth, myHeight);
		}
		
	}

}
