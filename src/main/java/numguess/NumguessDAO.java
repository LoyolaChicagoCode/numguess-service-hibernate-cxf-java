package numguess;

/**
 * A data access object (DAO) or repository for managing a collection of game
 * model entities. Similar to the home interface of an EJB.
 */
public interface NumguessDAO {
	/**
	 * Initializes the database tables.
	 */
	void init();

	/**
	 * Creates a game model object with the given fields.
	 * 
	 * @return the game model
	 */
	GameModel create(String id);

	/**
	 * Finds a game model object with the given unique id.
	 * 
	 * @return the game model
	 */
	GameModel read(String id);

	/**
	 * Updates a game model object.
	 */
	void update(GameModel model);

	/**
	 * Removes the game model object with the given unique id.
	 */
	void delete(String id);

	/**
	 * Obtains the shared data object.
	 * 
	 * @return the shared data object
	 */
	SharedData getSharedData();

	/**
	 * Updates the shared data object.
	 * 
	 * @return the shared data object
	 */
	void updateSharedData(SharedData sharedData);
}
