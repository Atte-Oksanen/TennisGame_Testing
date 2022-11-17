import static org.junit.Assert.*;

import org.junit.Test;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test
	public void TestNormalGame() throws TennisGameException{
		TennisGame game = new TennisGame();
		game.player1Scored();
		game.player1Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		assertEquals("The game should be won by player2", "player2 wins", game.getScore());
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();			
	}
	@Test
	public void testTennisGame_TestingAllPoints() throws TennisGameException{
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		assertEquals("Game score incorrect", "love - 15", game.getScore());
		game.player1Scored();
		assertEquals("Game score incorrect", "love - 30", game.getScore());
		game.player1Scored();
		assertEquals("Game score incorrect", "love - 40", game.getScore());
		game.player1Scored();
		assertEquals("Game score incorrect", "player1 wins", game.getScore());
	}
	@Test (expected = TennisGameException.class)
	public void testTennisGame_TestGetScore() throws TennisGameException{
		TennisGame game = new TennisGame();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.getScore();
		game.player2Scored();
		game.getScore();
		game.player2Scored();
		
		
	}
	
	@Test
	public void testTennisGame_TestAdvantage() throws TennisGameException{
		TennisGame game = new TennisGame();
		
		for(int n = 0; n < 3; n++) {
			game.player1Scored();
			game.player2Scored();
		}
		game.player2Scored();
		assertEquals("Advantage is not correct", "player2 has advantage", game.getScore());
		
		game = new TennisGame();
		for(int n = 0; n < 3; n++) {
			game.player1Scored();
			game.player2Scored();
		}
		game.player1Scored();
		assertEquals("Advantage is not correct", "player1 has advantage", game.getScore());
		
	}
}
