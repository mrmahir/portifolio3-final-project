import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import java.awt.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import org.w3c.dom.Text;


public class GeniusView {
    InfoControl control;
    private GridPane StartView;
    Button exitB = new Button("Exit");
    Button FindGrades = new Button("Find Grades");
    TextArea BoxText= new TextArea();
    Label SelectStudent= new Label("Select Student");
    Label SelectCourse= new Label("Select Course");
    ComboBox<String> StudentCombo = new ComboBox<>();
    ComboBox<String> CourseCombo = new ComboBox<>();





    public GeniusView(InfoControl control) {
        this.control=control;
        CreateAndConfigure();
    }
    private void CreateAndConfigure(){
        StartView=new GridPane();
        StartView.setMinSize(300,200);
        StartView.setPadding(new Insets(10,10,10,10));
        StartView.setVgap(5);
        StartView.setHgap(1);

        StartView.add(StudentCombo,15,1);
        StartView.add(CourseCombo,15,3);

        StartView.add(SelectStudent,1,1);
        StartView.add(SelectCourse,1,3);
        StartView.add(BoxText,1,7,15,7);
        StartView.add(FindGrades,15,6);
        StartView.add(exitB,20,15);



        ObservableList<String> CourseList= control.getCourses();
        CourseCombo.setItems(CourseList);

        ObservableList<String> studentList= control.getStudent();
        StudentCombo.setItems(studentList);

    }

    public Parent asParent(){
        return StartView;
    }

}
