
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class Main extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int width=800;
	public final static int height=800;
	public final static int gap=275;//鐎圭偤妾Λ瀣磸閸滃矁绔熷鍡欐畱闂傜绐�
	public  final static int side=85;//鐏忓繑顒滈弬鐟拌埌閻ㄥ嫯绔熷锟�
	public int row=8,column=8;
	public ChessBoard jpanel;
	Container con;

	//瀹搞儱鍙块弽锟�
	JToolBar jmain;
	JButton anew;
	JButton repent;
	JButton showOpen;
	JButton showSave;
	JButton exit;
	//瑜版挸澧犳穱鈩冧紖
	JLabel text;
	//娣囨繂鐡ㄨぐ鎾冲閹垮秳缍�
	@SuppressWarnings("rawtypes")
	Vector fileVar;
	@SuppressWarnings("rawtypes")
	Vector Var;
	public Main() {
		
		String path0 = "icon.png";
        ImageIcon icon = new ImageIcon(path0);
        setIconImage(icon.getImage());
        this.setResizable(false);
		
		// TODO Auto-generated constructor stub
//		con = this.getContentPane();
//		con.setLayout(null);
//
//		jmain = new JToolBar();
//		text = new JLabel("  閻戭厾鍎撳▎銏ｇ箣");
//		text.setToolTipText("閹绘劗銇氭穱鈩冧紖");
//		anew = new JButton(" 閺傦拷 濞擄拷 閹达拷 "	);
//		anew.setToolTipText("闁插秵鏌婂锟芥慨瀣煀閻ㄥ嫪绔寸仦锟�");
//		exit = new JButton(" 闁拷  閸戯拷 ");
//		exit.setToolTipText("闁拷閸戠儤婀扮粙瀣碍");
//		repent = new JButton(" 閹拷  濡拷 ");
//		repent.setToolTipText("鏉╂柨娲栭崚棰佺瑐濞喡よ泲濡娈戞担宥囩枂");				
//		showOpen = new JButton("閹垫挸绱�");
//		showOpen.setToolTipText("閹垫挸绱戞禒銉ュ濡鐪�");		
//		showSave = new JButton("娣囨繂鐡�");
//		showSave.setToolTipText("娣囨繂鐡ㄨぐ鎾冲濡鐪�");
//		
//		//閹跺﹦绮嶆禒鑸靛潑閸旂姴鍩屽銉ュ徔閺嶏拷
//		jmain.setLayout(new GridLayout(0,6));
//		jmain.add(anew);
//		jmain.add(repent);		
//		jmain.add(showOpen);
//		jmain.add(showSave);
//		jmain.add(exit);
//		jmain.add(text);
//		jmain.setBounds(0,500,450,30);
//		con.add(jmain);
//
//		jmain.setLayout(new GridLayout(0,6));
//		jmain.add(anew);
//		jmain.add(repent);		
//		jmain.add(showOpen);
//		jmain.add(showSave);
//		jmain.add(exit);
//		jmain.add(text);
//		jmain.setBounds(0,0,450,30);
//		con.add(jmain);
//
//		anew.addActionListener(this);
//		repent.addActionListener(this);
//		exit.addActionListener(this);		
//		showOpen.addActionListener(this);
//		showSave.addActionListener(this);

		this.setTitle("INTERNETIONAL CHESS");
		this.setBounds(225, 0, 900, 900);
		jpanel=new ChessBoard();
		this.add(jpanel);
		this.setVisible(true);
		//System out
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		
	}

	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
