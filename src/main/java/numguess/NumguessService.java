package numguess;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * An interface for the business logic of this web application.
 */
@WebService
public interface NumguessService extends GameModel {

	@Override
	public GuessResult guess(@WebParam(name = "guess")
	int guess);

	@Override
	public void reset(@WebParam(name = "min")
	int min, @WebParam(name = "max")
	int max);
}
