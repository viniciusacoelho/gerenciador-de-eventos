import view.AdminView;
import view.ParticipantView;

void main() {

    Scanner scanner = new Scanner(System.in);

    AdminView adminView = new AdminView();
    ParticipantView participantView = new ParticipantView();

    String[] menu = {
            "Administrador", "Participante", "Sair"
    };

    do {
        System.out.println("--------------------------------------------");
        System.out.println("           Gerenciador de Eventos");
        System.out.println("--------------------------------------------");
        System.out.println("Identifique-se\n");

        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + " - " + menu[i]);
        }

        try {
            System.out.println("--------------------------------------------\n\nDigite uma opção:");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> adminView.login();
                case 2 -> participantView.panel();
                case 3 -> {
                    System.out.println("Saindo...");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }

        } catch (InputMismatchException e) {
            System.err.println("[ERRO]: Digite um número!");
            scanner.nextLine();
        }
    } while (true);

}

/*
    Funções:
    - Login
    - Cadastrar Evento (Nome, Data, Local, Capacidade)
    - Listar Eventos
    - Inscrever Participante (Nome, Contato)
    - Exibir Participantes Inscritos
    - Confirmar Presença de Participante
*/

// TODO: Ticket (Name, Description, Price), create a Enum if ticket still have to buy, AVAILABLE or SOLD_OUT
// TODO: Create a abstract class Ticket and others extends from it and create a interface of their methods