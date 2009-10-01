package numguess;

/**
 * A simple extension of DefaultSharedData that provides the id attribute
 * required by Hibernate.
 */
public class HibernateSharedData extends DefaultSharedData {

	public static final int ID = 1;

	/**
	 * The unique key required by Hibernate.
	 */
	private int id = ID;

}
