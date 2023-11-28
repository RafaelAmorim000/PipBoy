package main;

import Windows.WindowFrame;
import Windows.WindowPanel;

public class Main {

	//Fields
	private static WindowFrame screen= new WindowFrame();
	private static WindowPanel windowPanel = new WindowPanel();
    
    
	//Constructor
	/**
	 * @author Rafael Silva Amorim
	 * @param args
	 */
	public static void main(String[] args) {
		 screen.add(windowPanel);
		 screen.repaint();
	}
	
	//IO
	public static WindowFrame getScreen() {
		return screen;
	}
	
	public static WindowPanel getWindowPanel() {
		return windowPanel;
	}

}
