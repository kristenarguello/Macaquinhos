import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Escolha qual caso de teste gostaria de testar:");
        System.out.println("[1] Caso com 50 macacos (Caso 0050)");
        System.out.println("[2] Caso com 100 macacos (Caso 0100)");
        System.out.println("[3] Caso com 200 macacos (Caso 0200)");
        System.out.println("[4] Caso com 400 macacos (Caso 0400)");
        System.out.println("[5] Caso com 600 macacos (Caso 0600)");
        System.out.println("[6] Caso com 800 macacos (Caso 0800)");
        System.out.println("[7] Caso com 900 macacos (Caso 0900)");
        System.out.println("[8] Caso com 1000 macacos (Caso 1000)");
        // System.out.println("[0] SAIR");

        try (Scanner input = new Scanner(System.in)) {
            int arquivo = -1;
            String strArq;
            try {
                arquivo = input.nextInt();
                switch (arquivo) {
                    case 1:
                        strArq = "caso0050.txt";
                        break;
                    case 2:
                        strArq = "caso0100.txt";
                        break;
                    case 3:
                        strArq = "caso0200.txt";
                        break;
                    case 4:
                        strArq = "caso0400.txt";
                        break;
                    case 5:
                        strArq = "caso0600.txt";
                        break;
                    case 6:
                        strArq = "caso0800.txt";
                        break;
                    case 7:
                        strArq = "caso0900.txt";
                        break;
                    case 8:
                        strArq = "caso1000.txt";
                        break;
                    default:
                        strArq = "";
                        break;
                }

                Macaquinhos.jogoResolucao(strArq);
            } catch (Exception e) {
                System.out.println("Por favor insira uma opção válida");
            }

        }
    }
}
