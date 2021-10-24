public class Test1 implements CarSensorInput, CarMotorOutput{
    @Override
    public void setSpeed(int x) throws CarException {

    }

    @Override
    public void steering(int x) throws CarException {

    }

    @Override
    public double getDistance(Sensor s) throws CarException {
        return 0;
    }



    Test1(){

    }

    public static void main(String[] args) throws InterruptedException, CarException {



        Controller testController = new Controller();

        testController.sensorMessWerte.put(CarSensorInput.Sensor.FL, 5d);
        testController.sensorMessWerte.put(CarSensorInput.Sensor.FR, 5d);
        testController.sensorMessWerte.put(CarSensorInput.Sensor.BL, 100d);
        testController.sensorMessWerte.put(CarSensorInput.Sensor.BR, 100d);

        testController.chkSensor(Sensor.FL, 5);



    }
    
}
