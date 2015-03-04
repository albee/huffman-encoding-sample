//Keenan Albee
//3/3/15
//Defines the position of a node in the GUI, and specifies how the node should be drawn

import java.awt.*;
import java.awt.geom.*;

public class NodeShape {
	
	private int x; //node x location
	private int y; //node y location
	private int prevX; //node x location of previous node
	private int prevY; //node y location of previous node
	private final int NODEWIDTH = 50; //diameter of node circle, pixels
	private char[] theCode;
	private char[] theChar;
	

	public NodeShape(int prevX, int prevY, int x, int y, String nodeCode, String nodeChar){
	      this.x = x;
	      this.y = y;
	      this.prevX = prevX;
	  	 this.prevY = prevY;
	      this.theCode = nodeCode.toCharArray();
	      this.theChar = nodeChar.toCharArray();
	}
	
	public void draw(Graphics2D g2){
          //specifies how the node looks when drawn
		Ellipse2D.Double node
                 = new Ellipse2D.Double(x-NODEWIDTH/2, y, 
                NODEWIDTH, NODEWIDTH);
		Line2D.Double line = new Line2D.Double(prevX,prevY,x,y);
		
		g2.draw(node);
		g2.draw(line);
		g2.drawChars(theCode,0,theCode.length,x-NODEWIDTH/4, y + NODEWIDTH/2);
		g2.drawChars(theChar,0,theChar.length,x-NODEWIDTH/4, y + NODEWIDTH*5/4);
	}
}
