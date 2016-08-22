// // Copyright 2016 theaigames.com (developers@theaigames.com)

//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at

//        http://www.apache.org/licenses/LICENSE-2.0

//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//  
//    For the full copyright and license information, please view the LICENSE
//    file that was distributed with this source code.

package bot;

/**
 * Move class
 * 
 * Stores a move.
 * 
 * @author Jim van Eeden <jim@starapple.nl>, Joost <joost@starapple.nl>
 */

public class Move {
	int mDirection;
	private static final int DIR_UP = 0;
	private static final int DIR_RIGHT = 90;
	private static final int DIR_DOWN = 180;
	private static final int DIR_LEFT = 270;
	
	public Move() {
	}
	
	public Move(int direction) {
		mDirection = direction;
	}
	
	public int getDirection() { 
		return mDirection; 
	}
	
	public String getDirectionString() {
		return directionToString(mDirection);
	}
	

	public static String directionToString(int direction) {
		switch (direction) {
			case DIR_UP:
				return "up";
			case DIR_RIGHT:
				return "right";
			case DIR_DOWN:
				return "down";
			case DIR_LEFT:
				return "left";
		}
		return "unknown";
	}
	
	public String toString() {
		return "move " + getDirectionString();
	}
}
