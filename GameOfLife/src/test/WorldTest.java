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
    public void testGiven1x1WorldAnd1CellWhenTheCellLiveInTheWorldThenTheCellShouldBeDead(){
        World world = new World(1,1);
        Cell cell = new Cell();
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
    public void testGiven1x2WorldAnd1CellWhenTheCellLiveInTheWorldThenTheCellShouldBeDead(){
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
    public void testGiven1x2WorldAnd2CellAt00And01WhenTheWorldOperateThen2CellsShouldBeDead(){
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
    public void testGiven1x3WorldAndCellA00AndCellB01AndCellC02WhenTheWorldOperateThenCellADeadCellBAliveCellCDead(){
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
     * Given a 2x1
     *
     */
}

