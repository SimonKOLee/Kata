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
     * When the world evolve
     * Then the cell should be dead
     */
    @Test
    public void testUnderPopulation_Given1x1WorldAnd1LiveCell_WhenTheWorldEvolve_ThenTheCellShouldBeDead(){
        World world = new World(1,1);
        Cell cell = new Cell();
        world.add(cell, new Position(0,0));
        world.evolve();
        assertFalse(cell.isAlive());
    }

    /**
     * Given a 1x1 world and a dead cell  in the world
     * When the world evolve
     * Then the cell should be dead
     */
    @Test
    public void testUnderPopulation_Given1x1WorldAnd1DeadCell_WhenTheWorldEvolve_ThenTheCellShouldBeDead(){
        World world = new World(1,1);
        Cell cell = new Cell();
        cell.setAlive(false);
        world.add(cell, new Position(0,0));
        world.evolve();
        assertFalse(cell.isAlive());
    }

    /**
     * Given a 1x2 world and a cell lives in world at position(0,0)
     * When the world evolve
     * Then the cell should be dead
     */
    @Test
    public void testUnderPopulation_Given1x2WorldAnd1LiveCell_WhenTheWorldEvolve_ThenTheCellShouldBeDead(){
        World world = new World(1,2);
        Cell cell = new Cell();
        world.add(cell,new Position(0,0));
        world.evolve();
        assertFalse(cell.isAlive());
    }

    /**
     * Given a 1x2 world and two cells live in world at position(0,0) and (0,1)
     * When the world evolve
     * Then two cells should be dead
     */
    @Test
    public void testUnderPopulation_Given1x2WorldAnd2LiveCellAt00And01_WhenTheWorldEvolve_Then2CellsShouldBeDead(){
        World world = new World(1,2);
        Cell cellA = new Cell();
        Cell cellB = new Cell();
        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(0,1));
        world.evolve();
        assertFalse(cellA.isAlive());
        assertFalse(cellB.isAlive());
    }

    /**
     * Given a 1x3 world and alive CellB at (0,1) has two alive neighbours CellA at (0,0) and CellC at (0,2)
     * When the world evolve
     * Then cellB should be alive; CellA and cellC should be dead
     */
    @Test
    public void testCellLiveWith2Neighbours_Given1x3WorldAndAliveCellB01With2AliveNeighboursCellA00AndCellC02_WhenTheWorldEvolve_ThenCellBAliveCellADeadCellCDead(){
        World world = new World(1,3);
        Cell cellA = new Cell();
        Cell cellB = new Cell();
        Cell cellC = new Cell();
        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(0,1));
        world.add(cellC,new Position(0,2));
        world.evolve();
        assertFalse(cellA.isAlive());
        assertTrue(cellB.isAlive());
        assertFalse(cellC.isAlive());
    }

    /**
     * Given a 1x3 world and alive CellB at (0,1) has two neighbours dead CellA at (0,0) and alive CellC at(0,2)
     * When the world evolve
     * Then all cell should be dead
     */
    @Test
    public void testCellLiveWith2Neighbours_Given1x3WorldAndLiveCellB01HasTwoNeighboursDeadCellA00AndLiveCellC02_WhenTheWorldEvolve_ThenAllCellDead(){
        World world = new World(1,3);
        Cell cellA = new Cell();
        cellA.setAlive(false);
        Cell cellB = new Cell();
        Cell cellC = new Cell();
        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(0,1));
        world.add(cellC,new Position(0,2));

        world.evolve();

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
    public void testAddCellToThePositionExceedTheMaxHeightOfTheWorld_ThenThrowException(){
        thrown.expect(PositionOutsideTheWorldException.class);
        World world = new World(1,1);
        Cell cell = new Cell();
        world.add(cell,new Position(0,1));
    }
    @Test
    public void testAddCellToThePositionLessThanTheMinHeightOfTheWorld_ThenThrowException(){
        thrown.expect(PositionOutsideTheWorldException.class);
        World world = new World(1,1);
        Cell cell = new Cell();
        world.add(cell,new Position(0,-1));
    }
    @Test
    public void testAddCellLivingToThePositionExceedTheMaxWidthOfTheWorld_ThenThrowException(){
        thrown.expect(PositionOutsideTheWorldException.class);
        World world = new World(1,1);
        Cell cell = new Cell();
        world.add(cell,new Position(1,0));
    }
    @Test
    public void testAddCellToThePositionLessThanTheMinWidthOfTheWorld_ThenThrowException(){
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
    public void testAddCellToThePositionOccupied_ThenThrowException(){
        thrown.expect(PositionOccupliedException.class);
        World world = new World(1,1);
        Cell cellA = new Cell();
        Cell cellB = new Cell();
        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(0,0));
    }

    /**
     * Given a 3x1 world and alive CellB at (1,0) has two alive neighbours CellA at (0,0) and CellC at(2,0)
     * When the world evolve
     * Then cellB should be alive; cellA and cellC should be dead
     */
    @Test
    public void testCellLiveWith2Neighbours_Given3x1WorldAndAliveCellB10With2AliveNeighboursCellA00AndCellC20_WhenTheWorldEvolve_ThenCellBAliveCellADeadCellCDead(){
        World world = new World(3,1);
        Cell cellA = new Cell();
        Cell cellB = new Cell();
        Cell cellC = new Cell();
        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(1,0));
        world.add(cellC,new Position(2,0));
        world.evolve();
        assertFalse(cellA.isAlive());
        assertTrue(cellB.isAlive());
        assertFalse(cellC.isAlive());
    }

    /**
     * Given a 3x1 world and alive CellB at (1,0) has two neighbours dead CellA at (0,0) and alive CellC at(2,0)
     * When the world evolve
     * Then all cell should be dead
     */
    @Test
    public void testCellLiveWith2Neighbours_Given3x1WorldAndLiveCellB10HasTwoNeighboursDeadCellA00AndLiveCellC20_WhenTheWorldEvolve_ThenAllCellDead(){
        World world = new World(3,1);
        Cell cellA = new Cell();
        cellA.setAlive(false);
        Cell cellB = new Cell();
        Cell cellC = new Cell();
        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(1,0));
        world.add(cellC,new Position(2,0));
        world.evolve();
        assertFalse(cellA.isAlive());
        assertFalse(cellB.isAlive());
        assertFalse(cellC.isAlive());
    }

    /**
     * Given a 2x2 world and  alive CellA at (0,0) has two alive neighbours CellB at (0,1) and CellC  at  (1,1)
     * When the world evolve
     * Then  all cells should be alive
     */
    @Test
    public void testCellLiveWith2Neighbours_Given2x2WorldAndAliveCellAt00HasTwoAliveNeighboursCellBAt01AndCellCAt11_WhenTheWorldEvolve_ThenAllCellsShouldBeAlive(){
        World world = new World(2,2);
        Cell cellA = new Cell();
        Cell cellB = new Cell();
        Cell cellC = new Cell();
        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(0,1));
        world.add(cellC,new Position(1,1));
        world.evolve();
        assertTrue(cellA.isAlive());
        assertTrue(cellB.isAlive());
        assertTrue(cellC.isAlive());
    }


    /**
     * Given a 2x2 world and  alive CellA at (0,0) has two alive neighbours CellB at (1,0) and CellC  at  (0,1)
     * When the world evolve
     * Then  all cells should be alive
     */
    @Test
    public void testCellLiveWith2Neighbours_Given2x2WorldAndAliveCellAt00HasTwoAliveNeighboursCellBAt10AndCellCAt01_WhenTheWorldEvolve_ThenAllCellsShouldBeAlive(){
        World world = new World(2,2);
        Cell cellA = new Cell();
        Cell cellB = new Cell();
        Cell cellC = new Cell();
        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(1,0));
        world.add(cellC,new Position(0,1));
        world.evolve();
        assertTrue(cellA.isAlive());
        assertTrue(cellB.isAlive());
        assertTrue(cellC.isAlive());
    }

    /**
     * Given a 2x2 world and  dead CellD live at (1,1) has 3 alive neighbours CellA at (0,0) and CellB at (1,0)  and CellC
     * When the world evolve
     * Then  all cells should be alive
     */
    @Test
    public void testReproduction_Given2x2WorldAndAliveCellDAt11Has3AliveNeighboursCellAAt00CellBAt10CellCAt01_WhenTheWorldEvolve_ThenAllCellsShouldBeAlive(){
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

        world.evolve();

        assertTrue(cellA.isAlive());
        assertTrue(cellB.isAlive());
        assertTrue(cellC.isAlive());
        assertTrue(cellD.isAlive());
    }
    /**
     * Given a 2x2 world and  alive CellA at (0,0)  has 3 alive neighbours CellB  at (1,0)  and CellC at (0,1) and CellD at (1,1)
     * When the world evolve
     * Then  all cells should be alive
     */
    @Test
    public void testCellLiveWith3Neighours_Given2x2WorldAndAliveCellAAt00Has3AliveNeighboursCellBAt10CellCAt01CellDAt11_WhenTheWorldEvolve_ThenAllCellsShouldBeAlive(){
        World world = new World(2,2);
        Cell cellA = new Cell();
        Cell cellB = new Cell();
        Cell cellC = new Cell();
        Cell cellD = new Cell();

        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(1,0));
        world.add(cellC,new Position(0,1));
        world.add(cellD,new Position(1,1));

        world.evolve();

        assertTrue(cellA.isAlive());
        assertTrue(cellB.isAlive());
        assertTrue(cellC.isAlive());
        assertTrue(cellD.isAlive());
    }


    /**
     * Given a 3x2 world and  alive CellE at (1,2)  has 4 alive neighbours CellA at (0,0) and CellB at  (1,0)  and CellC  at  (2,0) and CellD  at (1,1)
     * When the world evolve
     * Then  CellE and CellB should be dead;other cells should be alive
     */
    @Test
    public void testOvercrowding_Given3x2WorldAndAliveCellEAt11Has4AliveNeighboursCellAAt00CellBAt10CellCAt01CellDAt11_WhenTheWorldEvolve_ThenCellEAndCellBShouldBeDead(){
        World world = new World(3,2);
        Cell cellA = new Cell();
        Cell cellB = new Cell();
        Cell cellC = new Cell();
        Cell cellD = new Cell();
        Cell cellE = new Cell();

        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(1,0));
        world.add(cellC,new Position(2,0));
        world.add(cellD,new Position(0,1));
        world.add(cellE,new Position(1,1));

        world.evolve();

        assertTrue(cellA.isAlive());
        assertFalse(cellB.isAlive());
        assertTrue(cellC.isAlive());
        assertTrue(cellD.isAlive());
        assertFalse(cellE.isAlive());
    }

    /**
     * Given a 3x2 world and  alive CellE at (1,2)  has 5 alive neighbours CellA at (0,0) and CellB at  (1,0)  and CellC  at  (2,0) and CellD  at (1,1) and CellF at (2,1)
     * When the world evolve
     * Then  CellE and CellB should be dead;other cells should be alive
     */
    @Test
    public void testOvercrowding_Given3x2WorldAndAliveCellEAt11Has5AliveNeighboursCellAAt00CellBAt10CellCAt01CellDAt11CellFAt21_WhenTheWorldEvolve_ThenCellEAndCellBShouldBeDead(){
        World world = new World(3,2);
        Cell cellA = new Cell();
        Cell cellB = new Cell();
        Cell cellC = new Cell();
        Cell cellD = new Cell();
        Cell cellE = new Cell();
        Cell cellF = new Cell();

        world.add(cellA,new Position(0,0));
        world.add(cellB,new Position(1,0));
        world.add(cellC,new Position(2,0));
        world.add(cellD,new Position(0,1));
        world.add(cellE,new Position(1,1));
        world.add(cellF,new Position(2,1));

        world.evolve();

        assertTrue(cellA.isAlive());
        assertFalse(cellB.isAlive());
        assertTrue(cellC.isAlive());
        assertTrue(cellD.isAlive());
        assertFalse(cellE.isAlive());
        assertTrue(cellF.isAlive());
    }

    /**
     * Given a 1x1 world and no cell in the world
     * When display cell world
     * Then  "-" should be displayed
     */
    @Test
    public void testDiplayCellWorld_Given1x1WorldAndNoCell_WhenDiplayCellWorld_ThenEmpty1x1WorldShouldBeDisplayed(){
        World world = new World(1,1);
        String actualCellWorld = world.displayCellWorld();
        String expectedCellWorld = World.NO_CELL;
        assertEquals(expectedCellWorld,actualCellWorld);
    }

    @Test
    public void testDisplay3x3CellWorldWithFullAliveCells(){
        World world = new World(3,3);
        world.add(new Cell(),new Position(0,0));
        world.add(new Cell(),new Position(0,1));
        world.add(new Cell(),new Position(0,2));
        world.add(new Cell(),new Position(1,0));
        world.add(new Cell(),new Position(1,1));
        world.add(new Cell(),new Position(1,2));
        world.add(new Cell(),new Position(2,0));
        world.add(new Cell(),new Position(2,1));
        world.add(new Cell(),new Position(2,2));

        String expectedWorldMap = "1 1 1"+"\n"+"1 1 1"+"\n"+"1 1 1";
        assertEquals(expectedWorldMap,world.displayCellWorld());
    }
}


