package framework;

import java.io.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class test2 extends JFrame {   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	File f1; 
    BufferedReader in = null;   
    JPanel contentPane;   
    JTextArea jTextArea1 = new JTextArea();   
    TitledBorder titledBorder1 = new TitledBorder("");   
    JButton jButton1 = new JButton();   
    public test2() {   
        try {   
            setDefaultCloseOperation(EXIT_ON_CLOSE);    
            f1 = new File("test.txt");   
            in = new BufferedReader(new FileReader(f1));   
            String str1 = in.readLine();   
            while (str1 != null) {   
                jTextArea1.append("\n"+str1);   
                str1 = in.readLine();   
            }   
        } catch (Exception exception) {   
            exception.printStackTrace();   
        }   
    }
    public static void main(String args[]){
    	new test2();
    }
}