package org.jboss.optaplanner.controller.restservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.jboss.optaplanner.controller.database.Operation;

@Path("/")
/**
 * this class is restfull service for publish aka
 * @author martin
 *
 */
public class RESTPublishTask {

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
		try {
			xmlFile = op.getXmlFileName(id);
			name = op.getNameOfTask(id);
			permission = op.getPermissionOfTask(id);
			System.out.println(permission);

		} catch (Exception ex) {
			response = "";
			return Response.status(200).entity(response).build();
		}

		if (permission.equals("false")) {
			response = "";

		} else {
			//create page
			response = "<!DOCTYPE html >\n <html>\n <head> \n <link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.css\"> \n <script language=\"javascript\" type=\"text/javascript\" src=\"js/jquery-2.1.0.js\"></script> \n <script language=\"javascript\" type=\"text/javascript\" src=\"js/bootstrap.js\"></script>  \n  \n  </head> \n <body style=\"background-color:#eee\"> <div style=\"margin:0 auto;text-align:center;width:900px;font-weight:bold;\"> <h1> "
					+ name
					+ "</h1> <div class=\"jumbotron\"> \n	<plaintext>"
					+ xmlFile;
		}
		return Response.status(200).entity(response).build();
	}
}