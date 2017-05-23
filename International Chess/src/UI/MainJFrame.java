package UI;


import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Server.Server;
public class MainJFrame extends JFrame {  
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private URL url;
    private AudioClip ac;
    File f1 = new File("1.wav");

	@SuppressWarnings("deprecation")
	public MainJFrame() {   
        super("International Chess");   
        setSize(900, 600); 
        this.setResizable(false);
        setLocation(225, 60); 
        try{
        	url = f1.toURL();
        }catch (MalformedURLException e){
        	e.printStackTrace();
        }
        ac= Applet.newAudioClip(url);
        if(Music.flag == true){
        	ac.play();
        	ac.loop();
        	Music.flag = false;
        }
        
        String path0 = "icon.png";
        ImageIcon icon = new ImageIcon(path0);
        setIconImage(icon.getImage());
        
        Container container1 = getContentPane();
        container1.setLayout(null);

        JButton button1 = new JButton("START");
        button1.setBounds(375, 260, 150, 60);
        button1.setBackground(null);
        button1.setContentAreaFilled(false);
        button1.setFont(new Font("Arial",Font.BOLD,26));
        button1.setForeground(Color.black);
        button1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e!=null) {
					dispose();
					new Modechoose();
				}
			}
		});
        JButton button2 = new JButton("INTRO");
        button2.setBounds(375, 330, 150, 60);
        button2.setContentAreaFilled(false);
        button2.setFont(new Font("Arial",Font.BOLD,26));
        button2.setForeground(Color.black);
		button2.addMouseListener(new MouseListener()
			{
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					if(e != null){
						dispose();
						new Ruleintroduction();
					}
					
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				});
        
        Container container2 = getContentPane();
        container2.setLayout(null);
        
        JButton button3 = new JButton("AUTHOR");
        button3.setBounds(375, 400, 150, 60);
        button3.setContentAreaFilled(false);
        button3.setFont(new Font("Arial",Font.BOLD,26));
        button3.setForeground(Color.black);
        button3.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e != null){
					dispose();
					new Nameintroduction();
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			});
        
        JButton button4 = new JButton("EXIT");
        button4.setBounds(375, 470, 150, 60);
        button4.setContentAreaFilled(false);
        button4.setFont(new Font("Arial",Font.BOLD,26));
        button4.setForeground(Color.black);
        button4.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e != null){
					System.exit(0);
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			});
        
        Container container3 = getContentPane();
        container3.setLayout(null);
        JButton musicb = new JButton("Music/on");
        musicb.setBounds(600, 500, 120, 48);
        musicb.setContentAreaFilled(false);
        musicb.setFont(new Font("Arial",Font.BOLD,16));
        musicb.setForeground(Color.black);
        musicb.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(musicb.getText() == "Music/off"){
					ac.play();
					ac.loop();
					musicb.setText("Music/on");
					e = null;
				}
				if(musicb.getText() == "Music/on" && e != null){
					ac.stop();
					musicb.setText("Music/off");
				}

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        JButton onlineb = new JButton("OnlineMode");
        onlineb.setBounds(200, 500, 120, 48);
        onlineb.setContentAreaFilled(false);
        onlineb.setFont(new Font("Arial",Font.BOLD,14));
        onlineb.setForeground(Color.black);
        onlineb.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}});

        String path1 = "background.jpg";   
        
        ImageIcon background = new ImageIcon(path1);   
        JLabel label = new JLabel(background);   
        label.setBounds(0, 0, this.getWidth(), this.getHeight());   
        JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false); 
        
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));   
        setVisible(true); 
        imagePanel.add(button1);
        imagePanel.add(button2);
        imagePanel.add(button3);
        imagePanel.add(button4);
        imagePanel.add(musicb);
        
        repaint();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Server server=new Server();
    }  
  
    public static void main(String[] args) { 
        new MainJFrame();
//        Server server=new Server();
    }  
} 