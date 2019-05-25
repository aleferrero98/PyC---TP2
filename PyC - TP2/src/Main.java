public class Main {
    private static final int CANT_PRODUCTORES = 5;
    private static final int CANT_A_PRODUCIR = 10000;  //10000
    private static final int CANT_CONSUMIDORES = 8;
    private static final int CANT_A_CONSUMIR = 6250;      //NO ESPECIFICA, usamos 6250 para que se consuman todos los productos producidos

    public static void main(String[] args) throws InterruptedException {



        Productor productores[]=new Productor[CANT_PRODUCTORES];
        Consumidor consumidores[]=new Consumidor[CANT_CONSUMIDORES];
        Monitor monitor = new Monitor();


        for (int i = 0; i < CANT_PRODUCTORES; i++) {
            productores[i] = new Productor(monitor,CANT_A_PRODUCIR);      // productores
            productores[i].start();
        }




        for (int i = 0; i < CANT_CONSUMIDORES; i++) {
            consumidores[i] = new Consumidor(monitor,CANT_A_CONSUMIR);      //2 consumidores
            consumidores[i].start();
        }



        for (int i = 0; i < CANT_PRODUCTORES; i++) {
            productores[i].join();
        }
        for (int i = 0; i < 2; i++) {
            consumidores[i].join();
        }
        //       buffer.imprimir();
        //System.out.println("Fin del hilo Main");

    }

}

