package project_user;

import java.util.Objects;

public class UserDTO {
	private String id; 
	private String password;
	private String name; 
	private String gender; 
	private String email;
	
	public UserDTO() {
		this("","","","","");
	}

	public UserDTO(String id, String password, String name, String gender, String email) {
		this.id=id;
		this.password=password;
		this.name=name;
		this.gender=gender;
		this.email=email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", password=" + password + ", name=" + name + ", gender=" + gender + ", email="
				+ email + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
