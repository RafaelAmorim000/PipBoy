package Windows.panes;

import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import main.Main;

public class StatusContainer extends Container {

	//Fields
    private int currentFrame = 0; // Adicione esta variável para controlar os frames do sprite   
    private BufferedImage spriteSheet;
    private Timer timer;
    
	//Constructor
	public StatusContainer() {
		loadSpriteSheetStatus();
		velocityPriteSheet();
		
		setName("Status_Container");
		if (spriteSheet != null) {
			setSize(183,255);
    		setLayout(null);
            int frameWidth = 121;
            int frameHeight = 169;

            // Fatores de escala desejados 
            double scaleX = 1.5;
            double scaleY = 1.5;

            int scaledWidth = (int) (frameWidth * scaleX);
            int scaledHeight = (int) (frameHeight * scaleY);

            int x = (getWidth() - scaledWidth) / 2;
            int y = (getHeight() - scaledHeight) / 2;

            // Ajuste para considerar os frames
            int sourceX = currentFrame * frameWidth;

            // Desenha o frame atual com escala
            JLabel imageLabel = new JLabel();
            ImageIcon imageIcon = new ImageIcon(spriteSheet.getSubimage(sourceX, 0, frameWidth, frameHeight));
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_DEFAULT));
            imageLabel.setIcon(imageIcon);
            imageLabel.setBounds(0, 0, scaledWidth, scaledHeight);
            
            add(imageLabel);
            
            setLocation(x, y);
            Main.getWindowPanel().add(this);
            
            /*g.drawImage(spriteSheet.getSubimage(sourceX, 0, frameWidth, frameHeight),
                       490, 230, scaledWidth, scaledHeight, null); //*/
        }
	}
	
	private void velocityPriteSheet() {
		timer = new Timer(60, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentFrame = (currentFrame + 1) % 21;
				repaint();
			}
		});
		timer.start(); 
	}
	
	private void loadSpriteSheetStatus() {
        try {
            // Carrega a sprite sheet como um recurso do sistema de arquivos
            URL location = getClass().getClassLoader().getResource("imagens/pipboy-sprites.png");

            if (location != null) {
                //carregar a imagem
                this.spriteSheet = ImageIO.read(location);
            } else {
                System.err.println("Imagem não encontrada: "+ location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
