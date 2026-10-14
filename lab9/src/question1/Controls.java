package question1;

import java.util.Observable;

import application.InvalidAverageException;
import application.InvalidIdException;
import application.InvalidNameException;
import application.InvallidAgeException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controls {
	ObservableList<Student> students;

	@FXML
	private TextField AGE_textField;

	@FXML
	private TextField AVERAGE_textFiled;

	@FXML
	private TextField ID_textField;

	@FXML
	private TextField NAME_textField;

	@FXML
	private Button addStudent;

	@FXML
	private Button showAverage;
	@FXML
	private Button tableBtn;
	@FXML
	private Button deleteBtn;
	@FXML
	private TableColumn<Student, Integer> IdColumn;
	@FXML
	private TableColumn<Student, Integer> ageColumn;

	@FXML
	private TableColumn<Student, Double> averageColumn;

	@FXML
	private TableView<Student> mainTable;

	@FXML
	private TableColumn<Student, String> nameColumn;

	/**
	 * Validates text field inputs, instantiates a new Student object, and appends it to the list upon successful input verification.
	 */
	@FXML
	void addNewStudent(ActionEvent event) {
		try {
			int studentId = Integer.parseInt(ID_textField.getText().trim());
			String name = NAME_textField.getText().trim();
			int age = Integer.parseInt(AGE_textField.getText().trim());
			double average = Double.parseDouble(AVERAGE_textFiled.getText().trim());
			Student newStudent;

			newStudent = new Student(studentId, name, age, average);

			students.add(newStudent);
			showMessage("new student", "new student add: " + newStudent.toString());

			ID_textField.clear();
			NAME_textField.clear();
			AGE_textField.clear();
			AVERAGE_textFiled.clear();
		} catch (NumberFormatException e) {
			showMessage("Input Error", "Please ensure ID, Age, and Average are numbers, and fields aren't empty.");
		} catch (InvalidAverageException | InvallidAgeException | InvalidIdException | InvalidNameException e) {
			showMessage("Validation Error", e.getMessage());
		}
	}
	/**
	 * Calculates and displays the mathematical average grade of all students currently stored in the system.
	 */
	@FXML
	void showAverage(ActionEvent event) {
		if (students == null || students.isEmpty()) {
			showError("Average of the students:", "the average doesnt, exist no data.");
			return;
		}
		int studentsListSize = students.size();
		double sum = 0;
		if (students != null) {
			for (Student student : students) {
				if (student != null) {
					sum += student.getAverage();
				}
			}
			double avg = sum / studentsListSize;
			showMessage("Average of the Students- ", "the average of the class is: " + avg);
		}
	}
	/**
	 * Displays a standard JavaFX informational dialogue pop-up containing the provided title and description message.
	 */
	private void showMessage(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.showAndWait();
	}
	/**
	 * Displays a standard JavaFX error dialogue pop-up alerting the user to invalid inputs or missing selections.
	 */
	private void showError(String title, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private int selectedId = -1;
	private String studentName = "";
	/**
	 * Configures the TableView columns to match Student properties, initializes the data list, and attaches a table selection listener.
	 */
	@FXML
	public void initialize() {
		IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
		averageColumn.setCellValueFactory(new PropertyValueFactory<>("average"));
		students = javafx.collections.FXCollections.observableArrayList();
		
		mainTable.setItems(students);

		mainTable.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
			if (newSelection != null) {
				selectedId = newSelection.getId();
				studentName = newSelection.getName();
			}
		});
	}
	/**
	 * Removes the currently selected student record from the table and underlying observable data list.
	 */
	@FXML
	public void deleteSelected(ActionEvent event) {
		if (selectedId == -1) {
			showError("none selected", "please selece a student first!");
		} else {
			for (Student student : students) {
				if (student.getId() == selectedId) {
					selectedId = -1;
					students.remove(student);
					showMessage("student deleted", "the selected student deleted: " + studentName);
					break;
				}
			}
		}

	}
}
