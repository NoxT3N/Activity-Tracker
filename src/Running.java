public class Running extends Activity{
    private final double[] kph ={4,8,12,16,24};
    private final double[] intensityValues ={4.1,7.2,10,15.4,20.8};
    IntensityState intensityState;
    private double intensityNum;
    public Running(String type, int duration, String date, double distance, int averageHeartRate) {
        super(type, duration, date, distance, averageHeartRate);
        calcIntensity();
    }

    public void calcIntensity(){
        double kph = distance/(Math.round((double)duration/60));

        if(kph<this.kph[0]){
            intensityNum = intensityValues[0];
            intensityState = IntensityState.VERY_LIGHT;
        }
        else if(kph>this.kph[0]&& kph < this.kph[1]){
            intensityNum = intensityValues[1];
            intensityState = IntensityState.LIGHT;
        }
        else if(kph>this.kph[1]&& kph <this.kph[2]){
            intensityNum = intensityValues[2];
            intensityState = IntensityState.MODERATE;
        }
        else if(kph>this.kph[2]&& kph <this.kph[3]){
            intensityNum = intensityValues[3];
            intensityState = IntensityState.VIGOROUS;
        }
        else if(kph>this.kph[3]&& kph <this.kph[4]){
            intensityNum = intensityValues[4];
            intensityState = IntensityState.VERY_VIGOROUS;
        }
    }

    public double calcCaloriesBurned(){
        return intensityNum*duration;
    }
}
