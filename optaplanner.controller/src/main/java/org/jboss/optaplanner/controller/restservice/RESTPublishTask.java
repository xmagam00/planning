package org.jboss.optaplanner.controller.restservice;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.jboss.optaplanner.controller.database.Operation;
import org.jboss.seam.security.Identity;
import org.picketlink.idm.api.User;

@Path("/")
/**
 * this class is restfull service for publish aka
 * @author martin
 *
 */
public class RESTPublishTask {
	@Inject
	Identity identity;

	@GET
	@Path("/{id}")
	@Produces("text/html")
	/**
	 * Method return page for id task that should be publish
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Response getUserById(@PathParam("id") Long id) throws Exception {

		Operation op = new Operation();
		String xmlFile;
		String name;
		String permission;
		String response;
		User user2 = identity.getUser();
		
		if (user2 == null) {
			
			try {
				xmlFile = op.getXmlFileName(id);
				name = op.getNameOfTask(id);
				permission = op.getPermissionOfTask(id);

			} catch (Exception ex) {
				response = "";
				return Response.status(200).entity(response).build();
			}

			if (permission.equals("false")) {
				response = "";

			} else {
				// create page
				response = "<!DOCTYPE html >\n <html>\n <head> \n <link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.css\"> \n <script language=\"javascript\" type=\"text/javascript\" src=\"js/jquery-2.1.0.js\"></script> \n <script language=\"javascript\" type=\"text/javascript\" src=\"js/bootstrap.js\"></script>  \n  \n  </head> \n <body style=\"background-color:#eee\"> <div style=\"margin:0 auto;text-align:center;width:900px;font-weight:bold;\"> <h1> "
						+ name
						+ "</h1> <div class=\"jumbotron\"> \n	<plaintext>"
						+ xmlFile;
			}
			return Response.status(200).entity(response).build();
		}
		
		User user = identity.getUser();
		String userRole = "";
		String orgazanition = "";
		 permission="";
		//logged user
		//try {
			xmlFile = op.getXmlFileName(id);
			name = op.getNameOfTask(id);
			String result = op.getUserById(Long.parseLong(user.getId()));
			userRole = op.getUserRoleByUsername(result);
			String idTask = op.getOrganizationByIdTask(id);
			String idUser= op.getOrganizationByIdUser(Long.parseLong(user.getId()));
			if (idTask.equals(idUser))
			{
				permission = "true";
			}
			else
			{
				permission = "false";
			}

		/*} catch (Exception ex) {
			System.out.println("Ze by nejaka chyba?");
			response = "";
			return Response.status(200).entity(response).build();
		}*/

		if (userRole.equals("Administrator"))
		{ 
			response = "<!DOCTYPE html >\n <html>\n <head> \n <link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.css\"> \n <script language=\"javascript\" type=\"text/javascript\" src=\"js/jquery-2.1.0.js\"></script> \n <script language=\"javascript\" type=\"text/javascript\" src=\"js/bootstrap.js\"></script>  \n  \n  </head> \n <body style=\"background-color:#eee\"> <div style=\"margin:0 auto;text-align:center;width:900px;font-weight:bold;\"> <h1> "
					+ name
					+ "</h1> <div class=\"jumbotron\"> \n	<plaintext>"
					+ xmlFile;
			return Response.status(200).entity(response).build();

		} 
		else if (userRole.equals("Reader") && (permission.equals("true")))
		{   System.out.println("tu mam byt");
			response = "<!DOCTYPE html >\n <html>\n <head> \n <link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.css\"> \n <script language=\"javascript\" type=\"text/javascript\" src=\"js/jquery-2.1.0.js\"></script> \n <script language=\"javascript\" type=\"text/javascript\" src=\"js/bootstrap.js\"></script>  \n  \n  </head> \n <body style=\"background-color:#eee\"> <div style=\"margin:0 auto;text-align:center;width:900px;font-weight:bold;\"> <h1> "
					+ name
					+ "</h1> <div class=\"jumbotron\"> \n	<plaintext>"
					+ xmlFile;
			System.out.println("tu mam byt");
			return Response.status(200).entity(response).build();
			
		}
		else if (userRole.equals("Reader") && (permission.equals("false")))
		{
			System.out.println("tu nemam byt");
			response = "";
			return Response.status(200).entity(response).build();
		}
		
		else if (userRole.equals("Planner") && (permission.equals("true")))
		{ 	System.out.println("tu nemam byt nie som planner");
			response = "<!DOCTYPE html >\n <html>\n <head> \n <link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.css\"> \n <script language=\"javascript\" type=\"text/javascript\" src=\"js/jquery-2.1.0.js\"></script> \n <script language=\"javascript\" type=\"text/javascript\" src=\"js/bootstrap.js\"></script>  \n  \n  </head> \n <body style=\"background-color:#eee\"> <div style=\"margin:0 auto;text-align:center;width:900px;font-weight:bold;\"> <h1> "
					+ name
					+ "</h1> <div class=\"jumbotron\"> \n	<plaintext>"
					+ xmlFile;
			System.out.println("tu mam byt");
			return Response.status(200).entity(response).build();
		}
		else
		{System.out.println("tu nemam byt nie som planner");
			response = "";
			return Response.status(200).entity(response).build();
		}
		
		
		

	}
}