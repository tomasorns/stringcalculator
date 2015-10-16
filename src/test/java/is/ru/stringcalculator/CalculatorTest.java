package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CalculatorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

	public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("is.ru.stringcalculator.CalculatorTest");
    }

	@Test
	public void testEmptyString() {
		assertEquals(0, Calculator.add(""));
	}

	@Test
	public void testOneNumber() {
		assertEquals(2, Calculator.add("2"));
	}

	@Test
	public void testTwoNumbers() {
		assertEquals(3, Calculator.add("1,2"));
	}	

	@Test
    public void testMultipleNumbers(){
    	assertEquals(6, Calculator.add("1,2,3"));
    }

    @Test
    public void testNewLineInput(){
        assertEquals(6, Calculator.add("1\n2,3"));
    }

    @Test
    public void testNewLineInputOne(){
        assertEquals(6, Calculator.add("1\n2,3"));
    }

    @Test
    public void testDifferentDelimiter(){
        assertEquals(6, Calculator.add("//;\n1;2;3"));
    }

    @Test
    public void testDifferentDelimiterWithNewLine(){
        assertEquals(6, Calculator.add("//;\n1;2\n3"));
    }

    @Test
    public void testNegativeNumbers(){
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Negatives not allowed: -4,-5");
        Calculator.add("2,-4,3,-5");
    }

    @Test
    public void testNegativeNumber(){
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Negatives not allowed: -2");
        Calculator.add("-2");
    }

    @Test
    public void testWhenNumberIsLargerThan1000(){
        assertEquals(3, Calculator.add("1003"));
    }

    @Test
    public void testWhenNumbersAreLargerThan1000(){
        assertEquals(6, Calculator.add("//;\n1001;1002\n1003"));
    }

    @Test
    public void testDelimiterOfAnyLength(){
        assertEquals(6, Calculator.add("//[;;;]\n1001;;;1003;;;2"));
        assertEquals(6, Calculator.add("//[******]\n1001******1003\n2"));
    }

    @Test
    public void testMultipleDelimiters(){
        assertEquals(7, Calculator.add("//[;;][%]\n1;;2%3\n1"));
        assertEquals(6, Calculator.add("//[*****][****]\n1*****2****3"));
        assertEquals(15, Calculator.add("//[;][**][%%%]\n1;2**3%%%4\n5"));
    }
}