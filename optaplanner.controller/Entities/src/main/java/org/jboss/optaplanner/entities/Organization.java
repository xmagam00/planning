package org.jboss.optaplanner.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "organization")
/**
 * entity class for table organization
 * @author martin
 *
 */
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idOrganization;
	@NotNull
	private String nameOfOrganization;

	public long getIdOrganization() {
		return idOrganization;
	}

	public void setIdOrganization(long idOrganization) {
		this.idOrganization = idOrganization;
	}

	public String getNameOfOrganization() {
		return nameOfOrganization;
	}

	public void setNameOfOrganization(String NameOfOrganization) {
		this.nameOfOrganization = NameOfOrganization;
	}

}
