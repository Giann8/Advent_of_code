import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

public class MapTest {


    @Mock   Guard guard;
    @InjectMocks List<List<PointedObjects>> map;
    void moveGuardTest(){
        List<PointedObjects> riga1 = new ArrayList<>();
        List<PointedObjects> riga2 = new ArrayList<>();
        riga1.add(new EmptyPlace(1,1));
        riga1.add(new EmptyPlace(2,1));
        riga1.add(new EmptyPlace(3,1));
        riga2.add(new StaticObject(1,2));
        riga2.add(new EmptyPlace(2,2,true));
        riga2.add(new EmptyPlace(3,2));
                 map = new ArrayList<>();
        map.add(riga1);
        map.add(riga2);




    }
}
