package com.iluwatar.tasklist.web.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class BasePage extends WebPage {

	private static final long serialVersionUID = 1L;

	public BasePage() {
		super();
		init();
	}

	public BasePage(PageParameters params) {
		super(params);
		init();
	}

	private void init() {
		
	}
	
}
