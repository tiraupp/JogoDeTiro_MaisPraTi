import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        boolean continuarPartida = true;
        boolean iniciarJogo = true;
        boolean novoJogo = true;
        Scanner scanner = new Scanner(System.in);
        Cenario mapa = new Cenario();
        String entrada;

        while (iniciarJogo) {
            mapa.telaIncial();
            mapa.iniciarJogo();
            while (continuarPartida) {
                System.out.println("Entre com as posições x e y no formato x,y ou 'FIM / F' para sair.");
                entrada = scanner.nextLine().toUpperCase();
                if ((entrada.equals("FIM")) || (entrada.equals("F"))) {
                    iniciarJogo = false;
                    novoJogo=false;
                    new Thread().sleep(2000);
                    System.out.println(" ");
                    System.out.println("Até a proxima, saindo do jogo....");
                    System.out.println(" ");
                    break;
                }
                try {
                    String[] numeros = entrada.split(",");
                    int x = Integer.parseInt(numeros[0]);
                    int y = Integer.parseInt(numeros[1]);

                    if (x < 10 && y < 10) {
                        continuarPartida = mapa.disparo(x, y);
                        mapa.desenhaCenario();
                    } else {
                        System.out.println("Entrada invalida, números entre 0 e 9");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("Entrada no formato invalido, utilize numeros de 0 a 9 e sepados por virgula ','");
                }
            }
            if ((mapa.tirosRestantes == 0) && (mapa.pecasRestantes > 0)) {
                System.out.println(" ");
                System.out.println("Seus tiros acabaram e você não derrotou os inimigos, mas você pode tentar novamete...");
                System.out.println("S para novo jogo ou N para sair.");
                novoJogo = true;
            }
            if ((mapa.tirosRestantes > 0) && (mapa.pecasRestantes == 0)) {
                System.out.println(" ");
                System.out.println("Parabéns, você venceu!!!!!");
                System.out.println("Deseja jogar novamente?");
                System.out.println("S para novo jogo ou N para sair.");
                novoJogo = true;
            }
            while (novoJogo) {
                entrada = scanner.nextLine().toUpperCase();

                if (entrada.equals("S")) {
                    System.out.println("Iniciando novo Jogo, aguarde...");
                    new Thread().sleep(2000);
                    System.out.println(" ");
                    continuarPartida = true;
                    novoJogo = false;
                    continue;
                }
                if (entrada.equals("N")) {
                    new Thread().sleep(2000);
                    System.out.println(" ");
                    System.out.println("Até a proxima, saindo do jogo....");
                    System.out.println(" ");
                    iniciarJogo=false;
                    break;
                }
                System.out.println("Entrada invalida.");
                System.out.println("S para novo jogo ou N para sair.");
                System.out.println(" ");
            }
        }
    }
}