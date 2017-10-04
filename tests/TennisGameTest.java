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
	public void testTennisGame_PlayersGets1PointAndScore15() throws TennisGameException {
		TennisGame game = get1and1Game(); //Refactored with the help of JDeodorant (long method -> extract method)
		
		//Act
		String score = game.getScore();
		
		//Assert
		assertEquals("One point score incorrect", "15 - 15", score);
		
	}

	private TennisGame get1and1Game() throws TennisGameException { //Refactored with the help of JDeodorant (long method -> extract method)
		TennisGame game = new TennisGame();
		game.player1Scored();
		game.player2Scored();
		return game;
	}
	
	@Test
	public void testTennisGame_PlayersGet2PointsAndScore30() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player1Scored();
		
		//Act
		String score = game.getScore();
		
		//Assert
		assertEquals("Two point score incorrect", "30 - 30", score);
		
	}
	
	@Test
	public void testTennisGame_Player1HasAdvantage() throws TennisGameException {
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
		
		game.player1Scored();
		
		//Act
		String score = game.getScore();
		
		//Assert
		assertEquals("Player1 should have an advantage", "player1 has advantage", score);
		
	}
	
	@Test
	public void testTennisGame_Player1Wins() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		
		game.player1Scored();

		game.player1Scored();
		
		game.player1Scored();
		
		//Act
		String score = game.getScore();
		
		//Assert
		assertEquals("Player1 should have won", "player1 wins", score);
		
	}
	
	@Test
	public void testTennisGame_Player1Scores2PointsMoreButDoesntWin() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player2Scored();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		//Act
		String score = game.getScore();
		
		//Assert
		assertEquals("The game score is incorrect", "40 - 15", score);
		
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
}
