class Cell {

    private int x;
    private int y;
    private String cardinalPoint;

    // constructor
    public Cell() {
    }

    public Cell(int x, int y, String cardinalPoint) {
        this.x = x;
        this.y = y;
        this.cardinalPoint = cardinalPoint;
    }

    // getters and setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getCardinalPoint() {
        return cardinalPoint;
    }

    public void setCardinalPoint(String cardinalPoint) {
        this.cardinalPoint = cardinalPoint;
    }

    // to string
    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", cardinalPoint='" + cardinalPoint + '\'' +
                '}';
    }
}
