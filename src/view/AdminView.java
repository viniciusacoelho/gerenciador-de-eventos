package view;

import enums.Presence;
import model.Event;
import model.Participant;
import repository.EventRepository;
import repository.ParticipantRepository;
import service.EventService;
import service.ParticipantService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminView {

    private static final Scanner scanner = new Scanner(System.in);

    public static EventView eventView = new EventView();
//    public static AdminParticipantView adminParticipantView = new AdminParticipantView();

    public static Event event = new Event();
    public static EventRepository eventRepository = new EventRepository();
    public static EventService eventService = new EventService();

    public static Participant participant = new Participant();
    public static ParticipantRepository participantRepository = new ParticipantRepository();

    public void panel() {
        String[] menu = {
                "Evento", "Participante", "Sair"
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
                int opcao = scanner.nextInt();
                scanner.nextLine();
                System.out.println("--------------------------------------------");

                switch (opcao) {
                    case 1 -> eventView.panel();
                    case 2 -> System.out.println("Em breve...");
//                            adminParticipantView.panel();
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

    public void login() {
        System.out.println("--------------------------------------------");
        System.out.println("                   Login");
        System.out.println("--------------------------------------------");
        System.out.println("Digite seu e-mail:");
        String email = scanner.nextLine();
        System.out.println("Digite sua senha:");
        String password = scanner.nextLine();
        System.out.println("Seja bem-vindo " + email + "!");
        panel();
    }

    public static void registerParticipantEvent(Participant participant) {
        if (event.getTotalRegisteredEvents() == 0) {
            System.out.println("Nenhum evento cadastrado anteriormente.");
            return;
        }

        do {
            try {
                System.out.println("--------------------------------------------");
                eventRepository.readEvent();
                System.out.println("Digite o ID do evento para inscrever o participante:");
                int eventId = scanner.nextInt();
                System.out.println("--------------------------------------------");

                if (eventRepository.findEventById(eventId) != null) {
                    eventService.addParticipantEvent(participant, eventRepository.findEventById(eventId));
                    break;
                } else {
                    System.out.println("ID do evento inválido! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERRO]: Digite um número!");
                scanner.nextLine();
            }
        } while (true);
    }

    public static void confirmParticipantAttendance() {
        if (participant.getTotalRegisteredParticipants() == 0) {
            System.out.println("Nenhum participante cadastrado anteriormente.");
            return;
        }

        int participantId;
        do {
            try {
                System.out.println("      Confirmar Presença de Participante\n--------------------------------------------");
                participantRepository.readParticipant();
                System.out.println("Digite o ID do participante:");
                participantId = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("[ERRO]: Digite um número!");
            }
        } while (true);

        do {
            if (participantRepository.findParticipantById(participantId) != null) {
                System.out.println("Você deseja confirmar presença do participante " + participantRepository.findParticipantById(participantId).getName() + "? (s/n)");
                String response = scanner.nextLine();
                response = response.toLowerCase();

                if (response.equalsIgnoreCase("s") || response.equalsIgnoreCase("sim")) {
                    participantRepository.findParticipantById(participantId).setPresence(Presence.CONFIRMED);
                    System.out.println("Presença confirmada com sucesso!");
                    break;
                } else if (response.equalsIgnoreCase("n") || response.equalsIgnoreCase("nao") || response.equalsIgnoreCase("não")) {
                    participantRepository.findParticipantById(participantId).setPresence(Presence.CANCELED);
                    System.out.println("Presença cancelada.");
                    break;
                } else {
                    System.out.println("Resposta inválida! Tente novamente.");
                }

            } else {
                System.out.println("ID do participante inválido! Tente novamente.\n--------------------------------------------");
            }
        } while (true);
    }

}
