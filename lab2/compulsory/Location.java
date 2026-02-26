public class Location {

    private String locationName;
    private String locationType;
    private float x, y;

    public Location(String locationName, String locationType, float x, float y) {
        this.locationName = locationName;
        this.locationType = locationType;
        this.x = x;
        this.y = y;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Location: " +
                "Name:" + locationName  +
                ", Type:" + locationType +
                ", Coordinates:" + x +
                ", " + y;
    }
}
