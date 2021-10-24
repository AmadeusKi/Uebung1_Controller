import java.util.HashMap;

public class Controller{



    //Hashmap zur Speicherung der Messwerte
    HashMap<CarSensorInput.Sensor, Double> sensorMessWerte = new HashMap<>();
    //Initialisierung der Motorsteuerung
    CarMotor cm = new CarMotor();


    //Vorbereitung der Hashmap mit Anfangswerten
    //Übergabe des Controllers an die Sensoren (Asynchronität)
    Controller() throws InterruptedException {

        //Erstinitialisierung der Hashmap mit Anfangswerten
        sensorMessWerte.put(CarSensorInput.Sensor.FL, 100d);
        sensorMessWerte.put(CarSensorInput.Sensor.FR, 100d);
        sensorMessWerte.put(CarSensorInput.Sensor.BL, 100d);
        sensorMessWerte.put(CarSensorInput.Sensor.BR, 100d);

        CarSensor Sensor = new CarSensor(this);
        Sensor.startThreads();



    }

    //Methode zur Verarbeitung der Messwerte
    public void chkSensor(CarSensorInput.Sensor s, double messWert) throws CarException {
        sensorMessWerte.put(s, messWert);
        System.out.println(sensorMessWerte.get(s)+"    " + s.toString());
        if (sensorMessWerte.get(CarSensorInput.Sensor.FL) > 10 && sensorMessWerte.get(CarSensorInput.Sensor.FR) > 10){
            cm.setSpeed(100);
//            System.out.println("Freie Fahrt");
        }
        if (sensorMessWerte.get(CarSensorInput.Sensor.FL) < 10 && sensorMessWerte.get(CarSensorInput.Sensor.FR) < 10){
            cm.setSpeed(0);
//            System.out.println("Gefahrenbremsung");
        }
        if (sensorMessWerte.get(CarSensorInput.Sensor.FL) < 10 && sensorMessWerte.get(CarSensorInput.Sensor.BL) < 10){
            cm.steering(100);
//            System.out.println("lenke nach Rechts");
        }
        if (sensorMessWerte.get(CarSensorInput.Sensor.FR) < 10 && sensorMessWerte.get(CarSensorInput.Sensor.BR) < 10){
            cm.steering(-100);
//            System.out.println("lenke nach Links");
        }
        if (sensorMessWerte.get(CarSensorInput.Sensor.BL) < 10 && sensorMessWerte.get(CarSensorInput.Sensor.FL) < 10){
            cm.setSpeed(0);
//            System.out.println("Gefahrenbremsung beim ausparken");
        }


    }


}
