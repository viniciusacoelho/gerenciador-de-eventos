package view;

import exceptions.ParticipantNotFoundException;
import model.Participant;
import service.ParticipantService;

import java.util.InputMismatchException;
import java.util.Scanner;

import static view.ParticipantView.deleteAccount;
import static view.ParticipantView.participantRepository;

/**
 * Responsável pela interface de interação entre o administrador e as
 * funcionalidades relacionadas ao gerenciamento de participantes.
 */
public class AdminParticipantView {

    private static final Scanner scanner = new Scanner(System.in);

    private static final ParticipantService participantService = new ParticipantService();

    /**
     * Exibe o painel de gerenciamento de participantes para o administrador.
     */
    public void panel() {
        String[] menu = {
                "Cadastrar Participante", "Listar Participantes", "Buscar Participante",
                "Atualizar Participante", "Deletar Participante", "Voltar"
        };

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
                    case 1 -> registerParticipant();
                    case 2 -> participantRepository.readParticipants();
                    case 3 -> findParticipant();
                    case 4 -> updateParticipant();
                    case 5 -> removeParticipant();
                    case 6 -> {
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

    /**
     * Realiza o cadastro de um novo participante no sistema.
     */
    public static void registerParticipant() {
        System.out.println("           Inscrever Participante\n--------------------------------------------");

        //    String name;
        //    int contact;

        //    do {
        //        System.out.println("Digite o nome do participante:");
        //        name = scanner.nextLine();
        //        boolean validatedName = participantService.validateName(name);
        //
        //        if (validatedName) {
        //            break;
        //        } else {
        //            System.out.println("Nome inválido! Tente novamente.");
        //        }
        //    } while (true);

        //    do {
        //        try {
        //            System.out.println("Digite o contato do participante:");
        //            contact = scanner.nextInt();
        //            scanner.nextLine();
        //            boolean validatedContact = participantService.validateContact(contact);
        //
        //            if (validatedContact) {
        //                break;
        //            } else {
        //                System.out.println("Contato inválido! Tente novamente.");
        //            }
        //        } catch (InputMismatchException e) {
        //            System.err.println("[ERRO]: Digite um número!");
        //            scanner.nextLine();
        //        }
        //    } while (true);

        //    Participant participant = new Participant(name, contact);
        //    participantRepository.createParticipant(participant);

        Participant participant1 = new Participant("Vinícius Araújo Coêlho", participantService.formatContact("998271900"), "vinicius@email.com", "1234");
        Participant participant2 = new Participant("João Victor Araújo Coêlho", participantService.formatContact("987593594"), "joaovcitor@email.com", "1234");
        Participant participant3 = new Participant("Ricardo José de Andrade Coêlho", participantService.formatContact("999175344"), "ricardo@email.com", "1234");
        Participant participant4 = new Participant("Ângela Maria Araújo Coêlho", participantService.formatContact("999223567"), "angela@email.com", "1234");

        participantRepository.createParticipant(participant1);
        participantRepository.createParticipant(participant2);
        participantRepository.createParticipant(participant3);
        participantRepository.createParticipant(participant4);

        System.out.println("Participante '" + participant1.getName() + "' cadastrado com sucesso!");
    }

    /**
     * Busca um participante no sistema.
     *
     * @throws ParticipantNotFoundException caso o participante não seja encontrado.
     */
    public static void findParticipant() throws ParticipantNotFoundException {
        System.out.println("           Buscar Participante\n--------------------------------------------");
        Participant participant = chooseParticipant("buscar");

        if (participantService.isEmpty(participant)) {
            throw new ParticipantNotFoundException("E-mail do participante não encontrado.");
//            return;
        }

        System.out.println(participant);
    }

    /**
     * Atualiza as informações de um participante cadastrado no sistema.
     *
     * @throws ParticipantNotFoundException caso o participante não seja encontrado.
     */
    public static void updateParticipant() throws ParticipantNotFoundException {
        Participant participant = chooseParticipant("atualizar");

        if (participantService.isEmpty(participant)) {
            throw new ParticipantNotFoundException("E-mail do participante não encontrado.");
//            return;
        }

        deleteAccount(participant);
    }

    /**
     * Remove um participante cadastrado no sistema.
     *
     * @throws ParticipantNotFoundException caso o participante não seja encontrado.
     */
    public static void removeParticipant() throws ParticipantNotFoundException {
        Participant participant = chooseParticipant("deletar");

        if (participantService.isEmpty(participant)) {
            throw new ParticipantNotFoundException("Nome do participante não encontrado.");
//            return;
        }

        deleteAccount(participant);
    }

    /**
     * Permite escolher um participante com base na ação informada.
     *
     * @param action ação utilizada para definir a forma de seleção do participante.
     * @return participante selecionado.
     */
    public static Participant chooseParticipant(String action) {
        System.out.println("Digite o e-mail do participante para " + action + ':');
        String email = scanner.nextLine();
        return participantRepository.findParticipantByEmail(email);
    }

}
