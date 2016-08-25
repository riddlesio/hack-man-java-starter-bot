/*
 * Copyright 2016 riddles.io (developers@riddles.io)
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 *     For the full copyright and license information, please view the LICENSE
 *     file that was distributed with this source code.
 */

package bot;

import java.util.HashMap;

import field.Field;
import player.Player;

/**
 * bot.BotState
 *
 * This class stores all settings of the game and the information about the
 * current state of the game, handles the updates and settings gotten from the
 * parser. When using this in BotStarter.doMove, you can trust that this state
 * has been update to current game state (because updates are sent before action request).
 *
 * @author Jim van Eeden - jim@riddles.io
 */
public class BotState {
    private int MAX_TIMEBANK;
    private int TIME_PER_MOVE;
    private int MAX_ROUNDS;

    private int roundNr;
    private int timebank;
    private String myName;
    private HashMap<String, Player> players;

    private Field field;

    BotState() {
        this.field = new Field();
        this.players = new HashMap<>();
    }

    /**
     * Parses all the game settings given by the engine
     * @param key Type of setting given
     * @param value Value
     */
    void parseSettings(String key, String value) {
        try {
            switch(key) {
                case "timebank":
                    int time = Integer.parseInt(value);
                    this.MAX_TIMEBANK = time;
                    this.timebank = time;
                    break;
                case "time_per_move":
                    this.TIME_PER_MOVE = Integer.parseInt(value);
                    break;
                case "player_names":
                    String[] playerNames = value.split(",");
                    for (String playerName : playerNames)
                        this.players.put(playerName, new Player(playerName));
                    break;
                case "your_bot":
                    this.myName = value;
                    break;
                case "your_botid":
                    int myId = Integer.parseInt(value);
                    int opponentId = 2 - myId + 1;
                    this.field.setMyId(myId);
                    this.field.setOpponentId(opponentId);
                    break;
                case "field_width":
                    this.field.setWidth(Integer.parseInt(value));
                    break;
                case "field_height":
                    this.field.setHeight(Integer.parseInt(value));
                    break;
                case "max_rounds":
                    this.MAX_ROUNDS = Integer.parseInt(value);
                    break;
                default:
                    System.err.println(String.format(
                            "Cannot parse settings input with key '%s'", key));
            }
        } catch (Exception e) {
            System.err.println(String.format(
                    "Cannot parse settings value '%s' for key '%s'", value, key));
            e.printStackTrace();
        }
    }

    /**
     * Parse data about the game given by the engine
     * @param key Type of game data given
     * @param value Value
     */
    void parseGameData(String key, String value) {
        try {
            switch(key) {
                case "round":
                    this.roundNr = Integer.parseInt(value);
                    break;
                case "field":
                    this.field.initField();
                    this.field.parseFromString(value);
                    break;
                default:
                    System.err.println(String.format(
                            "Cannot parse game data input with key '%s'", key));
            }
        } catch (Exception e) {
            System.err.println(String.format(
                    "Cannot parse game data value '%s' for key '%s'", value, key));
            e.printStackTrace();
        }
    }

    /**
     * Parse data about given player that the engine has sent
     * @param playerName Player name that this data is about
     * @param key Type of player data given
     * @param value Value
     */
    void parsePlayerData(String playerName, String key, String value) {
        Player player = this.players.get(playerName);

        if (player == null) {
            System.err.println(String.format("Could not find player with name %s", playerName));
            return;
        }

        try {
            switch(key) {
                case "has_weapon":
                    player.setWeapon(Boolean.parseBoolean(value));
                    break;
                case "is_paralyzed":
                    player.setParalyzed(Boolean.parseBoolean(value));
                    break;
                case "snippets":
                    player.setSnippets(Integer.parseInt(value));
                    break;
                default:
                    System.err.println(String.format(
                            "Cannot parse %s data input with key '%s'", playerName, key));
            }
        } catch (Exception e) {
            System.err.println(String.format(
                    "Cannot parse %s data value '%s' for key '%s'", playerName, value, key));
            e.printStackTrace();
        }
    }

    public void setTimebank(int value) {
        this.timebank = value;
    }

    public int getTimebank() {
        return this.timebank;
    }

    public int getRoundNumber() {
        return this.roundNr;
    }

    public HashMap<String, Player> getPlayers() {
        return this.players;
    }

    public Field getField() {
        return this.field;
    }
}
