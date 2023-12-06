/*ZELINE PROJECT SERVICES STRUCTURE VISIT US at: http://zelineprojectservices.store
If you have any queries contact at: +91 8824414850
To request new structure mail us at : tempmasterdel@gmail.com*/
package Page1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application 
{
 public void start(Stage primaryStage) 
   {
		try {
				Parent root=(Parent) FXMLLoader.load(getClass().getResource("Page1View.fxml"));

				Scene scene = new Scene(root,800,800);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
		    } 
		catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	public static void main(String[] args) 
	{
		launch(args);
	}
}



