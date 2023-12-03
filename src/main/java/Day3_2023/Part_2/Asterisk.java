package Day3_2023.Part_2;

import java.util.ArrayList;
import java.util.List;

public class Asterisk {
    private final List<FullNumber> numbersNextToThisAsterisk = new ArrayList<>();

    public boolean twoAdjoiningNumbers(){
        return numbersNextToThisAsterisk.size() == 2;
    }
    public void add(FullNumber numberInGrid){
        numbersNextToThisAsterisk.add(numberInGrid);
    }
    public int factor(){
        return numbersNextToThisAsterisk.get(0).getFactor(numbersNextToThisAsterisk.get(1));
    }
}
