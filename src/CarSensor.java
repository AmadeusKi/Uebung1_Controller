public class CarSensor{

    boolean cont = true;
    Controller c;

    //Klasse um die Auswertung der Sensoren in Threads zu starten
    class SensorThread extends Thread implements CarSensorInput{

        double letzterWert = 999;
        double messWert = 0;
        Sensor s;

        //Methode um die Entfernung zum nächsten ortbare Objekt zu messen
        //zur Zeit werden Zufallswerte zum Testen verwendet
        public double getDistance(Sensor s) throws CarException {

            messWert = Math.random()*100;
            return messWert;
        }

        //Methode, welche im jeden Thread ausgeführt wird.
        //Es wird die Entfernung gemessen.
        //Der aktuelle Messwert wird mit dem letzten Messwert verglichen und sobald
        //sich ein Objekt nähert, wird der aktuelle Wert an den Controller geschickt.
        //Stopper-Thread beendet alle Threads, nach einer bestimmten Zeit
        public void run(){

            new Stopper(this).start();
            while (cont){
                try {
                    messWert = getDistance(s);
//                    System.out.println(messWert+s.toString());
                } catch (CarException e) {
                    e.printStackTrace();
                }
                if (letzterWert > messWert){
                    try {
                        c.chkSensor(s, messWert);
                    } catch (CarException e) {
                        e.printStackTrace();
                    }
                }
                letzterWert = messWert;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        SensorThread(Sensor s) {
            this.s = s;
        }

        public void setContinue(boolean v){cont=v;}
    }

    //Für jeden Sensor wird ein Thread initialisiert und gestartet.
    CarSensor(Controller c) throws InterruptedException {

        this.c = c;
    }

    public void startThreads(){
        SensorThread fl = new SensorThread(CarSensorInput.Sensor.FL);
        SensorThread fr = new SensorThread(CarSensorInput.Sensor.FR);
        SensorThread bl = new SensorThread(CarSensorInput.Sensor.BL);
        SensorThread br = new SensorThread(CarSensorInput.Sensor.BR);

        fl.start();
        fr.start();
        bl.start();
        br.start();
    }

    //Stopperklasse um alle Threads zu stoppen.
    class Stopper extends Thread{
        SensorThread thread;

        Stopper(SensorThread t){thread = t;}
        public void run(){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.setContinue(false);
        }
    }
}
