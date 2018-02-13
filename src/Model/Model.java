package Model;

import java.util.LinkedList;
import java.util.Observable;


public class Model{
	private String currentTotal;
	private String currentInputString;
	private CalcDisplayData update;

	public Model() {
		currentTotal = "0";
		currentInputString = "";
	}

	public void computeString(String currentInputString) {

		LinkedList<String> operationTokens = new StringParser(currentInputString).getTokens();

		MathOperationsList possibleOperations = new MathOperationsList();

		operationTokens = performMathInSequence(operationTokens, possibleOperations);

		boolean hasOnlyOneToken = (operationTokens.size() == 1);

		if (hasOnlyOneToken) {
			setCurrentTotal(operationTokens.get(0));
		} else {
			System.out.println("uhh.. something went wrong? LOL!");
		}
	}

	private LinkedList<String> performMathInSequence(LinkedList<String> operationTokens,
			MathOperationsList possibleOperations) {
		for (String operation : possibleOperations) {
			operationTokens = performOperations(operation, operationTokens);
		}
		return operationTokens;
	}

	private LinkedList<String> performOperations(String operation, LinkedList<String> tokens) {

		boolean isOperationCompleted = false;

		while (isOperationCompleted == false) {
			if (tokens.contains(operation)) {
				int operatorIndex = tokens.indexOf(operation);
				int firstOperandIndex = operatorIndex - 1;
				int secondOperandIndex = operatorIndex + 1;

				String firstOperand;
				if(firstOperandIndex==-1 && operation.equals("R")) 
					firstOperand="1";
				else
					firstOperand = tokens.get(firstOperandIndex);				
				
				String secondOperand = tokens.get(secondOperandIndex);
				
				float computationResult;

				// perform the relevant operation
				switch (operation) {
				case "R":
					computationResult = Float.parseFloat(firstOperand) * sqrt(Float.parseFloat(secondOperand));
					break;
				case "*":
					computationResult = mul(Float.parseFloat(firstOperand),Float.parseFloat(secondOperand));
					break;
				case "/":
					computationResult = div(Float.parseFloat(firstOperand),Float.parseFloat(secondOperand));
					break;
				
				case "-":
					computationResult = subt(Float.parseFloat(firstOperand),Float.parseFloat(secondOperand));
					break;
				case "+":
					computationResult = add(Float.parseFloat(firstOperand),Float.parseFloat(secondOperand));
					break;
				case "%":
					computationResult = (int)(mod(Float.parseFloat(firstOperand),Float.parseFloat(secondOperand)));
					break;
				default:
					computationResult = 0.0f;
					System.out.println("Cannot detect operation");
					break;
				}

				// cast the operation back into a String
				String tokenizedComputation = Float.toString(computationResult);

				// remove all relevant tokens
				tokens.remove(secondOperandIndex);
				tokens.remove(operatorIndex);
				if(firstOperandIndex!=-1)
					tokens.remove(firstOperandIndex);

				// place relevant token into relevant position
				if(firstOperandIndex!=-1)
				tokens.add(firstOperandIndex, tokenizedComputation);
				else
					tokens.add(firstOperandIndex+1, tokenizedComputation);

			} else {
				isOperationCompleted = true;
				return tokens;
			}
		}
		return tokens;
	}

	public void Clear() {
		currentTotal = "0";
		currentInputString = "";

		CalcDisplayData update = new CalcDisplayData();
		update.setComputationText(currentInputString);
		update.setCurrentTotal(currentTotal);

	}

	public void setComputationText(String newInputString) {
		currentInputString = newInputString;

		CalcDisplayData update = new CalcDisplayData();
		update.setComputationText(newInputString);

	}

	public void setCurrentTotal(String newTotal) {
		float floatTotal = Float.parseFloat(newTotal);
		int intTotal = (int) floatTotal;

		setCurrentTotalAsIntValueIfPossible(floatTotal, intTotal);

		update = new CalcDisplayData();
		update.setCurrentTotal(currentTotal);

	}
	public String getResult() {
		return update.getCurrentTotal();
	}

	private void setCurrentTotalAsIntValueIfPossible(float floatTotal, int intTotal) {
		if (floatTotal == intTotal) {
			currentTotal = Integer.toString(intTotal);
		} else {
			currentTotal = Float.toString(floatTotal);
		}
	}
	
	
	private float add(float augend, float addend) {
		return augend + addend;
	}

	private float subt(float minuend, float subtrahend) {
		return minuend - subtrahend;
	}

	private float mul(float multiplicand, float multiplier) {
		return multiplicand * multiplier;
	}

	private float div(float dividend, float divisor) throws ArithmeticException {
		return dividend / divisor;
	}

	private float mod(float dividend, float divisor) {
		return dividend % divisor;
	}

	private float sqrt(float radicand) {
		return (float) java.lang.Math.sqrt(radicand);
	}

}
