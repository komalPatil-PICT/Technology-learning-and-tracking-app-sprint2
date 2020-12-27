package com.capgemini.tlta.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class RegisterUser.
 */
@Entity
@Table(name="register_user")
@NoArgsConstructor
@Getter 
@Setter
public class RegisterUser {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Size(min=2, message="First name should have atleast 2 characters!")
	@Column(name = "first_name")
	private String firstName;
	
	@Null
	@Size(min=2, message="Last name should have atleast 2 characters!")
	@Column(name = "last_name")
	private String lastName;
	
	@Null
	@Email(message="Email format invalid!")
	@Column(name = "email")
	private String emailId;
	
	@Null
	@Pattern(regexp="^[A-Za-z_0-9@#$%]{6,12}",message="Password must be 6 characters")
	@Column(name = "password")
	private String password;
	
	@Null
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(mappedBy = "registerUser",cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<UserActivity> userActivity = new HashSet<UserActivity>();
	
	/**
	 * Adds the user activity.
	 *
	 * @param userActivity the user activity
	 */
	public void addUserActivity(UserActivity userActivity) {
		this.userActivity.add(userActivity);
	}
	
	public RegisterUser(String name) {
		this.firstName=name;
	}	
}
