
import com.gui.dbs.DBS;
import com.gui.form.LoginFrom;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application{
    public static void main (String [] arg){
     //   DBS.conn();
        launch(arg);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = new LoginFrom();
        primaryStage.show();
    }
}
