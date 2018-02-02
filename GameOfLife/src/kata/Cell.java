package kata;

public class Cell {
    public static enum Status{
        DEAD,BEING_DEAD,ALIVE,BEING_ALIVE;
    };

    private Status status;
    private boolean isAlive;
    private Position position;
    public Cell(){
        isAlive = true;
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

}
