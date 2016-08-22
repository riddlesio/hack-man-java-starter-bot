package bot;

import java.util.Random;

/**
 * BotLogic class
 *
 * This class implements the logic of your bot's moves. Edit makeTurn to output moves.
 *
 * @author Joost <joost@starapple.nl>
 */

public class BotLogic {

    private int botId = 0;

    /**
     * Makes a turn. Edit this class to make your bot smarter.
     * @return a Move object
     */
    public Move makeTurn(Board board, int moveNr) {

        Random r = new Random();
        int direction = r.nextInt(4)*90; /* 0, 90, 180 or 270 degrees */
        Move move = new Move(direction);
        return move;
    }

    /**
     * Sets the bot id.
     * @Param: int
     * @return:
     */
    public void setBotId(int id) { this.botId = id; }
}
