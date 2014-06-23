package org.jboss.optaplanner.controller.login;

import org.jboss.seam.faces.event.PhaseIdType;
import org.jboss.seam.faces.rewrite.FacesRedirect;
import org.jboss.seam.faces.security.AccessDeniedView;
import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.security.RestrictAtPhase;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;

@ViewConfig
/**
 * interface which allow/prohibit acess to specified pages
 * @author martin
 *
 */
public interface Pages {
	static enum Pages1 {

		@ViewPattern("/index.xhtml")
		INDEX,

		
		
		
		

		


				
	
		
	
		
		
		@ViewPattern("/Create_Organization.xhtml")
		@Administrator
		@LoginView("/Login.xhtml")
		@AccessDeniedView("/Login.xhtml")
		@RestrictAtPhase({ PhaseIdType.RESTORE_VIEW,
		PhaseIdType.INVOKE_APPLICATION })
		TEST4,

		@ViewPattern("/Organizations.xhtml")
		@Administrator
		@LoginView("/Login.xhtml")
		@AccessDeniedView("/Login.xhtml")
		@RestrictAtPhase({ PhaseIdType.RESTORE_VIEW,
		PhaseIdType.INVOKE_APPLICATION })
		TEST5,


		@ViewPattern("/Users.xhtml")
		@Administrator
		@LoginView("/Login.xhtml")
		@AccessDeniedView("/Login.xhtml")
		@RestrictAtPhase({ PhaseIdType.RESTORE_VIEW,
		PhaseIdType.INVOKE_APPLICATION })
		TEST6,

		@ViewPattern("/Create_User.xhtml")
		@Administrator
		@LoginView("/Login.xhtml")
		@AccessDeniedView("/Login.xhtml")
		@RestrictAtPhase({ PhaseIdType.RESTORE_VIEW,
		PhaseIdType.INVOKE_APPLICATION })
		TEST7,


		
		

		

		@FacesRedirect
		@ViewPattern("/*")
		@AccessDeniedView("/Login.xhtml")
		ALL;

	}
}
