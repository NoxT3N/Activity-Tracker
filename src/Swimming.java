public class Swimming extends Activity{
    private  final double[] kph ={0.5,1.25,2,2.75,3.5};
    private final double[] intensityValues ={5,6.3,7.6,8.9,10.2};
    IntensityState intensityState;
    private double intensityNum;
    public Swimming(String type, int duration, String date, double distance, int averageHeartRate) {
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
