package com.capgemini.tlta.sevice;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.tlta.exception.RegisterUserException;
import com.capgemini.tlta.model.RegisterUser;
import com.capgemini.tlta.repository.RegisterUserRepository;

/**
 * The Class RegisterUserServiceImpl.
 */
@Service(value = "registerUserService")
@Transactional
public class RegisterUserServiceImpl implements RegisterUserService {

	@Autowired
	RegisterUserRepository userRepository;
	@Autowired
	private JavaMailSender mailSender;
	
	/**
	 * Adds the user.
	 *
	 * @param user the user
	 * @return the register user
	 * @throws RegisterUserException the register user exception
	 */
	@Override
	public RegisterUser updateUser(RegisterUser user) throws RegisterUserException {
		try {
			RegisterUser i = userRepository.getOne(user.getId());
			if (i != null) {
				RegisterUser p = userRepository.save(user);
				return p;
			} else {
				throw new RegisterUserException();
			}
		} catch (DataAccessException e) {
			throw new RegisterUserException(e.getMessage(), e);
		} catch (Exception e) {
			throw new RegisterUserException(e.getMessage(), e);
		}
	}
	@Override
	public RegisterUser addUser(RegisterUser user) throws RegisterUserException {

		try {
			RegisterUser user1 = new RegisterUser();
			user1 = userRepository.save(user);
			return user1;
		} catch (DataAccessException e) {
			throw new RegisterUserException(e.getMessage(), e);
		} catch (Exception e) {
			throw new RegisterUserException(e.getMessage(), e);
		}
	}
	public void sendCredentialMail(RegisterUser user) throws MessagingException {
		String subject="TLTA CREDENTIALS";
		String mailContent="<p> Dear "+user.getFirstName()+",</p>";
		mailContent+="<p> Your Account is created for Technology Learning and Tracking Application.</p>";
		mailContent+="<p> Your credentials are: <br>USER EmailID : "+user.getEmailId()+"<br>PASSWORD: "+user.getPassword()+"</p>"+"<p>"+user.getRole()+"</p>";
		mailContent+="<p> Regards,<br>TLTA Teams</p>";
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		helper.setFrom("tltaproject2@gmail.com");
		helper.setTo(user.getEmailId());
		helper.setSubject(subject);
		helper.setText(mailContent, true);
		mailSender.send(message);
	}
	/**
	 * Gets the user by id.
	 *
	 * @param id the id
	 * @return the user by id
	 * @throws RegisterUserException the register user exception
	 */
	@Override
	public RegisterUser getUserById(Integer id) throws RegisterUserException {

		try {
			Optional<RegisterUser> optional = userRepository.findById(id);
			if (optional.isPresent()) {
				System.out.println(optional.get());
				return optional.get();
			} else {
				return null;
			}
		} catch (DataAccessException e) {
			throw new RegisterUserException(e.getMessage(), e);
		} catch (Exception e) {
			throw new RegisterUserException(e.getMessage(), e);
		}
	}

	/**
	 * Gets the moderator by id.
	 *
	 * @param id the id
	 * @return the moderator by id
	 * @throws RegisterUserException the register user exception
	 */
	@Override
	public RegisterUser getModeratorById(Integer id) throws RegisterUserException {

		return null;
	}
	
	/**
	 * Delete user.
	 *
	 * @param id the id
	 * @return the integer
	 * @throws RegisterUserException the register user exception
	 */
	@Override
	public Integer deleteUser(Integer id) throws RegisterUserException {

		try {
			userRepository.deleteById(id);
			return 1;
		} catch (DataAccessException e) {
			throw new RegisterUserException(e.getMessage(), e);
		} catch (Exception e) {
			throw new RegisterUserException(e.getMessage(), e);
		}
	}

	/**
	 * Update password.
	 *
	 * @param id the id
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param password the password
	 * @return the register user
	 * @throws RegisterUserException the register user exception
	 */
	@Override
	public RegisterUser updatePassword(RegisterUserChangePasswordDO userDo)
			throws RegisterUserException {
		RegisterUser user = null;
		try {
			user = userRepository.getOne(userDo.getId());

			if (user.getFirstName().equals(userDo.getFirstName()) && user.getLastName().equals(userDo.getLastName())) {
				user.setPassword(userDo.getPassword());
				user = userRepository.save(user);
			}
			return user;
		} catch (DataAccessException e) {
			throw new RegisterUserException(e.getMessage(), e);
		} catch (Exception e) {
			throw new RegisterUserException(e.getMessage(), e);
		}

	}

	/**
	 * Update first name.
	 *
	 * @param id the id
	 * @param firstName the first name
	 * @return the register user
	 * @throws RegisterUserException the register user exception
	 */
	@Override
	public RegisterUser updateFirstName(RegisterUserChangeFirstNameDo userDo) throws RegisterUserException {
		RegisterUser updateUser = null;
		try {
			updateUser = userRepository.getOne(userDo.getId());
			if (updateUser != null) {
				updateUser.setFirstName(userDo.getFirstName());
				userRepository.save(updateUser);
			}
			return updateUser;
		} catch (DataAccessException e) {
			throw new RegisterUserException(e.getMessage(), e);
		} catch (Exception e) {
			throw new RegisterUserException(e.getMessage(), e);
		}

	}

	/**
	 * Update last name.
	 *
	 * @param login the login
	 * @return the register user
	 * @throws RegisterUserException the register user exception
	 */
	@Override
	public RegisterUser updateLastName(RegisterUser login) throws RegisterUserException {

		return null;
	}

	/**
	 * Gets the all registered user.
	 *
	 * @return the all registered user
	 * @throws RegisterUserException the register user exception
	 */
	@Override
	public List<RegisterUser> getAllRegisteredUser() throws RegisterUserException {

		try {
			List<RegisterUser> usersList = userRepository.findAll();
			return usersList;
		} catch (DataAccessException e) {
			throw new RegisterUserException(e.getMessage(), e);
		} catch (Exception e) {
			throw new RegisterUserException(e.getMessage(), e);
		}
	}
	

}
