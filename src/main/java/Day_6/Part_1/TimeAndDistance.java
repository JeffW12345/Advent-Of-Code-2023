package Day_6.Part_1;

import java.util.ArrayList;
import java.util.List;

public class TimeAndDistance {

    private final int raceDuration;
    private int raceDistanceToBeat;

    public TimeAndDistance(int time) {
        this.raceDuration = time;
    }
    public int getNumberOfWillingHoldTimes() {
        return calculateWinningHoldTimes().size();
    }

    private List<Integer> calculateWinningHoldTimes(){
        List<Integer> winningHoldTimes = new ArrayList<>();
        for(int holdTime = 1; holdTime < raceDuration; holdTime++){
            int distanceForHoldTime = getDistanceForHoldTime(holdTime);
            if(distanceForHoldTime > raceDistanceToBeat){
                winningHoldTimes.add(distanceForHoldTime);
            }
        }
        return winningHoldTimes;
    }

    private int getDistanceForHoldTime(int holdTime) {
        return (raceDuration - holdTime) * holdTime;
    }

    public void setRaceDistanceToBeat(int raceDistanceToBeat) {
        this.raceDistanceToBeat = raceDistanceToBeat;
    }
}
