package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author aya
 *
 */
public class GameTest {
  Game makeGame = new Game;
  @Test
  public void nextTurnTest{
    
  }

  @Test
  public void nextRoundTest{

  }


  // ---For test execution----------------------
  public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(GameTest.class);
  }

}
