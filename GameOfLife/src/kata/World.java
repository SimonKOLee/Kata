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
        List<Cell> livingCells = new ArrayList<>();
        List<Cell> deadCells = new ArrayList<>();

        for(Cell cell:cells){
            if(countLivingNeighbours(cell)==2){
                livingCells.add(cell);
            }else{
                deadCells.add(cell);
            }
        }

        for(Cell cell:livingCells){
            cell.setAlive(true);
        }
        for(Cell cell:deadCells){
            cell.setAlive(false);
        }
    }

    private int countLivingNeighbours(Cell targetCell) {
        int neighbourCount = 0;
        for(Cell cell:cells){
            if(cell==targetCell){
                continue;
            }
            if(!cell.isAlive()){
                continue;
            }
            if(cell.getPosition().x == targetCell.getPosition().x && cell.getPosition().y ==targetCell.getPosition().y-1){
                neighbourCount++;
            }
            if(cell.getPosition().x == targetCell.getPosition().x && cell.getPosition().y ==targetCell.getPosition().y+1){
                neighbourCount++;
            }
            if(cell.getPosition().y == targetCell.getPosition().y && cell.getPosition().x ==targetCell.getPosition().x+1){
                neighbourCount++;
            }
            if(cell.getPosition().y == targetCell.getPosition().y && cell.getPosition().x ==targetCell.getPosition().x-1){
                neighbourCount++;
            }
            if(cell.getPosition().y == targetCell.getPosition().y+1 && cell.getPosition().x ==targetCell.getPosition().x+1){
                neighbourCount++;
            }
            if(cell.getPosition().y == targetCell.getPosition().y-1 && cell.getPosition().x ==targetCell.getPosition().x-1){
                neighbourCount++;
            }
            if(cell.getPosition().y == targetCell.getPosition().y-1 && cell.getPosition().x ==targetCell.getPosition().x+1){
                neighbourCount++;
            }
            if(cell.getPosition().y == targetCell.getPosition().y+1 && cell.getPosition().x ==targetCell.getPosition().x-1){
                neighbourCount++;
            }
        }
        return neighbourCount;
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
