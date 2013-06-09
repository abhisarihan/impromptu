package com.impromptu.users;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document()
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2128540612283789089L;

	@Id
    private String id;
	
    @NotBlank(message="First name is required")
    @Size(max=255, message="First name cannot exceed 255 characters")
    private String firstName;
    
    @NotBlank(message="Last name is required")
    @Size(max=255, message="Last name cannot exceed 255 characters")
    private String lastName;
    
    @NotBlank(message="Email is required")
    @Size(max=255, message="Email cannot exceed 255 characters")
    @Email(message="Email must be a valid email address")
    private String email;
    
    @NotBlank(message="Password is required")
    @Size(min=6, message="Password must be at least 6 characters")
    private String password;
    
    private String location;
    
    private Set<String> interests;
    
    private String profilePic;
    
    public User() {
        interests = new HashSet<String>();
    }
    
    public User(User other) {
        setId(other.getId());
        setFirstName(other.getFirstName());
        setLastName(other.getLastName());
        setEmail(other.getEmail());
        setPassword(other.getPassword());
        setLocation(other.getLocation());
        setInterests(other.getInterests());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public Set<String> getInterests() {
		return interests;
	}
    
    public void setInterests(Set<String> interests) {
		this.interests = interests;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
    public String getProfilePic() {
		return profilePic;
	}
    
    public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
    
}
