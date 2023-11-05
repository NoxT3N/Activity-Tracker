public class Activity implements Comparable<Activity> {
    private String type;
    private int duration;
    private String date;
    private double distance;
    private int averageHeartRate;
    IntensityState intensity;

    //constructors
    public Activity() {
    }

    public Activity(String type, int duration, String date, double distance, int averageHeartRate) {
        this.type = type;
        this.duration = duration;
        this.date = date;
        this.distance = distance;
        this.averageHeartRate = averageHeartRate;
        this.intensity = calcIntensity();
    }

    //getters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDuration() {
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

    public IntensityState calcIntensity(){
        IntensityState intensity;
        double kph = distance/(Math.round((double)duration/60));
        double[] swimming ={0.5,1.25,2,2.75,3.5};
        double[] running ={4,8,12,16,24};
        double[] cycling ={8,16,25,33,40};

        double[] choice = new double[5];

        switch (type){
            case "Swimming" -> System.arraycopy(swimming,0,choice,0,swimming.length);
            case "Running" -> System.arraycopy(running,0,choice,0,swimming.length);
            case "Cycling" -> System.arraycopy(cycling,0,choice,0,swimming.length);
        }

        if(kph<choice[0]){
            return intensity = IntensityState.VERY_LIGHT;
        }
        else if(kph>choice[0]&& kph < choice[1]){
            return intensity = IntensityState.LIGHT;
        }
        else if(kph>choice[1]&& kph <choice[2]){
            return intensity = IntensityState.MODERATE;
        }
        else if(kph>choice[2]&& kph <choice[3]){
            return intensity = IntensityState.VIGOROUS;
        }
        else if(kph>choice[3]&& kph <choice[4]){
            return intensity = IntensityState.VERY_VIGOROUS;
        }
        else{
            return null;
        }

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
