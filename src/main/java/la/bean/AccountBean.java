package la.bean;

public class AccountBean {
	private String name;
	private String address;
	private int addressNum;
	private int tel;
	private String email;
	private String date;
	private String pass;
	private int id;

	public AccountBean() {
	}

	public AccountBean(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public AccountBean(String name, int addressNum, String address, int tel, String email, String date, String pass,
			int id) {
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.addressNum = addressNum;
		this.email = email;
		this.date = date;
		this.pass = pass;
		this.id = id;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAddressNum() {
		return addressNum;
	}

	public void setAddressNum(int addressNum) {
		this.addressNum = addressNum;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
