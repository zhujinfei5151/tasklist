package com.iluwatar.tasklist.web.component;

import org.apache.wicket.extensions.markup.html.basic.ILinkParser;
import org.apache.wicket.extensions.markup.html.basic.SmartLinkLabel;
import org.apache.wicket.model.IModel;

import com.iluwatar.tasklist.web.parser.UrlParser;

public class SmarterLinkLabel extends SmartLinkLabel {

	private static final long serialVersionUID = 1L;

	public SmarterLinkLabel(String name) {
		super(name);
	}

	public SmarterLinkLabel(String name, IModel<String> model) {
		super(name, model);
	}

	public SmarterLinkLabel(String name, String text) {
		super(name, text);
	}

	@Override
	protected ILinkParser getLinkParser() {
		return new UrlParser();
	}
	
}
