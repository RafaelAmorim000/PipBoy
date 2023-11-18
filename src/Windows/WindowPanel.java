package Windows;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class WindowPanel extends JPanel {
    private String option = "Status";  // opição inicial
    private static JPanel panel;
  
    private int currentFrame = 0; // Adicione esta variável para controlar os frames do sprite   
    private BufferedImage spriteSheet;
    private Timer timer;
    
    public WindowPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK); 
        
        /*------------------------------------------------------
        *
        *			BUTTON
        *
       	-------------------------------------------------------*/
        JButton statusButton = createButton("Status", "Status");
        JButton skillsButton = createButton("Skills", "Skills");    
        JButton specialButton= createButton("S.P.E.C.I.A.L", "SPECIAL");
        JButton PerksButton  = createButton("Perks","Perks");
        JButton GeneralButton= createButton("General","General");
       
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.BLACK);
       
        buttonPanel.add(statusButton);
        buttonPanel.add(specialButton);
        buttonPanel.add(skillsButton);
        buttonPanel.add(PerksButton);
        buttonPanel.add(GeneralButton);

        add(buttonPanel, BorderLayout.SOUTH);
        
        //-------------------------------------------------------
        velocityPriteSheet();
        loadSpriteSheetStatus();
        
    }
    	
    private JButton createButton(String label, String newOption) {
        JButton button = new JButton(label);
        button.addActionListener(e -> {
            setOption(newOption);
        });
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch(option) {
        	case "Status":
        		drawStatus(g);
        	break;
        	case "SPECIAL":
        		drawSpecial(g);
        	break;
        	case "Skills":
        		drawSkills(g);
        	break;
        	case "Perks":
        		drawPerks(g);
        	break;
        	case "General":
        		drawGeneral(g);
        	break;
        
        }
       
        // Outros desenhos e componentes
        
        drawDownWindows(g, 0.4f, this);
        drawWindows(g, this);
        
    }
    
 //------------------------------------------
    
    
    		//Telas do pipBoy
    
    
    
  /*----------------------------------------*/
    
    
    /*-----------------------------------
    				
    		metodos da tela de skills
      
   --------------------------------------*/ 
    
    
    //layout skills-----------------------------
    private void drawSkills(Graphics g) {
        // Lógica para desenhar a tela de Skills	
		g.drawString("Skills", getWidth() / 2, getHeight() / 2);		
    }   
    
/*------------------------------------------------------
 
      			metodos do status    

 -------------------------------------------------------*/
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
            URL location = getClass().getResource("vaultboywalking-SheetSheet.png");

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
    private void drawStatus(Graphics g) {
        if (spriteSheet != null) {
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
            g.drawImage(spriteSheet.getSubimage(sourceX, 0, frameWidth, frameHeight),
                        x, y, scaledWidth, scaledHeight, null);
        }
    }

  //----------------------------------Fim da tela de status----------------------------------------------  
    private void drawSpecial(Graphics g) {
    	try {
        	//imagem apenas de inlustação
            File file = new File("src/imagens/special.jpg");
            BufferedImage img = ImageIO.read(file);

            int newWidth = (int) (getWidth() * 0.46); //314 Ajuste conforme necessário
            int newHeight = (int) ((double) newWidth / 80 * 65);//19

            
            int x = (getWidth() -newWidth ) / 2;
            int y = (getHeight()-newHeight ) / 2;
            
                    
            g.drawImage(img, x, y, 400, 360, panel);           
          
            
        } catch (IOException e) {
            System.out.println(e);
            
        }  
    }
    
    private void drawPerks(Graphics g) {
        // Lógica para desenhar a tela de Perks
        g.drawString("Perks", getWidth() / 2, getHeight() / 2);
    }
    private void drawGeneral(Graphics g) {
        // Lógica para desenhar a tela de General
        g.drawString("General", getWidth() / 2, getHeight() / 2);
    }

    // Métodos para atualizar a opção e a tela
    public String getOption() {
        return option;
    }

    public void setOption(String newOption) {
        option = newOption;
        repaint();
    }
    
 
    public static void drawWindows(Graphics g, JPanel panel) {
        try {
            File file = new File("src/imagens/TV.png");
            BufferedImage image = ImageIO.read(file);

            // Desenha a imagem no painel
            g.drawImage(image, 0, 0, panel.getWidth(), panel.getHeight(), panel);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    //--imagem da tela   
    public static void drawDownWindows(Graphics g,float opacity,JPanel panel) {
 	   try {
 		   File file = new File("src/imagens/telaDaTv.png");
 		   BufferedImage image = ImageIO.read(file);
 		   Graphics g2d = (Graphics2D) g.create();
          
          ((Graphics2D) g2d).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

 		   g2d.drawImage(image, 0, 0, panel.getWidth(), panel.getHeight(), panel);
 	   }catch (Exception e) {
		System.out.println(e);
	}
 	   
    }
   
}
