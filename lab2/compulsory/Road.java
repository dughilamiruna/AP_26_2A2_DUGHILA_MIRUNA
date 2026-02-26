public class Road {

    private String roadType;
    private float roadLenght;
    private int speedLimit;

    public Road(String roadType, float roadLenght, int speedLimit) {
        this.roadType = roadType;
        this.roadLenght = roadLenght;
        this.speedLimit = speedLimit;
    }

    public String getRoadType() {
        return roadType;
    }

    public void setRoadType(String roadType) {
        this.roadType = roadType;
    }

    public float getRoadLenght() {
        return roadLenght;
    }

    public void setRoadLenght(float roadLenght) {
        this.roadLenght = roadLenght;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    @Override
    public String toString() {
        return "Road: " +
                "Type:" + roadType  +
                ", Lenght:" + roadLenght +
                ", Limit:" + speedLimit;
    }
}
