package test;

import static org.junit.Assert.*;
import main.Cell;
import org.junit.Test;

public class CellTest {

    /** Given a living cell without neighbour
     * when evolve
     * then die
     */

    @Test
    public void test_GivenAliveCellNoNeighbour_WhenEvolve_ThenDie(){
        Cell cell = new Cell();
        cell.setPosition(0, 0);
        cell.setAlive(true);
        cell.evolve();
        assertFalse(cell.isAlive());
    }

    /** Given 2 cell, B is leftNeighbour to A
     *  when A checkNeighbour
     *  then return true
     */
    @Test
    public void test_GivenCellBisLeftNeighbourToA_WhenACheckNeighbour_ThenTrue(){
        Cell cell = new Cell();
        cell.setPosition(0, 0);

        Cell leftCell = new Cell();
        leftCell.setPosition(1, 0);

        assertTrue(cell.isNeighbour(leftCell));
    }


    /** Given 2 cell, B is not neighbour to A
     *  when A checkNeighbour
     *  then return false
     */
    @Test
    public void test_GivenCellBisNotNeighbourToA_WhenACheckNeighbour_ThenFalse(){
        Cell cell = new Cell();
        cell.setPosition(0, 0);

        Cell leftCell = new Cell();
        leftCell.setPosition(2, 0);

        assertFalse(cell.isNeighbour(leftCell));
    }



    /** Given a living cell with 1 left neighbour
     * when evolve
     * then die
     */

//    @Test
//    public void test_GivenAliveCellWith1LeftNeighbour_WhenEvolve_ThenDie(){
//        Cell cell = new Cell();
//        cell.setPosition(0, 0);
//
//        Cell leftCell = new Cell();
//        leftCell.setPosition(1, 0);
//        leftCell.setAlive(false);
//        cell.setNeighbour(leftCell);
//        cell.setAlive(true);
//        cell.evolve();
//        assertFalse(cell.isAlive());
//    }

}