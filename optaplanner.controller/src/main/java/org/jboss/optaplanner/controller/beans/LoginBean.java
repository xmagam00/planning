package org.jboss.optaplanner.controller.beans;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.jboss.optaplanner.controller.database.Operation;
import org.jboss.optaplanner.controller.login.UserRole;
import org.jboss.optaplanner.entities.User;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
import org.picketlink.idm.impl.api.PasswordCredential;

/**
 * @author martin
 * 
 */

/**
 * this class handle login
 * @author martin
 *
 */
public class LoginBean extends BaseAuthenticator {

	@Inject
	private Credentials credentials;

	private String username;
	private String password;
	private boolean usernameValid;
	private boolean passwordValid;
	private boolean usernameEmpty;
	private boolean passwordEmpty;
	private boolean admin = false;
	private boolean reader = false;
	private boolean planner = false;

	public void setReader(boolean reader) {
		this.reader = reader;
	}

	public boolean getReader() {
		return reader;
	}

	public void setPlanner(boolean planner) {
		this.planner = planner;
	}

	public boolean getPlanner() {
		return planner;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean getAdmin() {
		return admin;
	}

	/**
	 * this method check username
	 * @return the username
	 */
	public void checkUsername(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		setUsernameValid(false);
		String email = (String) value;
		Operation op = new Operation();

		if (!op.validateUsername(email)) {
			setStatus(AuthenticationStatus.FAILURE);
			setUsernameValid(true);

		}
		setUsername(email);

		return;
	}
	
	/**
	 * this method check password enter
	 * @param context
	 * @param component
	 * @param value
	 * @throws ValidatorException
	 */
	public void checkPassword(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		setPasswordValid(false);
		String email = (String) value;
		Operation op = new Operation();
		System.out.println(getUsername());
		if (!op.validatePassword(getUsername(), email)) {
			setStatus(AuthenticationStatus.FAILURE);
			setPasswordValid(true);
			return;

		}
		String roles = op.getUserRoleByUsername(getUsername());
		if (roles.equals("Administrator")) {
			setAdmin(true);
		} else if (roles.equals("Reader")) {
			setReader(true);
		} else if (roles.equals("Planner")) {
			setPlanner(true);
		}

		return;

	}

	public void setUsernameEmpty(boolean render) {
		this.usernameEmpty = render;
	}

	public boolean getUsernameEmpty() {
		return usernameEmpty;
	}

	public void setPasswordEmpty(boolean render) {
		this.passwordEmpty = render;
	}

	public boolean getPasswordEmpty() {
		return passwordEmpty;
	}

	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @paramisUsernameValid the isUsernameValid to set
	 */
	public void setUsernameValid(boolean isUsernameValid) {
		this.usernameValid = isUsernameValid;
	}

	public boolean getUsernameValid() {
		return usernameValid;
	}

	/**
	 * @paramisPasswordValid the isPasswordValid to set
	 */
	public void setPasswordValid(boolean isPasswordValid) {
		this.passwordValid = isPasswordValid;
	}

	public boolean getPasswordValid() {
		return passwordValid;
	}

	public void preRenderView() throws IOException {

	}
	
	/**
	 * this method will be triger after succesful validation
	 */
	@Override
	public void authenticate() {

		setUsernameValid(true);
		setPasswordValid(false);
		setUsernameEmpty(false);
		setPasswordEmpty(false);

		if (credentials.getUsername() == null
				|| credentials.getUsername().isEmpty()) {
			setUsernameEmpty(true);
			if (getPassword() == null) {
				setPasswordEmpty(true);

			}
			setStatus(AuthenticationStatus.FAILURE);
			return;
		}

		if (((PasswordCredential) credentials.getCredential()).getValue() == null
				|| ((PasswordCredential) credentials.getCredential())
						.getValue().isEmpty()) {
			setStatus(AuthenticationStatus.FAILURE);
			setPasswordEmpty(true);
			return;
		}

		Operation op = new Operation();
		

		final User user = op.getUserByUsername(credentials.getUsername());
		UserRole role = UserRole.ADMINISTRATOR;

		
		String roles = op.getUserRoleByUsername(credentials.getUsername());

		if (roles.equals("Administrator")) {
			role = UserRole.ADMINISTRATOR;
		} else if (roles.equals("Reader")) {
			role = UserRole.READER;
		} else if (roles.equals("Planner")) {
			role = UserRole.PLANNER;
		}

		final UserRole userRole = role;
		//set identity user
		setUser(new org.picketlink.idm.api.User() {
			@Override
			public String getId() {
				return Long.toString(user.getUserId());
			}

			@Override
			public String getKey() {
				return userRole.toString();
			}
		});
		//success authentification
		setStatus(AuthenticationStatus.SUCCESS);

	}

}
