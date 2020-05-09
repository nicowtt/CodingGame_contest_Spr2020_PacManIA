class Pac {
    private int pacId;
    private boolean mine;
    private int posX;
    private int posY;
    private int speedTurnsLeft;
    private int abilityCooldown;
    private Cell lockedCell;


    // constructor
    public Pac() {
    }

    public Pac(int pacId, boolean mine, int posX, int posY, int speedTurnsLeft, int abilityCooldown, Cell lockedCell) {
        this.pacId = pacId;
        this.mine = mine;
        this.posX = posX;
        this.posY = posY;
        this.speedTurnsLeft = speedTurnsLeft;
        this.abilityCooldown = abilityCooldown;
        this.lockedCell = lockedCell;
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

    public int getSpeedTurnsLeft() {
        return speedTurnsLeft;
    }

    public void setSpeedTurnsLeft(int speedTurnsLeft) {
        this.speedTurnsLeft = speedTurnsLeft;
    }

    public int getAbilityCooldown() {
        return abilityCooldown;
    }

    public void setAbilityCooldown(int abilityCooldown) {
        this.abilityCooldown = abilityCooldown;
    }

    public Cell getLockedCell() {
        return lockedCell;
    }

    public void setLockedCell(Cell lockedCell) {
        this.lockedCell = lockedCell;
    }

    // to string
    @Override
    public String toString() {
        return "Pac{" +
                "pacId=" + pacId +
                ", mine=" + mine +
                ", posX=" + posX +
                ", posY=" + posY +
                ", speedTurnsLeft=" + speedTurnsLeft +
                ", abilityCooldown=" + abilityCooldown +
                '}';
    }
}
