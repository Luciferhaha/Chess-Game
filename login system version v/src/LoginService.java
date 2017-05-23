import java.io.*;
public class LoginService {
	private static final String FILE_PATH = "c:\\user.txt";
	public boolean login(User user) {
		//获取文件当中存储的用户名和密码
		//与参数（前台）传递来的用户名和密码进行匹配
		//后期可以用数据库取代
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

