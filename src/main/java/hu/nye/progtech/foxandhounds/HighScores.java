package hu.nye.progtech.foxandhounds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


/**
 * Database test.
 */
@Configuration
public class HighScores {

    @Autowired
    private int id;
    @Autowired
    private String name;
    @Autowired
    private int point;

    @Autowired
    public HighScores(int id, String name, int point) {
        this.id = id;
        this.name = name;
        this.point = point;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HighScores{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", point=").append(point);
        sb.append('}');
        return sb.toString();
    }
}
