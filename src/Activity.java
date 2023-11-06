public class Activity implements Comparable<Activity> {
    protected String type;
    protected int duration;
    protected String date;
    protected double distance;
    protected int averageHeartRate;
    public double caloriesBurned;

    //IntensityState intensity;
    //protected double intensityValue;

    //constructors
    public Activity() {
    }

    public Activity(String type, int duration, String date, double distance, int averageHeartRate) {
        this.type = type;
        this.duration = duration;
        this.date = date;
        this.distance = distance;
        this.averageHeartRate = averageHeartRate;
        //this.intensity = calcIntensity();
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




//    public IntensityState calcIntensity(){
//        IntensityState intensity;
//        double kph = distance/(Math.round((double)duration/60));
//        double[] swimmingA ={0.5,1.25,2,2.75,3.5}; //kph
//        double[] swimmingB ={5,6.3,7.6,8.9,10.2}; //intensity values
//
//        double[] runningA ={4,8,12,16,24}; //kph
//        double[] runningB ={4.1,7.2,10,15.4,20.8}; //intensity values
//
//        double[] cyclingA ={8,16,25,33,40}; //kph
//        double[] cyclingB ={2,5,7,13,15}; //intensity values
//
//        double[] choiceA = new double[5];
//        double[] choiceB = new double[5];
//
//        switch (type){
//            case "Swimming":
//                System.arraycopy(swimmingA,0,choiceA,0,swimmingA.length);
//                System.arraycopy(swimmingB,0,choiceB,0,swimmingB.length);
//            break;
//            case "Running" :
//                System.arraycopy(runningA,0,choiceA,0,runningA.length);
//                System.arraycopy(runningB,0,choiceB,0,runningB.length);
//            break;
//            case "Cycling" :
//                System.arraycopy(cyclingA,0,choiceA,0,swimmingA.length);
//                System.arraycopy(cyclingB,0,choiceB,0,swimmingB.length);
//            break;
//        }
//
//        if(kph<choiceA[0]){
//            intensityValue = choiceB[0];
//            return intensity = IntensityState.VERY_LIGHT;
//        }
//        else if(kph>choiceA[0]&& kph < choiceA[1]){
//            intensityValue = choiceB[1];
//            return intensity = IntensityState.LIGHT;
//        }
//        else if(kph>choiceA[1]&& kph <choiceA[2]){
//            intensityValue = choiceB[2];
//            return intensity = IntensityState.MODERATE;
//        }
//        else if(kph>choiceA[2]&& kph <choiceA[3]){
//            intensityValue = choiceB[3];
//            return intensity = IntensityState.VIGOROUS;
//        }
//        else if(kph>choiceA[3]&& kph <choiceA[4]){
//            intensityValue = choiceB[4];
//            return intensity = IntensityState.VERY_VIGOROUS;
//        }
//        else{
//            return null;
//        }
//    }

//    public double calcCaloriesBurned(){
//        return intensityValue*duration;
//    }

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
