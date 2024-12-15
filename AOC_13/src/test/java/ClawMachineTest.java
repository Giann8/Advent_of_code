import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.program.Bottone;
import org.program.ClawMachine;


import static org.assertj.core.api.Assertions.*;
import static org.program.Main.chooseBottone;

public class ClawMachineTest {
    @Test
    void reduceXTest() {
        Bottone bottonea = new Bottone(1L, 2L, 0, "A");

        ClawMachine.Coordinate coordinate = new ClawMachine.Coordinate(2, 3);
        ClawMachine.Coordinate coordinatechanged = coordinate.reduceXY(bottonea);
        assertThat(coordinatechanged.x()).isEqualTo(1);
        assertThat(coordinatechanged.y()).isEqualTo(1);
    }


    @ParameterizedTest
    @CsvSource({
            "94,34,22,67,true,8400,5400",
            "84,37,17,86,true,7870,6450",
            "69,23,27,71,true,0,0"

    })
    void getPrizeTest(long xbottonea, long ybottonea, long xbottoneb, long ybottoneb, boolean expected, long x,long y) {
        Bottone bottonea = new Bottone(xbottonea, ybottonea, 0, "A");
        Bottone bottoneb = new Bottone(xbottoneb, ybottoneb, 0, "B");

        Bottone[] bottoni = new Bottone[]{bottonea, bottoneb};

        ClawMachine clawMachine = new ClawMachine(x, y, bottoni);
        assertThat(clawMachine.getPrize()).isEqualTo(expected);

    }

    @ParameterizedTest
    @CsvSource({
            "94,34,22,67,80,40,8400,5400",
            "84,37,17,86,86,38,7870,6450"

    })
    void getPrizeButtonTest(long xbottonea, long ybottonea, long xbottoneb, long ybottoneb, long expected1, long expected2, int x, int y) {
        Bottone bottonea = new Bottone(xbottonea, ybottonea, 0, "A");
        Bottone bottoneb = new Bottone(xbottoneb, ybottoneb, 0, "B");
        Bottone[] bottoni = new Bottone[]{bottonea, bottoneb};
        ClawMachine clawMachine = new ClawMachine(x, y, bottoni);

        clawMachine.getPrize();

        assertThat(clawMachine.getBottoni()[0].calledtime()).isEqualTo(expected1);
        assertThat(clawMachine.getBottoni()[1].calledtime()).isEqualTo(expected2);
    }

    @ParameterizedTest
    @CsvSource({
            "67,21,26,66",
            "69,23,27,71",

    })
    void getPrizeexceptionTest(long xbottonea, long ybottonea, long xbottoneb, long ybottoneb) {
        Bottone bottonea = new Bottone(xbottonea, ybottonea, 0, "A");
        Bottone bottoneb = new Bottone(xbottoneb, ybottoneb, 0, "B");

        Bottone[] bottoni = new Bottone[]{bottonea, bottoneb};
        ClawMachine clawMachine = new ClawMachine(-1, 0, bottoni);
        assertThat(clawMachine.getPrize()).isEqualTo(false);

    }

    @Test
    void calledBottoneTes() {
        Bottone bottonea = new Bottone(1L, 2L, 0, "A");
        bottonea = bottonea.calledBottone();
        bottonea = bottonea.calledBottone();
        assertThat(bottonea.calledtime()).isEqualTo(2);
    }

    @ParameterizedTest
    @CsvSource({
            "17,86,84,37"
    })
    void chooseBottoniTest(long xbottonea, long ybottonea, long xbottoneb, long ybottoneb) {
        Bottone bottonea = new Bottone(xbottonea, ybottonea, 0, "A");
        Bottone bottoneb = new Bottone(xbottoneb, ybottoneb, 0, "B");
        Bottone[] bottoni = chooseBottone(bottonea, bottoneb);
        assertThat(bottoni).containsExactly(bottoneb, bottonea);
    }

    @ParameterizedTest
    @CsvSource({
            "84,37,17,86"
    })
    void chooseBottoniRightTest(long xbottonea, long ybottonea, long xbottoneb, long ybottoneb) {
        Bottone bottonea = new Bottone(xbottonea, ybottonea, 0, "A");
        Bottone bottoneb = new Bottone(xbottoneb, ybottoneb, 0, "B");
        Bottone[] bottoni = chooseBottone(bottonea, bottoneb);
        assertThat(bottoni).containsExactly(bottonea, bottoneb);
    }

    @Test
    void determinanteTest() {
        Bottone bottonea = new Bottone(3L, 1L, 0, "A");
        Bottone bottoneb = new Bottone(2L, 4L, 0, "B");
        Bottone[] bottoni = new Bottone[]{bottonea, bottoneb};
        ClawMachine clawMachine = new ClawMachine(-1, 0, bottoni);
        assertThat(clawMachine.determinante()).isEqualTo(10);
    }
    @ParameterizedTest
    @CsvSource({
            "94,34,22,67,280,8400,5400",
            "17,86,84,37,200,7870,6450",
            "69,23,27,71,0,0,0",
            "69,23,27,71,0,18641,10279",
            "26,66,67,21,0,12748,12176",
            "14,63,39,12,0,10905,702"

    })
    void getPrize2Test(long xbottonea, long ybottonea, long xbottoneb, long ybottoneb, long expected, int x, int y) {
        Bottone bottonea = new Bottone(xbottonea, ybottonea, 0, "A");
        Bottone bottoneb = new Bottone(xbottoneb, ybottoneb, 0, "B");

        Bottone[] bottoni = new Bottone[]{bottonea, bottoneb};

        ClawMachine clawMachine = new ClawMachine(x, y, bottoni);
        assertThat(clawMachine.getPrize2()).isEqualTo(expected);

    }

}
