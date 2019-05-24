import java.util.concurrent.Semaphore;

public class CVSemaforo {
    Semaphore mutex;
    Semaphore condicion;
    int bloqueados;

    public CVSemaforo(Semaphore mutex){
        this.mutex=mutex;
        condicion = new Semaphore(0);
        bloqueados=0;
    }

    public void Delay(){
        bloqueados++;
        mutex.release();
        try {
            condicion.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void Resume(){
        if(bloqueados>0){
            bloqueados--;
            condicion.release();
        }
    }

    public boolean Empty(){
        if(bloqueados>0){
            return false;
        }else{
            return true;
        }
    }
}
