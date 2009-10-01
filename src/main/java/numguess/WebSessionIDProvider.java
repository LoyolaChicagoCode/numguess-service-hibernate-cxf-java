package numguess;

import org.springframework.web.context.request.RequestContextHolder;

/**
 * An implementation that returns the ID of the HttpSession associated with the
 * current service method invocation.
 */
public class WebSessionIDProvider implements UserIDProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see numguess.UserIDProvider#getUserID()
	 */
	@Override
	public String getUserID() {
		System.out.println("providing session ID");
		return RequestContextHolder.getRequestAttributes().getSessionId();
	}
}
