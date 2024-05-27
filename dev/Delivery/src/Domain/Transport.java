package Domain;

import java.sql.Time;
import java.util.Date;

public class Transport {
    private int transportID;
    private Date date;
    private Time timeOfDepurture;
    private int trackNumber;
    private String driversName;
    private Supplier source;
    private Store destination;
}
