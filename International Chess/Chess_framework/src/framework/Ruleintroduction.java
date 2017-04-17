package framework;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JPanel;  

public class Ruleintroduction extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ruleintroduction(){
		super("International Chess");   
        setSize(900, 600); 
        this.setResizable(true);
        setLocation(225, 60); 
        
        Container container1 = getContentPane();
        container1.setLayout(null);
        
        JButton button1 = new JButton("BACK");
        button1.setBounds(700, 475, 150, 60);
        button1.setContentAreaFilled(false);
        button1.setFont(new Font("Arial",Font.BOLD,26));
        button1.setForeground(Color.WHITE);
        button1.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e != null){
					dispose();
					new MainJFrame();
				}
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
        String path1 = "timg2.jpg";  
        
        Container container2 = getContentPane();
        container2.setLayout(null);
        
        JButton button3 = new JButton("BASIC");
        button3.setBounds(200, 150, 150, 60);
        button3.setContentAreaFilled(false);
        button3.setFont(new Font("Arial",Font.BOLD,26));
        button3.setForeground(Color.WHITE);
        button3.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e != null){
					dispose();
					new Basic_rule();
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
        
        JButton button4 = new JButton("ADVANCE");
        button4.setBounds(550, 150, 150, 60);
        button4.setContentAreaFilled(false);
        button4.setFont(new Font("Arial",Font.BOLD,22));
        button4.setForeground(Color.WHITE);
        button4.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new Advanced_rule();
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
        
        ImageIcon background = new ImageIcon(path1);   
        JLabel label = new JLabel(background);   
        label.setBounds(0, 0, this.getWidth(), this.getHeight());   
        JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false); 
        
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));   
        setVisible(true); 
        imagePanel.add(button1);
        imagePanel.add(button3);
        imagePanel.add(button4);
	}
}
