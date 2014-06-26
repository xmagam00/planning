package org.jboss.optaplanner.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tasktype")
/**
 * entity class for table organization
 * @author martin
 *
 */
public class TaskType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idType;
	@NotNull
	private String name;
	@NotNull
	private String configuration;
	

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
	
	
	
	public void setConfiguration(String configuration)
	{
		this.configuration = configuration;
	}
	
	public String getConfiguration()
	{
		return configuration;
	}
	

}