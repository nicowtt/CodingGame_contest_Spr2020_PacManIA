class Pac {
    private int pacId;
    private boolean mine;
    private int posX;
    private int posY;
    private String typeId;
    private int speedTurnsLeft;
    private int abilityCooldown;
    private Cell lockedCell;
    private boolean updated;
    private Cell previousPos;
    private boolean typeNotChange;


    // constructor
    public Pac() {
    }

    public Pac(int pacId, boolean mine, int posX, int posY, String typeId, int speedTurnsLeft, int abilityCooldown, Cell lockedCell, boolean updated, Cell previousPos, boolean typeNotChange) {
        this.pacId = pacId;
        this.mine = mine;
        this.posX = posX;
        this.posY = posY;
        this.typeId = typeId;
        this.speedTurnsLeft = speedTurnsLeft;
        this.abilityCooldown = abilityCooldown;
        this.lockedCell = lockedCell;
        this.updated = updated;
        this.previousPos = previousPos;
        this.typeNotChange = typeNotChange;
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

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
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

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public Cell getPreviousPos() {
        return previousPos;
    }

    public void setPreviousPos(Cell previousPos) {
        this.previousPos = previousPos;
    }

    public boolean isTypeNotChange() {
        return typeNotChange;
    }

    public void setTypeNotChange(boolean typeNotChange) {
        this.typeNotChange = typeNotChange;
    }

    // to string
    @Override
    public String toString() {
        return "Pac{" +
                "pacId=" + pacId +
                ", mine=" + mine +
                ", posX=" + posX +
                ", posY=" + posY +
                ", typeId=" + typeId +
                ", speedTurnsLeft=" + speedTurnsLeft +
                ", abilityCooldown=" + abilityCooldown +
                ", updated=" + updated +
                ", previousPos=" + previousPos +
                ", typeChange=" + typeNotChange +
                '}';
    }
}
