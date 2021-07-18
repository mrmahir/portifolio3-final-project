import StudentNames.StudentNames;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;



import static java.sql.DriverManager.getConnection;

public class StudentModel {
    Connection conn=null;
    String url;
    Statement stmt;
    ResultSet rs=null;
    PreparedStatement pstmt;

    StudentModel(String url){
        this.url=url;
    }
    public void connect() throws SQLException {
        conn=getConnection(url);
    }
    public void close() throws SQLException{
        if (conn != null)
        conn.close();
    }


    public void CreateStatement() throws SQLException{
        this.stmt= conn.createStatement();
    }
    public ArrayList<String> SQLQueryCourses(){
        ArrayList<String> Courses=new ArrayList<>();

        String sql;
        sql ="Select Course From Courses;";

        try {
            rs = stmt.executeQuery(sql);
            while(rs != null && rs.next()){
                String course = rs.getString(1);
                Courses.add(course);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Courses;
    }

    public ArrayList<String> SQLQueryStudentNames(){
        ArrayList<String> Names=new ArrayList<>();

        String sql;
        sql ="Select StudentNavn From StudentInfo;";

        try {
            rs = stmt.executeQuery(sql);
            while(rs != null && rs.next()){
                String name = rs.getString(1);
                Names.add(name);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Names;
    }

    public void PreparedStmtPrintStudent(){
//        String sql="SELECT G1.StudentID, S1.StudentNavn, C1.Course, G1.CoursesID, G1.Grades " +
//                "FROM Courses AS C1 JOIN Grades AS G1 ON C1.COUID = G1.CoursesID " +
//                "LEFT JOIN StudentInfo AS S1 ON G1.StudentID = S1.STUID " +
//                "WHERE G1.StudentID = ? AND G1.CoursesID = ?;";

        String sql = "SELECT * FROM Courses WHERE 1 = ?;";
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public ArrayList<StudentNamess> FindStudentData(String students, String courses) {
        PreparedStmtPrintStudent();
        ArrayList<StudentNamess> studentdatas = new ArrayList<>();
        try {
            pstmt.setString(1, "1");
//            pstmt.setString(2, courses);

            rs = pstmt.executeQuery();


            if (rs == null) {
                System.out.println("No data found, student does not exist");
            }


            while (rs != null && rs.next()) {

                studentdatas.add(new StudentNamess(rs.getString(1), rs.getString(2), (Integer) rs.getObject(3)));
                System.out.println(" The student name: " + rs.getString(1));
                System.out.println(" The Course name: " + rs.getString(2));
                System.out.println(" The students grade: " + rs.getObject(3));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentdatas;

    }

}

class StudentNamess {

    String StudentNavn;
    String Course;
    Integer Grade;


    public StudentNamess(String Course, String StudentNavn, Integer Grade) {

        this.Course = Course;
        this.StudentNavn = StudentNavn;
        this.Grade = Grade;

    }
}

