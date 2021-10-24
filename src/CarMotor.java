public class CarMotor implements CarMotorOutput{
    @Override
    public void setSpeed(int x) throws CarException {
//        System.out.println("Geschwindigkeit wird auf " + x + " % gesetzt" );
    }

    @Override
    public void steering(int x) throws CarException {

//        System.out.println("Lenkung wird auf " + x + " Grad gesetzt");

    }
}
