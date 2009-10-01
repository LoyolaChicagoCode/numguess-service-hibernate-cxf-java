package numguess;

/**
 * An interface for clients of the Numguess DAO.
 */
public interface NumguessDAOAware {

	/**
	 * Injects the dependency on the Numguess DAO.
	 * 
	 * @param dao
	 *            the Numguess DAO
	 */
	void setNumguessDAO(NumguessDAO dao);
}
