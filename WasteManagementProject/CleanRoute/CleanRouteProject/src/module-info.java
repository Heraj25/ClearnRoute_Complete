module ZelineProjectServices {
		requires javafx.controls;	
		requires javafx.fxml;
		requires java.sql;
		requires java.desktop;
		requires javafx.graphics;
		opens application to javafx.graphics, javafx.fxml;
		opens Page1 to javafx.graphics, javafx.fxml;
}
