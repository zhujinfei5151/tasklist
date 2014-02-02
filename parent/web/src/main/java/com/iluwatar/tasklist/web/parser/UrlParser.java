package com.iluwatar.tasklist.web.parser;

import org.apache.wicket.extensions.markup.html.basic.ILinkRenderStrategy;
import org.apache.wicket.extensions.markup.html.basic.LinkParser;

public class UrlParser extends LinkParser {

	/** URL pattern */
//	private static final String urlPattern = "([a-zA-Z]+://[\\w\\.\\-\\:\\/~]+)[\\w\\.:\\-/?&=%]*";
	private static final String urlPattern = "([a-zA-Z]+://[\\w\\.\\-\\/~\\+\\;\\:\\#\\!]+)[\\w\\.:\\-/?&=%\\+\\;\\:\\#\\!]*";

	public static final ILinkRenderStrategy URL_RENDER_STRATEGY = new ILinkRenderStrategy()
	{
		@Override
		public String buildLink(final String linkTarget)
		{
			int indexOfQuestion = linkTarget.indexOf('?');
			return "<a href=\"" + linkTarget + "\">" +
				(indexOfQuestion == -1 ? linkTarget : linkTarget.substring(0, indexOfQuestion)) +
				"</a>";
		}
	};

	public UrlParser()
	{
		addLinkRenderStrategy(urlPattern, URL_RENDER_STRATEGY);
	}
	
}
