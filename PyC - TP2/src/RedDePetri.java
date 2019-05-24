public class RedDePetri {
    private static final int[][] matIncidencia = {{1,-1,0,0,0,0,0,0},
                                                  {0,0,0,0,-1,0,0,1},
                                                  {0,0,0,0,0,1,-1,0},
                                                  {-1,1,0,0,-1,1,0,0},
                                                  {0,0,-1,1,0,0,-1,1},
                                                  {0,0,1,-1,0,0,0,0},
                                                  {0,0,0,0,1,-1,0,0},
                                                  {-1,0,0,1,0,0,0,0},
                                                  {0,1,-1,0,0,0,0,0},
                                                  {0,0,0,0,0,0,1,-1}};

    private int[] marcaInicial = {0,15,0,5,8,0,0,10,0,0};

    private boolean esSensibilizado(int[] disparo){
        for(int i=0; i<disparo.length; i++){
            if(disparo[i]==1){
                break;
            }
        }




    }
}
