package numguess;

/**
 * An interface for clients of the UserIDProvider.
 */
public interface UserIDProviderAware {

	/**
	 * Injects the dependency on the UserIDProvider.
	 * 
	 * @param idProvider
	 *            the UserIDProvider
	 */
	void setUserIDProvider(UserIDProvider idProvider);
}
