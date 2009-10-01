package numguess;

/**
 * A NumguessService that forwards incoming service method invocations to the
 * DAO based on the session ID obtained from the UserIDProvider. In the context
 * of a web application, the session ID corresponds to the session associated
 * with the incoming service method invocation.
 */
public class DaoAwareNumguessService implements NumguessService,
		NumguessDAOAware, UserIDProviderAware {

	private NumguessDAO dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see numguess.NumguessDAOAware#setNumguessDAO(numguess.NumguessDAO)
	 */
	@Override
	public void setNumguessDAO(final NumguessDAO dao) {
		System.out.println("Setting dao to " + dao);
		this.dao = dao;
	}

	private UserIDProvider idProvider;

	/*
	 * (non-Javadoc)
	 * 
	 * @see numguess.UserIDProviderAware#setSessionIDProvider(numguess.UserIDProvider)
	 */
	@Override
	public void setUserIDProvider(UserIDProvider idProvider) {
		System.out.println("setting ID provider to " + idProvider);
		this.idProvider = idProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see numguess.NumguessService#guess(int)
	 */
	@Override
	public GuessResult guess(int guess) {
		final GameModel model = dao.read(idProvider.getUserID());
		final GuessResult result = model.guess(guess);
		dao.update(model);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see numguess.NumguessService#reset(int, int)
	 */
	@Override
	public void reset(int min, int max) {
		final GameModel model = dao.read(idProvider.getUserID());
		model.reset(min, max);
		dao.update(model);
	}
}
