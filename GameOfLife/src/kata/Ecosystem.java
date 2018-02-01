package kata;

import static kata.Ecosystem.CellStatus.DEAD;

public class Ecosystem {
    public enum CellStatus{
        DEAD(0),  ALIVE(1);
        private int value;
        CellStatus(int i) {
            value = i;
        }
        public int getValue(){
            return value;
        }
    }
    int[][] cellEnvironment;
    public Ecosystem(int[][] cellEnv) {
        cellEnvironment = cellEnv;
    }

    public void evolve() {
        for(int i=0; i<cellEnvironment.length;i++){
            for(int j=0; j<cellEnvironment[0].length;j++) {
                cellEnvironment[i][j] = DEAD.getValue();
            }
        }
    }

    public int[][] getCellEnvironment() {
        return cellEnvironment;
    }
}
