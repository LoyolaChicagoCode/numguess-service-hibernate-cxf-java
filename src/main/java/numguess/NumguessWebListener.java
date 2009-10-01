package numguess;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * A stateless session listener that manages session-specific data directly in
 * the DAO.
 */
public class NumguessWebListener implements HttpSessionListener {

	/**
	 * Looks up the DAO from the Spring context associated with a servlet
	 * context.
	 * 
	 * @param context
	 *            the servlet context
	 * @return the DAO
	 */
	protected NumguessDAO getNumguessDAO(final ServletContext context) {
		return (NumguessDAO) WebApplicationContextUtils
				.getRequiredWebApplicationContext(context).getBean(
						"numguessDao");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("Creating session...");
		final String id = event.getSession().getId();
		final ServletContext context = event.getSession().getServletContext();
		getNumguessDAO(context).create(id);
		System.out.println("Session created: " + event.getSession());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("Destroying session...");
		final String id = event.getSession().getId();
		final ServletContext context = event.getSession().getServletContext();
		getNumguessDAO(context).delete(id);
		System.out.println("Session destroyed: " + event.getSession());
	}
}
