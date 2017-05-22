package Network;


import javax.swing.JLabel;

public class thread2 extends Thread{
	JLabel label;
	boolean isrunning;
	public thread2(JLabel label) {
		// TODO Auto-generated constructor stub
		this.label=label;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		isrunning=true;
		while (isrunning) {
			//hit the piece and it flashing

			if (label!=null) {
				label.setVisible(false);
					try {
						this.sleep(500);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				label.setVisible(true);
				try {
					this.sleep(500);
				} catch (Exception e) {
					// TODO: handle exception
				}
			
			}
			label.setVisible(true);
	}
		public void end() {
			isrunning=false;
		}
	
}
