package hu.nye.progtech.foxandhounds;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Controls game flow.
 */
@Configuration
public class Main implements ActionListener {

    @Autowired
    private static int nextStep = 1;
    @Autowired
    private int currentIPosition;
    @Autowired
    private int jPosition;
    @Autowired
    private static int currentI;
    @Autowired
    private static int currentJ;
    @Autowired
    private static int nextIPosition;
    @Autowired
    private static int moveY;
    @Autowired
    private static int user;
    @Autowired
    public int fWPoint;
    @Autowired
    public int hWPoint;

    @Autowired
    public Main(int i, int j, int sFigure) throws SQLException {
        currentIPosition = i;
        jPosition = j;
        user = sFigure;
    }

    /**
     * Perform steps.
     *
     * @param ae the event to be processed.
     */
    @Bean
    public void actionPerformed(ActionEvent ae) {

        switch (nextStep) {

            case 1:
                currentI = currentIPosition;
                currentJ = jPosition;
                System.out.print("Moved from [" + currentIPosition + "]" + "[" + jPosition + "]");

                switch (user) {
                    case 1:
                        if (Gui.userValidator(currentI, currentJ, user)) {
                            nextStep = 2;
                        } else {
                            JOptionPane.showMessageDialog(null, "Sorry, it is fox player's turn");
                        }
                        break;
                    case 2:
                        if (Gui.userValidator(currentI, currentJ, user)) {
                            nextStep = 2;
                        } else {
                            JOptionPane.showMessageDialog(null, "Sorry, it is hound player's turn");
                        }
                        break;
                }
                break;
            case 2:
                nextIPosition = currentIPosition;
                moveY = jPosition;
                System.out.println(" to [" + nextIPosition + "]" + "[" + moveY + "]");
                System.out.println();

                if (Gui.moveValidator(currentI, currentJ, nextIPosition, moveY, user)) {
                    if (Gui.positionChanger(currentI, currentJ, nextIPosition, moveY, user)) {
                        if (Gui.winValidator(nextIPosition, moveY, user)) {
                            String player = "";
                            if (user == 1) {
                                player = "fox";
                            } else {
                                player = "hound";
                            }

                            String message = "Player " + player + " has won the game. \nDo you want to play again?";
                            if (player.equals("fox")) {
                                fWPoint++;
                            } else {
                                hWPoint++;
                            }

                            int answer = JOptionPane.showConfirmDialog(
                                    null, message, "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
                            );

                            if (answer == JOptionPane.YES_OPTION) {
                                Gui.boardStarter();
                                Gui.imgPlacer();
                                user = 2;

                            } else if (answer == JOptionPane.NO_OPTION) {
                                System.exit(1);
                            }
                        }
                        user = Gui.playerChanger(user);
                        if (user == 1) {
                            Gui.figure1.setSelected(true);
                            Gui.figure2.setSelected(false);
                        } else {
                            Gui.figure1.setSelected(false);
                            Gui.figure2.setSelected(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid move");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid position");
                }
                nextStep = 1;
                break;
        }

    }

    /**
     * Entry Point Fox And Hounds Game.
     *
     * @param args set proper args.
     * @throws SQLException Throw Exception if necessary.
     */
    @Bean
    public static void main(String[] args) throws SQLException {

        System.out.println("Chose Command to execute ([start]/[highscore]/[deleteuser]/[userpoints])");

        Scanner scanner = new Scanner(System.in);

        String option = scanner.nextLine();


        switch (option) {
            case "start":
                new Gui();
                AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
                annotationConfigApplicationContext.scan(
                        "C:\\Users\\HOME BENCE\\Desktop\\hu_nye_progtech\\TheGame\\src\\main\\java\\hu\\nye\\progtech\\" +
                                "foxandhounds\\Gui.java"
                );
                break;
            case "highscore":
                Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/./highScores", "sa", "password");

                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM HIGHSCORES ");

                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String name = resultSet.getString("NAME");
                    int point = resultSet.getInt("POINT");

                    HighScores highScores = new HighScores(id, name, point);
                    System.out.println(highScores);
                }
                resultSet.close();
                statement.close();
                connection.close();
                break;
            case "deleteuser":
                //  TODO : ID MUST BE EDITABLE

                /*
                Connection connection2 = DriverManager.getConnection("jdbc:h2:tcp://localhost/./highScores", "sa", "password");
                Statement statement2 = connection2.createStatement();
                int i = statement2.executeUpdate("DELETE FROM HIGHSCORES WHERE ID = 1");
                System.out.println("Affected rows " + i);

                statement2.close();
                connection2.close();

                 */

                System.out.println("Please write in ID of row you want to delete from database:([ID])");
                Connection connection2 = DriverManager.getConnection("jdbc:h2:tcp://localhost/./highScores", "sa", "password");

                int id = scanner.nextInt();

                String deleteQuery = "DELETE FROM HIGHSCORES WHERE ID = ?";
                PreparedStatement preparedStatement = connection2.prepareStatement(deleteQuery);
                preparedStatement.setInt(1,id);

                int i = preparedStatement.executeUpdate();
                System.out.println(i);

                preparedStatement.close();
                connection2.close();
                break;

            case "userpoints":
                // TODO : BUILD THIS IN GAMEPLAY PROJECT

                System.out.println("Please write in name and point you want to store in the database:([name]),([point])");

                Connection connection3 = DriverManager.getConnection("jdbc:h2:tcp://localhost/./highScores", "sa", "password");

                String name = scanner.nextLine();
                int point = scanner.nextInt();

                // String insertQuery = "INSERT INTO HIGHSCORES (NAME, POINT) VALUES ('Teszt User', 32)";
                /*String insertQuery = "INSERT INTO HIGHSCORES (NAME, POINT) VALUES ('" + name + "'," + point + ")";
                statement = connection3.createStatement();
                int i1 = statement.executeUpdate(insertQuery);
                System.out.println(i1);
                 */

                String insertQuery = "INSERT INTO HIGHSCORES (NAME, POINT) VALUES (?, ?)";
                PreparedStatement preparedStatement2 = connection3.prepareStatement(insertQuery);
                preparedStatement2.setString(1,name);
                preparedStatement2.setInt(2,point);

                int i1 = preparedStatement2.executeUpdate();
                System.out.println(i1);

                preparedStatement2.close();
                connection3.close();
                break;

            default:
                System.out.println("incorrect command");

        }

    }
}