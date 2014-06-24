package org.jboss.optaplanner.controller.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.jboss.optaplanner.controller.database.Operation;
import org.jboss.optaplanner.controller.login.ShaEncoder;
import org.jboss.optaplanner.controller.model.OrganizationDef;
import org.jboss.optaplanner.controller.model.TaskDef;
import org.jboss.optaplanner.controller.model.UserDef;
import org.jboss.optaplanner.controller.service.Exception_Exception;
import org.jboss.optaplanner.controller.service.OptaPlannerWebService;
import org.jboss.optaplanner.controller.service.OptaPlannerWebServiceService;
import org.jboss.seam.security.Identity;
import org.picketlink.idm.api.User;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

@ManagedBean
@RequestScoped
/**
 * This class handle page for role Administrator
 * @author martin
 *
 */
public class AdministratorBean {

	@Inject
	private Identity identity;
	private String renderError;
	private List<TaskDef> task;
	private List<TaskDef> oldTask;
	private String renderEmailNot;
	private String renderEmailValidate;
	private List<UserDef> users;
	private List<UserDef> oldUsers;
	private String unrenderCommand;
	private List<OrganizationDef> organizations;
	private List<OrganizationDef> oldOrganizations;

	private String password;

	private int page = 1;
	private int page2 = 1;
	private int page3 = 1;
	private String pass;
	private String typeDef;
	private String passwordValidate = "true";

	private int test = 1;
	private long idUser;

	private String renderPoll = "true";

	private String xmlFile;
	private String save;
	private String idTask;
	private boolean sortIdTask;
	private boolean sortNameTask;
	private boolean sortStateTask;
	private boolean sortEtaTask;
	private boolean sortPermissionTask;
	private boolean sortOwnerTask;
	private String activeTask;
	private String activeUsers;
	private String activeOrganization;
	private String activeProperties;
	private boolean sortUsernameUser;

	private boolean sortEmailUser;
	private boolean findOperation3 = false;
	private boolean sortRoleUser;
	private boolean sortOrganizationUser;
	private List<String> listConf;
	private boolean sortIdOrganization;
	private boolean sortNameOrganization;
	private String activeEdit;
	private String username;

	private String changeUsername;

	private String changeOrg;

	private String organization;

	private List<String> editableOrganizations;
	private List<String> types;

	private List<String> editableOwner;

	private String renderArea;

	private String renderButton;

	private String unpublishInformation;

	private String publishInformation;

	private String name;
	private Operation op;

	private String tab = "Tasks";

	private String renderTab;

	private String renderTab1;
	private String renderTab2;
	private String findString;

	private String nieco;

	private boolean sortAscending = true;

	private boolean sortAscending2 = true;
	private boolean sortAscending3 = true;
	private String owner;

	private String state;

	private String findOption;

	private String renderName;

	private String renderUpload;

	private String renderUsername;

	private String renderPassword;
	private String renderPasswordValidate;
	private String renderPasswordNot;

	private String renderEmail;

	private String renderOrganization;

	private boolean findOperation2 = false;
	private String renderRole;
	private String renderResult2;
	private String result2;
	private String renderEmailFormat;
	private String renderResult3;
	private String result3;
	private Pattern pattern;
	private Matcher matcher;

	private String renderPassword2;
	private String renderPasswordValidate2;
	private String renderPasswordNot2;

	private String renderPassword3;
	private String renderPasswordValidate3;
	private String renderPasswordNot3;

	private String renderOrg;

	private String renderOption;

	private String renderFind;

	private String renderOption2;

	private String renderFind2;

	private String renderOption3;

	private String renderFind3;
	private String loggedUsername;

	private String loadFunction;

	private String idEntity;

	private String orgPoll = "true";

	private String updPoll;

	private String orgPoll2;

	private String result;
	private String email;
	private String renderResult;
	private String roleLogged;
	private String refreshValue = "Stop Refresh";
	private String refreshValue1 = "Stop Refresh";
	private String refreshValue2 = "Stop Refresh";
	private String renderUser = "true";

	private String renderRow;

	private String renderRow2;

	private String renderRow3;
	private String role;
	private OptaPlannerWebServiceService s;
	private OptaPlannerWebService ws;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * This method will be done before page render It will get all necessery
	 * information from database for table user, task, organization
	 */
	@PostConstruct
	public void init() {

		try {
			listConf = new ArrayList<String>();
			setActiveEdit("");
			setActiveTask("active");
			setActiveUsers("");
			setActiveOrganization("");
			setActiveProperties("");
			findOperation3 = false;
			setRenderRow("5");
			setRenderRow2("5");
			setRenderRow3("5");
			setRenderUser("true");
			setRefreshValue("Stop Refresh");
			setUpdPoll("true");
			setOrgPoll("true");
			setRenderTab("false");
			setLoadFunction("$('#MyTab a:first').tab('show')");
			setRenderEmailFormat("false");
			setRenderUsername("false");
			setRenderPassword("false");
			setRenderPasswordValidate("false");
			setRenderPasswordNot("false");
			setRenderOrganization("false");
			setRenderEmail("false");
			setRenderRole("false");
			setRenderUpload("false");
			setRenderName("false");
			setRenderArea("false");
			setRenderPoll("true");
			setRenderButton("false");

			setUnpublishInformation("");
			op = new Operation();
			// get identity of logged user
			User user = identity.getUser();

			String result = op.getUserById(Long.parseLong(user.getId()));
			// show logged username
			setLoggedUsername(result);

			roleLogged = op.getUserRole(getLoggedUsername());
			if (roleLogged.equals("Reader")) {
				setRenderTab1("false");
			} else {
				setRenderTab1("true");
			}
			if (roleLogged.equals("Administrator")) {
				setRenderTab2("true");
			} else {
				setRenderTab2("false");

			}
			System.out.println(roleLogged);
			organizations = new ArrayList<OrganizationDef>();
			task = new ArrayList<TaskDef>();
			users = new ArrayList<UserDef>();

			editableOrganizations = new ArrayList<String>();
			// get all organizatoions
			List<OrganizationDef> resultsOrg = op.getOrganizations();
			for (Object item : resultsOrg) {
				Object[] obj = (Object[]) item;
				organizations.add(new OrganizationDef(obj[0].toString(), obj[1]
						.toString(), "false", "false"));
				editableOrganizations.add(obj[1].toString());
			}
			// get all tasks
			List<TaskDef> resultsTask = new ArrayList<TaskDef>();
			if (roleLogged.equals("Administrator")) {
				resultsTask = op.getAllTasks();
			}

			else {
				resultsTask = op
						.selectTaskByUserWithOrganization(getLoggedUsername());
			}
			if (roleLogged.equals("Reader")) {
				unrenderCommand = "false";
			} else {
				unrenderCommand = "true";
			}
			for (Object item : resultsTask) {
				Object[] obj = (Object[]) item;
				task.add(new TaskDef(obj[0].toString(), obj[1].toString(),
						obj[2].toString(), obj[3].toString(),
						obj[4].toString(), convertIfPublic(obj[5].toString()),
						obj[6].toString(), obj[7].toString(), renderStop(obj[2]
								.toString()), renderRun(obj[2].toString()),
						renderPublish(obj[5].toString(), obj[2].toString()),
						renderUnpublish(obj[5].toString()), renderEdit(obj[2]
								.toString()), renderDelete(obj[2].toString()),
						renderCommand(obj[5].toString()), obj[8].toString()));

			}
			int index = 0;
			if (unrenderCommand.equals("false")) {
				for (Object item : task) {

					task.get(index).setRenderRun("false");
					task.get(index).setRenderStop("false");
					task.get(index).setRenderEdit("false");
					task.get(index).setRenderDelete("false");

					index++;

				}
			}
			// get all users
			List<UserDef> resultsUser = op.getAllUsers();
			for (Object item : resultsUser) {
				Object[] obj = (Object[]) item;
				users.add(new UserDef(obj[0].toString(), obj[1].toString(),
						obj[2].toString(), obj[3].toString(),
						obj[4].toString(), obj[5].toString(), "false", "false",
						"false", "false", "false"));

			}
			s = new OptaPlannerWebServiceService();
			ws = s.getOptaPlannerWebServicePort();

		} catch (Exception e) {

		}
	}

	/**
	 * this method render button edit
	 * 
	 * @param state
	 * @return
	 */
	private String renderEdit(String state) {
		if (state.equals("MODIFIED") | state.equals("NEW")
				| state.equals("COMPLETE") | state.equals("PAUSED")) {
			return "true";
		}

		else
			return "false";
	}

	/**
	 * this method render button run according to state
	 * 
	 * @param state
	 * @return
	 */
	private String renderRun(String state) {
		if (state.equals("PAUSED") | state.equals("MODIFIED")
				| state.equals("NEW")) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * this method render button stop according to state
	 * 
	 * @param state
	 * @return
	 */
	private String renderStop(String state) {
		if (state.equals("IN_PROGRESS") | state.equals("WAITING")) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * this method render button publish according to state
	 * 
	 * @param state
	 * @return
	 */
	private String renderPublish(String state, String stat) {
		if ((state.equals("false") && stat.equals("COMPLETE"))
				|| state.equals("false") && stat.equals("MODIFIED")) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * this method render button unpublish according to state
	 * 
	 * @param state
	 * @return
	 */
	private String renderUnpublish(String state) {
		if (state.equals("true")) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * this method render button delete according to state
	 * 
	 * @param state
	 * @return
	 */
	private String renderDelete(String state) {
		if (state.equals("NEW") | state.equals("COMPLETE")
				| state.equals("MODIFIED")) {
			return "true";
		} else {
			return "false";
		}
	}

	/**
	 * This method sort table task according to column name
	 * 
	 * @return
	 */
	public String sortByName() {
		sortNameTask = true;
		sortIdTask = false;
		sortOwnerTask = false;
		sortStateTask = false;
		sortEtaTask = false;
		sortPermissionTask = false;
		setLoadFunction("$('#MyTab a:first').tab('show')");
		if (sortAscending) {

			// ascending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o1.getName().compareTo(o2.getName());

				}

			});
			sortAscending = false;

		} else {

			// descending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o2.getName().compareTo(o1.getName());

				}

			});
			sortAscending = true;
		}

		return null;
	}

	/**
	 * This method sort table task according to column username
	 * 
	 * @return
	 */
	public String sortByUsername() {
		sortUsernameUser = true;
		sortEmailUser = false;
		sortRoleUser = false;
		sortOrganizationUser = false;
		setLoadFunction("$('#MyTab li:eq(2) a').tab('show')");
		if (sortAscending2) {

			// ascending order
			Collections.sort(users, new Comparator<UserDef>() {

				@Override
				public int compare(UserDef o1, UserDef o2) {

					return o1.getUsername().compareTo(o2.getUsername());

				}

			});
			sortAscending2 = false;

		} else {

			// descending order
			Collections.sort(users, new Comparator<UserDef>() {

				@Override
				public int compare(UserDef o1, UserDef o2) {

					return o2.getUsername().compareTo(o1.getUsername());

				}

			});
			sortAscending2 = true;
		}

		return null;
	}

	/**
	 * This method sort table organization according to column organization
	 * 
	 * @return
	 */
	public String sortByNameOrganization() {
		sortIdOrganization = false;
		sortNameOrganization = true;
		setLoadFunction("$('#MyTab li:eq(4) a').tab('show')");
		if (sortAscending3) {

			// ascending order
			Collections.sort(organizations, new Comparator<OrganizationDef>() {

				@Override
				public int compare(OrganizationDef o1, OrganizationDef o2) {

					return o1.getNameOfOrganization().compareTo(
							o2.getNameOfOrganization());

				}

			});
			sortAscending3 = false;

		} else {

			// descending order
			Collections.sort(organizations, new Comparator<OrganizationDef>() {

				@Override
				public int compare(OrganizationDef o1, OrganizationDef o2) {

					return o2.getNameOfOrganization().compareTo(
							o1.getNameOfOrganization());

				}

			});
			sortAscending3 = true;
		}

		return null;
	}

	/**
	 * This method sort table organization according to column id organization
	 * 
	 * @return
	 */
	public String sortByIdOrg() {
		sortIdOrganization = true;
		sortNameOrganization = false;
		setLoadFunction("$('#MyTab li:eq(4) a').tab('show')");
		if (sortAscending3) {

			// ascending order
			Collections.sort(organizations, new Comparator<OrganizationDef>() {

				@Override
				public int compare(OrganizationDef o1, OrganizationDef o2) {

					return o1.getIdOrganization().compareTo(
							o2.getIdOrganization());

				}

			});
			sortAscending3 = false;

		} else {

			// descending order
			Collections.sort(organizations, new Comparator<OrganizationDef>() {

				@Override
				public int compare(OrganizationDef o1, OrganizationDef o2) {

					return o2.getIdOrganization().compareTo(
							o1.getIdOrganization());

				}

			});
			sortAscending3 = true;
		}

		return null;
	}

	/**
	 * This method sort table task according to column email
	 * 
	 * @return
	 */
	public String sortByEmail() {
		sortUsernameUser = false;
		sortEmailUser = true;
		sortRoleUser = false;
		sortOrganizationUser = false;
		setLoadFunction("$('#MyTab li:eq(2) a').tab('show')");
		if (sortAscending2) {

			// ascending order
			Collections.sort(users, new Comparator<UserDef>() {

				@Override
				public int compare(UserDef o1, UserDef o2) {

					return o1.getEmail().compareTo(o2.getEmail());

				}

			});
			sortAscending2 = false;

		} else {

			// descending order
			Collections.sort(users, new Comparator<UserDef>() {

				@Override
				public int compare(UserDef o1, UserDef o2) {

					return o2.getEmail().compareTo(o1.getEmail());

				}

			});
			sortAscending2 = true;
		}

		return null;
	}

	/**
	 * This method sort table task according to column role
	 * 
	 * @return
	 */
	public String sortByRole() {
		sortUsernameUser = false;
		sortEmailUser = false;
		sortRoleUser = true;
		sortOrganizationUser = false;
		setLoadFunction("$('#MyTab li:eq(2) a').tab('show')");
		if (sortAscending2) {

			// ascending order
			Collections.sort(users, new Comparator<UserDef>() {

				@Override
				public int compare(UserDef o1, UserDef o2) {

					return o1.getRole().compareTo(o2.getRole());

				}

			});
			sortAscending2 = false;

		} else {

			// descending order
			Collections.sort(users, new Comparator<UserDef>() {

				@Override
				public int compare(UserDef o1, UserDef o2) {

					return o2.getRole().compareTo(o1.getRole());

				}

			});
			sortAscending2 = true;
		}

		return null;
	}

	/**
	 * This method sort table user according to column organization
	 * 
	 * @return
	 */
	public String sortByOrganization() {
		sortUsernameUser = false;
		sortEmailUser = false;
		sortRoleUser = false;
		sortOrganizationUser = true;
		setLoadFunction("$('#MyTab li:eq(2) a').tab('show')");
		if (sortAscending2) {

			// ascending order
			Collections.sort(users, new Comparator<UserDef>() {

				@Override
				public int compare(UserDef o1, UserDef o2) {

					return o1.getOrganization().compareTo(o2.getOrganization());

				}

			});
			sortAscending2 = false;

		} else {

			// descending order
			Collections.sort(users, new Comparator<UserDef>() {

				@Override
				public int compare(UserDef o1, UserDef o2) {

					return o2.getOrganization().compareTo(o1.getOrganization());

				}

			});
			sortAscending2 = true;
		}

		return null;
	}

	/**
	 * This method sort table task according to column permission
	 * 
	 * @return
	 */
	public String sortByPermission() {
		sortPermissionTask = true;
		sortIdTask = false;
		sortNameTask = false;
		sortStateTask = false;
		sortEtaTask = false;
		sortOwnerTask = false;
		setLoadFunction("$('#MyTab a:first').tab('show')");
		if (sortAscending) {

			// ascending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o1.getIfPublic().compareTo(o2.getIfPublic());

				}

			});
			sortAscending = false;

		} else {

			// descending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o2.getIfPublic().compareTo(o1.getIfPublic());

				}

			});
			sortAscending = true;
		}

		return null;
	}

	/**
	 * This method sort table task according to column owner
	 * 
	 * @return
	 */
	public String sortByOwner() {
		sortIdTask = false;
		sortNameTask = false;
		sortStateTask = false;
		sortEtaTask = false;
		sortPermissionTask = false;

		sortOwnerTask = true;
		setLoadFunction("$('#MyTab a:first').tab('show')");
		if (sortAscending) {

			// ascending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o1.getOwner().compareTo(o2.getOwner());

				}

			});
			sortAscending = false;

		} else {

			// descending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o2.getOwner().compareTo(o1.getOwner());

				}

			});
			sortAscending = true;
		}

		return null;
	}

	/**
	 * This method sort table task according to column state
	 * 
	 * @return
	 */
	public String sortByState() {
		sortStateTask = true;
		sortIdTask = false;
		sortNameTask = false;
		sortOwnerTask = false;
		sortEtaTask = false;
		sortPermissionTask = false;
		setLoadFunction("$('#MyTab a:first').tab('show')");
		if (sortAscending) {

			// ascending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o1.getState().compareTo(o2.getState());

				}

			});
			sortAscending = false;

		} else {

			// descending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o2.getState().compareTo(o1.getState());

				}

			});
			sortAscending = true;
		}

		return null;
	}

	/**
	 * This method sort table task according to column eta
	 * 
	 * @return
	 */
	public String sortByETA() {
		sortEtaTask = true;
		sortIdTask = false;
		sortNameTask = false;
		sortStateTask = false;
		sortOwnerTask = false;
		sortPermissionTask = false;
		setLoadFunction("$('#MyTab a:first').tab('show')");
		if (sortAscending) {

			// ascending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o1.getEstimatedTime().compareTo(
							o2.getEstimatedTime());

				}

			});
			sortAscending = false;

		} else {

			// descending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o2.getEstimatedTime().compareTo(
							o1.getEstimatedTime());

				}

			});
			sortAscending = true;
		}

		return null;
	}

	/**
	 * This method sort table task according to column id
	 * 
	 * @return
	 */
	public String sortByType() {
		sortIdTask = true;
		sortOwnerTask = false;
		sortNameTask = false;
		sortStateTask = false;
		sortEtaTask = false;
		sortPermissionTask = false;
		setLoadFunction("$('#MyTab a:first').tab('show')");
		if (sortAscending) {

			// ascending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o1.getType().compareTo(o2.getType());

				}

			});
			sortAscending = false;

		} else {

			// descending order
			Collections.sort(task, new Comparator<TaskDef>() {

				@Override
				public int compare(TaskDef o1, TaskDef o2) {

					return o2.getType().compareTo(o1.getType());

				}

			});
			sortAscending = true;
		}

		return null;
	}

	/**
	 * method get all users from database
	 */
	public void updateOwner() {

		List<String> org = new ArrayList<String>();
		List<UserDef> resultsOrg = op.getAllUsers();
		for (Object item : resultsOrg) {
			Object[] obj = (Object[]) item;

			org.add(obj[1].toString());
		}

		this.editableOwner = org;

	}

	/**
	 * method get actual organizations from databases
	 */
	public void updateEditableOrganizations() {

		this.editableOrganizations = null;
		List<String> org = new ArrayList<String>();
		List<OrganizationDef> resultsOrg = op.getOrganizations();
		for (Object item : resultsOrg) {
			Object[] obj = (Object[]) item;

			org.add(obj[1].toString());
		}
		this.editableOrganizations = org;
	}

	/**
	 * This method get types of planning problem
	 */
	public void updateTypes() {

		this.types = null;
		List<String> org = new ArrayList<String>();
		List<String> resultsOrg = op.getAllTypes();
		for (String item : resultsOrg) {

			org.add(item);
		}
		this.types = org;
	}

	/**
	 * method get actual users from databases
	 */
	public void updateEditableUsers() {
		this.editableOrganizations = null;
		List<String> org = new ArrayList<String>();
		List<OrganizationDef> resultsOrg = op.getOrganizations();
		for (Object item : resultsOrg) {
			Object[] obj = (Object[]) item;

			org.add(obj[1].toString());
		}
		this.editableOrganizations = org;
	}

	/**
	 * Method to set page of scroller in table task
	 */
	public void scroller() {

		test = getPage();
		setRenderPoll("true");

	}

	/**
	 * Method to set page of scroller in table users
	 */
	public void scroller2() {

		test = getPage2();
		setRenderUser("true");

	}

	/**
	 * This method get actual users
	 */
	public void updateTasks() {
		setRenderResult("false");
		setRenderPoll("true");

		setPage(test);
		List<TaskDef> org = new ArrayList<TaskDef>();
		List<TaskDef> resultsOrg = new ArrayList<TaskDef>();
		// get all tasks

		if (roleLogged.equals("Administrator")) {
			resultsOrg = op.getAllTasks();
		}

		else {
			resultsOrg = op
					.selectTaskByUserWithOrganization(getLoggedUsername());
		}
		for (Object item : resultsOrg) {
			Object[] obj = (Object[]) item;
			org.add(new TaskDef(obj[0].toString(), obj[1].toString(), obj[2]
					.toString(), obj[3].toString(), obj[4].toString(),
					convertIfPublic(obj[5].toString()), obj[6].toString(),
					obj[7].toString(), renderStop(obj[2].toString()),
					renderRun(obj[2].toString()), renderPublish(
							obj[5].toString(), obj[2].toString()),
					renderUnpublish(obj[5].toString()), renderEdit(obj[2]
							.toString()), renderDelete(obj[2].toString()),
					renderCommand(obj[5].toString()), obj[8].toString()));

		}
		int index = 0;
		if (unrenderCommand.equals("false")) {
			for (Object item : org) {

				org.get(index).setRenderRun("false");
				org.get(index).setRenderStop("false");
				org.get(index).setRenderEdit("false");
				org.get(index).setRenderDelete("false");

				index++;

			}
		}

		setPage(test);

		this.task = org;
		if (sortIdTask == Boolean.TRUE) {
			if (sortAscending == Boolean.TRUE) {
				sortAscending = false;
			} else {
				sortAscending = true;
			}
			sortByType();

		}
		if (sortNameTask == Boolean.TRUE) {
			if (sortAscending == Boolean.TRUE) {
				sortAscending = false;
			} else {
				sortAscending = true;
			}
			sortByName();
		}
		if (sortStateTask == Boolean.TRUE) {
			if (sortAscending == Boolean.TRUE) {
				sortAscending = false;
			} else {
				sortAscending = true;
			}
			sortByState();
		}
		if (sortEtaTask == Boolean.TRUE) {
			if (sortAscending == Boolean.TRUE) {
				sortAscending = false;
			} else {
				sortAscending = true;
			}
			sortByETA();
		}
		if (sortOwnerTask == Boolean.TRUE) {
			if (sortAscending == Boolean.TRUE) {
				sortAscending = false;
			} else {
				sortAscending = true;
			}
			sortByOwner();
		}
		if (sortPermissionTask == Boolean.TRUE) {
			if (sortAscending == Boolean.TRUE) {
				sortAscending = false;
			} else {
				sortAscending = true;
			}
			sortByPermission();
		}
		System.out.println("Nastav stranku" + test);
		setPage(test);
		setRenderPoll("true");
		setPage3(test);

	}

	/**
	 * this method render command link in table task
	 * 
	 * @param render
	 * @return
	 */
	private String renderCommand(String render) {

		if (render.equals("false") || render.equals("Private")) {
			return "false";

		}

		else
			return "true";

	}

	public void setTasks(List<TaskDef> tasks) {
		this.task = tasks;
	}

	/**
	 * Method convert number from database to Private|Public
	 * 
	 * @param ifPublic
	 * @return
	 */
	private String convertIfPublic(String ifPublic) {

		if (ifPublic.equals("false") || ifPublic.equals("Private")) {
			return "Private";
		} else {
			return "Public";
		}
	}

	/**
	 * Method return organizations
	 * 
	 * @return
	 */
	public List<OrganizationDef> getOrganizations() {
		return organizations;
	}

	/**
	 * method set organizations
	 * 
	 * @param organizations
	 */
	public void setOrganizations(List<OrganizationDef> organizations) {
		this.organizations = organizations;
	}

	/**
	 * method get task
	 * 
	 * @return
	 */
	public List<TaskDef> updateProgress() {
		return this.task;
	}

	/**
	 * method get users
	 * 
	 * @return
	 */
	public List<UserDef> getUser() {
		return this.users;
	}

	/**
	 * method get task
	 * 
	 * @return
	 */
	public List<TaskDef> getTask() {
		return this.task;
	}

	/**
	 * method set task
	 * 
	 * @param task
	 */
	public void setTask(List<TaskDef> task) {
		this.task = task;
	}

	/**
	 * method save users into databas e in table user
	 * 
	 * @return
	 */
	public String saveAction() {
		boolean end = false;
		setLoadFunction("$('#MyTab li:eq(2) a').tab('show')");
		// get all existing value but set "editable" to false
		for (UserDef order : users) {
			if (order.getUsername().equals("") || order.getUsername().isEmpty()) {
				setRenderUser("false");
				order.setErrorUsername("true");
				end = true;

			}
			if (order.getEmail().equals("") || order.getEmail().isEmpty()) {
				setRenderUser("false");
				order.setErrorEmail("true");
				end = true;
			}
			if (order.getRole().equals("") || order.getRole().isEmpty()) {
				setRenderUser("false");
				order.setErrorRole("true");
				end = true;
			}
			if (order.getOrganization().equals("")
					|| order.getOrganization().isEmpty()) {
				setRenderUser("false");
				order.setErrorOrganization("true");
				end = true;
			}

		}
		if (end == Boolean.TRUE) {
			return null;
		}
		for (UserDef order : users) {

			order.setEditable(false);
			order.setRenderSave("false");

		}
		setRenderUser("false");
		updateUsers(this.oldUsers, this.users);
		if (findOperation3 == Boolean.FALSE) {

			List<UserDef> userss = new ArrayList<UserDef>();
			List<UserDef> resultsUsers = op.getAllUsers();
			for (Object item : resultsUsers) {
				Object[] obj = (Object[]) item;
				userss.add(new UserDef(obj[0].toString(), obj[1].toString(),
						obj[2].toString(), obj[3].toString(),
						obj[4].toString(), obj[5].toString(), "false", "false",
						"false", "false", "false"));

			}
			setRenderUser("true");

			this.users = userss;
		} else {
			System.out.println("tu nemam byt");
			setRenderUser("false");
		}
		// return to current page
		return null;

	}

	/**
	 * method set id user to be deleted
	 * 
	 * @param user
	 */
	public void updateUser(UserDef user) {
		setIdUser(Long.parseLong(user.getId()));
	}

	/**
	 * Method update changed organizations in database
	 * 
	 * @param org
	 * @param orgChanged
	 */
	private void updateOrganizations(List<OrganizationDef> org,
			List<OrganizationDef> orgChanged) {
		int index = 0;
		for (OrganizationDef element : org) {

			/*
			 * if
			 * ((org.get(index).getNameOfOrganization()).equals(orgChanged.get(
			 * index).getNameOfOrganization()))
			 * 
			 * {
			 * 
			 * }
			 */{
				if (!(org.get(index).getNameOfOrganization()).equals(orgChanged
						.get(index).getNameOfOrganization())) {

					op.changeOrganization(
							Long.parseLong(org.get(index).getIdOrganization()),
							org.get(index).getNameOfOrganization());
				}

			}

			index++;
		}

	}

	/**
	 * Method save xml file in tab edit task
	 */
	public void saveXmlFile() {
		setRenderPoll("false");
		setRenderTab("true");
		setLoadFunction("$('#MyTab a:first').tab('show')");

		op.changeXmlFile(getName(), getXmlFile(), getOwner());
		setRenderPoll("true");
		setXmlFile("");
		setName("");
		setOwner("");
		setRenderTab("false");

	}

	/**
	 * Method to switch into edit task tab and get all necessary information
	 * 
	 * @param task
	 */
	public void editXmlFile(TaskDef task) {
		setRenderTab("true");

		setState(task.getState());
		setOwner(task.getOwner());
		setTypeDef(task.getType());
		editableOwner = null;
		editableOwner = new ArrayList<String>();
		List<UserDef> resultsUser = op.getAllUsers();
		for (Object item : resultsUser) {
			Object[] obj = (Object[]) item;
			editableOwner.add(obj[1].toString());

		}
		types = null;
		types = new ArrayList<String>();
		List<String> resultTypes = op.getAllTypes();
		for (String item : resultTypes) {

			types.add(item);

		}
		setEditTask();
		setXmlFile(task.getXmlFile());
		setIdTask(task.getId());
		setRenderPoll("false");

	}

	/**
	 * method get all users
	 */
	public void showAllUsers() {
		setRenderResult2("false");
		setLoadFunction("$('#MyTab li:eq(2) a').tab('show')");
		setFindOption("");
		setNieco("");
		findOperation3 = false;
		List<UserDef> resultsUser = op.getAllUsers();
		List<UserDef> resultsOrg = new ArrayList<UserDef>();
		for (Object item : resultsUser) {
			Object[] obj = (Object[]) item;
			resultsOrg.add(new UserDef(obj[0].toString(), obj[1].toString(),
					obj[2].toString(), obj[3].toString(), obj[4].toString(),
					obj[5].toString(), "false", "false", "false", "false",
					"false"));

		}

		this.users = resultsOrg;

	}

	/**
	 * merhod get all organizations
	 */
	public void showAllOrganizations() {
		findOperation2 = false;
		setLoadFunction("$('#MyTab li:eq(3) a').tab('show')");
		setNieco("");
		setFindOption("");
		setOrgPoll("true");
		List<OrganizationDef> list = new ArrayList<OrganizationDef>();
		List<OrganizationDef> resultsOrg = op.getOrganizations();
		for (Object item : resultsOrg) {
			Object[] obj = (Object[]) item;
			list.add(new OrganizationDef(obj[0].toString(), obj[1].toString(),
					"false", "false"));

		}
		findOperation2 = false;
		setRenderResult3("false");
		setOrgPoll("true");
		this.organizations = list;
	}

	/**
	 * method find all organization row in table according to parameters
	 */
	public void findListOrg() {
		setLoadFunction("$('#MyTab li:eq(3) a').tab('show')");
		updateOrganization();
		setOrgPoll("false");
		setRenderOption3("false");

		updateOrganization();

		findOperation2 = true;
		setLoadFunction("$('#MyTab li:eq(3) a').tab('show')");

		setRenderResult3("true");
		Date date = new Date();
		String str = String.format("Current Date/Time : %tc", date);
		setResult3("Search result - report as \"" + getNieco() + "\" " + " "
				+ str);

		int index = 0;

		List<OrganizationDef> find = new ArrayList<OrganizationDef>();

		if (getFindOption().equals("Name of Organization")) {

			for (OrganizationDef element : organizations) {
				if (((organizations.get(index).getNameOfOrganization())
						.toUpperCase()).equals(getNieco().toUpperCase())) {

					find.add(new OrganizationDef(organizations.get(index)
							.getIdOrganization(), organizations.get(index)
							.getNameOfOrganization(), "false", "false"));

				}
				index++;
			}

		} else if (getFindOption().equals("ID")) {

			for (OrganizationDef element : organizations) {
				if (((organizations.get(index).getIdOrganization()))
						.equals(getNieco())) {

					find.add(new OrganizationDef(organizations.get(index)
							.getIdOrganization(), organizations.get(index)
							.getNameOfOrganization(), "false", "false"));

				}
				index++;
			}

		}
		setOrgPoll("false");
		setLoadFunction("$('#MyTab li:eq(3) a').tab('show')");
		this.organizations = find;

	}

	/**
	 * method find all user row in table according to parameters
	 */
	public void findListOrganization() {

		setRenderOption("false");
		setRenderFind("false");
		updateUsers();
		findOperation3 = true;
		setRenderUser("false");
		setLoadFunction("$('#MyTab li:eq(2) a').tab('show')");

		setRenderResult2("true");
		Date date = new Date();
		String str = String.format("Current Date/Time : %tc", date);
		setResult2("Search result - report as \"" + getNieco() + "\"  " + str);
		int index = 0;

		List<UserDef> find = new ArrayList<UserDef>();

		if (getFindOption().equals("Username")) {

			for (UserDef element : users) {
				if (((users.get(index).getUsername()).toUpperCase())
						.equals(getNieco().toUpperCase())) {

					find.add(new UserDef(users.get(index).getId(), users.get(
							index).getUsername(), users.get(index)
							.getPassword(), users.get(index).getRole(), users
							.get(index).getOrganization(), users.get(index)
							.getEmail(), "false", "false", "false", "false",
							"false"));

				}
				index++;
			}

		} else if (getFindOption().equals("Organization")) {

			for (UserDef element : users) {
				if (((users.get(index).getOrganization()).toUpperCase())
						.equals(getNieco().toUpperCase())) {

					find.add(new UserDef(users.get(index).getId(), users.get(
							index).getUsername(), users.get(index)
							.getPassword(), users.get(index).getRole(), users
							.get(index).getOrganization(), users.get(index)
							.getEmail(), "false", "false", "false", "false",
							"false"));

				}
				index++;
			}

		} else if (getFindOption().equals("Email")) {

			for (UserDef element : users) {
				if (((users.get(index).getEmail()).toUpperCase())
						.equals(getNieco().toUpperCase())) {

					find.add(new UserDef(users.get(index).getId(), users.get(
							index).getUsername(), users.get(index)
							.getPassword(), users.get(index).getRole(), users
							.get(index).getOrganization(), users.get(index)
							.getEmail(), "false", "false", "false", "false",
							"false"));

				}
				index++;
			}

		} else if (getFindOption().equals("Role")) {

			for (UserDef element : users) {
				if (((users.get(index).getRole()).toUpperCase())
						.equals(getNieco().toUpperCase())) {

					find.add(new UserDef(users.get(index).getId(), users.get(
							index).getUsername(), users.get(index)
							.getPassword(), users.get(index).getRole(), users
							.get(index).getOrganization(), users.get(index)
							.getEmail(), "false", "false", "false", "false",
							"false"));

				}
				index++;
			}

		}

		this.users = find;
	}

	/**
	 * method find all task row in table according to parameters
	 */
	public void findList() {
		setLoadFunction("$('#MyTab a:first').tab('show')");
		setRenderPoll("false");
		setRenderOption("false");
		setRenderFind("false");
		setLoadFunction("$('#MyTab a:first').tab('show')");
		updateTasks();
		setLoadFunction("$('#MyTab a:first').tab('show')");

		setLoadFunction("$('#MyTab a:first').tab('show')");
		setRenderResult("true");
		Date date = new Date();
		String str = String.format("Current Date/Time : %tc", date);
		setResult("Search result - report as \"" + getNieco() + "\" " + str);
		int index = 0;

		List<TaskDef> find = new ArrayList<TaskDef>();

		if (getFindOption().equals("Owner")) {

			for (TaskDef element : task) {
				if (((task.get(index).getOwner()).toUpperCase())
						.equals(getNieco().toUpperCase())) {

					find.add(new TaskDef(task.get(index).getId(), task.get(
							index).getName(), task.get(index).getState(), task
							.get(index).getProgress(), task.get(index)
							.getEstimatedTime(), convertIfPublic(task
							.get(index).getIfPublic()), task.get(index)
							.getOwner(), task.get(index).getXmlFile(),
							renderStop(task.get(index).getState()),
							renderRun(task.get(index).getState()),
							renderPublish(task.get(index).getIfPublic(), task
									.get(index).getState()),
							renderUnpublish(task.get(index).getIfPublic()),
							renderEdit(task.get(index).getState()),
							renderDelete(task.get(index).getState()),
							renderCommand(task.get(index).getIfPublic()), task
									.get(index).getType()));

				}
				index++;
			}

		} else if (getFindOption().equals("ID")) {
			for (TaskDef element : task) {
				if (((task.get(index).getId())).equals(getNieco())) {

					find.add(new TaskDef(task.get(index).getId(), task.get(
							index).getName(), task.get(index).getState(), task
							.get(index).getProgress(), task.get(index)
							.getEstimatedTime(), convertIfPublic(task
							.get(index).getIfPublic()), task.get(index)
							.getOwner(), task.get(index).getXmlFile(),
							renderStop(task.get(index).getState()),
							renderRun(task.get(index).getState()),
							renderPublish(task.get(index).getIfPublic(), task
									.get(index).getState()),
							renderUnpublish(task.get(index).getIfPublic()),
							renderEdit(task.get(index).getState()),
							renderDelete(task.get(index).getState()),
							renderCommand(task.get(index).getIfPublic()), task
									.get(index).getType()));

				}

				index++;
			}

		} else if (getFindOption().equals("State")) {

			for (TaskDef element : task) {

				if (((task.get(index).getState())).equals(getNieco())) {

					find.add(new TaskDef(task.get(index).getId(), task.get(
							index).getName(), task.get(index).getState(), task
							.get(index).getProgress(), task.get(index)
							.getEstimatedTime(), convertIfPublic(task
							.get(index).getIfPublic()), task.get(index)
							.getOwner(), task.get(index).getXmlFile(),
							renderStop(task.get(index).getState()),
							renderRun(task.get(index).getState()),
							renderPublish(task.get(index).getIfPublic(), task
									.get(index).getState()),
							renderUnpublish(task.get(index).getIfPublic()),
							renderEdit(task.get(index).getState()),
							renderDelete(task.get(index).getState()),
							renderCommand(task.get(index).getIfPublic()), task
									.get(index).getType()));

				}

				index++;
			}

		} else if (getFindOption().equals("Permission")) {
			for (TaskDef element : task) {
				if (((task.get(index).getIfPublic()).toUpperCase())
						.equals(getNieco().toUpperCase())) {
					System.out.println(index);
					find.add(new TaskDef(task.get(index).getId(), task.get(
							index).getName(), task.get(index).getState(), task
							.get(index).getProgress(), task.get(index)
							.getEstimatedTime(), convertIfPublic(task
							.get(index).getIfPublic()), task.get(index)
							.getOwner(), task.get(index).getXmlFile(),
							renderStop(task.get(index).getState()),
							renderRun(task.get(index).getState()),
							renderPublish(task.get(index).getIfPublic(), task
									.get(index).getState()),
							renderUnpublish(task.get(index).getIfPublic()),
							renderEdit(task.get(index).getState()),
							renderDelete(task.get(index).getState()),
							renderCommand(task.get(index).getIfPublic()), task
									.get(index).getType()));

				}
				index++;
			}

		} else if (getFindOption().equals("Name")) {
			for (TaskDef element : task) {
				if (((task.get(index).getName()).toUpperCase())
						.equals(getNieco().toUpperCase())) {
					find.add(new TaskDef(task.get(index).getId(), task.get(
							index).getName(), task.get(index).getState(), task
							.get(index).getProgress(), task.get(index)
							.getEstimatedTime(), convertIfPublic(task
							.get(index).getIfPublic()), task.get(index)
							.getOwner(), task.get(index).getXmlFile(),
							renderStop(task.get(index).getState()),
							renderRun(task.get(index).getState()),
							renderPublish(task.get(index).getIfPublic(), task
									.get(index).getState()),
							renderUnpublish(task.get(index).getIfPublic()),
							renderEdit(task.get(index).getState()),
							renderDelete(task.get(index).getState()),
							renderCommand(task.get(index).getIfPublic()), task
									.get(index).getType()));

				}
				index++;
			}

		}
		setRenderPoll("false");
		setRenderOption("false");
		setRenderFind("false");
		setLoadFunction("$('#MyTab a:first').tab('show')");
		this.task = find;
		setLoadFunction("$('#MyTab a:first').tab('show')");
	}

	/**
	 * Method update information about users in database
	 * 
	 * @param user
	 * @param userChanged
	 */
	private void updateUsers(List<UserDef> user, List<UserDef> userChanged) {
		int index = 0;
		for (UserDef element : user) {
			System.out.println(user.get(index).getUsername());
			System.out.println(userChanged.get(index).getUsername());
			if ((user.get(index).getUsername()).equals(userChanged.get(index)
					.getUsername())
					& ((user.get(index).getEmail()).equals(userChanged.get(
							index).getEmail()))
					& ((user.get(index).getRole()).equals(userChanged
							.get(index).getRole()))
					& ((user.get(index).getOrganization()).equals(userChanged
							.get(index).getOrganization()))) {

			} else {
				if (!(user.get(index).getUsername()).equals(userChanged.get(
						index).getUsername())) {

					op.changeUsername(Long.parseLong(user.get(index).getId()),
							userChanged.get(index).getUsername());
				}

				if (!(user.get(index).getEmail()).equals(userChanged.get(index)
						.getEmail())) {

					op.changeEmail(Long.parseLong(user.get(index).getId()),
							element.getEmail());
				}
				if (!(user.get(index).getOrganization()).equals(userChanged
						.get(index).getOrganization())) {

					op.changeOrganizationForUser(
							Long.parseLong(user.get(index).getId()),
							userChanged.get(index).getOrganization());

				}
				if (!(user.get(index).getRole()).equals(userChanged.get(index)
						.getRole())) {
					op.changeUserRole(Long.parseLong(user.get(index).getId()),
							userChanged.get(index).getRole());
				}

			}

			index++;
		}
	}

	/**
	 * Method for table task where edit task will be triggered
	 * 
	 * @param task
	 * @return
	 */
	public String editActionTask(TaskDef task) {
		setRenderPoll("false");
		setSave("true");
		this.oldTask = null;
		this.oldTask = new ArrayList<TaskDef>();
		int index = 0;
		task.setEditable(true);
		for (TaskDef u : this.task) {

			this.oldTask.add(new TaskDef(this.task.get(index).getId(),
					this.task.get(index).getName(), this.task.get(index)
							.getState(), this.task.get(index).getProgress(),
					this.task.get(index).getEstimatedTime(), this.task.get(
							index).getIfPublic(), this.task.get(index)
							.getOwner(), this.task.get(index).getXmlFile(),
					renderStop(this.task.get(index).getState()),
					renderRun(this.task.get(index).getState()), renderPublish(
							this.task.get(index).getIfPublic(),
							this.task.get(index).getState()),
					renderUnpublish(this.task.get(2).getIfPublic()),
					renderEdit(this.task.get(index).getState()),
					renderDelete(this.task.get(index).getState()),
					renderCommand(this.task.get(index).getIfPublic()),
					this.task.get(index).getType()));
			index++;

		}

		return null;
	}

	/**
	 * Method to save all changed task
	 * 
	 * @return
	 */
	public String saveActionTask() {
		updateTasks();
		setFindOption("");
		setNieco("");
		setLoadFunction("$('#MyTab a:first').tab('show')");
		setRenderRow("5");
		setRenderPoll("true");
		setSave("false");
		return null;
	}

	/**
	 * Method update
	 * 
	 * @param task
	 * @param changedTask
	 */
	private void updateTaskName(List<TaskDef> task, List<TaskDef> changedTask) {
		int index = 0;
		for (TaskDef element : task) {
			if ((task.get(index).getName()).equals(changedTask.get(index)
					.getName()))

			{

			} else {
				if (!(task.get(index).getName()).equals(changedTask.get(index)
						.getName())) {

					op.changeNameOfTask(
							Long.parseLong(task.get(index).getId()),
							task.get(index).getName());
				}

			}

			index++;
		}

	}

	/**
	 * method will be trigger when edit user button will be clicked
	 * 
	 * @param user
	 * @return
	 */
	public String editAction(UserDef user) {
		setRenderUser("false");
		this.oldUsers = null;
		this.oldUsers = new ArrayList<UserDef>();
		int index = 0;

		setLoadFunction("$('#MyTab li:eq(2) a').tab('show')");
		user.setEditable(true);
		user.setRenderSave("true");
		for (UserDef u : users) {
			this.oldUsers.add(new UserDef(users.get(index).getId(), users.get(
					index).getUsername(), users.get(index).getPassword(), users
					.get(index).getRole(), users.get(index).getOrganization(),
					users.get(index).getEmail(), users.get(index)
							.getRenderSave(), "false", "false", "false",
					"false"));

			index++;

		}
		setRenderUser("false");
		return null;
	}

	/**
	 * delete user method
	 * 
	 * @param user
	 */
	public void deleteUser(UserDef user) {
		setIdEntity("");
		setLoadFunction("$('#MyTab li:eq(2) a').tab('show')");
		setIdEntity(user.getId());
	}

	/**
	 * delete user after click on yes in confirmation button
	 */
	public void deleteUserSpec() {

		op.deleteUser(Long.parseLong(getIdEntity()));

		if (findOperation3 == Boolean.TRUE) {
			findListOrganization();
		}
		setLoadFunction("$('#MyTab li:eq(2) a').tab('show')");
		setIdEntity("");

	}

	/**
	 * save all change organiation into database
	 * 
	 * @return
	 */
	public String saveActionOrganization() {
		boolean end = false;
		setLoadFunction("$('#MyTab li:eq(3) a').tab('show')");
		// get all existing value but set "editable" to false
		for (OrganizationDef order : organizations) {

			if (order.getNameOfOrganization().equals("")
					|| order.getNameOfOrganization().isEmpty()) {
				order.setRenderError("true");
				System.out.println("chyba");
				setOrgPoll("false");
				end = true;
			}

		}
		if (end == Boolean.TRUE) {
			setOrgPoll("false");
			return null;
		}
		for (OrganizationDef order : organizations) {
			order.setEditable(false);
			order.setRenderSave("false");
		}
		setSave("false");
		updateOrganizations(this.organizations, this.oldOrganizations);
		if (findOperation2 == Boolean.FALSE) {

			setOrgPoll("true");

			updateOrganization();
		} else {
			setOrgPoll("false");

		}
		setRenderResult3("false");
		setFindOption("");
		setNieco("");
		// return to current page
		return null;

	}

	/**
	 * edit organization
	 * 
	 * @param org
	 * @return
	 */
	public String editActionOrganization(OrganizationDef org) {
		setOrgPoll("false");

		setLoadFunction("$('#MyTab li:eq(3) a').tab('show')");
		int index = 0;
		org.setEditable(true);
		org.setRenderSave("true");
		this.oldOrganizations = null;
		this.oldOrganizations = new ArrayList<OrganizationDef>();
		for (OrganizationDef u : this.organizations) {
			this.oldOrganizations
					.add(new OrganizationDef(organizations.get(index)
							.getIdOrganization(), organizations.get(index)
							.getNameOfOrganization(), organizations.get(index)
							.getRenderSave(), organizations.get(index)
							.getRenderError()));

			index++;

		}

		return null;
	}

	/**
	 * method publish task
	 * 
	 * @param task
	 */
	public void publishTask(TaskDef task) {

		setRenderPoll("false");
		setLoadFunction("$('#MyTab a:first').tab('show')");

		op.changePermission(Long.parseLong(task.getId()), "1");
		task.setIfPublic("1");

		task.setRenderCommand("true");

		setRenderPoll("true");
	}

	/**
	 * method set to view task tab
	 */
	public void setCreateTask() {
		setActiveEdit("");
		setActiveTask("active");
		setActiveUsers("");
		setActiveOrganization("");
		setActiveProperties("");
		setNieco("");
		setRenderTab("false");

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		try {
			response.sendRedirect("Create_Task.xhtml");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void setCreateType() {
		setActiveEdit("");
		setActiveTask("active");
		setActiveUsers("");
		setActiveOrganization("");
		setActiveProperties("");
		setNieco("");
		setRenderTab("false");

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		try {
			response.sendRedirect("Create_Type.xhtml");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void setCreateOrg() {
		setActiveEdit("");
		setActiveTask("");
		setActiveUsers("");
		setActiveOrganization("active");
		setActiveProperties("");
		setNieco("");
		setRenderTab("false");

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		try {
			response.sendRedirect("Create_Organization.xhtml");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void setViewOrg() {
		setActiveEdit("");
		setActiveTask("");
		setActiveUsers("");
		setActiveOrganization("active");
		setActiveProperties("");
		setNieco("");
		setRenderTab("false");

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		try {
			response.sendRedirect("Organizations.xhtml");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void setViewUsers() {
		setActiveEdit("");
		setActiveTask("");
		setActiveUsers("active");
		setActiveOrganization("");
		setActiveProperties("");
		setNieco("");
		setRenderTab("false");

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		try {
			response.sendRedirect("Users.xhtml");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void setCreateUser() {
		setActiveEdit("");
		setActiveTask("");
		setActiveUsers("active");
		setActiveOrganization("");
		setActiveProperties("");
		setNieco("");
		setRenderTab("false");

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		try {
			response.sendRedirect("Create_User.xhtml");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void setChangePass() {
		setActiveEdit("");
		setActiveTask("");
		setActiveUsers("");
		setActiveOrganization("");
		setActiveProperties("active");
		setNieco("");
		setRenderTab("false");

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		try {
			response.sendRedirect("Change_Password.xhtml");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void setChangeEmail() {
		setActiveEdit("");
		setActiveTask("");
		setActiveUsers("");
		setActiveOrganization("");
		setActiveProperties("active");
		setNieco("");
		setRenderTab("false");

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		try {
			response.sendRedirect("Change_Email.xhtml");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	/**
	 * method set to view create task tab
	 */
	public void setViewTask() {
		setActiveEdit("");
		setActiveTask("active");
		setActiveUsers("");
		setActiveOrganization("");
		setActiveProperties("");
		setNieco("");
		setRenderTab("false");

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		try {
			response.sendRedirect("Tasks.xhtml");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void setEditTask() {
		setActiveEdit("active");
		setActiveTask("");
		setActiveUsers("");
		setActiveOrganization("");
		setActiveProperties("");
		setNieco("");

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		try {
			response.sendRedirect("Edit_Task.xhtml");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	/**
	 * method set to view user tab
	 */
	public void setUser() {
		setNieco("");
		if (getRenderTab().equals("true")) {
			setRenderTab("false");
			setLoadFunction("$('#MyTab li:eq(3) a').tab('show')");
		} else {
			setLoadFunction("$('#MyTab li:eq(2) a').tab('show')");
		}
	}

	/**
	 * method set to view organization tab
	 */
	public void setOrganization() {
		setNieco("");
		if (getRenderTab().equals("true")) {
			setRenderTab("false");
			setLoadFunction("$('#MyTab li:eq(4) a').tab('show')");
		} else {
			setLoadFunction("$('#MyTab li:eq(3) a').tab('show')");
		}
	}

	/**
	 * method set to view changepassword tab
	 */
	public void setPassword() {
		setNieco("");
		if (getRenderTab().equals("true")) {
			setRenderTab("false");
			setLoadFunction("$('#MyTab li:eq(5) a').tab('show')");
		} else {
			setLoadFunction("$('#MyTab li:eq(4) a').tab('show')");
		}
	}

	/**
	 * method call web service method to run task
	 * 
	 * @param task
	 * @throws Exception_Exception
	 */
	public void runTask(TaskDef task) throws Exception_Exception {
		setRenderPoll("false");
		setLoadFunction("$('#MyTab a:first').tab('show')");

		ws.startTask(Long.parseLong(task.getId()));

		setRenderPoll("true");
	}

	/**
	 * method call stop task from web service
	 * 
	 * @param task
	 * @throws Exception_Exception
	 */
	public void stopTask(TaskDef task) throws Exception_Exception {
		setLoadFunction("$('#MyTab a:first').tab('show')");
		System.out.println("we call web service method");
		ws.pauseTask(Long.parseLong(task.getId()));
		System.out.println(task.getId());

	}

	/**
	 * method create user
	 */
	public void createUser() {
		setRenderEmailFormat("false");
		setRenderUsername("false");
		setRenderPassword("false");
		setRenderPasswordValidate("false");
		setRenderPasswordNot("false");
		setRenderOrganization("false");
		setRenderEmail("false");
		setRenderRole("false");
		setLoadFunction("$('#MyTab li:eq(2) a').tab('show')");
		if (getUsername() == null || getUsername().isEmpty()) {
			setRenderUsername("true");
			return;
		}

		if (getPass() == null || getPass().isEmpty()) {
			setRenderPassword("true");
			return;
		}
		if (getPasswordValidate() == null || getPasswordValidate().isEmpty()) {
			setRenderPasswordValidate("true");
			return;
		}
		if (!getPass().equals(getPasswordValidate())) {
			setRenderPasswordNot("true");
			return;
		}
		if (getEmail() == null || getEmail().isEmpty()) {
			setRenderEmail("true");
			return;
		}

		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(getEmail());
		if (!matcher.matches()) {
			setRenderEmailFormat("true");
			return;
		}

		if (getOrganization() == null || getOrganization().isEmpty()) {
			setRenderOrganization("true");
			return;
		}
		if (getRole() == null || getRole().isEmpty()) {
			setRenderRole("true");
			return;
		}

		long org = op.getIdOrganization(getOrganization());

		op.createUser(getUsername(), getPass(), getRole(), getEmail(), org);
		setUsername("");
		setPass("");
		setEmail("");
		setRenderEmailFormat("false");
		setRenderUsername("false");
		setRenderPassword("false");
		setRenderPasswordValidate("false");
		setRenderPasswordNot("false");
		setRenderOrganization("false");
		setRenderEmail("false");
		setRenderRole("false");
		List<UserDef> user = new ArrayList<UserDef>();
		List<UserDef> resultsUsers = op.getAllUsers();
		for (Object item : resultsUsers) {
			Object[] obj = (Object[]) item;
			user.add(new UserDef(obj[0].toString(), obj[1].toString(), obj[2]
					.toString(), obj[3].toString(), obj[4].toString(), obj[5]
					.toString(), "false", "false", "false", "false", "false"));

		}
		this.users = user;

	}

	public void createTask() {
		setRenderTab("false");
		setLoadFunction("$('#MyTab li:eq(1) a').tab('show')");

		setRenderUpload("false");

		if (getXmlFile() == null || getXmlFile().isEmpty()) {
			setRenderUpload("true");
			return;
		}

		op.createTask(getName(), getXmlFile(), getLoggedUsername(),
				getTypeDef());
		setXmlFile("");
		setName("");

		setRenderPoll("true");

	}

	/**
	 * Method create new type of planning problem
	 */
	public void createType() {
		int items = 0;
		String confStr = "<solver>";
		setRenderTab("false");
		setLoadFunction("$('#MyTab li:eq(1) a').tab('show')");

		setRenderUpload("false");
		String confFile = new String();
		String droolsFile = new String();
		Iterator<String> iterator = listConf.iterator();
		while (iterator.hasNext()) {
			String element = (String) iterator.next();
			items++;
		}

		if (items != 2) {
			setRenderUpload("true");
			listConf = new ArrayList<String>();
			return;
		}
		items = 0;
		Iterator<String> iterator2 = listConf.iterator();
		while (iterator2.hasNext()) {
			String element = (String) iterator2.next();
			if (element.toLowerCase().contains(confStr.toLowerCase())) {

				break;

			}
			items++;
		}
		if (items == 0) {
			confFile = listConf.get(items);
			droolsFile = listConf.get(items + 1);
		} else {
			confFile = listConf.get(0);
			droolsFile = listConf.get(1);
		}
		// System.out.println(confFile);
		op.createType(getName(), confFile, droolsFile);
		listConf = new ArrayList<String>();
		setName("");

		setRenderPoll("true");

	}

	/**
	 * method unpublish task
	 * 
	 * @param task
	 */
	public void unpublishTask(TaskDef task) {

		setRenderPoll("false");
		setLoadFunction("$('#MyTab a:first').tab('show')");

		task.setIfPublic("0");
		task.setRenderCommand("false");
		op.changePermission(Long.parseLong(task.getId()), "0");
		setRenderPoll("true");
	}

	/**
	 * method delete task
	 * 
	 * @param task
	 */
	public void deleteTask(TaskDef task) {
		setOrgPoll("false");
		setIdEntity("");
		setIdEntity(task.getId());

		setOrgPoll2("false");

	}

	/**
	 * method delete task after confirmation
	 */
	public void deleteTaskSpec() {
		op.deleteTask(Long.parseLong(getIdEntity()));
		setIdEntity("");

		setOrgPoll("true");
	}

	/**
	 * method change password
	 */
	public void changePassword() {

		setRenderPassword3("false");
		setRenderPasswordValidate3("false");
		setRenderPasswordNot("false");

		if (getPass() == null | getPass().isEmpty()) {
			setRenderPassword3("true");
			return;

		}

		if (getPasswordValidate() == null | getPasswordValidate().isEmpty()) {
			setRenderPasswordValidate3("true");
			return;
		}

		if (!getPass().equals(getPasswordValidate())) {
			setRenderPasswordNot3("true");
			return;
		}

		op.changePassword(getIdUser(), ShaEncoder.hash(getPass()));
		setPass("");
		setPasswordValidate("");
		List<UserDef> user = new ArrayList<UserDef>();
		List<UserDef> resultsUsers = op.getAllUsers();
		for (Object item : resultsUsers) {
			Object[] obj = (Object[]) item;

			user.add(new UserDef(obj[0].toString(), obj[1].toString(), obj[2]
					.toString(), obj[3].toString(), obj[4].toString(), obj[5]
					.toString(), "false", "false", "false", "false", "false"));

		}
		setRenderPassword3("false");
		setRenderPasswordValidate3("false");
		setRenderPasswordNot("false");
		this.users = user;
	}

	/**
	 * method get uploaded xml file
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void listener(FileUploadEvent event) throws Exception {
		setLoadFunction("$('#MyTab li:eq(1) a').tab('show')");
		UploadedFile item = event.getUploadedFile();

		setXmlFile(new String(item.getData()));

	}

	public void listenerType(FileUploadEvent event) throws Exception {

		UploadedFile item = event.getUploadedFile();

		listConf.add(new String(item.getData()));

	}

	/**
	 * method create new organization
	 */
	public void createOrganization() {
		setLoadFunction("$('#MyTab li:eq(3) a').tab('show')");
		setUpdPoll("false");
		setRenderOrg("false");
		setOrgPoll("false");
		if (getOrganization() == null | getOrganization().isEmpty()) {
			setRenderOrg("true");
			return;

		}
		setRenderOrg("false");
		op.createOrganization(this.organization);
		updateOrganization();
		setUpdPoll("true");
		setOrgPoll("true");
	}

	/**
	 * method set page in table organizations
	 */
	public void scroller3() {
		test = getPage3();
		setOrgPoll("true");
	}

	/**
	 * method get all new organization
	 */
	public void updateOrganization() {
		System.out.println("update org");
		setOrgPoll("true");
		setPage3(test);
		List<OrganizationDef> org = new ArrayList<OrganizationDef>();

		List<OrganizationDef> resultsOrg = op.getOrganizations();

		for (Object item : resultsOrg) {
			Object[] obj = (Object[]) item;
			org.add(new OrganizationDef(obj[0].toString(), obj[1].toString(),
					"false", "false"));

		}
		this.organizations = org;
		if (sortIdOrganization == Boolean.TRUE) {
			if (sortAscending3 == Boolean.TRUE) {
				sortAscending3 = false;
			} else {
				sortAscending3 = true;
			}
			sortByIdOrg();

		}
		if (sortNameOrganization == Boolean.TRUE) {
			if (sortAscending3 == Boolean.TRUE) {
				sortAscending3 = false;
			} else {
				sortAscending3 = true;
			}
			sortByNameOrganization();
		}
		setOrgPoll("true");
		setPage3(test);
	}

	/**
	 * method get all user from database
	 */
	public void updateUsers() {
		System.out.println("update users");
		setPage2(test);
		List<UserDef> org = new ArrayList<UserDef>();
		List<UserDef> resultsUser = op.getAllUsers();
		for (Object item : resultsUser) {
			Object[] obj = (Object[]) item;
			org.add(new UserDef(obj[0].toString(), obj[1].toString(), obj[2]
					.toString(), obj[3].toString(), obj[4].toString(), obj[5]
					.toString(), "false", "false", "false", "false", "false"));

		}

		this.users = org;
		if (sortUsernameUser == Boolean.TRUE) {
			if (sortAscending2 == Boolean.TRUE) {
				sortAscending2 = false;
			} else {
				sortAscending2 = true;
			}
			sortByUsername();

		}
		if (sortEmailUser == Boolean.TRUE) {
			if (sortAscending2 == Boolean.TRUE) {
				sortAscending2 = false;
			} else {
				sortAscending2 = true;
			}
			sortByEmail();
		}
		if (sortRoleUser == Boolean.TRUE) {
			if (sortAscending2 == Boolean.TRUE) {
				sortAscending2 = false;
			} else {
				sortAscending2 = true;
			}
			sortByRole();
		}
		if (sortOrganizationUser == Boolean.TRUE) {
			if (sortAscending2 == Boolean.TRUE) {
				sortAscending2 = false;
			} else {
				sortAscending2 = true;
			}
			sortByOrganization();
		}
		setPage2(test);
		setRenderUser("true");
	}

	/**
	 * method delete organization
	 * 
	 * @param org
	 */
	public void deleteOrganization(OrganizationDef org) {
		setIdEntity("");
		setLoadFunction("$('#MyTab li:eq(4) a').tab('show')");
		setIdEntity(org.getIdOrganization());
	}

	/**
	 * method delter organization after confirmation
	 */
	public void deleteOrgSpec() {
		op.deleteOrganization(Long.parseLong(getIdEntity()));

		setIdEntity("");
		if (findOperation2 == Boolean.TRUE) {
			findListOrg();
		}
		setLoadFunction("$('#MyTab li:eq(3) a').tab('show')");

	}

	/**
	 * method logout user
	 */
	public void logout() {

		identity.logout();

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		externalContext.invalidateSession();
		try {
			externalContext.redirect("Login.xhtml");
		} catch (Exception ex) {

		}

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordValidate() {
		return passwordValidate;
	}

	public void setPasswordValidate(String passwordValidate) {
		this.passwordValidate = passwordValidate;
	}

	/**
	 * method change password for user
	 */
	public void validatePassword() {
		setLoadFunction("$('#MyTab li:eq(4) a').tab('show')");
		setRenderPassword2("false");
		setRenderPasswordValidate2("false");
		setRenderPasswordNot2("false");
		if (getPassword() == null || getPassword().isEmpty()) {
			setRenderPassword2("true");
			return;
		}

		if (getPasswordValidate() == null || getPasswordValidate().isEmpty()) {

			setRenderPasswordValidate2("true");
			return;
		}

		if (!getPassword().equals(getPasswordValidate())) {

			setRenderPasswordNot2("true");
			return;
		}

		op.changePasswordForUser(getLoggedUsername(), this.password);
		setRenderPassword2("false");
		setRenderPasswordValidate2("false");
		setRenderPasswordNot2("false");
	}

	public void validateEmail() {
		setLoadFunction("$('#MyTab li:eq(4) a').tab('show')");
		setRenderEmail("false");
		setRenderEmailValidate("false");
		setRenderEmailNot("false");
		if (getEmail() == null || getEmail().isEmpty()) {
			setRenderEmail("true");
			return;
		}

		if (getPasswordValidate() == null || getPasswordValidate().isEmpty()) {

		}

		if (!getEmail().equals(getPasswordValidate())) {

			setRenderEmailNot("true");
			return;
		}

		op.changeEmailForUser(getLoggedUsername(), this.email);
		setRenderPassword2("false");
		setRenderPasswordValidate2("false");
		setRenderPasswordNot2("false");
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public void setEditableOrganizations(List<String> org) {
		this.editableOrganizations = org;
	}

	public List<String> getEditableOrganizations() {
		return editableOrganizations;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String password) {
		this.pass = password;
	}

	public void setIdUser(long id) {
		this.idUser = id;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setRenderPoll(String render) {
		this.renderPoll = render;
	}

	public String getRenderPoll() {
		return renderPoll;
	}

	public void setXmlFile(String xmlFile) {
		this.xmlFile = xmlFile;
	}

	public String getXmlFile() {
		return xmlFile;
	}

	private void setIdTask(String idTask) {
		this.idTask = idTask;
	}

	private String getIdTask() {
		return this.idTask;
	}

	public String getRenderArea() {
		return renderArea;
	}

	public void setRenderArea(String render) {
		this.renderArea = render;
	}

	public void setRenderButton(String render) {
		this.renderButton = render;
	}

	public String getRenderButton() {
		return renderButton;
	}

	public String getUnpublishInformation() {
		return unpublishInformation;
	}

	public void setUnpublishInformation(String information) {
		this.unpublishInformation = information;
	}

	public void setPublishInformation(String information) {
		this.publishInformation = information;
	}

	public String getPublishInformation() {
		return publishInformation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public String getTab() {
		return tab;
	}

	public void setRenderTab(String renderTab) {
		this.renderTab = renderTab;
	}

	public String getRenderTab() {
		return renderTab;
	}

	public void setRenderTab1(String renderTab) {
		this.renderTab1 = renderTab;
	}

	public String getRenderTab1() {
		return renderTab1;
	}

	public void setRenderTab2(String renderTab) {
		this.renderTab2 = renderTab;
	}

	public String getRenderTab2() {
		return renderTab2;
	}

	public String getFindString() {
		return findString;
	}

	public void setFindString(String findString) {
		this.findString = findString;
	}

	public List<String> getEditableOwner() {
		return editableOwner;
	}

	public void setEditableOwner(List<String> list) {
		this.editableOwner = list;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFindOption() {
		return findOption;
	}

	public void setFindOption(String option) {
		this.findOption = option;
	}

	public void setNieco(String nieco) {
		this.nieco = nieco;

	}

	public String getNieco() {
		return nieco;
	}

	public String getRenderName() {
		return renderName;
	}

	public void setRenderName(String name) {
		this.renderName = name;
	}

	public void setRenderUpload(String render) {
		this.renderUpload = render;
	}

	public String getRenderUpload() {
		return renderUpload;
	}

	public void setRenderUsername(String name) {
		this.renderUsername = name;
	}

	public String getRenderUsername() {
		return renderUsername;
	}

	public void setRenderPassword(String name) {
		this.renderPassword = name;
	}

	public String getRenderPassword() {
		return renderPassword;
	}

	public void setRenderPasswordValidate(String name) {
		this.renderPasswordValidate = name;
	}

	public String getRenderPasswordValidate() {
		return renderPasswordValidate;
	}

	public void setRenderPasswordNot(String name) {
		this.renderPasswordNot = name;
	}

	public String getRenderPasswordNot() {
		return renderPasswordNot;
	}

	public void setRenderEmail(String name) {
		this.renderEmail = name;
	}

	public String getRenderEmail() {
		return renderEmail;
	}

	public void setRenderOrganization(String name) {
		this.renderOrganization = name;
	}

	public String getRenderOrganization() {
		return renderOrganization;
	}

	public void setRenderRole(String name) {
		this.renderRole = name;
	}

	public String getRenderRole() {
		return renderRole;
	}

	public void setRenderEmailFormat(String format) {
		this.renderEmailFormat = format;
	}

	public String getRenderEmailFormat() {
		return renderEmailFormat;
	}

	public void setRenderPassword2(String name) {
		this.renderPassword2 = name;
	}

	public String getRenderPassword2() {
		return renderPassword2;
	}

	public void setRenderPasswordValidate2(String name) {
		this.renderPasswordValidate2 = name;
	}

	public String getRenderPasswordValidate2() {
		return renderPasswordValidate2;
	}

	public void setRenderPasswordNot2(String name) {
		this.renderPasswordNot2 = name;
	}

	public String getRenderPasswordNot2() {
		return renderPasswordNot2;
	}

	public void setRenderPassword3(String name) {
		this.renderPassword3 = name;
	}

	public String getRenderPassword3() {
		return renderPassword3;
	}

	public void setRenderPasswordValidate3(String name) {
		this.renderPasswordValidate3 = name;
	}

	public String getRenderPasswordValidate3() {
		return renderPasswordValidate3;
	}

	public void setRenderPasswordNot3(String name) {
		this.renderPasswordNot3 = name;
	}

	public String getRenderPasswordNot3() {
		return renderPasswordNot3;
	}

	public void setRenderOrg(String org) {
		this.renderOrg = org;
	}

	public String getRenderOrg() {
		return renderOrg;
	}

	public void setRenderFind(String render) {
		this.renderFind = render;
	}

	public String getRenderFind() {
		return renderFind;
	}

	public void setRenderOption(String option) {
		this.renderOption = option;
	}

	public String getRenderOption() {
		return renderOption;
	}

	public void setRenderFind2(String render) {
		this.renderFind2 = render;
	}

	public String getRenderFind2() {
		return renderFind2;
	}

	public void setRenderOption2(String option) {
		this.renderOption2 = option;
	}

	public String getRenderOption2() {
		return renderOption2;
	}

	public void setRenderFind3(String render) {
		this.renderFind3 = render;
	}

	public String getRenderFind3() {
		return renderFind3;
	}

	public void setRenderOption3(String option) {
		this.renderOption3 = option;
	}

	public String getRenderOption3() {
		return renderOption3;
	}

	public void setUsers(List<UserDef> users) {
		this.users = users;
	}

	public List<UserDef> getUsers() {
		return users;
	}

	public void setChangeUsername(String username) {
		this.changeUsername = username;
	}

	public String getChangeUsername() {
		return changeUsername;
	}

	public void setChangeOrg(String org) {
		this.changeOrg = org;
	}

	public String getChangeOrg() {
		return changeOrg;
	}

	public void setLoggedUsername(String username) {
		this.loggedUsername = username;
	}

	public String getLoggedUsername() {
		return loggedUsername;
	}

	public void setLoadFunction(String fce) {
		this.loadFunction = fce;
	}

	public String getLoadFunction() {
		return loadFunction;
	}

	public void setIdEntity(String id) {
		this.idEntity = id;
	}

	public String getIdEntity() {
		return idEntity;
	}

	public void setOrgPoll(String set) {
		this.orgPoll = set;
	}

	public String getOrgPoll() {
		return orgPoll;
	}

	public void setUpdPoll(String set) {
		this.updPoll = set;
	}

	public String getUpdPoll() {
		return updPoll;
	}

	public String getOrgPoll2() {
		return orgPoll2;
	}

	public void setOrgPoll2(String set) {
		this.orgPoll2 = set;
	}

	public void setRefreshValue(String set) {
		this.refreshValue = set;
	}

	public String getRefreshValue() {
		return refreshValue;
	}

	public void setRefreshValue1(String set) {
		this.refreshValue1 = set;
	}

	public String getRefreshValue1() {
		return refreshValue1;
	}

	public void setRefreshValue2(String set) {
		this.refreshValue2 = set;
	}

	public String getRefreshValue2() {
		return refreshValue2;
	}

	public void setRenderUser(String set) {
		this.renderUser = set;
	}

	public String getRenderUser() {
		return renderUser;
	}

	public String getRenderRow() {
		return renderRow;
	}

	public void setRenderRow(String set) {
		this.renderRow = set;
	}

	public String getRenderRow2() {
		return renderRow2;
	}

	public void setRenderRow2(String set) {
		this.renderRow2 = set;
	}

	public void setRenderRow3(String set) {
		this.renderRow3 = set;
	}

	public String getRenderRow3() {
		return renderRow3;
	}

	public void setSave(String save) {
		this.save = save;
	}

	public String getSave() {
		return save;
	}

	public void setResult(String set) {
		this.result = set;
	}

	public String getResult() {
		return result;
	}

	public void setRenderResult(String set) {
		this.renderResult = set;
	}

	public String getRenderResult() {
		return renderResult;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPage() {
		return page;
	}

	public void setResult2(String set) {
		this.result2 = set;
	}

	public String getResult2() {
		return result2;
	}

	public void setRenderResult2(String set) {
		this.renderResult2 = set;
	}

	public String getRenderResult2() {
		return renderResult2;
	}

	public void setPage2(int set) {
		this.page2 = set;
	}

	public int getPage2() {
		return page2;
	}

	public void setPage3(int page) {
		this.page3 = page;

	}

	public int getPage3() {
		return page3;
	}

	public void setResult3(String set) {
		this.result3 = set;
	}

	public String getResult3() {
		return result3;
	}

	public void setRenderResult3(String set) {
		this.renderResult3 = set;
	}

	public String getRenderResult3() {
		return renderResult3;
	}

	public void setRenderError(String set) {
		this.renderError = set;
	}

	public String getRenderError() {
		return renderError;
	}

	public void setActiveTask(String set) {
		this.activeTask = set;
	}

	public String getActiveTask() {
		return activeTask;
	}

	public void setActiveOrganization(String set) {
		this.activeOrganization = set;
	}

	public String getActiveOrganization() {
		return activeOrganization;
	}

	public void setActiveUsers(String set) {
		this.activeUsers = set;
	}

	public String getActiveUsers() {
		return activeUsers;
	}

	public void setActiveEdit(String set) {
		this.activeEdit = set;
	}

	public String getActiveEdit() {
		return activeEdit;
	}

	public void setActiveProperties(String set) {
		this.activeProperties = set;
	}

	public String getActiveProperties() {
		return activeProperties;
	}

	public void setRenderEmailValidate(String name) {
		this.renderEmailValidate = name;
	}

	public String getRenderEmailValidate() {
		return renderEmailValidate;
	}

	public void setRenderEmailNot(String name) {
		this.renderEmailNot = name;
	}

	public String getRenderEmailNot() {
		return renderEmailNot;
	}

	public void setTypes(List<String> set) {
		this.types = set;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypeDef(String set) {
		this.typeDef = set;
	}

	public String getTypeDef() {
		return typeDef;
	}

}
