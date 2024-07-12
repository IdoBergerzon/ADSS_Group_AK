package Domain.HR;

public class Week {
    private static int weekNum=0;

    public Week(int week){
        weekNum=week;
    }
    public static int getWeek() {
        return weekNum;
    }

    public static void setWeek() {
        Week.weekNum += 1;
    }

}
