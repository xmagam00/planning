package org.jboss.optaplanner.controller.model;

import java.io.Serializable;

/**
 * this class is model for organization
 * @author martin
 *
 */
public class OrganizationDef implements Serializable {

	private static final long serialVersionUID = -8349963947101031989L;
	private String id_organization;
	private String name_of_organization;
	private String renderSave;
	boolean editable;
	private String renderError;

	public OrganizationDef(String idOrganization, String nameOfOrganization,
			String renderSave,String renderError) {
		this.id_organization = idOrganization;
		this.name_of_organization = nameOfOrganization;
		this.renderSave = renderSave;
		this.renderError = renderError;
	}

	public String getNameOfOrganization() {
		return name_of_organization;
	}

	public void setNameOfOrganization(String organization) {
		this.name_of_organization = organization;
	}

	public void setIdOrganization(String id) {
		this.id_organization = id;
	}

	public String getIdOrganization() {
		return id_organization;
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
	
	public void setRenderError(String set)
	{
		this.renderError = set;
	}
	
	public String getRenderError()
	{
		return renderError;
	}

}
