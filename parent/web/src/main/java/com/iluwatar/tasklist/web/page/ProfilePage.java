package com.iluwatar.tasklist.web.page;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

@AuthorizeInstantiation("USER")
public class ProfilePage extends BasePage {

	private static final long serialVersionUID = 1L;

}
