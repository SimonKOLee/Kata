package kata;


import java.util.ArrayList;
import java.util.List;

public class World {
    private int width;
    private int height;
    List<Cell> cells;
    private String[][] cellWorld;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new ArrayList<>();
        cellWorld = new String[width][height];
    }

    public void operate() {
        if(cells.isEmpty()) return;
        List<Cell> cellsBeingAlive = new ArrayList<>();
        List<Cell> cellsBeingDead = new ArrayList<>();
        for(Cell cell:cells){
            if(cell.isAlive()&&(countLivingNeighbours(cell)<2||countLivingNeighbours(cell)>3)){
                cellsBeingDead.add(cell);
            }
            if((!cell.isAlive())&&countLivingNeighbours(cell)==3){
                cellsBeingAlive.add(cell);
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
        return cellWorld[position.x][position.y]!=null;
    }

    private boolean isOutsideTheWorld(Position position) {
        return (position.y>this.height-1||position.y<0)||(position.x>this.width-1||position.x<0);
    }


    public String displayCellWorld() {
        String result = "";
        for(int i = 0; i< cellWorld.length; i++){
            for(int j = 0; j< cellWorld[i].length; j++){
                if(cellWorld[i][j]==null){
                    result += "-";
                }else{
                    result += cellWorld[i][j];
                }
                if(j!= cellWorld[i].length-1){
                    result +=" ";
                }
            }
            if(i!= cellWorld.length-1){
                result +="\n";
            }
        }
        return result;
    }

    public String[][] getCellWorld() {
        return cellWorld;
    }
}
