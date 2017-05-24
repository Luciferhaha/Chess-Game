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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Advanced_rule extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Advanced_rule(){
		super("International Chess");   
        setSize(900, 600); 
        this.setResizable(true);
        setLocation(225, 60); 
        
        String path0 = "src/Graph/icon.png";
        ImageIcon icon = new ImageIcon(path0);
        setIconImage(icon.getImage());
        
        Container container1 = getContentPane();
        container1.setLayout(null);
        
        JButton button1 = new JButton("BACK");
        button1.setBounds(700, 475, 150, 60);
        button1.setContentAreaFilled(false);
        button1.setFont(new Font("Arial",Font.BOLD,26));
        button1.setForeground(Color.black);
        button1.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e != null){
					dispose();
					new Ruleintroduction();
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
        String path1 = "src/Graph/timg2.jpg";
        
        JTextArea a = new JTextArea();
        a.setOpaque(false);
        a.setEditable(false);
        JScrollPane scroll = new JScrollPane(a);
        scroll.setVisible(true);
        scroll.setBounds(100, 100, 700, 300);
        a.setFont(new Font("Arial",Font.BOLD,16));
        a.setText("ADVANCED¡¡RULE\n"
        		+ "capture of pawn en passant:\n"
        		+ "If the other pawns first moves and straight into the two lattice\n"
        		+ "just formed the Youbing and close to the lateral side by side\n"
        		+ "the side of the soldiers immediately oblique, the other side of the soldiers eat\n"
        		+ "and regarded as a move. The action must be performed immediately and delayed without delay.\n"
        		+ "----------------------------------------------------------------------------------------------------------------\n"
        		+ "Vehicle king translocation:\n"
        		+ "This is a chess game that is more specific.\n"
        		+ "In a word, the car and the king can exchange positions in special circumstances.\n"
        		+ "Details: the king and the car did not come under the premise\n"
        		+ "(two chess in the same line in the swap line chess)\n"
        		+ "on the way or the starting point and destination\n"
        		+ "the king does not have other pieces can attack, a car swap position.\n"
        		+ "----------------------------------------------------------------------------------------------------------------\n"
        		+ "Rise of pawn:\n"
        		+ "Any soldier in this party will enter into the opponent's bottom line and then change into a chess player\n"
        		+ "other than 'king' and 'pawn'. \n"
        		+ "But the 'pawn', 'rook', 'knight' and 'bishop' can not be changed. \n"
        		+ "This is seen as a move. After the pawn is changed, the chess is moved according to the rules of the new chess piece.");
        
        ImageIcon background = new ImageIcon(path1);   
        JLabel label = new JLabel(background);   
        label.setBounds(0, 0, this.getWidth(), this.getHeight());   
        JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false); 
        
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));   
        setVisible(true); 
        imagePanel.add(button1);
        imagePanel.add(scroll, a);
	}
}
