import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

import java.sql.SQLException;
import java.util.ArrayList;



public class InfoControl {
    StudentModel model;
    GeniusView view;
    public InfoControl(StudentModel model) {
        this.model = model;
        try {
            model.connect();
            model.CreateStatement();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void setView(GeniusView view) {
        this.view = view;
        view.exitB.setOnAction(e-> Platform.exit());
        EventHandler<ActionEvent> PrintGrades= e->HandlerPrintGrades(view.StudentCombo.getValue(),view.CourseCombo.getValue(),view.BoxText);
        view.FindGrades.setOnAction(PrintGrades);

    }
    public void HandlerPrintGrades(String Course,String StudentNavn, TextArea txtArea) {
        txtArea.clear();
            model.PreparedStmtPrintStudent();
            ArrayList<StudentNamess> StudNames = model.FindStudentData(StudentNavn,Course);
        System.out.println(StudNames.size());
        System.out.println(StudNames.size());
            for(int i =0;i<StudNames.size();i++){
                txtArea.appendText("Student Name: " + StudNames.get(i).StudentNavn + "\n" +
                        "Course Name: " + StudNames.get(i).Course + "\n" +
                        "Grade: " + StudNames.get(i).Grade + "\n");

        }

    }


    public ObservableList<String> getCourses(){
        ArrayList<String> Names= model.SQLQueryCourses();
        ObservableList<String>Course= FXCollections.observableList(Names);
        return Course;
    }

    public ObservableList<String> getStudent(){
        ArrayList<String> Names= model.SQLQueryStudentNames();
        ObservableList<String> StudentNames= FXCollections.observableList(Names);

        return StudentNames;
    }


}




