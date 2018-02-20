package main;

public class Cell {
    public int x;
    public int y;
    private boolean alive;
    private Cell leftNeighbour;

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void evolve() {
        setAlive(false);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isNeighbour(Cell neighbour) {

        return this.x == neighbour.x-1 && this.y == neighbour.y;
    }
}

