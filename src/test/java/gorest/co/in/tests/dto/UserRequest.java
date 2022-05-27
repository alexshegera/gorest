package gorest.co.in.tests.dto;

public class UserRequest {

	private String name;
	private String email;
	private String gender;
	private String status;
	
	public static class Builder {
		
		private UserRequest user;
		
		public Builder() {
			user = new UserRequest();
		}

		public Builder setName(String name) {
			user.setName(name);
			return this;
		}
		
		public Builder setEmail(String email) {
			user.setEmail(email);
			return this;
		}

		public Builder setGender(String gender) {
			user.gender = gender;
			return this;
		}

		public Builder setStatus(String status) {
			user.status = status;
			return this;
		}
		
		public UserRequest build() {
			return user;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserRequest)) return false;

		UserRequest that = (UserRequest) o;

		if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
		if (getEmail() != null ? !getEmail().equals(that.getEmail()) : that.getEmail() != null) return false;
		if (getGender() != null ? !getGender().equals(that.getGender()) : that.getGender() != null) return false;
		return getStatus() != null ? getStatus().equals(that.getStatus()) : that.getStatus() == null;
	}

	@Override
	public int hashCode() {
		int result = getName() != null ? getName().hashCode() : 0;
		result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
		result = 31 * result + (getGender() != null ? getGender().hashCode() : 0);
		result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
		return result;
	}
}