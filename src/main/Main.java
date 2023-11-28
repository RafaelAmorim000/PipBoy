package main;

import windows.WindowFrame;
import windows.WindowPanel;

public class Main {

	//Fields
	private static WindowFrame screen= new WindowFrame();
	private static WindowPanel panel = new WindowPanel();
    
    
	//Constructor
	/**
	 * @author Rafael Silva Amorim
	 * @param args
	 */
	public static void main(String[] args) {
		 screen.add(panel);
		 screen.repaint();
	}

}
