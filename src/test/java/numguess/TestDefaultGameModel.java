/**
 * 
 */
package numguess;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author laufer
 * 
 */
public class TestDefaultGameModel {

	private DefaultGameModel model;

	private DefaultSharedData shared;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		model = new DefaultGameModel();
		shared = new DefaultSharedData();
		model.setSharedData(shared);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		model = null;
		shared = null;
	}

	/**
	 * Test method for {@link numguess.DefaultGameModel#guess(int)}.
	 */
	@Test
	public void testGuess() {
		model.reset(1, 100);
		GuessResult result = model.guess(50);
		assertEquals(1, result.getNumGuesses());
	}

	/**
	 * Test method for {@link numguess.DefaultGameModel#guess(int)}.
	 */
	@Test
	public void testMultipleGuesses() {
		model.reset(1, 100);
		GuessResult result = model.guess(50);
		assertEquals(1, result.getNumGuesses());
		result = model.guess(50);
		assertEquals(2, result.getNumGuesses());
		result = model.guess(50);
		assertEquals(3, result.getNumGuesses());
	}

	/**
	 * Test method for {@link numguess.DefaultGameModel#guess(int)}.
	 */
	@Test
	public void testComplex() {
		model.reset(1, 100);
		model.setAnswer(30);
		GuessResult result = model.guess(50);
		assertEquals(1, result.getNumGuesses());
		assertFalse(result.isNewBestScore());
		assertTrue(result.getComparison() > 0);
		result = model.guess(25);
		assertEquals(2, result.getNumGuesses());
		assertFalse(result.isNewBestScore());
		assertTrue(result.getComparison() < 0);
		result = model.guess(30);
		assertEquals(3, result.getNumGuesses());
		assertTrue(result.isNewBestScore());
		assertEquals(3, result.getBestScore());
		assertEquals(0, result.getComparison());
	}

	/**
	 * Test method for {@link numguess.DefaultGameModel#reset(int, int)}.
	 */
	@Test
	public void testReset() {
		model.reset(1, 100);
	}

	/**
	 * Test method for {@link numguess.DefaultGameModel#reset(int, int)}.
	 */
	@Test
	public void testResetBad() {
		try {
			model.reset(100, 1);
			fail("should have raised IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
	}
}
