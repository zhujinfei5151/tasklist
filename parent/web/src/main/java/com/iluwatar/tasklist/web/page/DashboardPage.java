package com.iluwatar.tasklist.web.page;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;

// 1. select tasklist -> move to view tasklist page
// 2. create new tasklist
// 3. remove tasklist

@AuthorizeInstantiation("USER")
public class DashboardPage extends WebPage {

	private static final long serialVersionUID = 1L;

}
