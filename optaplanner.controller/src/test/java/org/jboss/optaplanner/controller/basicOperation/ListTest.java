package org.jboss.optaplanner.controller.basicOperation;

import java.util.ArrayList;
import java.util.List;

import org.jboss.optaplanner.controller.model.*;
import org.junit.*;




public class ListTest {

	
	
	
	
	@Test
	public void listCompare() throws Exception
	{
		List<OrganizationDef> org = new ArrayList<OrganizationDef>();
		for (int i =0;i<2;i++)
		{
			org.add(new OrganizationDef(String.valueOf(i),"nieco","fds","false"));
		}
		
		
	}
	
	
}

