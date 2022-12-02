package hu.nye.progtech.foxandhounds;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Graphical User Interface for fox and hounds program.
 */
@Configuration
public class Gui extends JFrame {

    @Autowired
    private JFrame jFrame;
    @Autowired
    public static JButton[][] jButtons;
    @Autowired
    private JMenuBar jMenuBar;
    @Autowired
    private JMenu jMenu;
    @Autowired
    private JMenuItem jMRules;
    @Autowired
    private JMenuItem jMExit;
    @Autowired
    private JMenuItem jMHighScores;
    @Autowired
    private JMenuItem jMSave;
    @Autowired
    private JMenuItem jMLoad;
    @Autowired
    private JButton jBReset;
    @Autowired
    private JLabel jLFox;
    @Autowired
    private JLabel jLHounds;
    @Autowired
    static JPanel jPGrid;
    @Autowired
    private static int figure = 1;
    @Autowired
    private static int[][] gameBoard = new int[8][8];
    @Autowired
    private static int fHound = 1;
    @Autowired
    private static int fFox = 2;
    @Autowired
    public static JRadioButton figure1, figure2;

    @Autowired
    String f1;
    @Autowired
    String f2;

    @Autowired
    public Gui() {

        setSize(600, 600);
        setLocation(350, 100);

        JFrame jFrame = new JFrame("Fox And Hounds");
        JFrame jFDescription = new JFrame();

        jMRules = new JMenuItem("Game-Description");
        jMHighScores = new JMenuItem("High-Scores");
        jMSave = new JMenuItem("Save-Game");
        jMLoad = new JMenuItem("Load-Game");
        jMExit = new JMenuItem("Exit-Game");


        Settings jMSettings = new Settings(jMRules, jMHighScores, jMSave, jMLoad, jMExit, jBReset);
        jMRules.addActionListener(jMSettings);
        jMHighScores.addActionListener(jMSettings);
        jMSave.addActionListener(jMSettings);
        jMLoad.addActionListener(jMSettings);
        jMExit.addActionListener(jMSettings);

        JMenu jMenu_Exit = new JMenu("Exit");
        jMenu_Exit.add(jMExit);

        JMenu jMenu = new JMenu("Options");
        jMenu.add(jMHighScores);
        jMenu.add(jMSave);
        jMenu.add(jMLoad);

        JMenu jMenu2 = new JMenu("Information");
        jMenu2.add(jMRules);

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu_Exit);
        jMenuBar.add(jMenu);
        jMenuBar.add(jMenu2);
        setJMenuBar(jMenuBar);

        JPanel jPController = new JPanel();
        JPanel jPFigure1 = new JPanel();
        jPController.setBackground(Color.white);

        figure1 = new JRadioButton("HOUNDS", true);
        ImageIcon iHoundIMG = new ImageIcon(
                "C:\\Users\\HOME BENCE\\Desktop\\assignment2\\src\\main\\resources\\img\\hound.png"
        );
        JLabel jLHound = new JLabel(iHoundIMG);
        JLabel jLFigure1 = new JLabel("Figure 1");
        jPFigure1.add(jLHound);
        jPFigure1.add(figure1);

        jPFigure1.setLayout(new BorderLayout());
        jPFigure1.add(jLHound, BorderLayout.WEST);
        jPFigure1.add(figure1, BorderLayout.EAST);
        jPFigure1.add(jLFigure1, BorderLayout.SOUTH);
        jPFigure1.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel jPFigure2 = new JPanel();
        figure2 = new JRadioButton("FOX", false);
        ImageIcon iFoxIMG = new ImageIcon(
                "C:\\Users\\HOME BENCE\\Desktop\\assignment2\\src\\main\\resources\\img\\fox.png"
        );
        JLabel foxLabel = new JLabel(iFoxIMG);
        JLabel foxplayer = new JLabel("Figure 2");
        jPFigure2.add(foxLabel);
        jPFigure2.add(figure2);

        jPFigure2.setLayout(new BorderLayout());
        jPFigure2.add(foxLabel, BorderLayout.WEST);
        jPFigure2.add(figure2, BorderLayout.EAST);
        jPFigure2.add(foxplayer, BorderLayout.SOUTH);
        jPFigure2.setBorder(BorderFactory.createLineBorder(Color.darkGray));

        JButton jBReset = new JButton("Reset-Game");
        jBReset.setBorder(BorderFactory.createRaisedBevelBorder());
        JPanel jPResetButton = new JPanel();
        jPResetButton.add(jBReset);
        jBReset.addActionListener(jMSettings);

        jPController.setLayout(new BoxLayout(jPController, BoxLayout.Y_AXIS));
        jPController.add(jPResetButton);
        jPController.add(jPFigure1);
        jPController.add(jPFigure2);


        jPController.setSize(new Dimension(100, 400));

        TitledBorder titledBorder = BorderFactory.createTitledBorder("FXNHS");
        titledBorder.setTitleJustification(TitledBorder.DEFAULT_JUSTIFICATION);
        titledBorder.setTitleColor(Color.BLACK);
        jPController.setBorder(titledBorder);

        add(jPController, BorderLayout.WEST);

        try {
            gridCreator();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        f1 = JOptionPane.showInputDialog("Enter hound figures name:");
        jLFigure1.setText(f1);
        jLFigure1.setBounds(100, 150,  125, 40);
        jLFigure1.setHorizontalAlignment(SwingConstants.CENTER);

        f2 = JOptionPane.showInputDialog("Enter fox figures name: ");
        foxplayer.setText(f2);
        foxplayer.setBounds(100, 100,  125, 40);
        foxplayer.setHorizontalAlignment(SwingConstants.CENTER);

    }

    /**
     * Generate Grid for Game Board.
     *
     */
    @Bean
    public void gridCreator() throws SQLException {

        jPGrid = new JPanel(new GridLayout(8, 8));

        jButtons = new JButton[8][8];
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Game-Field");
        titledBorder.setTitleJustification(TitledBorder.CENTER);

        titledBorder.setTitleColor(Color.BLACK);
        jPGrid.setBorder(titledBorder);
        add(jPGrid, BorderLayout.CENTER);

        for (int i = 0; i < 8; i++) {
            int k = i;
            for (int j = 0; j < 8; j++) {
                jButtons[i][j] = new JButton();
                jButtons[i][j].setBorderPainted(false);
                jButtons[i][j].setOpaque(true);
                gameBoard[i][j] = 0;
                k++;
                if (k % 2 == 0) {
                    jButtons[i][j].setBackground(Color.WHITE);
                } else {
                    jButtons[i][j].setBackground(Color.BLACK);
                }

                jButtons[i][j].addActionListener(new Main(i, j, figure));
                jPGrid.add(jButtons[i][j]);
            }
        }
        imgPlacer();
    }


    @Bean
    public void boardReseter() throws SQLException {
        gridCreator();
    }

    /**
     * Draw the game-board without figures using Gui.
     */
    @Bean
    public static void boardStarter() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard[i][j] = 0;
                jButtons[i][j].setIcon(null);
            }
        }
    }
    /**
     * Place the starter icons on game board with the img of fox and hounds.
     */

    @Bean
    public static void imgPlacer() {
        jButtons[0][1].setIcon(new ImageIcon(
                "C:\\Users\\HOME BENCE\\Desktop\\assignment2\\src\\main\\resources\\img\\hound.png"
        ));
        jButtons[0][3].setIcon(new ImageIcon(
                "C:\\Users\\HOME BENCE\\Desktop\\assignment2\\src\\main\\resources\\img\\hound.png"
        ));
        jButtons[0][5].setIcon(new ImageIcon(
                "C:\\Users\\HOME BENCE\\Desktop\\assignment2\\src\\main\\resources\\img\\hound.png"
        ));
        jButtons[0][7].setIcon(new ImageIcon(
                "C:\\Users\\HOME BENCE\\Desktop\\assignment2\\src\\main\\resources\\img\\hound.png"
        ));

        jButtons[7][4].setIcon(new ImageIcon(
                "C:\\Users\\HOME BENCE\\Desktop\\assignment2\\src\\main\\resources\\img\\fox.png"
        ));

        gameBoard[0][1] = fFox;
        gameBoard[0][3] = fFox;
        gameBoard[0][5] = fFox;
        gameBoard[0][7] = fFox;
        gameBoard[7][4] = fHound;
        figure = 1;
    }
    /**
     * Validate User object.
     *
     * @param i Current position of X axis.
     * @param j Current position of Y axis.
     * @param figureSelected Currently selected player.
     * @return Return point.
     */

    @Bean
    public static boolean userValidator(int i, int j, int figureSelected) {

        figure = figureSelected;
        boolean isPlayersTurn = false;

        switch (figure) {
            case 1:
                if (gameBoard[i][j] == fHound) {
                    isPlayersTurn = true;
                }
                break;
            case 2:
                if (gameBoard[i][j] == fFox) {
                    isPlayersTurn = true;
                }
                break;
        }
        return isPlayersTurn;
    }
    /**
     * Method to help switching player.
     *
     * @param currentFigure user object.
     *
     * @return return point.
     */

    @Bean
    public static int playerChanger(int currentFigure) {
        figure = currentFigure;

        if (figure == 1) {
            figure = 2;
        } else {
            figure = 1;
        }
        return figure;
    }
    /**
     * Method to check movements before executed for validate movements.
     *
     * @param currentI Current position of X axis.
     *
     * @param currentJ Current position of Y axis.
     *
     * @param nextI Move player On X axis.
     *
     * @param nextJ Move Player On Y axis.
     *
     * @param sFigure Move selected figure.
     *
     * @return return point.
     */

    @Bean
    public static boolean moveValidator(int currentI, int currentJ, int nextI, int nextJ, int sFigure) {
        int iFrom = currentI;
        int jFrom = currentJ;
        int iTo = nextI;
        int jTo = nextJ;
        figure = sFigure;

        boolean isMoveValid = false;

        switch (figure) {
            case 1:
                if (gameBoard[iFrom][jFrom] == fHound) {
                    if (gameBoard[iTo][jTo] != fHound) {
                        isMoveValid = true;
                    }
                }
                break;
            case 2:
                if (gameBoard[iFrom][jFrom] == fFox) {
                    if (gameBoard[iTo][jTo] != fFox) {
                        isMoveValid = true;
                    }
                }
                break;
        }
        return isMoveValid;
    }

    /**
     * Selected position if players can move on X and Y axis.
     */

    @Bean
    public static boolean positionChanger(int currentI, int currentJ, int nextI, int nextJ, int sFigure) {
        boolean isMoveValid = false;
        int iFrom = currentI;
        int jFrom = currentJ;
        int iTo = nextI;
        int jTo = nextJ;
        figure = sFigure;

        switch (figure) {
            case 1:
                if ((iFrom != iTo) && (jFrom != jTo)) {
                    if (((iFrom + 1) == iTo) || ((iFrom - 1) == iTo)) {
                        if ((jFrom == (jTo + 1)) || (jFrom == (jTo - 1))) {
                            if (gameBoard[iTo][jTo] == 0) {
                                gameBoard[iTo][jTo] = fHound;
                                gameBoard[iFrom][jFrom] = 0;
                                jButtons[iFrom][jFrom].setIcon(null);
                                jButtons[iTo][jTo].setIcon(new ImageIcon(
                                        "C:\\Users\\HOME BENCE\\Desktop\\assignment2\\src\\main\\resources\\img\\fox.png"
                                ));
                                isMoveValid = true;
                            }
                        }
                    }
                }
                break;
            case 2:
                if ((iFrom != iTo) && (jFrom != jTo)) {
                    if ((iFrom + 1) == iTo) {
                        if (((jFrom + 1) == jTo) || ((jFrom - 1) == jTo)) {
                            if (gameBoard[iTo][jTo] == 0) {
                                gameBoard[iTo][jTo] = fFox;
                                gameBoard[iFrom][jFrom] = 0;
                                jButtons[iFrom][jFrom].setIcon(null);
                                jButtons[iTo][jTo].setIcon(new ImageIcon(
                                        "C:\\Users\\HOME BENCE\\Desktop\\assignment2\\src\\main\\resources\\img\\hound.png"
                                ));
                                isMoveValid = true;
                            }
                        }
                    }
                }
                break;
        }
        return isMoveValid;
    }

    /**
     * Check positions if fox or hounds has won the game yet.
     *
     * @param currentI currentI Move player On X axis.
     *
     * @param currentJ Move Player On Y axis.
     *
     * @param _user Move selected player.
     *
     * @return return point.
     */
    @Bean
    public static boolean winValidator(int currentI, int currentJ, int _user) {
        boolean isGameFinished = false;
        int iFrom = currentI;
        int jFrom = currentJ;
        int i = 0;
        int j = 0;
        figure = _user;

        switch (figure) {
            case 1:
                if (iFrom == 0) {
                    isGameFinished = true;
                }
                break;
            case 2:

                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        if (gameBoard[x][y] == 1) {
                            i = x;
                            j = y;
                        }
                    }
                }
                if (j == 0) {
                    if ((gameBoard[i - 1][j + 1] == fFox) && (gameBoard[i + 1][j + 1] == fFox)) {
                        isGameFinished = true;
                    }
                } else if (j == 7) {
                    if ((gameBoard[i + 1][j - 1] == fFox) && (gameBoard[i - 1][j - 1] == fFox)) {
                        isGameFinished = true;
                    }
                } else {
                    if ((gameBoard[i + 1][j + 1] == fFox) && (gameBoard[i - 1][j + 1] == fFox)) {
                        if ((gameBoard[i - 1][j - 1] == fFox) && (gameBoard[i + 1][j - 1] == fFox)) {
                            isGameFinished = true;
                        }
                    }
                }
                break;
        }

        return isGameFinished;
    }
}
