package org.jboss.optaplanner.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "type")
/**
 * entity class for table organization
 * @author martin
 *
 */
public class Type {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idType;
	@NotNull
	private String name;
	@NotNull
	private String configuration;
	@NotNull
	private String drools;

	public long getIdType() {
		return idType;
	}

	public void setIdType(long idOrganization) {
		this.idType = idOrganization;
	}

	public String getName() {
		return name;
	}

	public void setName(String NameOfOrganization) {
		this.name = NameOfOrganization;
	}
	
	public void setDrools(String drools)
	{
		this.drools = drools;
	}
	
	public String getDrools()
	{
		return drools;
	}
	
	public void setConfiguration(String configuration)
	{
		this.configuration = configuration;
	}
	
	public String getConfiguration()
	{
		return configuration;
	}
	

}