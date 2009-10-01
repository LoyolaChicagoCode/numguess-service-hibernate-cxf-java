package numguess;

/**
 * An interface for read-only access to guess results going back from the game
 * to the user. It is an example of the ValueObject (TransferObject) pattern.
 */
public interface GuessResult {

	int getGuess();

	int getComparison();

	int getNumGuesses();

	int getBestScore();

	boolean isNewBestScore();

}