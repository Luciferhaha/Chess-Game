package LoginSystem;
import java.io.*;
public class RegisterService{
	
	private static  String FILE_PATH = "user.txt";
	public void register(User user) throws IOException {
	
		File file = new File("user.txt");
	    // create file
	    file.createNewFile();
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

