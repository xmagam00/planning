package org.jboss.optaplanner.controller.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.optaplanner.controller.login.ShaEncoder;
import org.jboss.optaplanner.controller.model.OrganizationDef;
import org.jboss.optaplanner.controller.model.TaskDef;
import org.jboss.optaplanner.controller.model.UserDef;
import org.jboss.optaplanner.entities.Organization;
import org.jboss.optaplanner.entities.Task;
import org.jboss.optaplanner.entities.TaskStatus;
import org.jboss.optaplanner.entities.User;

/**
 * 
 * @author martin Maga This class do oper over MySQL database
 */

public class Operation {
	// link to persisntece unit
	private static final String PERSISTENCE_UNIT_NAME = "optaplanner";

	private static EntityManagerFactory factory;

	@PersistenceContext
	private EntityManager eManager;

	public Operation() {

		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		eManager = factory.createEntityManager();

	}

	/**
	 * This method create a new task for user
	 * 
	 * @param username
	 * @param xmlfile
	 * @param urlflag
	 * @return
	 */
	public void createTask(String name, String xmlfile, String username) {
		Query query = eManager
				.createQuery("select idUser from User where username='"
						+ username + "'");
		Object idUser = query.getSingleResult();

		User usertab = eManager.getReference(User.class,
				Long.parseLong(idUser.toString()));

		query = eManager
				.createQuery("select organization from User where username='"
						+ username + "'");
		Object idOrganization = query.getSingleResult();
		Organization org = (Organization) (idOrganization);

		Organization orgtab = eManager.getReference(Organization.class,
				org.getIdOrganization());

		eManager.getTransaction().begin();
		Task task = new Task();
		task.setXmlFile(xmlfile);
		task.setStatus(TaskStatus.NEW);
		task.setPublic(false);
		task.setProgress(0);
		task.setETA(0);
		task.setUser(usertab);
		task.setName(name);
		task.setOrganization(orgtab);

		eManager.persist(task);
		eManager.getTransaction().commit();

	}

	/**
	 * create modified task
	 * 
	 * @param name
	 * @param xmlfile
	 * @param username
	 */
	public void createTaskState(String name, String xmlfile, String username) {
		Query query = eManager
				.createQuery("select idUser from User where username='"
						+ username + "'");
		Object idUser = query.getSingleResult();

		User usertab = eManager.getReference(User.class,
				Long.parseLong(idUser.toString()));

		query = eManager
				.createQuery("select organization from User where username='"
						+ username + "'");
		Object idOrganization = query.getSingleResult();
		Organization org = (Organization) (idOrganization);

		Organization orgtab = eManager.getReference(Organization.class,
				org.getIdOrganization());

		eManager.getTransaction().begin();
		Task task = new Task();
		task.setXmlFile(name);
		task.setStatus(TaskStatus.MODIFIED);
		task.setPublic(false);
		task.setProgress(0);
		task.setETA(0);
		task.setUser(usertab);
		task.setName(xmlfile);
		task.setOrganization(orgtab);

		eManager.persist(task);
		eManager.getTransaction().commit();

	}

	/**
	 * This method create a new user
	 * 
	 * @param username
	 * @param xmlfile
	 * @param urlflag
	 * @return
	 */
	public void createUser(String username, String password, String UserRole,
			String email, long organization) {
		Organization org = eManager.getReference(Organization.class,
				organization);

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(UserRole);
		user.setEmail(email);
		user.setOrganization(org);

		eManager.getTransaction().begin();
		eManager.persist(user);
		eManager.getTransaction().commit();

	}

	/**
	 * Method return all users in database
	 * 
	 * @return
	 */
	public List<UserDef> getAllUsers() {
		Query q = eManager
				.createQuery("SELECT  u.idUser,u.username, u.password,u.role, o.nameOfOrganization,u.email FROM User u,Organization o where u.organization=o.idOrganization");
		List<UserDef> todoList = q.getResultList();

		return todoList;
	}

	/**
	 * Method validate if entered password for user existsin database
	 * 
	 * @param username
	 * @param string
	 * @return true if password match with password in database
	 */
	public boolean validatePassword(String username, String string) {
		boolean result = false;

		Query q = eManager
				.createQuery("select password from User where username='"
						+ username + "'");
		Object pass = q.getSingleResult();
		/*
		 * if (pass.toString().equals(ShaEncoder.hash(string))) { result = true;
		 * 
		 * }
		 */
		if (pass.toString().equals(ShaEncoder.hash(string))) {
			result = true;

		}

		return result;
	}

	/**
	 * Validate existince of username
	 * 
	 * @param username
	 * @return
	 */
	public boolean validateUsername(String username) {
		boolean answer = false;

		Query q = eManager
				.createQuery("SELECT username FROM User where username='"
						+ username + "'");
		List<User> todoList = q.getResultList();
		if (todoList.size() > 0)
			answer = true;

		return answer;
	}

	/**
	 * Return all created task
	 * 
	 * @return
	 */
	public List<TaskDef> getAllTasks() {
		Query q = eManager
				.createQuery("select t.id,t.name,t.status,t.progress,t.eta,t.pub, u.username,t.xmlFile from Task t,User u where t.user = u.idUser");

		List<TaskDef> todoList = q.getResultList();

		return todoList;
	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	public List<TaskDef> selectTaskByUserWithOrganization(String username) {

		Query query = eManager
				.createQuery("select idUser from User where username='"
						+ username + "'");
		Object result = query.getSingleResult();
		User user = eManager.getReference(User.class,
				Long.parseLong(result.toString()));

		Organization test = (Organization) user.getOrganization();

		Query query2 = eManager
				.createQuery("select t.id,t.name,t.status,t.progress,t.eta,t.pub, u.username,t.xmlFile from Task t,User u where t.user = u.idUser and t.organization="
						+ test.getIdOrganization());
		List<TaskDef> todoList = query2.getResultList();
		return todoList;

	}

	/**
	 * Method delete specified user
	 * 
	 * @param username
	 * @return true if operation succeed
	 */
	public void deleteUser(long id) {
		User user = eManager.find(User.class, id);

		eManager.getTransaction().begin();
		eManager.remove(user);
		eManager.getTransaction().commit();

	}

	/**
	 * 
	 * This method delete task
	 * 
	 * @param username
	 * @return
	 */
	public void deleteTask(long idTask) {
		Task task = eManager.find(Task.class, idTask);

		eManager.getTransaction().begin();
		eManager.remove(task);
		eManager.getTransaction().commit();

	}

	/**
	 * This method change userpassword
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public void changePasswordForUser(String username, String password) {
		Query query = eManager
				.createQuery("select idUser from User where username='"
						+ username + "'");
		Object result = query.getSingleResult();

		User user = eManager
				.find(User.class, Long.parseLong(result.toString()));
		eManager.getTransaction().begin();
		user.setPassword(ShaEncoder.hash(password));
		eManager.getTransaction().commit();
	}
	public void changeEmailForUser(String username, String email) {
		Query query = eManager
				.createQuery("select idUser from User where username='"
						+ username + "'");
		Object result = query.getSingleResult();

		User user = eManager
				.find(User.class, Long.parseLong(result.toString()));
		eManager.getTransaction().begin();
		user.setEmail(email);
		eManager.getTransaction().commit();
	}

	public void changePassword(long id, String password) {

		User user = eManager.find(User.class, id);
		eManager.getTransaction().begin();
		user.setPassword(password);
		eManager.getTransaction().commit();
	}

	/**
	 * Change userRole for specified user by parameter username
	 * 
	 * @param username
	 * @param userRole
	 * @return
	 */
	public void changeUserRole(Long id, String userRole) {
		EntityTransaction entr = eManager.getTransaction();
		entr.begin();
		User user = eManager.find(User.class, id);
		user.setRole(userRole);

		entr.commit();

	}

	/**
	 * Method change email for username
	 * 
	 * @param username
	 * @param email
	 */
	public void changeEmail(Long id, String email) {

		EntityTransaction entr = eManager.getTransaction();
		entr.begin();
		User user = eManager.find(User.class, id);
		user.setEmail(email);

		entr.commit();
	}

	/**
	 * Method set username as usernameNew in database
	 * 
	 * @param usernameOld
	 * @param usernameNew
	 */
	public void changeUsername(Long usernameOld, String usernameNew) {

		EntityTransaction entr = eManager.getTransaction();
		entr.begin();
		User user = eManager.find(User.class, usernameOld);
		user.setUsername(usernameNew);

		entr.commit();

	}

	public void changeOrganizationForUser(long id, String org) {
		EntityTransaction entr = eManager.getTransaction();
		entr.begin();
		User user = eManager.find(User.class, id);
		Query query = eManager
				.createQuery("select idOrganization from Organization where nameOfOrganization='"
						+ org + "'");
		Object result = query.getSingleResult();
		Organization organization = eManager.getReference(Organization.class,
				Long.parseLong(result.toString()));
		user.setOrganization(organization);

		entr.commit();

	}

	/**
	 * Method return all organizations
	 * 
	 * @return
	 */
	public List<OrganizationDef> getOrganizations() {
		Query query = eManager
				.createQuery("select idOrganization,nameOfOrganization  from Organization");
		List<OrganizationDef> todoList = query.getResultList();

		return todoList;
	}

	/**
	 * Method create organization
	 * 
	 * @param organization
	 */
	public void createOrganization(String organization) {

		Organization org = new Organization();
		org.setNameOfOrganization(organization);

		eManager.getTransaction().begin();
		eManager.persist(org);
		eManager.getTransaction().commit();

	}

	/**
	 * Method change name of organization when is needed
	 * 
	 * @param oldOrg
	 * @param newOrg
	 */
	public void changeOrganization(Long id, String newOrg) {

		EntityTransaction entr = eManager.getTransaction();
		entr.begin();
		Organization org = eManager.find(Organization.class, id);
		org.setNameOfOrganization(newOrg);

		entr.commit();

	}

	/**
	 * Delete organization
	 * 
	 * @param org
	 */
	public void deleteOrganization(Long org) {
		Organization organization = eManager.getReference(Organization.class,
				org);

		try {
			eManager.getTransaction().begin();
			eManager.remove(organization);
			eManager.getTransaction().commit();
		} catch (Exception ex) {

		}

	}

	/**
	 * Method return userrole for username
	 * 
	 * @param username
	 */
	public String getUserRoleByUsername(String username) {
		String answer = null;

		Query q = eManager.createQuery("select role from User where username='"
				+ username + "'");
		Object role = q.getSingleResult();

		answer = role.toString();

		return answer;

	}

	/**
	 * get id organization
	 * 
	 * @param organization
	 * @return
	 */
	public long getIdOrganization(String organization) {
		long answer = 0;

		Query q = eManager
				.createQuery("select idOrganization from Organization where nameOfOrganization='"
						+ organization + "'");
		Object result = q.getSingleResult();

		answer = Long.valueOf(result.toString()).longValue();

		return answer;
	}

	/**
	 * method get id of user
	 * 
	 * @param username
	 * @return
	 */
	public long getIdUser(String username) {
		long answer = 0;

		Query q = eManager
				.createQuery("select idUser from User where username='"
						+ username + "'");
		Object user = q.getSingleResult();

		answer = Long.valueOf(user.toString()).longValue();

		return answer;
	}

	/**
	 * Method set new xml file
	 * 
	 * @param idTask
	 * @param xmlFile
	 */
	public void changeXmlFile(String xmlFile, String name, String owner) {

		createTaskState(name, xmlFile, owner);

	}

	/**
	 * method change name of task
	 * 
	 * @param idTask
	 * @param newName
	 */
	public void changeNameOfTask(long idTask, String newName) {
		EntityTransaction entr = eManager.getTransaction();
		entr.begin();
		Task task = eManager.getReference(Task.class, idTask);
		task.setName(newName);

		entr.commit();
	}

	/**
	 * method change permission
	 * 
	 * @param idTask
	 * @param permission
	 */
	public void changePermission(long idTask, String permission) {
		EntityTransaction entr = eManager.getTransaction();
		entr.begin();
		Task task = eManager.find(Task.class, idTask);
		task.setPublic(convertPermission(permission));

		entr.commit();
	}

	private boolean convertPermission(String perm) {
		return ((perm == "0") ? false : true);
	}

	/**
	 * Method return User entity for logging
	 * 
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username) {
		Query q = eManager
				.createQuery("select idUser from User where username='"
						+ username + "'");
		Object user = q.getSingleResult();
		User users = eManager.getReference(User.class,
				Long.parseLong(user.toString()));
		return users;
	}

	/**
	 * method get id user
	 * 
	 * @param id
	 * @return
	 */
	public String getUserById(long id) {
		Query q = eManager
				.createQuery("select username from User where idUser="
						+ String.valueOf(id));
		Object user = q.getSingleResult();

		return user.toString();
	}

	/**
	 * method get xml file of task with id
	 * 
	 * @param idTask
	 * @return
	 */
	public String getXmlFileName(long idTask) {
		Query q = eManager
				.createQuery("select  t.xmlFile from Task t where  t.id="	+ idTask);
		Object todoList = q.getSingleResult();
		return todoList.toString();
	}

	/**
	 * method get name of task with id
	 * 
	 * @param idTask
	 * @return
	 */
	public String getNameOfTask(long idTask) {
		Query q = eManager.createQuery("select t.name from Task t where  t.id="	+ idTask);
		Object todoList = q.getSingleResult();
		return todoList.toString();
	}

	/**
	 * method get permission of task by id
	 * 
	 * @param idTask
	 * @return
	 */
	public String getPermissionOfTask(long idTask) {
		Query q = eManager.createQuery("select  t.pub from Task t where  t.id=" + idTask);
		Object todoList = q.getSingleResult();
		return todoList.toString();
	}
	
	public String getUserRole(String Username) {
		Query q = eManager.createQuery("select  t.role from User t where  t.username=" + Username);
		Object todoList = q.getSingleResult();
		return todoList.toString();
	}
	
	

}
