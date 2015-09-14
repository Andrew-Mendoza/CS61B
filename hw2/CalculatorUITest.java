import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorUITest
{
	@Test
	public void testIsValidEquation()
	{
		CalculatorUI cUI = new CalculatorUI();
		assertEquals(true, cUI.isValidEquation("3 + -4"));
		assertEquals(true, cUI.isValidEquation("-3 * -4"));
		assertEquals(false, cUI.isValidEquation("3 = -4"));
		assertEquals(false, cUI.isValidEquation("3 + -a"));
		assertEquals(false, cUI.isValidEquation("3 +"));
	}

	public static void main(String... args)
	{
		jh61b.junit.textui.runClasses(CalculatorUITest.class);
	}
}