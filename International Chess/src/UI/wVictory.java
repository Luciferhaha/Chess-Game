package UI;

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

public class wVictory extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public wVictory() {
		super("International Chess");   
        setSize(500, 300); 
        this.setResizable(false);
        setLocation(450, 220); 
        
        String path0 = "icon.png";
        ImageIcon icon = new ImageIcon(path0);
        setIconImage(icon.getImage());
        
        Container container1 = getContentPane();
        container1.setLayout(null);
        
        JButton button1 = new JButton("close");
        button1.setBounds(200, 150, 90, 36);
        button1.setContentAreaFilled(false);
        button1.setFont(new Font("Arial",Font.BOLD,16));
        button1.setForeground(Color.black);
        button1.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e != null){
					dispose();
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
        
        String path1 = "ww.jpg";
        
        ImageIcon background = new ImageIcon(path1);   
        JLabel label = new JLabel(background);   
        label.setBounds(0, 0, this.getWidth(), this.getHeight());   
        JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false); 
        
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));   
        setVisible(true); 
        imagePanel.add(button1);
	}
	public static void main(String args[]){
		new wVictory();
	}
}
