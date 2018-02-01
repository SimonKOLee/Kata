package test;

import org.junit.Test;

import kata.Ecosystem;

import static org.junit.Assert.*;

public class EcosystemTest {
    /**
     * Given 3X3 environment and no living cells
     * When evolve
     * Then no living cells
     */
    @Test
    public void testGiven3X3AndNoLivingCellsWhenEvolveThenNoLivingCells(){
        int[][] cellEnv = new int[][]{
                { 0, 0, 0},
                { 0, 0, 0},
                { 0, 0, 0}
        };
        Ecosystem ecoSys = new Ecosystem(cellEnv);
        ecoSys.evolve();
        assertArrayEquals( cellEnv, ecoSys.getCellEnvironment() );

    }

    /**
     *Given 3x3 environment and a living cell at position(0,0)
     *When evolve
     *Then no living cells
     */
    @Test
    public void testGiven3X3AndlivingCellsAt00WhenEvolveThenNoLivingCells(){
        int[][] cellEnv = new int[][]{
                { 1, 0, 0},
                { 0, 0, 0},
                { 0, 0, 0}
        };
        Ecosystem ecoSys = new Ecosystem(cellEnv);
        ecoSys.evolve();
        int[][] expectedCellEnv = new int[][]{
                { 0, 0, 0},
                { 0, 0, 0},
                { 0, 0, 0}
        };
        assertArrayEquals(expectedCellEnv, ecoSys.getCellEnvironment() );
    }

    /**
     *Given 3x3 environment and a living cell at position(0,0) and (0,1)
     *When evolve
     *Then no living cells
     */
    @Test
    public void testGiven3X3AndlivingCellsAt00And01WhenEvolveThenNoLivingCells(){
        int[][] cellEnv = new int[][]{
                { 1, 1, 0},
                { 0, 0, 0},
                { 0, 0, 0}
        };
        Ecosystem ecoSys = new Ecosystem(cellEnv);
        ecoSys.evolve();
        int[][] expectedCellEnv = new int[][]{
                { 0, 0, 0},
                { 0, 0, 0},
                { 0, 0, 0}
        };
        assertArrayEquals(expectedCellEnv, ecoSys.getCellEnvironment() );
    }



}