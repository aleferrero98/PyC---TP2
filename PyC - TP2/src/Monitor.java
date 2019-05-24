import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Monitor {
    private Queue<Dato> buffer1;
    private Queue<Dato> buffer2;
    Semaphore mutex=new Semaphore(1);  //semaforo binario de exclusion mutua
    CVSemaforo cond = new CVSemaforo(mutex);


    public Monitor(){
         buffer1 = new LinkedList<Dato>();
         buffer2 = new LinkedList<Dato>();
    }

    public void insertar(){
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(!condicion){
            cond.Delay();       //bloqueo
        }
        buffer1.add(new Dato(7));
        cond.Resume();          //desbloqueo otro hilo
        mutex.release();
    }

    public void extraer(){

    }


}
