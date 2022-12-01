package hu.nye.progtech.foxandhounds;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class to manage GUI actions.
 * */

@Configuration
public class Settings implements ActionListener {
    @Autowired
    private JMenuItem jMRules;
    @Autowired
    private JMenuItem jMHighScores;
    @Autowired
    private JMenuItem jMSave;
    @Autowired
    private JMenuItem jMLoad;
    @Autowired
    private JMenuItem jMExit;
    @Autowired
    private JButton rjMReset;



    @Autowired
    public Settings(JMenuItem _rules, JMenuItem _highScores, JMenuItem _saveGame, JMenuItem _loadGame, JMenuItem _exit, JButton _reset) {
        jMRules = _rules;
        jMHighScores = _highScores;
        jMSave = _saveGame;
        jMLoad = _loadGame;
        jMExit = _exit;
        rjMReset = _reset;

    }

    @Autowired
    String rules_Msg = (
            "Fox and Hounds ~ gameRules" + "\n" + "\n" + "This version (also called \"Wolf and Sheep\", " +
                    "\"Hounds and Hare\", or \"Devil and Tailors\") is played on an 8×8 chess/checkerboard." + "\n" +
                    "As in draughts, only the dark squares are used. The four hounds are initially placed" +
                    " on the dark squares at one edge of the board;" + "\n" +
                    "the fox is placed on any dark square on the opposite edge. " +
                    "The objective of the fox is to cross from one side of the board to the other, " + "\n" +
                    "arriving at any one of the hounds' original squares; the hounds' " +
                    "objective is to prevent it from doing so." + "\n" +
                    "The hounds move like a draughts man, diagonally forward one square. " +
                    "\n" + "The fox moves like a draughts king, diagonally forward or backward on square" + "\n" +
                    "However, there is no jumping, promotion, or removal of pieces. " +
                    "The play alternates with the fox moving first. " + "\n" +
                    "The player controlling the hounds may move only one of them each turn." + "\n" +
                    "The fox is trapped when it can no longer move to a vacant square. " +
                    "It is possible for two hounds to trap the fox against an edge of the board" + "\n" +
                    "(other than their original home-row) or even one corner (see diagram) where a single hound may do the trapping." +
                    "\n" + "Should a hound reach the fox's original home row it will be unable to move further." + "\n" +
                    "Perfect play will result in a \"hounds\" victory, even if the fox is allowed to choose any starting square" +
                    "\n" + " and to pass his turn once during the game, as demonstrated in Winning Ways."
    );

    @Autowired
    String high_Scores = ("High Scores - TODO");

    @Autowired
    String save_Game = ("TODO");

    @Autowired
    String load_Game = ("TODO");


    /**
     * Proceed the proper action from the list when called.
     *
     * @param e the event to be processed
     */

    @Bean
    public void actionPerformed(ActionEvent e) {
        String actionString = e.getActionCommand();
        if (actionString.equalsIgnoreCase("Game-Description")) {
            JFrame dialogframe = new JFrame("Fox And Hounds Rules");
            JOptionPane.showMessageDialog(dialogframe, rules_Msg);
        } else if (actionString.equalsIgnoreCase("High-Scores")) {
            JFrame dialogframe = new JFrame("Fox And Hounds High Scores");
            JOptionPane.showMessageDialog(dialogframe, high_Scores);
        } else if (actionString.equalsIgnoreCase("Save-Game")) {
            JFrame dialogframe = new JFrame("Fox And Hounds Save Game");
            JOptionPane.showMessageDialog(dialogframe, save_Game);
        } else if (actionString.equalsIgnoreCase("Load-Game")) {
            JFrame dialogframe = new JFrame("Fox And Hounds Load Game");
            JOptionPane.showMessageDialog(dialogframe, load_Game);
        } else if (actionString.equalsIgnoreCase("Exit-Game")) {
            System.exit(0);
        } else if (actionString.equalsIgnoreCase("Reset-Game")) {
            Gui.boardStarter();
            Gui.imgPlacer();
        }


    }

}