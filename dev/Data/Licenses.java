package Data;

import Domain.License;

import java.util.ArrayList;
import java.util.List;

public class Licenses {
    private List<License> licenses;

    public Licenses() {
        this.licenses = new ArrayList<>();
    }

    public List<License> getLicenses() {
        return licenses;
    }
    public void addLicense(License license) {
        this.licenses.add(license);
    }
}
