package numguess;

/**
 * A provider of a user ID specific to the context of the current service method
 * invocation.
 */
public interface UserIDProvider {

	/**
	 * Returns a user ID specific to the context of the current service method
	 * invocation.
	 * 
	 * @return the user ID
	 */
	String getUserID();
}
