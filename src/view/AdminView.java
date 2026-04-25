package view;

import exceptions.IncorrectDefaultPasswordException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminView {

    private static final Scanner scanner = new Scanner(System.in);

    public static EventView eventView = new EventView();
    public static AdminParticipantView adminParticipantView = new AdminParticipantView();

    public void login() throws IncorrectDefaultPasswordException {
        String DEFAULT_PASSWORD = "1234";

        System.out.println("--------------------------------------------");
        System.out.println("                   Login");
        System.out.println("--------------------------------------------");
        System.out.println("Digite seu e-mail:");
        String email = scanner.nextLine();
        System.out.println("Digite sua senha:");
        String password = scanner.nextLine();

        if (!password.equals(DEFAULT_PASSWORD)) {
            throw new IncorrectDefaultPasswordException("[ERRO]: Senha inválida! Tente novamente.");
        }

        System.out.println("Seja bem-vindo " + email + '!');
        panel();
    }

    public static void panel() {
        String[] menu = {"Evento", "Participante", "Voltar"};

        do {
            System.out.println("--------------------------------------------");
            System.out.println("           Gerenciador de Eventos");
            System.out.println("--------------------------------------------");
            System.out.println("Dashboard\n");

            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + " - " + menu[i]);
            }

            try {
                System.out.println("--------------------------------------------");
                System.out.println("Digite uma opção:");
                int option = scanner.nextInt();
                scanner.nextLine();
                System.out.println("--------------------------------------------");

                switch (option) {
                    case 1 -> eventView.panel();
                    case 2 -> adminParticipantView.panel();
                    case 3 -> {
                        System.out.println("Voltando...");
                        return;
                    }
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }

            } catch (InputMismatchException e) {
                System.err.println("[ERRO]: Digite um número!");
                scanner.nextLine();
            }
        } while (true);
    }

}
