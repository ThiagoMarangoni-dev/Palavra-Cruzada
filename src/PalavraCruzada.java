import java.util.Arrays;
import java.util.Scanner;
public class PalavraCruzada {
    static String[][] grade = new String[20][20];
    static boolean[] respondido;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int totalFases = 3;
        for (int faseAtual = 1; faseAtual <= totalFases; faseAtual++) {
            jogarFase(faseAtual);
        }
        System.out.println("🏆 Parabéns! Você completou todas as fases do jogo!");
    }
    static void jogarFase(int fase) {
        inicializarGrade();
        String[] dicas;
        String[] respostas;
        int[] linhas;
        int[] colunas;
        boolean[] vertical;
        switch (fase) {
            case 1:
                dicas = new String[]{
                        "1. Pessoa que frequenta a escola ou universidade.",
                        "2. É do Japão e tem um público grande que assiste.",
                        "3. Onde nasceu a receita que combina massa, molho e queijo.",
                        "4. Quando uma roupa não é passada a ferro, ela fica?",
                        "5. Palavra que expressa gratidão."
                };
                respostas = new String[]{"ESTUDANTE", "ANIMES", "ITALIA", "AMASSADA", "OBRIGADO"};
                linhas = new int[]{2, 5, 1, 2, 9};
                colunas = new int[]{2, 2, 4, 7, 2};
                vertical = new boolean[]{false, false, true, true, false};
                break;
            case 2:
                dicas = new String[]{
                        "1. Maior planeta do sistema solar.",
                        "2. Cor do Céu.",
                        "3. Profissional que salva vidas.",
                        "4. Combustível fóssil preto.",
                        "5. Metal precioso muito valioso.",
                        "6. Líquido vital ao corpo humano."
                };
                respostas = new String[]{"JUPITER", "AZUL", "MEDICO", "PETROLEO", "OURO", "AGUA"};
                // --- INÍCIO DA CORREÇÃO ---
                // Coordenadas e orientações corrigidas para um encaixe lógico e sem conflitos
                linhas = new int[]{5, 8, 9, 3, 3, 3};
                colunas = new int[]{2, 3, 5, 6, 8, 3};
                vertical = new boolean[]{false, false, false, true, true, true};
                // --- FIM DA CORREÇÃO ---
                break;
            case 3:
                dicas = new String[]{
                        "1. O Morse é um famoso sistema de comunicação baseado neles.",
                        "2. Área da ciência que estuda o comportamento.",
                        "3. Parte do corpo usada para pensar.",
                        "4. País onde está a Torre Eiffel.",
                        "5. contrario de mãe.",
                        "6. Processo de adquirir conhecimento.",
                        "7. Matéria que lida com códigos e lógica."
                };
                respostas = new String[]{"CODIGOS", "PSICOLOGIA", "CEREBRO", "FRANCA", "PAI", "APRENDIZADO", "INFORMATICA"};
                linhas = new int[]{1, 0, 5, 4, 8, 9, 6};
                colunas = new int[]{8, 14, 5, 10, 12, 2, 0};
                vertical = new boolean[]{false, true, false, true, false, false, false};
                break;
            default:
                return;
        }
        respondido = new boolean[respostas.length];
        exibirIntroducao(fase);
        int acertos = 0;
        while (acertos < respostas.length) {
            mostrarDicas(dicas);
            preencherEspacosPendentes(respostas, linhas, colunas, vertical);
            exibirGrade();
            System.out.print("Digite sua resposta: ");
            String entrada = sc.nextLine().toUpperCase();
            boolean acertou = false;
            for (int i = 0; i < respostas.length; i++) {
                if (!respondido[i] && entrada.equals(respostas[i])) {
                    inserirPalavra(respostas[i], linhas[i], colunas[i], vertical[i]);
                    respondido[i] = true;
                    acertos++;
                    acertou = true;
                    System.out.println("✔ Correto!\n");
                    break;
                }
            }
            if (!acertou) {
                System.out.println("❌ Errado ou já respondido. Tente outra dica.\n");
            }
        }
        preencherEspacosPendentes(respostas, linhas, colunas, vertical);
        exibirGrade();
        System.out.println("✅ Fase " + fase + " concluída!\n");
    }
    static void inicializarGrade() {
        for (int i = 0; i < grade.length; i++) {
            Arrays.fill(grade[i], " ");
        }
    }
    static void exibirIntroducao(int fase) {
        System.out.println("\n======= FASE " + fase + " =======");
        System.out.println("--------------------------------\n");
    }
    static void mostrarDicas(String[] dicas) {
        System.out.println("* DICAS:");
        for (int i = 0; i < dicas.length; i++) {
            if (!respondido[i]) {
                System.out.println(dicas[i]);
            }
        }
        System.out.println();
    }
    static void preencherEspacosPendentes(String[] palavras, int[] linhas, int[] colunas, boolean[] vertical) {
        for (int i = 0; i < palavras.length; i++) {
            if (!respondido[i]) {
                desenharEspaco(linhas[i], colunas[i], palavras[i], vertical[i]);
            }
        }
    }
    static void desenharEspaco(int linha, int coluna, String palavra, boolean vertical) {
        for (int i = 0; i < palavra.length(); i++) {
            int l = vertical ? linha + i : linha;
            int c = vertical ? coluna : coluna + i;
            if (grade[l][c].equals(" ")) {
                grade[l][c] = "_";
            }
        }
    }
    static void inserirPalavra(String palavra, int linha, int coluna, boolean vertical) {
        for (int i = 0; i < palavra.length(); i++) {
            int l = vertical ? linha + i : linha;
            int c = vertical ? coluna : coluna + i;
            grade[l][c] = String.valueOf(palavra.charAt(i));
        }
    }
    static void exibirGrade() {
        System.out.print("╔");
        for (int i = 0; i < 17; i++) {
            System.out.print("══");
        }
        System.out.println("═╗");
        for (int i = 0; i < 12; i++) {
            System.out.print("║ ");
            for (int j = 0; j < 17; j++) {
                System.out.print(grade[i][j] + " ");
            }
            System.out.println("║");
        }
        System.out.print("╚");
        for (int i = 0; i < 17; i++) {
            System.out.print("══");
        }
        System.out.println("═╝\n");
    }
}