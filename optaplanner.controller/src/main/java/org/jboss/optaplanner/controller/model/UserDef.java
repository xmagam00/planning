package org.jboss.optaplanner.controller.model;

import java.io.Serializable;

public class UserDef implements Serializable {
	private String id;
	private String username;
	private String password;
	private String role;
	private String organization;
	private String email;
	boolean editable = false;
	private String renderSave;
	private String errorUsername;
	private String errorEmail;
	private String errorRole;
	private String errorOrganization;

	private static final long serialVersionUID = -8349963947101031982L;

	public UserDef(String id, String username, String password, String role,
			String organization, String email, String renderSave, String errorUsername, String errorEmail, String errorRole, String errorOrganization) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.organization = organization;
		this.email = email;
		this.renderSave = renderSave;
		this.errorUsername = errorUsername;
		this.errorEmail = errorEmail;
		this.errorRole = errorRole;
		this.errorOrganization = errorOrganization;
	}

	public String getId() {
		return id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getOrganization() {
		return organization;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public String getRenderSave() {
		return renderSave;
	}

	public void setRenderSave(String set) {
		this.renderSave = set;
	}
	
	public void setErrorUsername(String set)
	{
		this.errorUsername = set;
	}
	
	public String getErrorUsername()
	{
		return errorUsername;
	}
	
	public void setErrorEmail(String set)
	{
		this.errorEmail = set;
	}
	
	public String getErrorEmail()
	{
		return errorEmail;
	}
	
	public void setErrorRole(String set)
	{
		this.errorRole = set;
	}
	
	public String getErrorRole()
	{
		return errorRole;
	}
	
	public void setErrorOrganization(String set)
	{
		this.errorOrganization = set;
	}
	
	public String getErrorOrganization()
	{
		return errorOrganization;
	}

}
