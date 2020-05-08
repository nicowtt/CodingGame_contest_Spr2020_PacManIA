class Past {
    private int pastX;
    private int pastY;
    private int value;

    // contructor
    public Past() {
    }

    public Past(int pastX, int pastY, int value) {
        this.pastX = pastX;
        this.pastY = pastY;
        this.value = value;
    }

    // getters and setters
    public int getPastX() {
        return pastX;
    }

    public void setPastX(int pastX) {
        this.pastX = pastX;
    }

    public int getPastY() {
        return pastY;
    }

    public void setPastY(int pastY) {
        this.pastY = pastY;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // to string
    @Override
    public String toString() {
        return "Past{" +
                "pastX=" + pastX +
                ", pastY=" + pastY +
                ", value=" + value +
                '}';
    }
}
