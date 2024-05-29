package Data;

import Domain.License;

import java.util.ArrayList;
import java.util.List;

public class LicensesData {
    private List<License> licenses;

    public LicensesData() {
        this.licenses = new ArrayList<>();
    }

    public List<License> getLicenses() {
        return licenses;
    }

    public void addLicense(License license) {
        this.licenses.add(license);
    }
}
