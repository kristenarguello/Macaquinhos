import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Macaquinhos {
    public static void jogoResolucao(String arquivo) {
        int[][] teste = readFile("/Users/kristen/Downloads/3semestre/alestII/t1/MacaquinhosT1/src/casos/" + arquivo);
        
        int n = teste[0][0];//n de rodadas
        while (n-- > 0) {
            for (int i = 1; i < teste.length; i++) {// complexidade O(v*n-1) = O(r*n)
                int macacoPar = teste[i][0] + 1, macacoImpar = teste[i][1] + 1;
                //macaco par recebe os cocos, dps zera o que distribuiu
                teste[macacoPar][2] += teste[i][2];
                teste[i][2] = 0;
                //macaco impar recebe os cocos, dps zera o que distribuiu
                teste[macacoImpar][3] += teste[i][3];
                teste[i][3] = 0;
            }
        }

        int macacoMaior = 0, nMaior = teste[1][2] + teste[1][3];
        for (int i = 2; i < teste.length; i++) {// complexidade O(n - 2) = O(n)
            int aux = teste[i][2] + teste[i][3];
            if (aux > nMaior) {
                macacoMaior = i - 1;
                nMaior = aux;
            }
        } // vai comparando ate achar o maior dos valores
        System.out.println("O macaco com mais coquinhos é o macaco número " + macacoMaior);
        System.out.printf("Ele terminou o jogo com %d coquinhos", nMaior);
    }

    public static int[][] readFile(String nomeArq) {
        Path path1 = Paths.get(nomeArq);
        int cont = 0;
        int[][] result = null;
       

        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.forName("utf8"))) {
            String line = null;
            // cria a matriz pra armazenar os macacos
            // 0 = pra onde o par vai
            // 1 = pra onde o impar vai
            // 2 = quantos pares
            // 3 = quantos impares
            int macaco = 1;

            while ((line = reader.readLine()) != null) {// complexidade O(n)
                
                String[] dados = line.split(" ");
                if (cont == 0) {//1 linha de todas
                    String aux = dados[1].substring(0, dados[1].length() - 2);
                    int lines = Integer.parseInt(aux) + 1;//linhas sempre (n rodadas menos 2 ultimos 0) + 1
                    result = new int[lines][4];//cria a matriz
                    result[0][0] = Integer.parseInt(dados[1]);//pega quantidade de rodadas e armazena na matriz
                    cont = 1;//proximas linhas
                    // primeira vez/linha = numero de vezes
                } else {
                    int numero = 0;
                    int impar = 0, par = 0;
                    int cont2 = 0;//conta qual palavra sendo lida do txt
                    for (String s : dados) {
                        if (s.matches("[0-9]+")) {//regex = é um numero int
                            if (cont2 == 4 || cont2 == 7) {//5 ou 8 - caso os destinos para par ou impar
                                result[macaco][numero++] = Integer.parseInt(s);
                            } else if (cont2 > 10) {//caso seja pedrinhas nos cocos
                                if (Integer.parseInt(s) % 2 == 0) {
                                    par++;
                                } else {
                                    impar++;
                                }//conta quantos impares e quantos pares cada macaco tem
                            }
                        }
                        cont2++;
                    }
                    result[macaco][numero++] = par;
                    result[macaco++][numero] = impar;
                }
            }
        } catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }
        return result;
    }
}