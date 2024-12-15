import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class GuardTest {

    @Test
    void createGuardTest() {
        Guard guard = new Guard(1, 2, "L");
        assertThat(guard.getColonna()).isEqualTo(1);
        assertThat(guard.getRiga()).isEqualTo(2);
        assertThat(guard.getDirection()).isEqualTo("L");
    }
    @Test
    void setTest() {
        Guard guard = new Guard(1, 2, "L");
        guard.setColonna(3);
        guard.setRiga(2);
        guard.changeDirection();
        assertThat(guard.getColonna()).isEqualTo(3);
        assertThat(guard.getRiga()).isEqualTo(2);
        assertThat(guard.getDirection()).isEqualTo("U");
    }

    @Test
    void nextMoveTest(){
        Guard guard = new Guard(2, 2, "L");
        EmptyPlace nextmove = guard.nextMove();
        assertThat(nextmove.getColonna()).isEqualTo(1);
        assertThat(nextmove.getRiga()).isEqualTo(2);
    }
}
