package windows;

import javax.swing.JFrame;

public class WindowFrame extends JFrame {
	
	//Fields
    private static WindowPanel panel;

    //Constructor
    public WindowFrame() {
        initialize();
    }

    //Methods
    private void initialize() {
        setTitle("Windows");
        setLocation(0,0);       
        setSize(520*2, 460*2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        panel = new WindowPanel();
        getContentPane().add(panel);

        setVisible(true);
    }

}