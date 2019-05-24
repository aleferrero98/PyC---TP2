import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private Productor[] productores;
    private Consumidor[] consumidores;

    private  int tamanio;
    private Queue<Dato> pila;
    private int datosPerdidos = 0;
    private int datosConsumidos=0;
    private int datosInsertados=0;

    public Buffer(int i) {
        tamanio = i;
        pila = new LinkedList<Dato>();

    }

    synchronized public void extraer() {
        while (pila.size()==0) {
            if(productoresTerminated()){
                return;
            }
            try {
                wait();
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }
        String dato_sacado = pila.poll().getDato().toString();
        //   System.out.println("Dato quitado: " + dato_sacado + " espacio ocupado en pila: " + pila.size());
        datosConsumidos++;

        notifyAll();

    }

    synchronized public void insertar(Dato elem) {
        if (pila.size() < tamanio) {
            pila.add(elem);
            //   System.out.println("Dato agregado: " + elem.getDato().toString() + " espacio ocupado en pila: " + pila.size());
            datosInsertados++;


        }else{
            datosPerdidos++;


            // System.out.println("dato perdido nro: " + ++datosPerdidos);
        }
        notifyAll();
    }

    public int getCantElementos(){
        return pila.size();
    }

    public void setProductores(Productor[] p){
        productores = p;
    }

    public void setConsumidores(Consumidor[] c){consumidores = c;}


    private boolean productoresTerminated(){
        boolean terminated = true;
        for(int i = 0; i < productores.length; i++){
            terminated = terminated && (productores[i].getState()==Thread.State.TERMINATED);
        }
        return terminated;
    }

    public void imprimir(){
        System.out.println("datos consumidos: "+datosConsumidos);
        System.out.println("datos insertados: "+datosInsertados);
        System.out.println("datos perdidos: "+datosPerdidos);
    }
    public int getDatosInsertados(){
        return datosInsertados;
    }

}

