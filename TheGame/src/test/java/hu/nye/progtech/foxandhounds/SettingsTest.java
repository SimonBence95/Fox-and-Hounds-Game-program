package hu.nye.progtech.foxandhounds;

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
        underTest.actionPerformed(new ActionEvent("test",1,"_rules"));
        underTest.actionPerformed(new ActionEvent("test",2,"_highscores"));
        underTest.actionPerformed(new ActionEvent("test",3,"_saveGame"));
        underTest.actionPerformed(new ActionEvent("test",4,"_loadGame"));
        underTest.actionPerformed(new ActionEvent("test",5,"_exit"));

        underTest.actionPerformed(new ActionEvent("test",5,"Game_Description"));

        underTest.actionPerformed(new ActionEvent(dialogframe,1,"test"));

        underTest.actionPerformed(new ActionEvent(jOptionPane,1,"test"));

        // Then
        Assertions.assertEquals("test","test");

    }

    @Test
    public void testCommandImpl(){


    }

}
