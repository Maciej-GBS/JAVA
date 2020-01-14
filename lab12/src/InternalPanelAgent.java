import java.util.concurrent.*;

public class InternalPanelAgent extends Thread {
	static class InternalCall{
        private final int toFloor;
        InternalCall(int toFloor){
            this.toFloor = toFloor;
        }
    }

    InternalPanelAgent(ElevatorCar elevatorCar){
        this.elevatorCar = elevatorCar;
    }

    BlockingQueue<InternalCall> input = new ArrayBlockingQueue<>(100);
    ElevatorCar elevatorCar;

    public void run(){
        for(;;){
          InternalCall ic = null;
            try {
                ic = input.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // ignorujemy wezwanie na piętro, na którym winda się znajduje
            if(ic.toFloor==elevatorCar.getFloor())
                continue;
            // dodajemy do jednej z tablic zgłoszeń
            if(elevatorCar.getFloor() < ic.toFloor){
                ElevatorStops.get().setLiftStopUp(ic.toFloor);
            }else{
                ElevatorStops.get().setLiftStopDown(ic.toFloor);
            }
        }
    }
}
