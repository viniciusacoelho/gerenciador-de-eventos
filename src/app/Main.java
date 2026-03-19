import enums.Presence;
import model.Event;
import model.Participant;
import repository.EventRepository;
import repository.ParticipantRepository;
import service.EventService;
import service.ParticipantService;

import java.util.InputMismatchException;

public static final Scanner scanner = new Scanner(System.in);

public static Event event = new Event();
public static EventRepository eventRepository = new EventRepository();
public static EventService eventService = new EventService();

public static Participant participant = new Participant();
public static ParticipantRepository participantRepository = new ParticipantRepository();
public static ParticipantService participantService = new ParticipantService();

void main() {
    login();
    String[] menu = {
//    String[] menuAdmin = {
            "Cadastrar Evento", "Listar Eventos", "Inscrever Participante",
            "Exibir Participantes Inscritos", "Confirmar Presença de Participante", "Sair"
    };

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
            int opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.println("--------------------------------------------");

            switch (opcao) {
                case 1 -> registerEvent();
                case 2 -> eventRepository.readEvent();
                case 3 -> registerParticipant();
                case 4 -> participantRepository.readParticipant();
                case 5 -> confirmParticipantAttendance();
                case 6 -> {
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

public static void login() {
    System.out.println("--------------------------------------------");
    System.out.println("                   Login");
    System.out.println("--------------------------------------------");
    System.out.println("Digite seu e-mail:");
    String email = scanner.nextLine();
    System.out.println("Digite sua senha:");
    String password = scanner.nextLine();
    System.out.println("Seja bem-vindo " + email + "!");
}

public static void registerEvent() {
    System.out.println("              Cadastrar Evento\n--------------------------------------------");
//    String name;
//    String dateTime;
//    LocalDateTime dateTimeConverted;
//    String location;
//    int capacity;

//    do {
//        System.out.println("Digite o nome do evento:");
//        name = scanner.nextLine();
//        boolean validatedName = eventService.validateName(name);

//        if (validatedName) {
//            break;
//        } else {
//            System.out.println("Nome inválido! Tente novamente.");
//        }
//    } while (true);

//    do {
//        try {
//            System.out.println("Digite a data e hora do evento: (dd/MM/yyyy HH:mm)");
//            dateTime = scanner.nextLine();
//            dateTimeConverted = eventService.convertDateTime(dateTime);
//            break;
//        } catch (DateTimeParseException e) {
//            System.out.println("[ERRO]: Data inválida. Tente: 'dd/MM/yyyy HH:mm'.");
//        }
//    } while (true);

//    do {
//        System.out.println("Digite o local do evento:");
//        location = scanner.nextLine();
//        boolean validatedLocation = eventService.validateLocation(location);

//        if (validatedLocation) {
//            break;
//        } else {
//            System.out.println("Local inválido! Tente novamente.");
//        }
//    } while (true);

//    do {
//        try {
//            System.out.println("Digite a capacidade do evento:");
//            capacity = scanner.nextInt();
//            boolean validatedCapacity = eventService.validateCapacity(capacity);

//            if (validatedCapacity) {
//                break;
//            } else {
//                System.out.println("Capacidade inválida! Tente novamente.");
//            }
//        } catch (InputMismatchException e) {
//            System.err.println("[ERRO]: Digite um número!");
//            scanner.nextLine();
//        }
//    } while (true);

//    Event event = new Event(name, date, location, capacity);
//    eventRepository.createEvent(event);

    String dateTime1 = "17/01/2026 21:10";
    String dateTime2 = "17/01/2026 21:10";
    String dateTime3 = "17/01/2026 21:10";
    String dateTime4 = "17/01/2026 21:10";

    LocalDateTime dateTimeConverted1 = eventService.convertDateTime(dateTime1);
    LocalDateTime dateTimeConverted2 = eventService.convertDateTime(dateTime2);
    LocalDateTime dateTimeConverted3 = eventService.convertDateTime(dateTime3);
    LocalDateTime dateTimeConverted4 = eventService.convertDateTime(dateTime4);

    Event event1 = new Event("Verão Massayo - Show do Matuê", dateTimeConverted1, "Estacionamento Jaraguá", 30000);
    Event event2 = new Event("Verão Massayo - Show do Teto", dateTimeConverted2, "Estacionamento Jaraguá", 30000);
    Event event3 = new Event("Verão Massayo - Show do Wiu", dateTimeConverted3, "Estacionamento Jaraguá", 30000);
    Event event4 = new Event("Verão Massayo - Show do Brandão", dateTimeConverted4, "Estacionamento Jaraguá", 30000);

    eventRepository.createEvent(event1);
    eventRepository.createEvent(event2);
    eventRepository.createEvent(event3);
    eventRepository.createEvent(event4);

    System.out.println("Evento '" + event1.getName() + "' cadastrado com sucesso!");
}

public static void registerParticipant() {
    System.out.println("           Inscrever Participante\n--------------------------------------------");

//    String name;
//    int contact;

//    do {
//        System.out.println("Digite o nome do participante:");
//        name = scanner.nextLine();
//        boolean validatedName = participantService.validateName(name);

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

    Participant participant1 = new Participant("Vinícius", 998271900);
    Participant participant2 = new Participant("João Victor", 987593594);
    Participant participant3 = new Participant("Ricardo", 999175344);
    Participant participant4 = new Participant("Ângela", 999223567);

    participantRepository.createParticipant(participant1);
    participantRepository.createParticipant(participant2);
    participantRepository.createParticipant(participant3);
    participantRepository.createParticipant(participant4);

    System.out.println("Participante '" + participant1.getName() + "' cadastrado com sucesso!");
    registerParticipantEvent(participant1);
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
            String resposta = scanner.nextLine();
            resposta = resposta.toLowerCase();

            if (resposta.equalsIgnoreCase("s") || resposta.equalsIgnoreCase("sim")) {
                participantRepository.findParticipantById(participantId).setPresence(Presence.CONFIRMED);
                System.out.println("Presença confirmada com sucesso!");
                break;
            } else if (resposta.equalsIgnoreCase("n") || resposta.equalsIgnoreCase("nao") || resposta.equalsIgnoreCase("não")) {
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
// TODO: Create account (Participant)