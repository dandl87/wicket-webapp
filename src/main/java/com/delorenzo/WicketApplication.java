package com.delorenzo;

import com.delorenzo.page.TemplatePage;
import java.io.IOException;
import java.net.ContentHandler;
import java.net.URLConnection;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.delorenzo.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return TemplatePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		getCspSettings().blocking().disabled();
		/*getCspSettings().blocking().disabled();*/
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		//Scan package for annotated beans
		ctx.scan("com.delorenzo.persistence.dao");
		

		ctx.refresh();
		getComponentInstantiationListeners().add(new SpringComponentInjector(this, ctx));
		// needed for the styling used by the quickstart
/*		getCspSettings().blocking()
			.add(CSPDirective.STYLE_SRC, CSPDirectiveSrcValue.SELF)
			.add(CSPDirective.STYLE_SRC, "https://fonts.googleapis.com/css")
			.add(CSPDirective.FONT_SRC, "https://fonts.gstatic.com");*/
		 

		// add your configuration here
	}
}
