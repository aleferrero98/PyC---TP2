import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Monitor {
    private Queue<Dato> buffer1;
    private Queue<Dato> buffer2;
    Semaphore mutex=new Semaphore(1);  //semaforo binario de exclusion mutua
    CVSemaforo cond = new CVSemaforo(mutex);
    private int[] marcaInicial = {0,15,0,5,8,0,0,10,0,0};
    private static final int[][] matrizDeIncidencia = {{1,-1,0,0,0,0,0,0},
            {0,0,0,0,-1,0,0,1},
            {0,0,0,0,0,1,-1,0},
            {-1,1,0,0,-1,1,0,0},
            {0,0,-1,1,0,0,-1,1},
            {0,0,1,-1,0,0,0,0},
            {0,0,0,0,1,-1,0,0},
            {-1,0,0,1,0,0,0,0},
            {0,1,-1,0,0,0,0,0},
            {0,0,0,0,0,0,1,-1}};
    RedDePetri RdP = new RedDePetri(marcaInicial, matrizDeIncidencia);

    public Monitor(){
         buffer1 = new LinkedList<Dato>();
         buffer2 = new LinkedList<Dato>();
    }

    public void insertar(Dato dato){
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(!(RdP.algunSensibilizado(0, 4))){ //me fijo si T0 y/o T4 estan sensibilizadas
            cond.Delay();       //bloqueo
        }
        if(RdP.esSensibilizado(0)) {
            RdP.disparar(0);
            buffer1.add(dato);       //inserto en el buffer de 15
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RdP.disparar(1);
        }else if(RdP.esSensibilizado(4)){
            RdP.disparar(4);
            buffer2.add(dato);       //inserto en el buffer de 10
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RdP.disparar(5);
        }

        cond.Resume();          //desbloqueo otro hilo
        mutex.release();
    }

    public void extraer(){
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(!(RdP.algunSensibilizado(2, 6))){ //me fijo si T2 y/o T6 estan sensibilizadas
            cond.Delay();       //bloqueo
        }
        if(RdP.esSensibilizado(2)) {
            RdP.disparar(2);
            buffer1.poll();       //saco del buffer de 15
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RdP.disparar(3);
        }else if(RdP.esSensibilizado(6)){
            RdP.disparar(6);
            buffer2.poll();       //saco del buffer de 10
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RdP.disparar(7);
        }

        cond.Resume();          //desbloqueo otro hilo
        mutex.release();
    }


}
