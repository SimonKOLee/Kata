package test;
import kata.*;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class WorldTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    /**
     * Given a 1x1 world and a cell living in the world
     * When the world operate
     * Then the cell should be dead
     */
    @Test
    public void testGiven1x1WorldAnd1LiveCellWhenTheWorldOperateThenTheCellShouldBeDead(){
        World world = new World(1,1);
        Cell cell = new Cell();
        world.add(cell, new Position(0,0));
        world.operate();
        assertFalse(cell.isAlive());
    }

    /**
     * Given a 1x1 world and a dead cell  in the world
     * When the world operate
     * Then the cell should be dead
     */
    @Test
    public void testGiven1x1WorldAnd1DeadCellWhenTheWorldOperateThenTheCellShouldBeDead(){
        World world = new World(1,1);
        Cell cell = new Cell();
        cell.setAlive(false);
        world.add(cell, new Position(0,0));
        world.operate();
        assertFalse(cell.isAlive());
    }

    /**
     * Given a 1x2 world and a cell lives in world at position(0,0)
     * When the world operate
     * Then the cell should be dead
     */
    @Test
    public void testGiven1x2WorldAnd1LiveCellWhenTheWorldOperateThenTheCellShouldBeDead(){
        World world = new World(1,2);
        Cell cell = new Cell();
        world.add(cell,new Position(0,0));
        world.operate();
        assertFalse(cell.isAlive());
    }

    /**
     * Given a 1x2 world and two cells live in world at position(0,0) and (0,1)
     * When the world operate
     * Then two cells should be dead
     */
    @Test
    public void testGiven1x2WorldAnd2LiveCellAt00And01WhenTheWorldOperateThen2CellsShouldBeDead(){
        World world = new World(1,2);
        Cell cellA = new Cell();
        Cell cellB = new Cell();
        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(0,1));
        world.operate();
        assertFalse(cellA.isAlive());
        assertFalse(cellB.isAlive());
    }

    /**
     * Given a 1x3 world and cellA live at (0,0) and CellB at (0,1) and CellC at(0,2)
     * When the world operate
     * Then cellA should be dead, cellB should be alive and cellC should be dead
     */
    @Test
    public void testGiven1x3WorldAndLiveCellA00AndLiveCellB01AndLiveCellC02WhenTheWorldOperateThenCellADeadCellBAliveCellCDead(){
        World world = new World(1,3);
        Cell cellA = new Cell();
        Cell cellB = new Cell();
        Cell cellC = new Cell();
        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(0,1));
        world.add(cellC,new Position(0,2));
        world.operate();
        assertFalse(cellA.isAlive());
        assertTrue(cellB.isAlive());
        assertFalse(cellC.isAlive());
    }

    /**
     * Given a 1x3 world and cellA die at (0,0) and CellB live at (0,1) and CellC live at(0,2)
     * When the world operate
     * Then cellA should be dead, cellB should be alive and cellC should be dead
     */
    @Test
    public void testGiven1x3WorldAndDeadCellA00AndLiveCellB01AndLiveCellC02WhenTheWorldOperateThenCellADeadCellBADeadCellCDead(){
        World world = new World(1,3);
        Cell cellA = new Cell();
        cellA.setAlive(false);
        Cell cellB = new Cell();
        Cell cellC = new Cell();
        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(0,1));
        world.add(cellC,new Position(0,2));
        world.operate();
        assertFalse(cellA.isAlive());
        assertFalse(cellB.isAlive());
        assertFalse(cellC.isAlive());
    }

    /**
     * Given a 1x1 world and a cell live outside the world
     * When add the cell to the world
     * Then exception thrown
     */
    @Test
    public void testCellLivingAtThePositionExceedTheMaxHeightOfTheWorldThenThrowException(){
        thrown.expect(PositionOutsideTheWorldException.class);
        World world = new World(1,1);
        Cell cell = new Cell();
        world.add(cell,new Position(0,1));
    }
    @Test
    public void testCellLivingAtThePositionLessThanTheMinHeightOfTheWorldThenThrowException(){
        thrown.expect(PositionOutsideTheWorldException.class);
        World world = new World(1,1);
        Cell cell = new Cell();
        world.add(cell,new Position(0,-1));
    }
    @Test
    public void testCellLivingAtThePositionExceedTheMaxWidthOfTheWorldThenThrowException(){
        thrown.expect(PositionOutsideTheWorldException.class);
        World world = new World(1,1);
        Cell cell = new Cell();
        world.add(cell,new Position(1,0));
    }
    @Test
    public void testCellLivingAtThePositionLessThanTheMinWidthOfTheWorldThenThrowException(){
        thrown.expect(PositionOutsideTheWorldException.class);
        World world = new World(1,1);
        Cell cell = new Cell();
        world.add(cell,new Position(-1,0));
    }

    /**
     * Given a 1x1 world and one cell living in the world
     * When the world add a new cell
     * Then PositionOccupiedException will be thrown
     */
    @Test
    public void testPositionOccupiedException(){
        thrown.expect(PositionOccupliedException.class);
        World world = new World(1,1);
        Cell cellA = new Cell();
        Cell cellB = new Cell();
        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(0,0));
    }

    /**
     * Given a 3x1 world and cellA live at (0,0) and CellB at (1,0) and CellC at(2,0)
     * When the world operate
     * Then cellA should be dead, cellB should be alive and cellC should be dead
     */
    @Test
    public void testGiven3x1WorldAndLiveCellA00AndLiveCellB10AndLiveCellC20WhenTheWorldOperateThenCellADeadCellBAliveCellCDead(){
        World world = new World(3,1);
        Cell cellA = new Cell();
        Cell cellB = new Cell();
        Cell cellC = new Cell();
        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(1,0));
        world.add(cellC,new Position(2,0));
        world.operate();
        assertFalse(cellA.isAlive());
        assertTrue(cellB.isAlive());
        assertFalse(cellC.isAlive());
    }

    /**
     * Given a 3x1 world and cellA die at (0,0) and CellB live at (1,0) and CellC live at(2,0)
     * When the world operate
     * Then cellA should be dead, cellB should be alive and cellC should be dead
     */
    @Test
    public void testGiven3x1WorldAndDeadCellA00AndLiveCellB10AndLiveCellC20WhenTheWorldOperateThenCellADeadCellBADeadCellCDead(){
        World world = new World(3,1);
        Cell cellA = new Cell();
        cellA.setAlive(false);
        Cell cellB = new Cell();
        Cell cellC = new Cell();
        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(1,0));
        world.add(cellC,new Position(2,0));
        world.operate();
        assertFalse(cellA.isAlive());
        assertFalse(cellB.isAlive());
        assertFalse(cellC.isAlive());
    }

    /**
     * Given a 2x2 world and  cellA live at position(0,0) and  CellB live at position (0,1)  and CellC live at position (1,1) in the world
     * When the world operate
     * Then  all cells should be alive
     */
    @Test
    public void testGiven2x2WorldAndCellAliveAt00AndCellBLiveAt01CellCLiveAt11WhenTheWorldOperateThen3CellsShouldBeAlive(){
        World world = new World(2,2);
        Cell cellA = new Cell();
        Cell cellB = new Cell();
        Cell cellC = new Cell();
        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(0,1));
        world.add(cellC,new Position(1,1));
        world.operate();
        assertTrue("Cell A",cellA.isAlive());
        assertTrue("Cell B",cellB.isAlive());
        assertTrue("Cell C",cellC.isAlive());
    }

    /**
     * Given a 2x2 world and  cellA live at position(0,0) and  CellB live at position (1,0)  and CellC live at position (0,1) in the world
     * When the world operate
     * Then  all cells should be alive
     */
    @Test
    public void testGiven2x2WorldAndCellAliveAt00AndCellBLiveAt10CellCLiveAt01WhenTheWorldOperateThen3CellsShouldBeAlive(){
        World world = new World(2,2);
        Cell cellA = new Cell();
        Cell cellB = new Cell();
        Cell cellC = new Cell();
        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(1,0));
        world.add(cellC,new Position(0,1));
        world.operate();
        assertTrue("Cell A",cellA.isAlive());
        assertTrue("Cell B",cellB.isAlive());
        assertTrue("Cell C",cellC.isAlive());
    }

    /**
     * Given a 2x2 world and  cellA live at position(0,0) and  CellB live at position (1,0)  and CellC live at position (0,1) in the world and CellD die at (1,1)
     * When the world operate
     * Then  all cells should be alive
     */
    @Test
    public void testGiven2x2WorldAndCellAliveAt00AndCellBLiveAt10CellCLiveAt01CellDDieAt11WhenTheWorldOperateThenAllCellsShouldBeAlive(){
        World world = new World(2,2);
        Cell cellA = new Cell();
        Cell cellB = new Cell();
        Cell cellC = new Cell();
        Cell cellD = new Cell();
        cellD.setAlive(false);

        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(1,0));
        world.add(cellC,new Position(0,1));
        world.add(cellD,new Position(1,1));

        world.operate();

        assertTrue("Cell A",cellA.isAlive());
        assertTrue("Cell B",cellB.isAlive());
        assertTrue("Cell C",cellC.isAlive());
        assertTrue("Cell D",cellD.isAlive());
    }

}


