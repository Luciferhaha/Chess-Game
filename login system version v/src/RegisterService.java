import java.io.*;
public class RegisterService{
	private static final String FILE_PATH = "c:\\user.txt";
	public void register(User user) {
		//获取到用户名和密码
		//存储到文件当中
		//目前只能储存一个人的信息
		String msg = user.getUserName() + "," + user.getPwd() + "," + user.getemail();
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(FILE_PATH));
			bw.write(msg);
			bw.flush();
	    }
	    catch (IOException ex) {
	    	ex.printStackTrace();
	    } finally  {
	    	try{
	    		if(bw != null)
	    			bw.close();	
	    	}catch(IOException e) {
	    		e.printStackTrace();
	    	}
	    	
	    }
		
	}
}

