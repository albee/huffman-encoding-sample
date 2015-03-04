//Keenan Albee
//Draws the huffman nodes

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class DisplayHuff extends JComponent{
	
	private ArrayList<NodeShape> theNodes;
	
	public DisplayHuff(ArrayList<NodeShape> inNodes,int height, int width) {
		theNodes = inNodes;
		setPreferredSize(new Dimension(width,height));
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		for (NodeShape each : theNodes){
			each.draw(g2);
		}
	}
}
