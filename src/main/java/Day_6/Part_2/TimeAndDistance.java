package Day_6.Part_2;

import java.util.ArrayList;
import java.util.List;

public class TimeAndDistance {

    private final long raceDuration;
    private long raceDistanceToBeat;

    public TimeAndDistance(long time) {
        this.raceDuration = time;
    }
    public long getNumberOfWillingHoldTimes() {
        return calculateWinningHoldTimes().size();
    }

    private List<Long> calculateWinningHoldTimes(){
        List<Long> winningHoldTimes = new ArrayList<>();
        for(int holdTime = 1; holdTime < raceDuration; holdTime++){
            long distanceForHoldTime = getDistanceForHoldTime(holdTime);
            if(distanceForHoldTime > raceDistanceToBeat){
                winningHoldTimes.add(distanceForHoldTime);
            }
        }
        return winningHoldTimes;
    }

    private long getDistanceForHoldTime(long holdTime) {
        return (raceDuration - holdTime) * holdTime;
    }

    public void setRaceDistanceToBeat(long raceDistanceToBeat) {
        this.raceDistanceToBeat = raceDistanceToBeat;
    }
}
