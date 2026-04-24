package view;

import model.Event;
import model.Participant;
import repository.ParticipantRepository;
import service.ParticipantService;

import java.util.InputMismatchException;
import java.util.Scanner;

import static view.EventView.event;
import static view.EventView.eventRepository;
import static view.EventView.eventService;

public class ParticipantView {

    private static final Scanner scanner = new Scanner(System.in);

    public static ParticipantRepository participantRepository = new ParticipantRepository();
    public static ParticipantService participantService = new ParticipantService();

    public void panel() {
        String[] menu = {"Criar Conta", "Login", "Voltar"};

        do {
            System.out.println("--------------------------------------------");
            System.out.println("           Gerenciador de Eventos");
            System.out.println("--------------------------------------------");

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
                    case 1 -> createAccount();
                    case 2 -> login();
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

    public void login() {
        System.out.println("--------------------------------------------");
        System.out.println("                   Login");
        System.out.println("--------------------------------------------");
        System.out.println("Digite seu e-mail:");
        String email = scanner.nextLine();
        System.out.println("Digite sua senha:");
        String password = scanner.nextLine();
        Participant participant = participantRepository.findParticipantByEmail(email);

        if (participant != null && participantRepository.findParticipantByPassword(password) != null) {
            System.out.println("Seja bem-vindo(a) de volta " + participant.getName() + '!');
            panel(participant);
        }
    }

    public void createAccount() {
        System.out.println("           Cadastro\n--------------------------------------------");

//        String name;
//        int contact;
//        String email;
//        String password;

//        do {
//            System.out.println("Digite seu nome:");
//            name = scanner.nextLine();
//            boolean validatedName = participantService.validateName(name);
//
//            if (validatedName) {
//                break;
//            } else {
//                System.out.println("Nome inválido! Tente novamente.");
//            }
//        } while (true);

//        do {
//            try {
//                System.out.println("Digite seu contato:");
//                contact = scanner.nextInt();
//                scanner.nextLine();
//                boolean validatedContact = participantService.validateContact(contact);
//
//                if (validatedContact) {
//                    break;
//                } else {
//                    System.out.println("Contato inválido! Tente novamente.");
//                }
//            } catch (InputMismatchException e) {
//                System.err.println("[ERRO]: Digite um número!");
//                scanner.nextLine();
//            }
//        } while (true);

//        do {
//            System.out.println("Digite seu e-mail:");
//            email = scanner.nextLine();
//            boolean validatedEmail = participantService.validateEmail(email);
//
//            if (validatedEmail) {
//                break;
//            } else {
//                System.out.println("Nome inválido! Tente novamente.");
//            }
//        } while (true);

//        do {
//            System.out.println("Digite sua senha:");
//            password = scanner.nextLine();
//            boolean validatedPassword = participantService.validatePassword(password);
//
//            if (validatedPassword) {
//                break;
//            } else {
//                System.out.println("Senha inválida! Tente novamente.");
//            }
//        } while (true);

//        do {
//            System.out.println("Confirme sua senha:");
//            String confirmPassword = scanner.nextLine();
//
//            if (confirmPassword.equals(password)) {
//                break;
//            } else {
//                System.out.println("Senha inválida! Digite a mesma senha.");
//            }
//        } while (true);
//
//        Participant participant = new Participant(name, contact, email, password);
//        participantRepository.createParticipant(participant);

        Participant participant1 = new Participant("Vinícius", 998271900, "vinicius@email.com", "1234");
//        Participant participant2 = new Participant("João Victor", 987593594, "joaovcitor@email.com", "1234");
//        Participant participant3 = new Participant("Ricardo", 999175344, "ricardo@email.com", "1234");
//        Participant participant4 = new Participant("Ângela", 999223567, "angela@email.com", "1234");

        participantRepository.createParticipant(participant1);
//        participantRepository.createParticipant(participant2);
//        participantRepository.createParticipant(participant3);
//        participantRepository.createParticipant(participant4);

        System.out.println("Participante '" + participant1.getName() + "' cadastrado com sucesso!");
        panel(participant1);
    }

    public void panel(Participant participant) {
        String[] menu = {
                "Inscrever-se em um Evento", "Visualizar Eventos/Disponíveis", "Confirmar Presença no Evento",
                "Histórico de Eventos Inscritos", "Cancelar Inscrição no Evento",
                "Atualizar Cadastro", "Excluir Conta", "Voltar"
        };

        do {
            System.out.println("--------------------------------------------");
            System.out.println("           Gerenciador de Eventos");
            System.out.println("--------------------------------------------");
            System.out.println("Seja bem-vindo(a) " + participant.getName()+ "!\n");

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
                    case 1 -> registerParticipantEvent(participant);
                    case 2 -> viewAvailableEvents(participant);
                    case 3 -> confirmAttendance(participant);
                    case 4 -> historyRegisteredEvents(participant);
                    case 5 -> removeParticipantEvent(participant);
                    case 6 -> updateAccount(participant);
                    case 7 -> deleteAccount(participant);
                    case 8 -> {
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

    public static void registerParticipantEvent(Participant participant) {
        if (event.getTotalRegisteredEvents() == 0) {
            System.out.println("Nenhum evento cadastrado anteriormente.");
            return;
        }

        do {
            try {
                System.out.println("--------------------------------------------");
                eventRepository.readEvents();
                System.out.println("Digite o ID do evento para inscrever-se:");
                int eventId = scanner.nextInt();
                System.out.println("--------------------------------------------");
                Event event = eventRepository.findEventById(eventId);

                if (event == null) {
                    System.out.println("ID do evento inválido! Tente novamente.");
                    return;
                }

                if (participantService.verifyRegisteredEvent(participant, eventId)) {
                    System.out.println("Evento já inscrito anteriormente. Tente novamente.");
                    return;
                }

                eventService.addParticipantEvent(participant, event);
                participantService.addEventParticipant(event, participant);
                break;
            } catch (InputMismatchException e) {
                System.out.println("[ERRO]: Digite um número!");
                scanner.nextLine();
            }
        } while (true);
    }

    public static void viewAvailableEvents(Participant participant) {
        // TODO: List events that wasn't registered in participant, and list events that are only available, not sold out (maybe)
        System.out.println("Em breve...");
    }

    public static void confirmAttendance(Participant participant) {
        System.out.println("      Confirmar Presença de Participante\n--------------------------------------------");

        if (event.getTotalRegisteredEvents() == 0) {
            System.out.println("Nenhum evento cadastrado anteriormente.");
            return;
        }

        if (participant.getEvents() == null) {
            System.out.println("Nenhum evento inscrito anteriormente.");
            return;
        }

        do {
            try {
                System.out.println("--------------------------------------------");
                participantRepository.readParticipantEvents(participant);
                System.out.println("Digite o ID do evento para confirmar presença:");
                int eventId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("--------------------------------------------");

                if (!participantService.verifyRegisteredEvent(participant, eventId)) {
                    System.out.println("ID do evento inválido! Tente novamente.");
                    return;
                }

                Event event = eventRepository.findEventById(eventId);

                if (eventRepository.findEventById(eventId) != null) {
                        System.out.println("Você deseja confirmar presença no evento '" + eventRepository.findEventById(eventId).getName() + "'? (s/n)");
                        String response = scanner.nextLine().toLowerCase();

                        if (response.equalsIgnoreCase("s") || response.equalsIgnoreCase("sim")) {
                            // TODO: Maybe don't use set, use update, because Presence always starts with PENDING
                            // TODO: This line need to update de presence, nowadays, it are adding presences, and it need to update
                            participantService.confirmPresence(participant, event);
                            System.out.println("Presença confirmada com sucesso!");
                            break;
                        } else if (response.equalsIgnoreCase("n") || response.equalsIgnoreCase("nao") || response.equalsIgnoreCase("não")) {
                            participantService.cancelPresence(participant, event);
                            System.out.println("Presença cancelada.");
                            break;
                        } else {
                            System.out.println("Resposta inválida! Tente novamente.");
                        }

                    } else {
                        System.out.println("ID do evento inválido! Tente novamente.\n--------------------------------------------");
                    }
                    // TODO: Test if this 'break' is necessary
                    break;
            } catch (InputMismatchException e) {
                System.out.println("[ERRO]: Digite um número!");
                scanner.nextLine();
            }
        } while (true);
    }

    public void historyRegisteredEvents(Participant participant) {
        // TODO: A method that validate if the participant presence are confirmed (maybe)
        // TODO: Read events using Stack (LIFO), because the Events that the participant registered recently need stay on the top
        participantRepository.readParticipantEvents(participant);
    }

    public void removeParticipantEvent(Participant participant) {
        System.out.println("Em breve");
    }

    public void updateAccount(Participant participant) {
        System.out.println("           Atualizar Participante\n--------------------------------------------");

        String[] menu = {
                "Atualizar Nome", "Atualizar Contato", "Atualizar E-mail", "Atualizar Senha", "Voltar"
        };

        do {
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
                    case 1 -> updateName(participant);
                    case 2 -> updateContact(participant);
                    case 3-> updateEmail(participant);
                    case 4-> updatePassword(participant);
                    case 5 -> {
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

    public static void updateName(Participant participant) {
        do {
            System.out.println("Digite o novo nome para atualizar:");
            String newName = scanner.nextLine();
            boolean validatedNewName = participantService.validateName(newName);

            if (validatedNewName && !participant.getName().equalsIgnoreCase(newName)) {
                participantRepository.updateParticipant(participant.getParticipantId(), newName, "Nome");
                break;
            } else {
                System.out.println("Novo nome inválido! Tente novamente.");
            }
        } while (true);
    }

    public static void updateContact(Participant participant) {
        do {
            System.out.println("Digite o novo contato para atualizar:");
            int newContact = scanner.nextInt();
            boolean validatedNewContact = participantService.validateContact(newContact);

            if (validatedNewContact && (participant.getContact() != newContact)) {
                participantRepository.updateParticipant(participant.getParticipantId(), newContact, "Contato");
                break;
            } else {
                System.out.println("Novo contato inválido! Tente novamente.");
            }
        } while (true);
    }

    public static void updateEmail(Participant participant) {
        do {
            System.out.println("Digite o novo email para atualizar:");
            String newEmail = scanner.nextLine();
            boolean validatedNewEmail = participantService.validateEmail(newEmail);

            if (validatedNewEmail && !participant.getEmail().equalsIgnoreCase(newEmail)) {
                participantRepository.updateParticipant(participant.getParticipantId(), newEmail, "E-mail");
                break;
            } else {
                System.out.println("Novo e-mail inválido! Tente novamente.");
            }
        } while (true);
    }

    public static void updatePassword(Participant participant) {
        do {
            System.out.println("Digite a nova senha para atualizar:");
            String newPassword = scanner.nextLine();
            boolean validatedNewPassword = participantService.validatePassword(newPassword);

            if (validatedNewPassword && !participant.getPassword().equalsIgnoreCase(newPassword)) {
                participantRepository.updateParticipant(participant.getParticipantId(), newPassword, "Senha");
                break;
            } else {
                System.out.println("Nova senha inválida! Tente novamente.");
            }
        } while (true);
    }

    public static void deleteAccount(Participant participant) {
        System.out.println("           Deletar Participante\n--------------------------------------------");
        // TODO: This isn't working because it only removes from the participants list, not from the Participant class
        participantRepository.deleteParticipant(participant);
    }

}
