module lab9 {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;
	exports application;
	// This fixes the exact error you are seeing right now:
    exports question2; 

    // You will ALSO need this line so JavaFX can inject your FXML fields:
    opens question2 to javafx.graphics, javafx.fxml;
    opens question1 to javafx.graphics, javafx.fxml;
    exports question1;
}
