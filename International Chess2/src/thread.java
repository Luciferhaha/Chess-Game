

import javax.swing.JLabel;

public class thread extends Thread{
	JLabel label;
	boolean isrunning;
	public thread(JLabel label) {
		// TODO Auto-generated constructor stub
		this.label=label;
	}
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		isrunning=true;
		while (isrunning) {
			//鍗曞嚮妫嬪瓙绗竴涓嬪紑濮嬮棯鐑�
			if (label!=null) {
				label.setVisible(false);
					try {
						this.sleep(400);
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
