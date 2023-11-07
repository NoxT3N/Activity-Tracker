public class Activity implements Comparable<Activity> {
    private String type;
    private int duration;
    private String date;
    private double distance;
    private int averageHeartRate;
    IntensityState intensity;
    private double intensityValue;

    //constructors
    public Activity() {
    }

    public Activity(String type, int duration, String date, double distance, int averageHeartRate) {
        this.type = type;
        this.duration = duration;
        this.date = date;
        this.distance = distance;
        this.averageHeartRate = averageHeartRate;
        calcIntensity();
    }

    //getters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    //setters

    public void setDate(String date) {
        this.date = date;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getAverageHeartRate() {
        return averageHeartRate;
    }

    public void setAverageHeartRate(int averageHeartRate) {
        this.averageHeartRate = averageHeartRate;
    }

    public void calcIntensity(){
        IntensityState intensity;
        double kph = distance/(Math.round((double)duration/60));
        double[] swimKPH ={0.5,1.25,2,2.75,3.5};
        double[] swimIntensity ={5,6.3,7.6,8.9,10.2};

        double[] runKPH ={4,8,12,16,24};
        double[] runIntensity ={4.1,7.2,10,15.4,20.8};

        double[] cyclingKPH ={8,16,25,33,40};
        double[] cyclingIntensity ={2,5,7,13,15};

        double[] choiceA = new double[5];
        double[] choiceB = new double[5];

        switch (type){
            case "Swimming":
                System.arraycopy(swimKPH,0,choiceA,0,swimKPH.length);
                System.arraycopy(swimIntensity,0,choiceB,0,swimIntensity.length);
            break;
            case "Running" :
                System.arraycopy(runKPH,0,choiceA,0,runKPH.length);
                System.arraycopy(runIntensity,0,choiceB,0,runIntensity.length);
            break;
            case "Cycling" :
                System.arraycopy(cyclingKPH,0,choiceA,0,swimKPH.length);
                System.arraycopy(cyclingIntensity,0,choiceB,0,swimIntensity.length);
            break;
        }

        if(kph<choiceA[0]){
            intensityValue = choiceB[0];
            intensity = IntensityState.VERY_LIGHT;
        }
        else if(kph>choiceA[0]&& kph < choiceA[1]){
            intensityValue = choiceB[1];
            intensity = IntensityState.LIGHT;
        }
        else if(kph>choiceA[1]&& kph <choiceA[2]){
            intensityValue = choiceB[2];
            intensity = IntensityState.MODERATE;
        }
        else if(kph>choiceA[2]&& kph <choiceA[3]){
            intensityValue = choiceB[3];
            intensity = IntensityState.VIGOROUS;
        }
        else if(kph>choiceA[3]&& kph <choiceA[4]){
            intensityValue = choiceB[4];
            intensity = IntensityState.VERY_VIGOROUS;
        }
    }

    public double calcCaloriesBurned(){
        return intensityValue*duration;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "type='" + type + '\'' +
                ", duration=" + duration +
                ", date='" + date + '\'' +
                ", distance=" + distance +
                ", averageHeartRate=" + averageHeartRate +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if ( o == null || getClass() != o.getClass()) return false;
        Activity activity  = (Activity) o;
        return type.equals(activity.type) &&
                duration == activity.duration &&
                date.equals(activity.date) &&
                distance == activity.distance &&
                averageHeartRate == activity.averageHeartRate;
    }
    @Override
    public int compareTo(Activity a){
        return this.duration - a.duration;
    }

}
