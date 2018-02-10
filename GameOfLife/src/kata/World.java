package kata;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class World {
    public static final String NO_CELL = "-";
    private int width;
    private int height;
    List<Cell> cells;
    private String[][] cellWorld;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new ArrayList<>();
        initializeCellWorld(width, height);
    }

    private void initializeCellWorld(int width, int height) {
        cellWorld = new String[width][height];
        for(String[] cellWorldRow:cellWorld){
            Arrays.fill(cellWorldRow,NO_CELL);
        }
    }

    public void evolve() {
        if(cells.isEmpty()) return;
        List<Cell> cellsBeingAlive = new ArrayList<>();
        List<Cell> cellsBeingDead = new ArrayList<>();
        for(Cell cell:cells){
            if(isUnderPopulation(cell) || isOvercrowding(cell)){
                cellsBeingDead.add(cell);
            }
            if(isReproduction(cell)){
                cellsBeingAlive.add(cell);
            }
        }
        cellsBeingAlive.stream().forEach(cell->cell.setAlive(true));
        cellsBeingDead.stream().forEach(cell->cell.setAlive(false));

    }

    private boolean isReproduction(Cell cell) {
        return (!cell.isAlive())&&countLivingNeighbours(cell)==3;
    }

    private boolean isOvercrowding(Cell cell) {
        return cell.isAlive()&&countLivingNeighbours(cell)>3;
    }

    private boolean isUnderPopulation(Cell cell) {
        return cell.isAlive()&&countLivingNeighbours(cell)<2;
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
        if(isOutsideTheWorld(position)){
            throw new PositionOutsideTheWorldException();
        }
        if(isOccupied(position)){
            throw new PositionOccupliedException();
        }
        newCell.setPosition(position);
        cellWorld[newCell.getPosition().x][newCell.getPosition().y]=newCell.toString();
        cells.add(newCell);
    }
    private boolean isOccupied(Position position) {
        return cellWorld[position.x][position.y]!= NO_CELL;
    }

    private boolean isOutsideTheWorld(Position position) {
        return (position.y>this.height-1||position.y<0)||(position.x>this.width-1||position.x<0);
    }


    public String displayCellWorld() {
        StringBuilder result = new StringBuilder();
        for(String[] cellWorldRow:cellWorld){
            for(String content:cellWorldRow){
                result.append(content).append(" ");
            }
            result.deleteCharAt(result.length()-1);
            result.append("\n");
        }
        result.deleteCharAt(result.length()-1);
        return result.toString();
    }
}
