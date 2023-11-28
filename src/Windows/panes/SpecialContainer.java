package Windows.panes;

import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.Main;

public class SpecialContainer extends Container {

	//Constructor
	public SpecialContainer() {
		setName("Special_Container");
		try {
    		setBounds(0, 0, 400, 360);
    		setLayout(null);
    		
        	//imagem apenas de ilustração
            File file = new File("src/imagens/screen-special.png");
            BufferedImage bufferedImage = ImageIO.read(file);
            
            JLabel jlabel = new JLabel();
            jlabel.setBounds(getBounds());
            
            Image img = bufferedImage.getScaledInstance(jlabel.getWidth(), jlabel.getHeight(), Image.SCALE_DEFAULT);
            jlabel.setIcon(new ImageIcon(img));
            add(jlabel);
            
            int newWidth = (int) (Main.getWindowPanel().getWidth() * 0.46); //314 Ajuste conforme necessário
            int newHeight = (int) ((double) newWidth / 80 * 65);//19

            
            int x = (Main.getWindowPanel().getWidth() -newWidth ) / 2;
            int y = (Main.getWindowPanel().getHeight()-newHeight ) / 2;
            
            setLocation(x, y);
            Main.getWindowPanel().add(this);
            
                    
            //g.drawImage(img, x, y, 400, 360, panel);
            //g.drawRect(x, y, newWidth, newHeight);
          
            
        } catch (IOException e) {
            System.out.println(e);
            
        }
	}
}
