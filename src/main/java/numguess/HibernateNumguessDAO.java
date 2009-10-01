package numguess;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This class implements a data access object (DAO) that accesses GameModel
 * objects from the database using Hibernate. This DAO has to be context-aware
 * for access to the LocalSessionFactoryBean, which provides access to the
 * hbm2ddl methods for database schema creation etc.
 */
public class HibernateNumguessDAO extends HibernateDaoSupport implements
		NumguessDAO, ApplicationContextAware {

	/**
	 * The Spring application context.
	 */
	private ApplicationContext context;

	/**
	 * The name of the Hibernate session factory.
	 */
	private String sessionFactoryName;

	public void setApplicationContext(ApplicationContext context) {
		this.context = context;
	}

	public void setSessionFactoryName(String sessionFactoryName) {
		this.sessionFactoryName = sessionFactoryName;
	}

	/**
	 * Obtains the LSFB through the Spring context
	 */
	protected LocalSessionFactoryBean getLsfb() {
		return (LocalSessionFactoryBean) context.getBean("&"
				+ sessionFactoryName);
	}

	/**
	 * Invoked automatically by Spring.
	 */
	@Override
	public void initDao() {
		try {
			// create the schema according to the Hibernate mapping
			// but only if it does not exist yet
			// otherwise an exception occurs and nothing happens
			System.out.println("checking if we need to initialize the database");
			getLsfb().createDatabaseSchema();
			createSharedData();
		} catch (DataAccessException e) {
			System.out.println("the database already exists");
		}
	}

	/**
	 * Invoked by the user.
	 */
	@Override
	public void init() {
		// drop the schema in case it exists
		try {
			getLsfb().dropDatabaseSchema();
		} catch (DataAccessException e) {
		}
		// then recreate and populate the schema
		initDao();
	}

	@Override
	public GameModel create(String id) {
		return createGameModel(id);
	}

	protected HibernateGameModel createGameModel(String id) {
		final HibernateGameModel model = new HibernateGameModel();
		model.setId(id);
		model.setSharedData(getSharedData());
		getHibernateTemplate().save(model);
		return model;
	}

	@Override
	public GameModel read(final String id) {
		HibernateGameModel model = (HibernateGameModel) getHibernateTemplate()
				.get(HibernateGameModel.class, id);
		if (model == null)
			model = createGameModel(id);
		return model;
	}

	@Override
	public void delete(final String id) {
		final GameModel model = (GameModel) getHibernateTemplate().get(
				HibernateGameModel.class, id);
		getHibernateTemplate().delete(model);
	}

	@Override
	public void update(final GameModel model) {
		getHibernateTemplate().saveOrUpdate(model);
	}

	@Override
	public SharedData getSharedData() {
		return (SharedData) getHibernateTemplate().get(
				HibernateSharedData.class, HibernateSharedData.ID);
	}

	@Override
	public void updateSharedData(SharedData sharedData) {
		getHibernateTemplate().saveOrUpdate(sharedData);
	}

	protected void createSharedData() {
		getHibernateTemplate().save(new HibernateSharedData());
		System.out.println("Created shared data "
				+ getSharedData().getBestScore());
	}
}