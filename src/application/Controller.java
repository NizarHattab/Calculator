package application;

import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
	boolean operationCompleted = false;

	private Model model = new Model();
	private int operation;

	@FXML
	private Button oneBtn;

	@FXML
	private Button twoBtn;

	@FXML
	private Button threeBtn;

	@FXML
	private Button fourBtn;

	@FXML
	private Button fiveBtn;

	@FXML
	private Button sixBtn;

	@FXML
	private Button sevenBtn;

	@FXML
	private Button eightBtn;

	@FXML
	private Button nineBtn;

	@FXML
	private Button zeroBtn;

	@FXML
	private Button dotBtn;

	@FXML
	private Button modBtn;

	@FXML
	private Button equalBtn;

	@FXML
	private Button plusBtn;

	@FXML
	private Button minusBtn;

	@FXML
	private Button mulBtn;

	@FXML
	private Button divBtn;

	@FXML
	private Button sqrtBtn;

	@FXML
	private TextField statementField;

	@FXML
	private Label resultLbl;

	@FXML
	private Button clearBtn;

	@FXML
	private Button clearElementBtn;

	@FXML
	public void print(ActionEvent e) {
		if(operationCompleted==true) {
			operationCompleted=false;
			Clear();
		}
		
		String toPrint = "";
		if (e.getSource() == oneBtn) {
			toPrint = statementField.getText() + "1";
		} else if (e.getSource() == clearBtn) {
			Clear();
		} else if (e.getSource() == zeroBtn) {

			toPrint = statementField.getText() + "0";
		} else if (e.getSource() == twoBtn) {
			toPrint = statementField.getText() + "2";
		} else if (e.getSource() == threeBtn) {
			toPrint = statementField.getText() + "3";
		} else if (e.getSource() == fourBtn) {
			toPrint = statementField.getText() + "4";
		} else if (e.getSource() == fiveBtn) {
			toPrint = statementField.getText() + "5";
		} else if (e.getSource() == sixBtn) {
			toPrint = statementField.getText() + "6";
		} else if (e.getSource() == sevenBtn) {
			toPrint = statementField.getText() + "7";
		} else if (e.getSource() == eightBtn) {
			toPrint = statementField.getText() + "8";
		} else if (e.getSource() == nineBtn) {
			toPrint = statementField.getText() + "9";
		}

		else if (e.getSource() == clearElementBtn) {
			if (statementField.getText().length() > 0)
				toPrint = statementField.getText().substring(0, statementField.getText().length() - 1);
		}

		// Operations
		else {
			if (!checkEntry()) {
				if (e.getSource() == plusBtn) {

					toPrint = statementField.getText() + "+";
				} else if (e.getSource() == minusBtn) {

					toPrint = statementField.getText() + "-";
				} else if (e.getSource() == mulBtn) {

					toPrint = statementField.getText() + "*";
				} else if (e.getSource() == divBtn) {

					toPrint = statementField.getText() + "/";
				} else if (e.getSource() == modBtn) {

					toPrint = statementField.getText() + "%";
				}
				else if (e.getSource() == sqrtBtn) {
					toPrint = statementField.getText() + "R";
				} else if (e.getSource() == dotBtn) {
					toPrint = statementField.getText() + ".";
				}
			} else
			{
				
				toPrint = statementField.getText();
			}
				
		}
		statementField.setText(toPrint);
		}
	
	

	@FXML
	public void Clear() {
		model.Clear();
		statementField.setText("");
		
	}

	private boolean checkEntry()
	// checks if the last character if the input mathematical statement is an
	// operator. returns true when yes. false otherwise
	{
		String stmt = statementField.getText();
		if (stmt.endsWith("+") || stmt.endsWith("-") || stmt.endsWith("*") || stmt.endsWith("/") || stmt.endsWith("%")
				|| stmt.endsWith("R") || stmt.endsWith(".") ) {
			return true;
		} else
			return false;

	}

	public String getResult() {
		if (!statementField.getText().isEmpty() && !checkEntry()) {
			model.computeString(statementField.getText());
			return model.getResult();
		} else
			return "ERROR";
	}

	@FXML
	public void displayResult() {
		if (operationCompleted == false) {
			resultLbl.setText(String.valueOf(getResult()));
			operationCompleted=true;
		}
	}
}