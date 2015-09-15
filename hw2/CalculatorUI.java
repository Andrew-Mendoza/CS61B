import java.lang.NumberFormatException;

public class CalculatorUI
{
	public static void main(String[] args)
	{
		Calculator c = new Calculator();

		while (true)
		{
			System.out.print("> ");
			String input = StdIn.readLine();

			if (input.equalsIgnoreCase("quit"))
				break;
			else if (input.equalsIgnoreCase("dump"))
				c.printAllHistory();
			else if (input.matches("history\\s++\\d++"))
			{
				String[] inputParts = input.split("\\s++");
				c.printHistory(Integer.parseInt(inputParts[1]));
			}
			else if (input.equalsIgnoreCase("undo"))
				c.undoEquation();
			else if (input.equalsIgnoreCase("clear"))
				c.clearHistory();
			else if (input.equalsIgnoreCase("sum"))
				System.out.println(c.cumulativeSum());
			else if (input.equalsIgnoreCase("product"))
				System.out.println(c.cumulativeProduct());
			else if (input.matches("\\d++\\s*[+*]\\s*-?\\d++\\s*"))
			{
				String equation = input.replaceAll("\\s", "");

				boolean add = true;
				int operatorIndex = equation.indexOf('+');
				if (operatorIndex == -1)
				{
					add = false;
					operatorIndex = equation.indexOf('*');
				}
				int x = Integer.parseInt(equation.substring(0, operatorIndex)),
				    y = Integer.parseInt(equation.substring(operatorIndex + 1, equation.length())),
				    result;

				if (add)
				{
					result = c.add(x, y);
					equation = x + " + " + y;
				}
				else
				{
					result = c.multiply(x, y);
					equation = x + " * " + y;
				}

				System.out.println(result);
				c.saveEquation(equation, result);
			}
			else
				System.out.println("Invalid command");
		}
	}

	/* getEquationResult() is a method which returns the result of
	 * an equation given in the form of;
	 *    operand operator operand
	 * i.e
	 *    3 * 4  or -2 + 3 
	 * @param equation is the string representation of the equation
	 *        to solve
	 * @return the result fo the equation */
	private static int getEquationResult(String equation, Calculator c)
	{
		String[] splitEquation = equation.split("\\s*");
		int lOperand = Integer.parseInt(splitEquation[0]),
		    rOperand = Integer.parseInt(splitEquation[2]);
		String operator = splitEquation[1];

		if (operator.equals("+"))
			return c.add(lOperand, rOperand);
		else
			return c.multiply(lOperand, rOperand);
	}
}