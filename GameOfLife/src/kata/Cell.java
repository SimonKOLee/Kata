package kata;

public class Cell {
    public static final String LIVING_CELL = "1";
    public static final String DEAD_CELL = "0";
    private boolean isAlive;
    private Position position;
    public Cell(){
        isAlive = true;
    }
    public Cell(Boolean isAlive){
        isAlive = isAlive;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean isAlive){
        this.isAlive = isAlive;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return isAlive?LIVING_CELL:DEAD_CELL;
    }
}
