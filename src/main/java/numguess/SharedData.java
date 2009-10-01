package numguess;

/**
 * An interface for the data shared at the application level.
 */
public interface SharedData {

	public boolean setIfBestScore(int bestScore);

	public int getBestScore();
}