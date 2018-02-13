package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.*;
import static org.junit.Test;

import Model.Model;
import application.Controller;
import junit.framework.TestCase;

public class TestingClass extends TestCase {
	private Controller Controller;
	private Model model;

	public TestingClass() {
		Controller = new Controller();
		model = new Model();
	}
	public void testingCalculator() {	

		testCase("5+8", "13");
		testCase("6-1", "5");
		testCase("3*3*3", "27");
		testCase("8/5", "1.6");
		testCase("64%7", "1");
		
		testCase("5%8/0", "Infinity");
		testCase("4*7+8-1000/15*23", "33.101448");
		testCase("R8", "2.828427");
		testCase("22/7", "3.142857");
		testCase("14R9", "42");
		
		testCase("9-15", "-6");
		testCase("6-1%2", "5");
		testCase("3*3*3/3/3/3", "1");
		testCase("8/5/0*23", "Infinity");
		testCase("0/0", "NaN");
		

	}

	public void testCase(String statement, String actual) {

		model.computeString(statement);
		String result = model.getResult();
		assertEqual(actual, result,0.0);

	}

}
