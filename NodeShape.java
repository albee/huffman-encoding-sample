//Keenan Albee
//kea2134
//11/26/14

import java.awt.*;
import java.awt.geom.*;

public class NodeShape {
	
	private int x; //node x location
	private int y; //node y location
	private int prevX; //node x location
	private int prevY; //node y location
	private final int nodeWidth = 50;
	private char[] theCode;
	private char[] theChar;
	

	public NodeShape(int prevX, int prevY, int x, int y, String nodeCode, String nodeChar){
	      this.x = x;
	      this.y = y;
	      this.prevX = prevX; //node x location
	  	  this.prevY = prevY; //node y location
	      this.theCode = nodeCode.toCharArray();
	      this.theChar = nodeChar.toCharArray();
	}
	
	public void draw(Graphics2D g2){
		Ellipse2D.Double node
        = new Ellipse2D.Double(x-nodeWidth/2, y, 
                nodeWidth, nodeWidth);
		Line2D.Double line = new Line2D.Double(prevX,prevY,x,y);
		
		g2.draw(node);
		g2.draw(line);
		g2.drawChars(theCode,0,theCode.length,x-nodeWidth/4, y + nodeWidth/2);
		g2.drawChars(theChar,0,theChar.length,x-nodeWidth/4, y + nodeWidth*5/4);
	}
}