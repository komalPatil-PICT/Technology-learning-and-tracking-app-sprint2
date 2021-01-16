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
		mailContent+="<p> Your credentials are: <br>USERID: "+user.getId()+"<br>PASSWORD: "+user.getPassword()+"</p>";
		mailContent+="<p> Regards,<br>TLTA Teams</p>";
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		helper.setFrom("patlelala09@gmail.com");
		helper.setTo("patlelala09@gmail.com");
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
	 * @param user      the user
	 * @param firstName the first name
	 * @param lastName  the last name
	 * @return the register user
	 * @throws RegisterUserException the register user exception
	 */
	@Override
	public RegisterUser updatePassword(Integer id, String firstName, String lastName,String password)
			throws RegisterUserException {
		RegisterUser user = null;
		try {
			System.out.println(user+","+id);
			user = userRepository.getOne(id);
			
			if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)) {
				user.setPassword(password);
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
	 * @param login the login
	 * @return the register user
	 * @throws RegisterUserException the register user exception
	 */
	@Override
	public RegisterUser updateFirstName(Integer id, String firstName) throws RegisterUserException {
		RegisterUser updateUser = null;
		try {
			updateUser = userRepository.getOne(id);
			if (updateUser != null) {
				updateUser.setFirstName(firstName);
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
