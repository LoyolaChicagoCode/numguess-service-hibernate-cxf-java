package numguess;

import java.util.Random;

/**
 * A class representing the business logic of the game. There is one instance of
 * this class per user session.
 */
public class DefaultGameModel implements GameModel {

	private int answer = -1;
	private int numGuesses = 0;

	private SharedData sharedData;

	public void setSharedData(final SharedData sharedData) {
		this.sharedData = sharedData;
	}

	public SharedData getSharedData() {
		return sharedData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see numguess.GameModel#guess(int)
	 */
	@Override
	public synchronized GuessResult guess(int guess) {
		boolean isNewBestScore = false;
		int bestScore = -1;
		// update data model
		numGuesses++;
		// update best score in shared data model if available
		if (guess == answer && sharedData != null) {
			isNewBestScore = sharedData.setIfBestScore(numGuesses);
			bestScore = sharedData.getBestScore();
		}
		// populate resulting ValueObject
		DefaultGuessResult result = new DefaultGuessResult();
		result.setGuess(guess);
		result.setNumGuesses(numGuesses);
		result.setComparison(guess - answer);
		result.setBestScore(bestScore);
		result.setNewBestScore(isNewBestScore);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see numguess.GameModel#reset(int, int)
	 */
	@Override
	public synchronized void reset(final int min, final int max) {
		if (min > max)
			throw new IllegalArgumentException("min > max");
		answer = min + Math.abs(new Random().nextInt() % (max - min + 1));
		numGuesses = 0;
		System.out.println("The answer is " + answer);
	}

	/**
	 * Sets the answer to a specific value for testing.
	 * 
	 * @param answer
	 *            the answer
	 */
	public synchronized void setAnswer(int answer) {
		this.answer = answer;
		numGuesses = 0;
		System.out.println("The answer is " + answer);
	}
}