package Windows;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class WindowPanel extends JPanel {
	
	//Fields
    private String option = "Status";  // opição inicial
    private static JPanel panel;
    
    //Elemenetos do game
    private int level= 3;
    private int HP = 190;
    private int MaxHP=260;
    
    private static int resistencia = 5 ;
    private static int rad =obterNivelDeRadiacao(60);
    private static double valorResistencia = rad * ((double) resistencia / 100);
     

    //membros
    private int HPmembros=100;
    private int MaxHPmembros=200;
    
    //ap
    private int AP=75;
    private int MAXAP=75;
    
    //LVL
    private int xpLvl=1000;
    private int XP= 2504;
    private int MaxXP=xpLvl*level;
    //--------------------
    private int currentFrame = 0; //variável para controlar os frames do sprite   
    private BufferedImage spriteSheet;
    private Timer timer;
    //SPECIAL
    private int Strength=5;
    private int Perception=5;
    private int Endurance=5;
    private int Charisma=5;
    private int Intelligence=5;
    private int Agility=5;
    private int Luck=5;
    
    public  JButton CND = createButton("CND", "CND");
    public  JButton RAD = createButton("RAD", "RAD");
    public  JButton EFF = createButton("EFF", "EFF");
    //Constructor
    public WindowPanel() {
    	setLayout(null);
        setBackground(Color.BLACK);

        /*------------------------------------------------------
        *
        *			BUTTON
        *
        -------------------------------------------------------*/
    
        // Cria os botões
        JButton statusButton = createButton("Status", "Status");
        JButton skillsButton = createButton("Skills", "Skills");
        JButton specialButton = createButton("S.P.E.C.I.A.L", "SPECIAL");
        JButton perksButton = createButton("Perks", "Perks");
        JButton generalButton = createButton("Geral", "General");
        
        // Define as propriedades dos botões
        setPipBoyButtonProperties(statusButton);
        setPipBoyButtonProperties(skillsButton);
        setPipBoyButtonProperties(specialButton);
        setPipBoyButtonProperties(perksButton);
        setPipBoyButtonProperties(generalButton);             
        setPipBoyButtonProperties(CND);
        setPipBoyButtonProperties(RAD);
        setPipBoyButtonProperties(EFF);
        
        // Cria o painel de botões
        statusButton.setBounds(264, 519, 71, 30);  
        specialButton.setBounds(380, 519, 105, 30); 
        skillsButton.setBounds(520, 519, 100, 30);  
        perksButton.setBounds(655, 519, 100, 30);  
        generalButton.setBounds(786, 519, 100, 30);  
        
        Border border = BorderFactory.createLineBorder(Color.green);
        CND.setBorder(border); 
        RAD.setBorder(border);
        EFF.setBorder(border);
        CND.setBounds(210,240, 67, 30);
        RAD.setBounds(210,280, 67, 30);
        EFF.setBounds(210,320, 67, 30);
        CND.setBorderPainted(true);
        RAD.setBorderPainted(true);
        EFF.setBorderPainted(true);
       
         
        // Adiciona os botões ao painel
        add(statusButton);
        add(specialButton);
        add(skillsButton);
        add(perksButton);
        add(generalButton);
        
        add(CND);
        add(RAD);
        add(EFF);
        
        
        
        repaint();
        //-------------------------------------------------------
        
         velocityPriteSheet();
         loadSpriteSheetStatus();
    }    
    //Methods
    // Função para configurar as propriedades dos botões no estilo Pip-Boy
    private void setPipBoyButtonProperties(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(Color.GREEN);
        button.setFont(new Font("Arial", Font.BOLD,12));                   
        button.setFocusPainted(false); 
        button.setModel(new DefaultButtonModel());
       

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
        	case "CND":	
    		drawStatus(g); 
    		
    		break;
    		
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
        
        
        
        if(option.equals("Status")||option.equals("CND")) {  	
        	paintCND(g);       	         	
        }
        else if(option.equals("RAD")) {      	
        	paintRAD(g);        	
        }
        else if(option.equals("EFF")) {        	
        	paintEFF(g);      	
        }
               
        if(option.equals("Status")||option.equals("CND")||option.equals("RAD")||option.equals("EFF")) {
    		CND.setVisible(true);
    		RAD.setVisible(true);
    		EFF.setVisible(true);
    	}
        else {
        	CND.setVisible(false);
        	RAD.setVisible(false);
    		EFF.setVisible(false);
        }
        

    	// Outros desenhos e componentes    
        paintstats(g);
        drawDownWindows(g, 0.4f, this);        
        drawWindows(g, this);    
        
    }
    //------------------------------------------------
    
    		//Presonalização do STATS
    
    //  ----------------------------------------------
    public void paintstats(Graphics g) {
    	
    try {
		File file1 = new File("src/imagens/borda1.png");
		File file2 = new File("src/imagens/borda2.png");
		File file3 = new File("src/imagens/borda3.png");
		File file4 = new File("src/imagens/borda4.png");
		File file5 = new File("src/imagens/barra.png");
		
		BufferedImage img1 = ImageIO.read(file1);		
		BufferedImage img2 = ImageIO.read(file2);	
		BufferedImage img3 = ImageIO.read(file3);	
		BufferedImage img4 = ImageIO.read(file4);	
		BufferedImage img5 = ImageIO.read(file5);
		
		//parte de baixo
		g.drawImage(img3, 225, 495,40, 40, panel);
		g.drawImage(img2, 885, 495,40, 40, panel);
		g.drawImage(img5, 335, 530,45, 5, panel);
		g.drawImage(img5, 485, 530,35, 5, panel);
		g.drawImage(img5, 619, 530,37, 5, panel);
		g.drawImage(img5, 754, 530,34, 5, panel);
		
		//Status---------------------
		g.drawImage(img1, 240, 180,40,40 , panel);	
		g.drawImage(img4, 450, 181,40,40 , panel);
		g.drawImage(img5, 360, 180,125,5 , panel);		
		JLabel label	 = new JLabel("STATUS");	
		JLabel labelLvl  = new JLabel(String.valueOf("LVL      "+level));
		
		
		//Hp---------------------		
		g.drawImage(img4,600, 181,40,40 , panel);
		g.drawImage(img5,500, 180,125,5 , panel);		
		JLabel HP = new JLabel("HP       "+this.HP+" / "+this.MaxHP);

		//AP-----------------------
		g.drawImage(img4,880, 181,40,40 , panel);
		g.drawImage(img5,790, 180,125,5 , panel);	
		JLabel AP = new JLabel("AP       "+this.AP+" / "+this.MAXAP);
							
		//XP------------------------
		g.drawImage(img4,740, 181,40,40 , panel);
		g.drawImage(img5,645, 180,125,5 , panel);	
		JLabel XP = new JLabel("XP    "+this.XP+" / "+this.MaxXP);
				
		//--------------------------
		
		XP.setBounds(800,190,120,40);
		AP.setBounds(670,190,120,40);
		HP.setBounds(510,190,120,40);
		labelLvl.setBounds(420,190,60,40);
		label.setBounds(295, 160, 60, 40);	
		
		XP.setForeground(Color.green);
		AP.setForeground(Color.green);
		HP.setForeground(Color.green);
		label.setForeground(Color.green);
		labelLvl.setForeground(Color.green);
		
		
		Font font = new Font("Arial", Font.BOLD, 15); // fonte
		XP.setFont(font);
		AP.setFont(font);
		HP.setFont(font);
		labelLvl.setFont(font);	
		label.setFont(font);

		add(XP);
		add(AP);
		add(HP);
		add(labelLvl);
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
            File location = new File ("src/imagens/pipboy-sprites.png");

            if (location != null) {
                //carregar a imagem
                this.spriteSheet = ImageIO.read(location);
            } else {
                System.out.println("Imagem não encontrada: "+ location);
            }
        } catch (IOException e) {
           System.out.println(e);
        }
    }       
    private void drawStatus(Graphics g) {   
    	
    	
    	//sprite andando na tela
        if (spriteSheet != null) {
            int frameWidth = 121;
            int frameHeight = 169;

            // Fatores de escala desejados 
            double scaleX = 1.2;
            double scaleY = 1.2;

            int scaledWidth = (int) (frameWidth * scaleX);
            int scaledHeight = (int) (frameHeight * scaleY);

            int x = (getWidth() - scaledWidth) / 2;
            int y = (getHeight() - scaledHeight) / 2;

            // Ajuste para considerar os frames
            int sourceX = currentFrame * frameWidth;

            // Desenha o frame atual com escala
            g.drawImage(spriteSheet.getSubimage(sourceX, 0, frameWidth, frameHeight),
                       495, 260, scaledWidth, scaledHeight, null);
        }
    }

  //----------------------------------Fim da tela de status----------------------------------------------  
    private void drawSpecial(Graphics g) {
    	
    	
    	try {
    		    		
    		g.drawString("special", 500, 300);
            
        } catch (Exception e) {
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
    
    //desenha o botão CND
    public void paintCND(Graphics g){
    	try {
    		//iamgens
    		File img = new File("src/imagens/barrasuperior.png");
    		File img2 = new File("src/imagens/barra2.png");
    		File imge = new File("src/imagens/barra.png");
			BufferedImage barrasup = ImageIO.read(img);
			BufferedImage barra = ImageIO.read(imge);
			BufferedImage barra2 = ImageIO.read(img2);
			
			//inverte imagem
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
	        tx.translate(-barrasup.getWidth(null), 0);
	        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	        BufferedImage invertedImage = op.filter(barrasup, null);
	        
			//calculo para determinar a vida de cada membro
	        int larguraDesejada = (int) (43 * ((double)HPmembros / MaxHPmembros));
			larguraDesejada = Math.min(larguraDesejada, 43);
	        
	        //adiciona as imagens da barra superior		        
			g.drawImage(barrasup, 430,330 ,56, 15, panel);
			g.drawImage(barrasup, 445,430 ,56, 15, panel);
			g.drawImage(barrasup, 445,430 ,56, 15, panel);
			g.drawImage(barra2, 547,480 ,50, 13, panel);
			g.drawImage(barra2, 540,235 ,50, 13, panel);
			
			//imagens invertidas
			g.drawImage(invertedImage, 635,430 ,56, 15, panel);
			g.drawImage(invertedImage, 645,330 ,56, 15, panel);
			
			//adiciona a barra inferior
			g.drawImage(barra, 645,434 ,43, 12, panel);
			g.drawImage(barra, 655,334 ,43, 12, panel);
			g.drawImage(barra, 447,434 ,43, 12, panel);
			g.drawImage(barra, 432, 334, larguraDesejada, 12, panel);
			g.drawImage(barra, 550,483 ,43, 12, panel);
			g.drawImage(barra, 543,238 ,43, 12, panel);
			
			
		} catch (IOException e) {
			System.out.println(e);
		}
    }
    
    //desenha o botão RAD
    public static void paintRAD(Graphics g) {
        try {
        	
        	
        	double valorFinal = rad - valorResistencia;
            // radiação
            File file = new File("src/imagens/barraderad.png");
            File file1 = new File("src/imagens/seta.png");
            File file2 = new File("src/imagens/borda4.png");
            File file3 = new File("src/imagens/barra.png");
            
            BufferedImage img = ImageIO.read(file);
            BufferedImage img1 = ImageIO.read(file1);
            BufferedImage img2 = ImageIO.read(file2);
            BufferedImage img3 = ImageIO.read(file3);
            
            int posicaoSeta = calcularPosicaoSeta((int)valorFinal);

            g.drawImage(img, 519, 360, 410, 100, panel);
            g.drawImage(img1, 600 + posicaoSeta + 20, 400, 20, 40, panel);
            g.drawImage(img2, 430, 360, 85, 80, panel);
            g.drawImage(img3, 217, 359, 300, 15, panel);
            
            Font font = new Font("Arial", Font.BOLD, 15);
            g.setFont(font);
            g.setColor(Color.GREEN);
            g.drawString("RAD", 520, 390);

            
            String valorRadStr = Integer.toString((int)valorFinal*2);
            FontMetrics metrics = g.getFontMetrics(font);
            int larguraTexto = metrics.stringWidth(valorRadStr);

            // Ajuste na posição do texto
            g.drawString(valorRadStr, 588 + posicaoSeta - larguraTexto / 2 + 20, 440);

           
            g.drawString("RESIST RADIAÇÃO  "+resistencia+"%", 221, 390);
            g.drawString("500", 775, 350);
            g.drawString("1000", 895, 350);
    		
    	
           
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static int obterNivelDeRadiacao(int valorSimulado) {
     
        return calcularPosicaoSeta(valorSimulado);
    }

    private static int calcularPosicaoSeta(int nivelDeRadiacao) {
        int nivelLimitado = Math.min(nivelDeRadiacao, 1000);
        int fatorDeEscalonamento = 1;
        return nivelLimitado * fatorDeEscalonamento/2;
    }
     //desenha o eff
    public static void paintEFF(Graphics g) {
    	g.drawString("EFF", 500, 300);
    }
}
