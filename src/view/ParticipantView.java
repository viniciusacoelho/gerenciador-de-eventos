package view;

import exceptions.EventFullException;
import exceptions.EventNotFoundException;
import exceptions.NoStudentIdException;
import exceptions.ParticipantEventNotFoundException;
import model.Event;
import model.Participant;
import model.Ticket;
import repository.ParticipantRepository;
import service.ParticipantService;
import util.IsEqualUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

import static view.AdminView.ticketView;
import static view.EventView.event;
import static view.EventView.eventRepository;
import static view.EventView.eventService;

/**
 * Responsável pela interface de interação entre o participante e as
 * funcionalidades do sistema.
 */
public class ParticipantView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final IsEqualUtil isEqualUtil = new IsEqualUtil();

    public static ParticipantRepository participantRepository = new ParticipantRepository();
    public static ParticipantService participantService = new ParticipantService();

    /**
     * Exibe o painel principal de funcionalidades do participante.
     */
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

    /**
     * Realiza o login de um participante no sistema.
     */
    public void login() {
        System.out.println("--------------------------------------------");
        System.out.println("                   Login");
        System.out.println("--------------------------------------------");
        System.out.println("Digite seu e-mail:");
        String email = scanner.nextLine();
        System.out.println("Digite sua senha:");
        String password = scanner.nextLine();
        Participant participant = participantRepository.findParticipantByEmail(email);

        if (!participantService.isEmpty(participant) && participantService.isPasswordCorrect(password)) {
            System.out.println("Seja bem-vindo(a) de volta " + participant.getName() + '!');
            panel(participant);
            return;
        }

        System.out.println("E-mail e/ou senha inválidos!");
    }

    /**
     * Realiza o cadastro de uma nova conta de participante no sistema.
     */
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

        Participant participant1 = new Participant("Vinícius", participantService.formatContact("82998271900"), "vinicius@email.com", "1234");
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

    /**
     * Exibe o painel de funcionalidades de um participante específico.
     *
     * @param participant participante que terá suas opções exibidas.
     */
    public void panel(Participant participant) {
        String[] menu = {
                "Inscrever-se em um Evento", "Visualizar Eventos/Disponíveis", "Confirmar Presença no Evento",
                "Histórico de Eventos Inscritos", "Cancelar Inscrição no Evento",
                "Visualizar Cadastro", "Atualizar Cadastro", "Excluir Conta", "Voltar"
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
                    case 5 -> cancelParticipantAttendance(participant);
                    case 6 -> viewRegistration(participant);
                    case 7 -> updateAccount(participant);
                    case 8 -> deleteAccount(participant);
                    case 9 -> {
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
     * Realiza a inscrição de um participante em um evento.
     *
     * @param participant participante que será inscrito no evento.
     */
    public static void registerParticipantEvent(Participant participant) {
        if (eventService.hasEventsRegistered(event)) {
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
                scanner.nextLine();

                if (eventService.isEmpty(event)) {
                    System.out.println("ID do evento inválido! Tente novamente.");
                    return;
                }

                if (participantService.isEventRegistered(participant, event)) {
                    System.out.println("Evento já inscrito anteriormente. Tente novamente.");
                    return;
                }

                Ticket ticket;
                try {
                    ticket = ticketView.buyTicket(participant, event);
                } catch (NoStudentIdException e) {
                    System.out.println(e.getMessage());
                    return;
                }

                if (ticket == null) {
                    return;
                }

                try {
                    eventService.addParticipantEvent(participant, event, ticket);
                } catch (EventFullException e) {
                    System.err.println(e.getMessage());
                    return;
                }

                participantService.addEventParticipant(event, participant);
                break;
            } catch (InputMismatchException e) {
                System.out.println("[ERRO]: Digite um número!");
                scanner.nextLine();
            }
        } while (true);
    }

    /**
     * Exibe os eventos disponíveis para inscrição de um participante.
     *
     * @param participant participante que visualizará os eventos disponíveis.
     */
    public static void viewAvailableEvents(Participant participant) {
        try {
            participantRepository.readParticipantEventsNotRegistered(participant);
        } catch (EventNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Confirma a presença de um participante em um evento.
     *
     * @param participant participante que terá a presença confirmada.
     * @throws EventNotFoundException caso o evento não seja encontrado.
     * @throws ParticipantEventNotFoundException caso o participante não possua o evento informado.
     */
    public static void confirmAttendance(Participant participant) throws EventNotFoundException, ParticipantEventNotFoundException {
        System.out.println("      Confirmar Presença de Participante\n--------------------------------------------");

        if (eventService.hasEventsRegistered(event)) {
            System.out.println("Nenhum evento cadastrado anteriormente.");
            return;
        }

        try {
            participantService.hasParticipantEventsRegistered(participant);
        } catch (EventNotFoundException e) {
            System.err.println(e.getMessage());
            return;
        }

        do {
            try {
                System.out.println("--------------------------------------------");

                participantRepository.readParticipantEventsNotConfirmed(participant);

                if (participantRepository.getCounterConfirmedEvents() == 0) {
                    System.out.println("Nenhum evento pendente para confirmação.");
                    return;
                } else {
                    participantRepository.setCounterConfirmedEvents(0);
                }

                System.out.println("Digite o ID do evento para confirmar presença:");
                int eventId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("--------------------------------------------");
                Event event = eventRepository.findEventById(eventId);

                if (eventService.isEmpty(event) || !participant.getEvents().contains(event)) {
                    System.out.println("ID do evento inválido! Tente novamente.");
                    return;
                }

                System.out.println("Você deseja confirmar presença no evento '" + event.getName() + "'? (s/n)");
                String response = scanner.nextLine().toLowerCase().strip();

                if (response.equalsIgnoreCase("s") || response.equalsIgnoreCase("sim")) {
                    participantService.confirmAttendance(participant, event);
                    System.out.println("Presença confirmada com sucesso!");
                    break;
                } else if (response.equalsIgnoreCase("n") || response.equalsIgnoreCase("nao") || response.equalsIgnoreCase("não")) {
                    break;
                } else {
                    System.out.println("Resposta inválida! Tente novamente.");
                }

            } catch (InputMismatchException e) {
                System.out.println("[ERRO]: Digite um número!");
                scanner.nextLine();
            }
        } while (true);
    }

    /**
     * Exibe o histórico de eventos cadastrados de um participante.
     *
     * @param participant participante cujo histórico de eventos será exibido.
     */
    public void historyRegisteredEvents(Participant participant) {
        System.out.println("      Histórico de Eventos Inscritos\n--------------------------------------------");
        try {
            participantRepository.readParticipantEvents(participant);
        } catch (EventNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Cancela a presença de um participante em um evento.
     *
     * @param participant participante que terá a presença cancelada.
     */
    public void cancelParticipantAttendance(Participant participant) {
        System.out.println("      Cancelar Presença de Participante\n--------------------------------------------");

        if (eventService.hasEventsRegistered(event)) {
            System.out.println("Nenhum evento cadastrado anteriormente.");
            return;
        }

        try {
            participantService.hasParticipantEventsRegistered(participant);
        } catch (EventNotFoundException e) {
            System.err.println(e.getMessage());
            return;
        }

        do {
            try {
                System.out.println("--------------------------------------------");
                participantRepository.readParticipantEventsNotCanceled(participant);

                if (participantRepository.getCounterCanceledEvents() == 0) {
                    System.out.println("Nenhum evento disponível para cancelar.");
                    return;
                } else {
                    participantRepository.setCounterCanceledEvents(0);
                }

                System.out.println("Digite o ID do evento para cancelar presença:");
                int eventId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("--------------------------------------------");
                Event event = eventRepository.findEventById(eventId);

                if (eventService.isEmpty(event)) {
                    System.out.println("ID do evento inválido! Tente novamente.");
                    return;
                }

                if (!participantService.isEventRegistered(participant, event)) {
                    System.out.println("ID do evento inválido! Tente novamente.");
                    return;
                }

                System.out.println("Você deseja cancelar presença no evento '" + eventRepository.findEventById(eventId).getName() + "'? (s/n)");
                String response = scanner.nextLine().toLowerCase().trim();

                if (response.equalsIgnoreCase("s") || response.equalsIgnoreCase("sim")) {
                    participantService.cancelAttendance(participant, event);
                    System.out.println("Presença cancelada com sucesso!");
                    break;
                } else if (response.equalsIgnoreCase("n") || response.equalsIgnoreCase("nao") || response.equalsIgnoreCase("não")) {
                    break;
                } else {
                    System.out.println("Resposta inválida! Tente novamente.");
                }

            } catch (InputMismatchException e) {
                System.out.println("[ERRO]: Digite um número!");
                scanner.nextLine();
            }
        } while (true);
    }

    /**
     * Exibe as inscrições realizadas por um participante.
     *
     * @param participant participante que terá suas inscrições exibidas.
     */
    public static void viewRegistration(Participant participant) {
        System.out.println("           Visualizar Cadastro\n--------------------------------------------");
        System.out.println(participant);
    }

    /**
     * Atualiza as informações da conta de um participante.
     *
     * @param participant participante que terá os dados atualizados.
     */
    public void updateAccount(Participant participant) {
        System.out.println("           Atualizar Participante\n--------------------------------------------");

        String[] menu = {
                "Atualizar Nome", "Atualizar Contato",
                "Atualizar E-mail", "Atualizar Senha",
                "Voltar"
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

    /**
     * Atualiza o nome de um participante.
     *
     * @param participant participante que terá o nome atualizado.
     */
    public static void updateName(Participant participant) {
        do {
            System.out.println("Digite o novo nome para atualizar:");
            String newName = scanner.nextLine();
            boolean isNewNameValidated = participantService.validateName(newName);

            if (!isNewNameValidated) {
                return;
            }

            if (!newName.equalsIgnoreCase(participant.getName())) {
                participantRepository.updateParticipant(participant, newName, "Nome");
                break;
            } else {
                System.out.println("Novo nome inválido! Tente novamente.");
            }
        } while (true);
    }

    /**
     * Atualiza o contato de um participante.
     *
     * @param participant participante que terá o contato atualizado.
     */
    public static void updateContact(Participant participant) {
        do {
            System.out.println("Digite o novo contato para atualizar:");
            String newContact = scanner.nextLine();
            boolean isNewContactValidated = participantService.validateContact(newContact);

            if (!isNewContactValidated) {
                return;
            }

            participantService.formatContact(newContact);

            if (!isEqualUtil.isEqual(newContact, participant.getContact())) {
                participantRepository.updateParticipant(participant, newContact, "Contato");
                break;
            } else {
                System.out.println("Novo contato inválido! Tente novamente.");
            }
        } while (true);
    }

    /**
     * Atualiza o e-mail de um participante.
     *
     * @param participant participante que terá o e-mail atualizado.
     */
    public static void updateEmail(Participant participant) {
        do {
            System.out.println("Digite o novo email para atualizar:");
            String newEmail = scanner.nextLine();
            boolean isNewEmailValidated = participantService.validateEmail(newEmail);

            if (!isNewEmailValidated) {
                return;
            }

            if (!newEmail.equalsIgnoreCase(participant.getEmail())) {
                participantRepository.updateParticipant(participant, newEmail, "E-mail");
                break;
            } else {
                System.out.println("Novo e-mail inválido! Tente novamente.");
            }
        } while (true);
    }

    /**
     * Atualiza a senha de um participante.
     *
     * @param participant participante que terá a senha atualizada.
     */
    public static void updatePassword(Participant participant) {
        do {
            System.out.println("Digite a nova senha para atualizar:");
            String newPassword = scanner.nextLine();
            boolean isNewPasswordValidated = participantService.validatePassword(newPassword);

            if (!isNewPasswordValidated) {
                return;
            }

            if (!newPassword.equalsIgnoreCase(participant.getPassword())) {
                participantRepository.updateParticipant(participant, newPassword, "Senha");
                break;
            } else {
                System.out.println("Nova senha inválida! Tente novamente.");
            }
        } while (true);
    }

    /**
     * Remove a conta de um participante do sistema.
     *
     * @param participant participante que terá a conta removida.
     */
    public static void deleteAccount(Participant participant) {
        System.out.println("           Excluir Conta\n--------------------------------------------");

        do {
            System.out.println("Você tem certeza que deseja excluir sua conta? (s/n)");
            String response = scanner.nextLine().toLowerCase();

            if (response.equalsIgnoreCase("s") || response.equalsIgnoreCase("sim")) {
                participantRepository.deleteParticipant(participant);
                break;
            } else if (response.equalsIgnoreCase("n") || response.equalsIgnoreCase("nao") || response.equalsIgnoreCase("não")) {
                break;
            } else {
                System.out.println("Resposta inválida! Tente novamente.");
            }
        } while (true);
    }

}
