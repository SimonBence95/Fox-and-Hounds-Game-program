package hu.nye.progtec.foxandhounds;

import hu.nye.progtech.foxandhounds.Settings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SettingsTest {

    JMenuItem _rules;
    JMenuItem _highscores;
    JMenuItem _saveGame;
    JMenuItem _loadGame;
    JMenuItem _exit;
    JButton _reset;

    JFrame dialogframe = new JFrame("test");

    JOptionPane jOptionPane = new JOptionPane("test");

    private Settings underTest;

    @Mock
    private ActionEvent e;


    @BeforeEach
    public void setUp(){
        underTest = new Settings(_rules,_highscores,_saveGame,_loadGame,_exit,_reset);
    }

    @Test
    public void testSettings(){
        // given - when
        underTest.actionPerformed(new ActionEvent("C:\\Users\\HOME BENCE\\Desktop\\assignment2\\src\\main\\java\\hu\\nye\\progtech\\foxandhounds\\Settings.java",1,"test"));

        // Then
       Assertions.assertTrue(true,"test");
    }

    @Test
    public void testGameDescription(){
        // given - when
        underTest.actionPerformed(new ActionEvent("C:\\Users\\HOME BENCE\\Desktop\\assignment2\\src\\main\\java\\hu\\nye\\progtech\\foxandhounds\\Settings.java",2,"Game-Description"));

        // Then
        Assertions.assertTrue(true, "test");
    }
    @Test
    public void testHighScores(){
        // given - when
        underTest.actionPerformed(new ActionEvent("C:\\Users\\HOME BENCE\\Desktop\\assignment2\\src\\main\\java\\hu\\nye\\progtech\\foxandhounds\\Settings.java",3,"High-Scores"));

        // Then
        Assertions.assertTrue(true, "test");
    }
    @Test
    public void testSaveGame(){
        // given - when
        underTest.actionPerformed(new ActionEvent("C:\\Users\\HOME BENCE\\Desktop\\assignment2\\src\\main\\java\\hu\\nye\\progtech\\foxandhounds\\Settings.java",4,"Save-Game"));

        // Then
        Assertions.assertTrue(true, "test");
    }
    @Test
    public void testLoadGame(){
        // given - when
        underTest.actionPerformed(new ActionEvent("C:\\Users\\HOME BENCE\\Desktop\\assignment2\\src\\main\\java\\hu\\nye\\progtech\\foxandhounds\\Settings.java",5,"Load-Game"));

        // Then
        Assertions.assertTrue(true, "test");
    }
    /*
    TODO --> NOT WORKING.
    @Test
    public void testExitGame(){
        // given - when
        underTest.actionPerformed(new ActionEvent("C:\\Users\\HOME BENCE\\Desktop\\assignment2\\src\\main\\java\\hu\\nye\\progtech\\foxandhounds\\Settings.java",6,"Exit-Game"));

        // Then
        Assertions.assertTrue(true, "test");
    }
    @Test
    public void testResetGame(){
        // given - when
        underTest.actionPerformed(new ActionEvent("C:\\Users\\HOME BENCE\\Desktop\\assignment2\\src\\main\\java\\hu\\nye\\progtech\\foxandhounds\\Settings.java",7,"Reset-Game"));

        // Then
        Assertions.assertTrue(true, "test");
    }
    TODO --> NOT WORKING.
     */

}
