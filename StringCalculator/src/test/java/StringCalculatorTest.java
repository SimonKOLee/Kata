package test.java;

import main.java.StringCalculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {

    @Test
    public void testGivenEmptyStringWhenCalThenReturnZero() {
        int actualResult = StringCalculator.add("");
        assertEquals(0, actualResult);

    }

    @Test
    public void testGivenOneWhenCalThenReturnOne() {
        int actualResult = StringCalculator.add("1");
        assertEquals(1, actualResult);
    }


    @Test
    public void testGivenZeroWhenCalThenReturnZero() {
        int actualResult = StringCalculator.add("0");
        assertEquals(0, actualResult);
    }

    @Test
    public void testGivenTwoWhenCalThenReturnTwo() {
        String input = "2";
        int actualResult = StringCalculator.add(input);
        assertEquals(2, actualResult);
    }

    @Test
    public void testGivenOneTwoWhenCalThenReturnTwo() {
        String input = "1,2";
        int actualResult = StringCalculator.add(input);
        assertEquals(3, actualResult);
    }

//    @Test
//    public void testGivenEmptyEmptyWhenCalThenReturnZero() {
//        String input = ",";
//        int actualResult = StringCalculator.add(input);
//        assertEquals(0, actualResult);
//    }

    @Test
    public void testGivenOneTwoThreeWhenCalThenReturnSix(){
        String input = "1,2,3";
        int actualResult = StringCalculator.add(input);
        assertEquals(6, actualResult);
    }

    @Test
    public void testGivenOneTwoThreeFourWhenCalThenReturnTen(){
        String input = "1,2,3,4";
        int actualResult = StringCalculator.add(input);
        assertEquals(10, actualResult);
    }

    @Test
    public void testGivenOneTwoThreeWithNewLineAndCommaWhenCalThenReturnSix(){
        String input = "1\n2,3";
        int actualResult = StringCalculator.add(input);
        assertEquals(6, actualResult);
    }

    @Test
    public void testGivenOneTwoThreeWithCommaAndNewLineWhenCalThenReturnSix(){
        String input = "1,2\n3";
        int actualResult = StringCalculator.add(input);
        assertEquals(6, actualResult);
    }

    @Test
    public void testGivenDelimiterIsColonAndEmptyWhenCalThenReturnZero() {
        String input = "//:\n";
        int actualResult = StringCalculator.add(input);
        assertEquals(0, actualResult);
    }

    @Test
    public void testGivenDelimiterIsColonAndOneTwoWhenCalThenReturnThree(){
        String input = "//:\n1:2";
        int actualResult = StringCalculator.add(input);
        assertEquals(3, actualResult);
    }

    @Test
    public void testGivenDelimiterIsColonAndOneWhenCalThenReturnOne(){
        String input = "//:\n1";
        int actualResult = StringCalculator.add(input);
        assertEquals(1, actualResult);
    }

    @Test
    public void testGivenDelimiterIsNewlineAndOneWhenCalThenReturnOne(){
        String input = "//\n\n1";
        int actualResult = StringCalculator.add(input);
        assertEquals(1, actualResult);
    }

    @Test
    public void testGivenDelimiterIsNewlineAndOneTwoWhenCalThenReturnThree(){
        String input = "//\n\n1\n2";
        int actualResult = StringCalculator.add(input);
        assertEquals(3, actualResult);
    }

    @Test
    public void testGivenNegativeOneWhenCalThenThrowException(){
        String input = "-1";
    }
}
