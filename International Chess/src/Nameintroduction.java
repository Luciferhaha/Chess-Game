
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

import java.awt.Container;
public class Nameintroduction extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Nameintroduction(){
		super("International Chess");   
        setSize(900, 600); 
        this.setResizable(true);
        setLocation(225, 60); 
        
        Container container1 = getContentPane();
        container1.setLayout(null);
        
        JButton button1 = new JButton("BACK");
        button1.setBounds(700, 470, 150, 60);
        button1.setContentAreaFilled(false);
        button1.setFont(new Font("Arial",Font.BOLD,26));
        button1.setForeground(Color.black);
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
     
         ImageIcon background = new ImageIcon("src/Graph/timg3.jpg");   
        JLabel label = new JLabel(background);   
        label.setBounds(0, 0, this.getWidth(), this.getHeight());   
        JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false); 
        
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));   
        setVisible(true); 
        imagePanel.add(button1);
	}
}
