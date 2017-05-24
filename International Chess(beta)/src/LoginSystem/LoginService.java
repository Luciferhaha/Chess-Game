package LoginSystem;
import java.io.*;
public class LoginService {
	private static final String FILE_PATH = "c:\\user.txt";
	public boolean login(User user) {
		//��ȡ�ļ����д洢���û���������
		//�������ǰ̨�����������û������������ƥ��
		//���ڿ��������ݿ�ȡ��
		try {
			BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
			String msg = br.readLine();
			br.close();
			if(msg == null) {
				return false;
			}
			String[] temp = msg.split(",");
			return temp[0].equals(user.getUserName()) && temp[1].equals(user.getPwd());
	    }
	    catch (Exception ex) {
	    	return false;
	    }
		
	}
}

