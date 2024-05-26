package Domain;

public class Week {
    private static int weekNum=0;

    public static int getWeek() {
        return weekNum;
    }

    public static void setWeek() {
        Week.weekNum += 1;
    }

}
