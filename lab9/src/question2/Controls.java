package question2;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Controls {

	@FXML
	private Button btn_divide;

	@FXML
	private Button btn_equal;

	@FXML
	private Button btn_minus;

	@FXML
	private Button btn_modulo;

	@FXML
	private Button btn_multiply;

	@FXML
	private Button btn_plus;

	@FXML
	private Button btn_power;

	@FXML
	private Button num_0;

	@FXML
	private Button num_1;

	@FXML
	private Button num_2;

	@FXML
	private Button num_3;

	@FXML
	private Button num_4;

	@FXML
	private Button num_5;

	@FXML
	private Button num_6;

	@FXML
	private Button num_7;

	@FXML
	private Button num_8;

	@FXML
	private Button num_9;

	@FXML
	private TextField screen;

	@FXML
	private Button btn_clear;

	@FXML
	private Button btn_del;
	String currentNumber = "";
	private boolean cleanScreen = true;

	// add a selected value(number) to the screen of the calculator.
	// and update the currentNumber string.
	@FXML
	void addNewTextToScreen(ActionEvent event) {
		if (cleanScreen) {
			Button clickedButton = (Button) event.getSource();

			String buttonText = clickedButton.getText();
			currentNumber += buttonText;
			screen.appendText(buttonText);
		}
	}

	// add a selected value(operator) to the screen of the calculator.
	@FXML
	void addOperationToScreen(ActionEvent event) {
		Button clickedButton = (Button) event.getSource();

		String buttonText = clickedButton.getText();

		screen.appendText(buttonText);

	}

	// declare 2 arrayList of numbers and operators for the final calculation.
	ArrayList<Double> numbers = new ArrayList<Double>();
	ArrayList<String> operators = new ArrayList<String>();

	// adds the operation and the number on the screen to the lists.
	@FXML
	void activateOperation(ActionEvent event) {
		if (cleanScreen) {
			Button clickedButton = (Button) event.getSource();
			String buttonText = clickedButton.getText();

			numbers.add(Double.parseDouble(currentNumber));
			System.out.println("added:" + Double.parseDouble(currentNumber));
			currentNumber = "";
			operators.add(buttonText);
			addOperationToScreen(event);
		}
	}

	// when the = button is pressed makes the final calculation
	// alerts the user and clears all of the fields.
	@FXML
	// 4 + 5 * 2
	// + *
	// 4 + 5
	void activateEqual(ActionEvent event) {
		if (currentNumber.equals("")) {
			numbers.add(0.0);
		}
		numbers.add(Double.parseDouble(currentNumber));
		double answer = numbers.get(0);
		for (int i = 1; i < numbers.size(); i++) {
			if (operators.get(i - 1).equals("+")) {
				answer += numbers.get(i);
			} else if (operators.get(i - 1).equals("-")) {
				answer -= numbers.get(i);
			} else if (operators.get(i - 1).equals("*")) {
				answer = answer * numbers.get(i);
			} else if (operators.get(i - 1).equals("/")) {
				if (numbers.get(i) == 0) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Important");
					alert.setContentText("cant divide with 0.");
					alert.showAndWait();
					break;
				}
				answer = answer / numbers.get(i);
			} else if (operators.get(i - 1).equals("%")) {
				answer = answer % numbers.get(i);
			} else if (operators.get(i - 1).equals("^")) {
				answer = Math.pow(answer, numbers.get(i));
			}
		}
		numbers.clear();
		operators.clear();
		screen.setText("");
		String finalAnswer = String.valueOf(answer);
		screen.setText(finalAnswer);
		currentNumber = "";
		cleanScreen = false;
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Important");
		alert.setContentText("Please clear the screen before trying again, otherwise it wont work.");
		alert.showAndWait();
	}

	// func for the Clear button to clear the screen back to empty string.
	@FXML
	void clearScreen(ActionEvent event) {
		screen.setText("");
		cleanScreen = true;
	}

	// delete one number from the screen
	@FXML
	void deleteOne(ActionEvent event) {
		if (cleanScreen) {
			String currentText = screen.getText();

			if (currentText != null && !currentText.isEmpty()) {
				String newText = currentText.substring(0, currentText.length() - 1);
				screen.setText(newText);
			}
		}

	}

}
