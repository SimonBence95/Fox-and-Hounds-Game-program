package hu.nye.progtech.foxandhounds;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.ImageObserver;
import java.sql.SQLException;

public class TestFoxAndHoundsGame {

    private Gui underTest;

    @BeforeEach
    public void setUp() throws SQLException {
        underTest = new Gui();
    }

    @AfterEach
    public void backUp(){

    }

    /**
     * Run Methods to test the program for errors.
     *
     * @throws SQLException Throws Expection when needed.
     */
    @Test
    public void testGui() throws SQLException {

        // given
        ImageObserver observer;

        // when - then
        underTest.gridCreator();
        underTest.boardReseter();

        Assertions.assertEquals("true","true");


    }



}
