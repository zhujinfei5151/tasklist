package com.iluwatar.tasklist.web.page;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.iluwatar.tasklist.web.TasklistSession;
import com.iluwatar.tasklist.web.behavior.VisibleWhenCurrentPageOfTypeBehavior;
import com.iluwatar.tasklist.web.behavior.VisibleWhenLoggedInBehavior;
import com.iluwatar.tasklist.web.model.ProfileButtonModel;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar.Position;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarAjaxLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.less.LessResourceReference;

public class BasePage extends WebPage {

	private static final long serialVersionUID = 1L;
	
	protected IModel<String> tasklistNameModel = Model.of("");

	public BasePage() {
		super();
		init();
	}

	public BasePage(PageParameters params) {
		super(params);
		init();
	}

	private void init() {
		Navbar navbar = new Navbar("navbar");
		add(navbar);
		navbar.setPosition(Position.TOP);
		navbar.brandName(Model.of("Tasklist"));
		navbar.fluid();

		NavbarButton homeButton = new NavbarButton(DashboardPage.class, Model.of("Dashboard"));
		navbar.addComponents(NavbarComponents.transform(
				Navbar.ComponentPosition.LEFT,
				homeButton));

		NavbarButton tasksButton = new NavbarButton(ViewTasklistPage.class, tasklistNameModel);
		navbar.addComponents(NavbarComponents.transform(
				Navbar.ComponentPosition.LEFT,
				tasksButton));
		tasksButton.add(new VisibleWhenCurrentPageOfTypeBehavior(ViewTasklistPage.class));
		
		NavbarButton profileButton = new NavbarButton(ProfilePage.class, new ProfileButtonModel());
		navbar.addComponents(NavbarComponents.transform(
				Navbar.ComponentPosition.RIGHT,
				profileButton));
		profileButton.add(new VisibleWhenLoggedInBehavior());
	
		LogoutNavbarAjaxLink logoutButton = new LogoutNavbarAjaxLink(Model.of("Logout"));
		navbar.addComponents(NavbarComponents.transform(
				Navbar.ComponentPosition.RIGHT,
				logoutButton
        ));		
		logoutButton.add(new VisibleWhenLoggedInBehavior());
		
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(CssHeaderItem.forReference(new LessResourceReference(this.getClass(), "style.less")));
	}
	
	private static class LogoutNavbarAjaxLink extends NavbarAjaxLink<String> {

		private static final long serialVersionUID = 1L;

		public LogoutNavbarAjaxLink(final IModel<String> labelModel) {
			super(labelModel);
		}
		
		@Override
		public void onClick(AjaxRequestTarget target) {
			TasklistSession.get().signOut();
			setResponsePage(DashboardPage.class);
		}
		
	}
	
}
