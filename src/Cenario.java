public class Cenario {
    int[][] mapa;
    String[] desenhos;
    Pecas[] inimigos;
    int pecasRestantes;
    int tirosRestantes;

    public Cenario() {


    }
    public void telaIncial()throws InterruptedException {
            System.out.println(" ");
            System.out.println("*************************************");
            System.out.println("******** Bem vindo ao jogo!! ********");
            System.out.println("*************************************");
            new Thread().sleep(2000);
            System.out.println(" ");
    }
    public void iniciarJogo(){
        this.pecasRestantes = 5;
        this.tirosRestantes = 25;
        this.mapa = new int[10][10];
        this.desenhos = new String[] {".","X","*"};
        this.inimigos = new Pecas[this.pecasRestantes];
        for(int i = 0; i < this.pecasRestantes; i++) {
            this.inimigos[i] = new Pecas();
        }
    }

    public void desenhaLinha(int[] linha) {
        String strLinha = "";
        for( int celula: linha) {
            strLinha += this.desenhos[celula] + " ";
        }
        System.out.println(strLinha);
    }

    public void desenhaCenario() {
        for(int[] linha: this.mapa) {
            this.desenhaLinha(linha);
        }

        System.out.println("Peças Restantes: " + this.pecasRestantes);
        System.out.println("Tiros Restantes: " + this.tirosRestantes);

    }

    public boolean disparo(int x, int y) {
        this.tirosRestantes--;
        double distancia = 100;
        double d1 = 0;

        for( Pecas p: inimigos) {
            if (p.isVivo()) {
                d1 = p.disparo(x, y);
                if (d1 == 0) {
                    this.pecasRestantes--;
                }
                distancia = (d1 < distancia) ? d1 : distancia;
            }
        }
        System.out.println("====================================");
        System.out.println("Distancia é: " + distancia);
        System.out.println("====================================");

        this.mapa[y][x] = (distancia>0)?1:2;

        return ((this.pecasRestantes > 0 ) && (this.tirosRestantes > 0));
    }

}
