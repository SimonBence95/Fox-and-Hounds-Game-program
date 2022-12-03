package hu.nye.progtec.foxandhounds;

import hu.nye.progtech.foxandhounds.HighScores;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HighScoresTest {

    int age = 0;
    int id = 0;
    String name = "";

    private HighScores underTest;

    @BeforeEach
    public void setUp()
    {
        underTest = new HighScores(id,name,age);
    }

    @Test
    public void testUser(){
        // given
        String actual = "HighScores{id=0, name='', point=0}";
        String expected = underTest.toString();

        // when
        underTest.toString().equalsIgnoreCase("highscorer");

        // then
        Assertions.assertEquals(actual,expected);
    }

}
