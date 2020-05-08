class Pac {
    private int pacId;
    private boolean mine;
    private int posX;
    private int posY;


    // constructor
    public Pac() {
    }

    public Pac(int pacId, boolean mine, int posX, int posY) {
        this.pacId = pacId;
        this.mine = mine;
        this.posX = posX;
        this.posY = posY;
    }

    // getters and setters
    public int getPacId() {
        return pacId;
    }

    public void setPacId(int pacId) {
        this.pacId = pacId;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    // to string
    @Override
    public String toString() {
        return "Pac{" +
                "pacId=" + pacId +
                ", mine=" + mine +
                ", posX=" + posX +
                ", posY=" + posY +
                '}';
    }
}
