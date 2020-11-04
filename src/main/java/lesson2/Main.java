package lesson2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Main {

    private static Connection conn;
    private static Statement stmt;
    private static PreparedStatement pstmt;

    public static void main(String[] args) {
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            createTable();
            Student[] students = new Student[4];
            students[0] = new Student("Bob1", "80");
            students[1] = new Student("Bob2", "100");
            students[2] = new Student("Bob3", "70");
            students[3] = new Student("Bob4", "90");
            int[] ids = new int[4];
            for (int i = 0; i < students.length; i++) {
                ids[i] = insertRec(students[i]);
            }
            for (int i = 0; i < ids.length; i++) {
                System.out.println(selectRec(ids[i]));
            }
            System.out.println();
            deleteRec(ids[3]);
            updateFromFile("test.txt");
            for (int i = 0; i < ids.length; i++) {
                System.out.println(selectRec(ids[i]));
            }
            deleteTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        disconnect();
    }

    public static void createTable() throws SQLException {
        stmt.executeUpdate("CREATE TABLE if not exists students (\n" +
                "    id    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name  TEXT,\n" +
                "    score TEXT\n" +
                ");");
    }

    //возвращает id добавленной записи
    public static int insertRec(Student student) throws SQLException {
        stmt.executeUpdate(String.format("insert into students (name, score) VALUES ('%s', '%s');", student.getName(), student.getScore()));
        ResultSet rs = stmt.executeQuery("select max(id) from students;");
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return 0;
        }
    }

    //получение записи по id
    public static Student selectRec(int id) throws SQLException {
        ResultSet rs = stmt.executeQuery(String.format("select name, score from students where id = '%d';", id));
        if (rs.next()) {
            //Кажется, на лекции прозвучало, что getString может принимать только название колонки.
            //Посмотрела, и getString и getInt и методы для остальных типов могут принимать как название, так и индекс.
            //Верно?
            return new Student(rs.getString(1), rs.getString(2));
        } else {
            return null;
        }
    }

    //удаление записи по id
    public static void deleteRec(int id) throws SQLException {
        stmt.executeUpdate(String.format("delete from students where id = '%d';", id));
    }

    public static void deleteTable() throws SQLException {
        stmt.executeUpdate("drop table if exists students;");
    }

    public static void updateFromFile(String fileName) throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        pstmt = conn.prepareStatement("update students set score = ? where name = ?;");
        while ((line = br.readLine()) != null) {
            String[] arrLine = line.split(" ");
            pstmt.setString(1, arrLine[2]);
            pstmt.setString(2, arrLine[1]);
            pstmt.addBatch();
        }
        pstmt.executeBatch();
    }

    private static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = conn.createStatement();
    }

    private static void disconnect() {
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
