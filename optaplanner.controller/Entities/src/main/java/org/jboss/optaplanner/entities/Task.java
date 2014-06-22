package org.jboss.optaplanner.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "task")
/**
 * entity class for table task
 * @author martin
 *
 */
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private String xmlFile;
	@NotNull
	@Enumerated(EnumType.STRING)
	private TaskStatus status;
	@NotNull
	private int progress;
	@NotNull
	private boolean pub;
	@NotNull
	private long eta;
	@NotNull
	private String name;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user", referencedColumnName = "idUser")
	private User user;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "organization", referencedColumnName = "idOrganization")
	private Organization organization;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "type", referencedColumnName = "idType")
	private Type type;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setXmlFile(String xmlFile) {
		this.xmlFile = xmlFile;
	}

	public String getXmlFile() {
		return xmlFile;

	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setPublic(boolean pub) {
		this.pub = pub;
	}

	public boolean isPublic() {
		return pub;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public int getProgress() {
		return progress;
	}

	public long getETA() {
		return eta;
	}

	public void setETA(long eta) {
		this.eta = eta;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	public Type getType()
	{
		return type;
	}
	
	public void setType(Type set)
	{
		this.type = set;
	}

}
