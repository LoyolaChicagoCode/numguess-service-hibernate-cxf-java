package numguess;

/**
 * A simple extension of DefaultGameModel that provides the id attribute
 * required by Hibernate.
 */
public class HibernateGameModel extends DefaultGameModel {

	/**
	 * The unique key required by Hibernate.
	 */
	private String id;

	/**
	 * Sets the id property required by Hibernate.
	 * 
	 * @param id
	 *            the unique key of this instance
	 */
	public void setId(final String id) {
		this.id = id;
	}
}
