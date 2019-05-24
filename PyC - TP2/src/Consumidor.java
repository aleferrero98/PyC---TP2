public class Consumidor extends Thread{
    private Buffer buffer;
    private int cant_a_consumir;
    private int alfa=1;  //ms
    private int beta=10;
    private boolean estado;  //true: disponible        false:ocupado-consumiendo

    public Consumidor(Buffer b, int cantConsumir){
        buffer=b;
        cant_a_consumir = cantConsumir;
        estado=true;    //esta disponible
        //System.out.println("entra el consumidor");
    }

    @Override
    public void run() {
        for (int i = 0; i < cant_a_consumir; i++) {
            //   while(buffer.getDatosInsertados()<=1000){
            //        while(true){
            try {
                this.setEstado(true);  //disponible
                buffer.extraer();
                this.procesar();
            } catch (Exception e) {
                e.getMessage();
            }
            //   System.out.println("saco el elemento " + elem);

        }
    }
    public void procesar(){
        try {
            this.setEstado(false); //ocupado
            this.sleep((long) Math.floor(Math.random()*(beta-alfa+1)+alfa)); //Duerme al hilo en un tiempo entero de entre alfa y beta milisegundos(incluye al beta)
            this.setEstado(true);   //disponible
        } catch (InterruptedException e) {
            e.getMessage();
        }

    }
    public String getEstado(){
        if(estado){
            return "DISPONIBLE";
        }
        return "OCUPADO-CONSUMIENDO";

    }
    public void setEstado(boolean state){
        estado=state;
    }
}


