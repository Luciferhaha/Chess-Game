package LoginSystem;
public class User {
	private String userName;
	private String pwd;
	private String email;
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return this.userName;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPwd() {
		return this.pwd;
	}
	public void setemail(String email) {
		this.email = email;
	}
	public String getemail() {
		return this.email;
	}
}
