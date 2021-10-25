public class Main {

    public static void main(String[] args) throws InterruptedException {

        Controller c = new Controller();
        CarSensor Sensor = new CarSensor(c);
        Sensor.startThreads();
    }
}
