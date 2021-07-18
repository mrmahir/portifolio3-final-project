import java.sql.SQLData;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Database extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:C:/Users/Maho/IdeaProjects/portifolio3/src/StudentInfoInfo.db";
        StudentModel SM=new StudentModel(url);
        InfoControl control=new InfoControl(SM);
        GeniusView view= new GeniusView(control);
        control.setView(view);
        primaryStage.setTitle("GeniusUI");
        primaryStage.setScene(new Scene(view.asParent(), 600,475));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }




}


