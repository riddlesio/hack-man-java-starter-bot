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

import java.util.Scanner;

import move.Move;
import move.MoveType;

/**
 * bot.BotParser
 * 
 * Main class that will keep reading output from the engine.
 * Will either update the bot state or get actions.
 * 
 * @author Jim van Eeden - jim@riddles.io
 */

class BotParser {

	private Scanner scan;
	private BotStarter bot;

	private BotState currentState;

	BotParser(BotStarter bot) {
		this.scan = new Scanner(System.in);
		this.bot = bot;
		this.currentState = new BotState();
	}

	void run() {
		while(scan.hasNextLine()) {
			String line = scan.nextLine();

			if (line.length() == 0) continue;

			String[] parts = line.split(" ");
			switch (parts[0]) {
				case "settings":
					this.currentState.parseSettings(parts[1], parts[2]);
					break;
				case "update":
					if (parts[1].equals("game")) {
						this.currentState.parseGameData(parts[2], parts[3]);
					} else {
						this.currentState.parsePlayerData(parts[1], parts[2], parts[3]);
					}
					break;
				case "action":
					if (parts[1].equals("move")) { /* move requested */
						this.currentState.setTimebank(Integer.parseInt(parts[2]));
						Move move = this.bot.doMove(this.currentState);

						if (move != null) {
							System.out.println(move.toString());
						} else {
							System.out.println(MoveType.PASS.toString());
						}
					}
					break;
				default:
					System.out.println("unknown command");
					break;
			}
		}
	}
}