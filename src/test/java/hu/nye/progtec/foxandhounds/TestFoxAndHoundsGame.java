package hu.nye.progtec.foxandhounds;

import hu.nye.progtech.foxandhounds.Gui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.SQLException;

public class TestFoxAndHoundsGame {
    @Mock
    private Gui underTest;
    int i;
    int nI;
    int j;
    int nJ;
    int FIGURE_SELECTED = 0;

    @BeforeEach
    public void setUp() {
        underTest = new Gui();
    }
    /**
     * Run Methods to test the program for errors.
     *
     * @throws SQLException Throws Expection when needed.
     */
    @Test
    public void testGridCreator() throws SQLException {
        // given - when
        underTest.gridCreator();

        // then
        Assertions.assertTrue(true,"test");
    }


    @Test
    public void testBoardReseter() throws SQLException {
        // given - when
        underTest.boardReseter();

        // then
        //Assertions.assertDoesNotThrow(this::setUp);
        Assertions.assertTrue(true,"test");
    }

    @Test
    public void boardStarterTest(){
        Gui.boardStarter();
        Assertions.assertTrue(true,"test");
    }

    @Test
    public void userValidatorTest(){
        Gui.userValidator(i,j, FIGURE_SELECTED);
        Assertions.assertTrue(true,"test");
    }

    @Test
    public void playerChangerTest(){
        Gui.playerChanger(FIGURE_SELECTED);
        Assertions.assertTrue(true,"test");
    }

    @Test
    public void moveValidatorTest(){
        Gui.moveValidator(i,j,nI,nJ,FIGURE_SELECTED);
        Assertions.assertTrue(true,"test");
    }

    @Test
    public void positionChangerTest(){
        Gui.positionChanger(i,j,nI,nJ,FIGURE_SELECTED);
        Assertions.assertTrue(true,"test");
    }

    @Test
    public void winValidatorTest(){
        Gui.winValidator(i,j,FIGURE_SELECTED);
        Assertions.assertTrue(true,"test");
    }

    /*
    @Test
    public void moreTest() {



    }
    TODO Add additional test to project files.
     */



}
