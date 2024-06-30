package Domain;

public class Week {
    private static int weekNum=1;

    public static int getWeek() {
        return weekNum;
    }

    public static void setWeek() {
        Week.weekNum += 1;
    }

}
