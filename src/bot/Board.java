package bot;

/**
 * Created by joost on 29-6-16.
 */
public class Board {
    protected String[][] fields;
    protected int width = 20;
    protected int height = 11;

    public Board() {
        this.fields = new String[this.width][this.height];
    }

    public Board(int w, int h) {
        this.width = w;
        this.height = h;
        this.fields = new String[this.width][this.height];
    }

    /**
     * Serialises the board to a String
     * @param:
     * @return: String
     */
    public String toString() {
        String s = "";
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                s += fields[x][y];
            }
        }
        return s;
    }

    /**
     * Parses the board from a String. Make sure width and height are set!
     * @param: String
     * @return:
     */
    public void parse(String input) {
        String[] s = input.split(",");
        this.fields = new String[this.width][this.height];
        int x = 0, y = 0;
        for (int i = 0; i < s.length; i++) {
            this.fields[x][y] = s[i];
            if (++x == this.width) {
                x = 0; y++;
            }
        }
    }

    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }

    /**
     * Sets the width of the board. Warning: this clears the board!
     * @param: width
     * @return:
     */
    public void setWidth(int w) {
        this.width = w;
        this.fields = new String[this.width][this.height];
    }

    /**
     * Sets the height of the board. Warning: this clears the board!
     * @param: height
     * @return:
     */
    public void setHeight(int h) {
        this.height = h;
        this.fields = new String[this.width][this.height];
    }

    /**
     * Returns the field at Coordinate
     * @param: Coordinate
     * @return: String
     */
    public String getFieldAt(Coordinate c) {
        return fields[c.getX()][c.getY()];
    }

    /**
     * Sets the field at Coordinate
     * @param: Coordinate, String
     * @return:
     */
    public void setFieldAt(Coordinate c, String s) {
        fields[c.getX()][c.getY()] = s;
    }


    public void dump() {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                System.out.print(fields[x][y]);
            }
            System.out.println();
        }
    }
}
