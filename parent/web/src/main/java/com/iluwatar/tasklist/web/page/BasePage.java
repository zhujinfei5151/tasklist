package com.iluwatar.tasklist.web.page;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.iluwatar.tasklist.web.TasklistSession;
import com.iluwatar.tasklist.web.behavior.VisibleWhenCurrentPageOfTypeBehavior;
import com.iluwatar.tasklist.web.behavior.VisibleWhenLoggedInBehavior;
import com.iluwatar.tasklist.web.model.ProfileButtonModel;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar.Position;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarAjaxLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.less.LessResourceReference;

public class BasePage extends WebPage {

	private static final long serialVersionUID = 1L;
	
	protected IModel<String> titleModel = Model.of("");
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

		NavbarButton homeButton = new NavbarButton(DashboardPage.class, Model.of("Dashboard")) {

			@Override
			public boolean isActive(Component button) {
				if (button.getPage().getClass().equals(DashboardPage.class) ||
						button.getPage().getClass().equals(CreateTasklistPage.class)) {
					return true;
				}
				return false;
			}
			
		};
		navbar.addComponents(NavbarComponents.transform(
				Navbar.ComponentPosition.LEFT,
				homeButton));

		NavbarButton tasksButton = new NavbarButton(ViewTasklistPage.class, tasklistNameModel) {

			@Override
			public boolean isActive(Component button) {
				// TODO: refactor
				if (button.getPage().getClass().equals(ViewTasklistPage.class) ||
						button.getPage().getClass().equals(EditTasklistPage.class) ||
						button.getPage().getClass().equals(CreateTaskPage.class) ||
						button.getPage().getClass().equals(ReorderTasklistPage.class)) {
					return true;
				}
				return false;
			}
			
		};
		navbar.addComponents(NavbarComponents.transform(
				Navbar.ComponentPosition.LEFT,
				tasksButton));
		// TODO: refactor
		tasksButton.add(new VisibleWhenCurrentPageOfTypeBehavior(ViewTasklistPage.class, 
				EditTasklistPage.class, CreateTaskPage.class, ReorderTasklistPage.class));
		
		NavbarButton profileButton = new NavbarButton(ProfilePage.class, new ProfileButtonModel());
		navbar.addComponents(NavbarComponents.transform(
				Navbar.ComponentPosition.RIGHT,
				profileButton));
		profileButton.add(new VisibleWhenLoggedInBehavior());
		profileButton.setIconType(IconType.user);
	
		LogoutNavbarAjaxLink logoutButton = new LogoutNavbarAjaxLink(Model.of("Logout"));
		navbar.addComponents(NavbarComponents.transform(
				Navbar.ComponentPosition.RIGHT,
				logoutButton
        ));		
		logoutButton.add(new VisibleWhenLoggedInBehavior());
		logoutButton.setIconType(IconType.off);
		
		add(new Label("title", titleModel));
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(JavaScriptHeaderItem.forReference(Bootstrap.getSettings().getJsResourceReference()));
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
