package Domain;

public class License {
    private String title;
    private double maxWeight;

    public License(String title, double maxWeight) {
        this.title = title;
        this.maxWeight = maxWeight;
    }
    public String getTitle() {return title;}
    public double getMaxWeight() {return maxWeight;}
    public void setTitle(String title) {this.title = title;}
    public void setMaxWeight(double maxWeight) {this.maxWeight = maxWeight;}


    @Override
    public String toString() {
        return "License{" +
                "title='" + title + '\'' +
                ", maxWeight=" + maxWeight +
                '}';
    }
}
