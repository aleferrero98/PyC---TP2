public class Productor extends Thread {
    private Buffer buffer;
    private int cant_a_producir;
    private int i=5;


    public Productor(Buffer b, int cantProducir){
        buffer=b;
        cant_a_producir = cantProducir;
        //System.out.println("entra el productor ");
    }

    @Override
    public void run(){
        for(int i = 0; i < cant_a_producir; i++){

            //   while(buffer.getDatosInsertados()<=1000){
            //   while(true){
            try{
                buffer.insertar(new Dato(i));
            }catch(Exception e){
                e.getMessage();
            }
        }

        //   System.out.println("he puesto el elemento ");
    }
}

