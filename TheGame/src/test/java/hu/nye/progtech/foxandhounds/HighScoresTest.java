package hu.nye.progtech.foxandhounds;

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
        underTest.equals(HighScores.class);
        underTest.toString().equalsIgnoreCase("highscorer");
    }

}
