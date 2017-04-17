package framework;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
 
public class test extends JFrame{
    public test() {
        JTextArea jta = new JTextArea("´ó¼ÒºÃ");
        jta.setOpaque(false);
        JPanel jp = new JPanel();
        jp.setBackground(Color.green);
        jp.add(jta);
        this.add(jp);
        setBounds(0, 0, 300, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new test();
    }
}