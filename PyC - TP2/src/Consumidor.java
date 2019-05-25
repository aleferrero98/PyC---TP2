public class Consumidor extends Thread{
    private Monitor monitor;
    private int cant_a_consumir;


    public Consumidor(Monitor monitor, int cantConsumir){
        this.monitor=monitor;
        cant_a_consumir = cantConsumir;

        System.out.println("entra el consumidor");
    }

    @Override
    public void run() {
        for (int i = 0; i < cant_a_consumir; i++) {
            //   while(buffer.getDatosInsertados()<=1000){
            //        while(true){
            try {

               monitor.extraer();

            } catch (Exception e) {
                e.getMessage();
            }
               System.out.println("saco el elemento " );

        }
    }

}


