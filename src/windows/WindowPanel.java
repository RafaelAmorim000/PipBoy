package Windows;

import javax.imageio.ImageIO;
import javax.swing.*;

import Windows.panes.SpecialContainer;
import Windows.panes.StatusContainer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class WindowPanel extends JPanel {
	
	//Fields
    private String option = "Status";  // opição inicial
    private static JPanel panel;
    private static Container containerPane = new Container();
    
    //Constructor
    public WindowPanel() {
    	panel = new JPanel();
    	setSize(520*2, 460*2);
        setLayout(null);
        setBackground(Color.BLACK);
        
        

        createComponents();
    }    
    
    public void createComponents() {
    	/*------------------------------------------------------
        *
        *			BUTTON
        *
        -------------------------------------------------------*/

        // Cria e configura os botões
        JButton statusButton = createButton("Status", "Status",0,0);
        JButton skillsButton = createButton("Skills", "Skills",130,0);
        JButton specialButton = createButton("S.P.E.C.I.A.L", "SPECIAL",260,0);
        JButton perksButton = createButton("Perks", "Perks",390,0);
        JButton generalButton = createButton("General", "General",520,0);         
        
        // Cria o painel de botões
        Container buttonPanel = new Container();
        buttonPanel.setBounds(190, 470, 967, 300);
        buttonPanel.setLayout(null);
        //buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        //buttonPanel.setOpaque(false);
         
        // Adiciona os botões ao painel
        buttonPanel.add(statusButton);
        buttonPanel.add(specialButton);
        buttonPanel.add(skillsButton);
        buttonPanel.add(perksButton);
        buttonPanel.add(generalButton);
        

        add(buttonPanel);

        repaint();
        //-------------------------------------------------------
    }
    
    //Methods
    // Função para configurar as propriedades dos botões no estilo Pip-Boy   
    private JButton createButton(String label, String newOption, int x, int y) {
        JButton button = new JButton(label);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(true);
        button.setForeground(Color.GREEN);
        button.setBorder(BorderFactory.createLineBorder(Color.green, 1));
        button.setFont(new Font("Arial", Font.BOLD,button.getFont().getSize()));
        button.setText(button.getText().toUpperCase());
        button.setSize(130, 80);
        button.setLocation(x, y);
        //button.setMargin(new Insets(5, 60,30,5));
        button.setFocusPainted(false); 
        button.setModel(new DefaultButtonModel());
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
        		remove(containerPane);
        		createComponents();
        		drawStatus(g);        		
        	break;
        	case "SPECIAL":
        		remove(containerPane);
                createComponents();
        		drawSpecial(g);
        	break;
        	case "Skills":
        		remove(containerPane);
                createComponents();
        		drawSkills(g);
        	break;
        	case "Perks":
        		remove(containerPane);
        		createComponents();
        		drawPerks(g);
        	break;
        	case "General":
        		remove(containerPane);
        		createComponents();
        		drawGeneral(g);
        	break;
        
        }
        
        // Outros desenhos e componentes    
        paintstats(g);
        drawDownWindows(g, 0.4f, this);
        
        drawWindows(g, this);
       
        
        
    }
    public void paintstats(Graphics g) {
    	
    try {
		BufferedImage img1 = ImageIO.read(getClass().getClassLoader().getResource("imagens/borda1.png"));		
		BufferedImage img2 = ImageIO.read(getClass().getClassLoader().getResource("imagens/borda2.png"));	
		BufferedImage img3 = ImageIO.read(getClass().getClassLoader().getResource("imagens/borda3.png"));	
		BufferedImage img4 = ImageIO.read(getClass().getClassLoader().getResource("imagens/borda4.png"));	
		g.drawImage(img1, 240, 180,40,40 , panel);	
		g.drawImage(img4, 360, 180,40,40 , panel);
		
		JLabel label= new JLabel("STATUS");
		label.setBounds(295, 160, 60, 40);		
		label.setForeground(Color.green);
		
		Font font = new Font("Arial", Font.BOLD, 14); // Substitua "Arial" pela fonte desejada e 14 pelo tamanho desejado
        label.setFont(font);
        
		label.setVisible(true);
		add(label);
		
	} catch (Exception e) {
		System.out.println(e);
	}
    	   
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
  
           
    private void drawStatus(Graphics g) {
    	containerPane = new StatusContainer();
    }

  //----------------------------------Fim da tela de status----------------------------------------------  
    private void drawSpecial(Graphics g) {
    	containerPane = new SpecialContainer();
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
            File file = new File("src/imagens/cover.png");
            BufferedImage image = ImageIO.read(file);

            // Desenha a imagem no painel
            g.drawImage(image, -360, 0, 580*3, 320*3, panel);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    //--imagem da tela   
    public static void drawDownWindows(Graphics g,float opacity,JPanel panel) {
 	   try {
 		   File file = new File("src/imagens/screen.png");
 		   BufferedImage image = ImageIO.read(file);
 		   Graphics g2d = (Graphics2D) g.create();
          
          ((Graphics2D) g2d).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

 		   g2d.drawImage(image, 100,125, 960, 500, panel);
 	   }catch (Exception e) {
		System.out.println(e);
	}
 	   
    }
   
}
