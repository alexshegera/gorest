package gorest.co.in.tests.dto;

public class User {

	private Long id;
	private String name;
	private String email;
	private String gender;
	private String status;
	
	public static class Builder {
		
		private User user;
		
		public Builder() {
			user = new User();
		}

		public Builder id(Long id) {
			user.id = id;
			return this;
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
		
		public User build() {
			return user;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		if (!(o instanceof User)) return false;

		User user = (User) o;

		if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
		if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
		if (getGender() != null ? !getGender().equals(user.getGender()) : user.getGender() != null) return false;
		return getStatus() != null ? getStatus().equals(user.getStatus()) : user.getStatus() == null;
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