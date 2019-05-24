public class RedDePetri {
    private int[] marcaActual;
    private int[][] matrizDeIncidencia;

    public RedDePetri(int[] marcaInicial, int[][] matrizDeIncidencia) {

        this.marcaActual = marcaInicial;
        this.matrizDeIncidencia = matrizDeIncidencia;
    }

    public boolean esSensibilizado(int transicion){ //devuelve true si la transicion esta sensibilizada
        for(int i = 0; i < marcaActual.length; i++){
            if((marcaActual[i] + matrizDeIncidencia[i][transicion])  < 0){
                return false;
            }
        }
        return true;
    }

    public boolean algunSensibilizado(int TA, int TB){ //devuelve true si alguna de las 2 transiciones esta sensibilizada
        return (this.esSensibilizado(TA) || this.esSensibilizado(TB));
    }

    public void disparar(int transicion) {  //dispara una transicion modificando la marcaActual
        if (this.esSensibilizado(transicion)) {   //la dispara solo si esta sensibilizada
            for (int i = 0; i < marcaActual.length; i++) {
                marcaActual[i] = marcaActual[i] + matrizDeIncidencia[i][transicion];
            }
        }
    }
}