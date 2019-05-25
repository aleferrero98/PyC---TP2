public class Productor extends Thread {
    private Monitor monitor;
    private int cant_a_producir;



    public Productor(Monitor monitor, int cantProducir){
        this.monitor=monitor;
        cant_a_producir = cantProducir;
        System.out.println("entra el productor ");
    }

    @Override
    public void run(){
        for(int i = 0; i < cant_a_producir; i++){

            //   while(buffer.getDatosInsertados()<=1000){
            //   while(true){
            try{
                monitor.insertar(new Dato(i));
            }catch(Exception e){
                e.getMessage();
            }
               System.out.println("he puesto el elemento ");
        }

        //   System.out.println("he puesto el elemento ");
    }
}

