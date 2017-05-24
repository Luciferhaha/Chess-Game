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

public class Basic_rule extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Basic_rule(){
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
        button1.setForeground(Color.BLACK);
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
        a.setText("A chess board is made up of 64 black and white squares\n"
        		+ "(in computer software that can be set in different colors)\n"
        		+ "each with 16 black and white pieces. Chess is one of the most popular games in the world.\n"
        		+ "Hundreds of millions of people play chess in a variety of ways.\n"
        		+ "----------------------------------------------------------------------------------------------------------------\n"
        		+ "Chess distribution:\n"
        		+ "For each side:\n"
        		+ "King: 1, Queen: 1, Rook: 2, Bisshop: 2, Knight: 2 Pawn: 8\n"
        		+ "----------------------------------------------------------------------------------------------------------------\n"
        		+ "Pawn (P):\n"
        		+ "Pawn can only walk straight ahead and can't retreat.\n"
        		+ "But when you take the first step, you can walk one or two squares.\n"
        		+ "Pawn eat and play differently.\n"
        		+ "It is inclined straight to eat,if the former Pawn into a box with inclined other pieces,\n"
        		+ "you can eat it. "
        		+ "so as to occupy the lattice position.\n"
        		+ "----------------------------------------------------------------------------------------------------------------\n"
        		+ "Knight (N):\n"
        		+ "The first step of each line, horizontal or straight style,\n"
        		+ "and then outward move; or oblique move, and finally to horizontal or vertical move\n"
        		+ "(i.e., go on the word, this is similar to chess and Chinese).\n"
        		+ "You can walk more, and no bad horse leg limit China chess.\n"
        		+ "----------------------------------------------------------------------------------------------------------------\n"
        		+ "Bishop (B):\n"
        		+ "Can only walk diagonally, the number of spaces is unlimited, but you can not play chess.\n"
        		+ "At the start of each side has two, accounted for a square, a total of hague.\n"
        		+ "----------------------------------------------------------------------------------------------------------------\n"
        		+ "Rook (R):\n"
        		+ "Both horizontal and vertical can walk, the number of steps is unlimited, and can not walk diagonally\n"
        		+ "In addition to the king car translocation, you can not cross the pieces.\n"
        		+ "----------------------------------------------------------------------------------------------------------------\n"
        		+ "Queen (Q):\n"
        		+ "Horizontal, straight and oblique can go, the number of steps is unlimited\n"
        		+ "but you can not cross the pieces.\n"
        		+ "The Queen is also the most powerful chess piece.\n"
        		+ "----------------------------------------------------------------------------------------------------------------\n"
        		+ "Eat pieces:\n"
        		+ "Where any pieces of yours and it can reach, and there are other chess pieces on the box\n"
        		+ "the opponent's chess pieces can be eaten.\n"
        		+ "----------------------------------------------------------------------------------------------------------------\n"
        		+ "Outcome:\n"
        		+ "Beat each other to eat each other for kill the king.\n");
        
        ImageIcon background = new ImageIcon(path1);   
        JLabel label = new JLabel(background);   
        label.setBounds(0, 0, this.getWidth(), this.getHeight());   
        JPanel imagePanel = (JPanel) this.getContentPane();  
        imagePanel.setOpaque(false); 
        
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));   
        setVisible(true); 
        imagePanel.add(button1);
        imagePanel.add(scroll);
        
        
	}
	public static void main(String[] args){
		new Basic_rule();
	}
}
