package kata;


import java.util.ArrayList;
import java.util.List;

public class World {
    private int width;
    private int height;
    List<Cell> cells;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new ArrayList<>();
    }

    public void operate() {
        if(cells.isEmpty()) return;
        List<Cell> cellsBeingAlive = new ArrayList<>();
        List<Cell> cellsBeingDead = new ArrayList<>();

        for(Cell cell:cells){
            if(cell.isAlive()){
                if(countLivingNeighbours(cell)==2){
                    cellsBeingAlive.add(cell);
                }else{
                    cellsBeingDead.add(cell);
                }
            }else{
                if(countLivingNeighbours(cell)==3){
                    cellsBeingAlive.add(cell);
                }
            }
        }

        for(Cell cell:cellsBeingAlive){
            cell.setAlive(true);
        }
        for(Cell cell:cellsBeingDead){
            cell.setAlive(false);
        }
    }

    private int countLivingNeighbours(Cell targetCell) {
        int neighbourCount = 0;
        for(Cell cell:cells){
            if(cell==targetCell||!cell.isAlive()){
                continue;
            }
            if(isNeighbour(targetCell, cell)){
                neighbourCount++;
            }
        }
        return neighbourCount;
    }

    private boolean isNeighbour(Cell targetCell, Cell cell) {
        return (cell.getPosition().y == targetCell.getPosition().y-1 && cell.getPosition().x ==targetCell.getPosition().x)
                ||(cell.getPosition().y ==targetCell.getPosition().y+1 && cell.getPosition().x == targetCell.getPosition().x)
                ||(cell.getPosition().y == targetCell.getPosition().y && cell.getPosition().x ==targetCell.getPosition().x+1)
                ||(cell.getPosition().y == targetCell.getPosition().y && cell.getPosition().x ==targetCell.getPosition().x-1)
                ||(cell.getPosition().y == targetCell.getPosition().y+1 && cell.getPosition().x ==targetCell.getPosition().x+1)
                ||(cell.getPosition().y == targetCell.getPosition().y-1 && cell.getPosition().x ==targetCell.getPosition().x-1)
                ||(cell.getPosition().y == targetCell.getPosition().y-1 && cell.getPosition().x ==targetCell.getPosition().x+1)
                ||(cell.getPosition().y == targetCell.getPosition().y+1 && cell.getPosition().x ==targetCell.getPosition().x-1);
    }

    public void add(Cell newCell, Position position) {
        if((position.y>this.height-1||position.y<0)||(position.x>this.width-1||position.x<0)){
            throw new PositionOutsideTheWorldException();
        }
        for(Cell cell:cells){
            if(cell.getPosition().x==position.x&&cell.getPosition().y == position.y){
                throw new PositionOccupliedException();
            }
        }
        newCell.setPosition(position);
        cells.add(newCell);
    }
}
