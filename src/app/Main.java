import model.Event;
import model.Participant;
import repository.EventRepository;
import repository.ParticipantRepository;
import service.EventService;
import service.ParticipantService;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;

public static final Scanner scanner = new Scanner(System.in);
public static EventRepository eventRepository = new EventRepository();
public static EventService eventService = new EventService();
public static ParticipantRepository participantRepository = new ParticipantRepository();
public static ParticipantService participantService = new ParticipantService();

// Eventos ->
public static List<Integer> idEventoCadastrado = new ArrayList<>();
public static List<String> nomeEventoCadastrado = new ArrayList<>();
public static List<String> dataEventoCadastrado = new ArrayList<>();
public static List<String> localEventoCadastrado = new ArrayList<>();
public static List<Integer> capacidadeEventoCadastrado = new ArrayList<>();
public static int totalEventosCadastrados = 0;

// Participantes ->
public static List<Integer> idParticipanteCadastrado = new ArrayList<>();
public static List<String> nomeParticipanteCadastrado = new ArrayList<>();
public static List<Integer> contatoParticipateCadastrado = new ArrayList<>();
public static int totalParticipantesCadastrados = 0;

// Eventos e Participantes ->
public static List<Integer> participanteCadastradoEvento = new ArrayList<>();
public static int[] numeroParticipantesCadastradosEvento = new int[100];

void main() {
    login();
    String[] menu = {
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
                case 5 -> confirmarPresencaParticipante();
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
    String name;
    String date;
    String location;
    int capacity;

    do {
        System.out.println("Digite o nome do evento:");
        name = scanner.nextLine();
        boolean validatedName = eventService.validateName(name);
        if (validatedName) {
            break;
        } else {
            System.out.println("Nome inválido! Tente novamente.");
        }
    } while (true);

    do {
        System.out.println("Digite a data do evento:");
        date = scanner.nextLine();
        boolean validatedDate = eventService.validateDate(date);
        if (validatedDate) {
            break;
        } else {
            System.out.println("Data inválida! Tente novamente.");
        }
    } while (true);

    do {
        System.out.println("Digite o local do evento:");
        location = scanner.nextLine();
        boolean validatedLocation = eventService.validateLocation(location);
        if (validatedLocation) {
            break;
        } else {
            System.out.println("Local inválido! Tente novamente.");
        }
    } while (true);

    do {
        try {
            System.out.println("Digite a capacidade do evento:");
            capacity = scanner.nextInt();
            boolean capacityValidated = eventService.validateCapacity(capacity);
            if (capacityValidated) {
                break;
            } else {
                System.out.println("Capacidade inválida! Tente novamente.");
            }
        } catch (InputMismatchException e) {
            System.err.println("[ERRO]: Digite um número!");
            scanner.nextLine();
        }
    } while (true);

    Event event = new Event(name, date, location, capacity);
    eventRepository.createEvent(event);

//    Event event2 = new Event("Verão Massayo - Show do Teto", "17/01/2026", "Estacionamento Jaraguá", 30000);
//    Event event3 = new Event("Verão Massayo - Show do Wiu", "17/01/2026", "Estacionamento Jaraguá", 30000);
//    Event event4 = new Event("Verão Massayo - Show do Brandão", "17/01/2026", "Estacionamento Jaraguá", 30000);

//    Event event1 = new Event("V", "17/01/2026", "Estacionamento Jaraguá", 30000);
//    eventRepository.createEvent(event1);
//    eventRepository.createEvent(event2);
//    eventRepository.createEvent(event3);
//    eventRepository.createEvent(event4);

    System.out.println("Evento '" + event.getName() + "' cadastrado com sucesso!");
}

public static void listarEventos(int opcao) {
    if (totalEventosCadastrados == 0) {
        System.out.println("Nenhum evento cadastrado anteriormente.");
        return;
    }

    for (int i = 0; i < totalEventosCadastrados; i++) {
        System.out.println("Evento " + (i + 1) + ":");
        System.out.println("Nome: " + nomeEventoCadastrado.get(i));
        System.out.println("Data: " + dataEventoCadastrado.get(i));
        System.out.println("Local: " + localEventoCadastrado.get(i));
        System.out.println("Capacidade: " + capacidadeEventoCadastrado.get(i));

        if (totalParticipantesCadastrados != 0) {
            System.out.println("Número de Participantes: " + numeroParticipantesCadastradosEvento[i]);
        } else {
            System.out.println("Número de Participantes: " + 0);
        }
        System.out.println("--------------------------------------------");
    }

    if (opcao == 2) {
        System.out.println("Eventos listados com sucesso!");
    }
}

public static void registerParticipant() {
    String name;
    int contact;

    do {
        System.out.println("Digite o nome do participante:");
        name = scanner.nextLine();
        boolean validatedName = participantService.validateName(name);
        if (validatedName) {
            break;
        } else {
            System.out.println("Nome inválido! Tente novamente.");
        }
    } while (true);

    do {
        try {
            System.out.println("Digite o contato do participante:");
            contact = scanner.nextInt();
            scanner.nextLine();
            boolean validatedContact = participantService.validateContact(contact);
            if (validatedContact) {
                break;
            } else {
                System.out.println("Contato inválido! Tente novamente.");
            }
        } catch (InputMismatchException e) {
            System.err.println("[ERRO]: Digite um número!");
            scanner.nextLine();
        }
    } while (true);

    Participant participant = new Participant(name, contact);
    participantRepository.createParticipant(participant);

//    Participant participant1 = new Participant("Vinícius", 998271900);
//    Participant participant2 = new Participant("João Victor", 987593594);
//    Participant participant3 = new Participant("Ricardo", 999175344);
//    Participant participant4 = new Participant("Ângela", 999223567);

//    participantRepository.createParticipant(participant1);
//    participantRepository.createParticipant(participant2);
//    participantRepository.createParticipant(participant3);
//    participantRepository.createParticipant(participant4);

    System.out.println("Participante '" + participant.getName() + "' cadastrado com sucesso!");
}

public static void inscreverParticipante() {
    if (totalEventosCadastrados == 0) {
        System.out.println("Nenhum evento cadastrado anteriormente.");
        return;
    }

    System.out.println("           Inscrever Participante\n--------------------------------------------");
    registerParticipant();
    do {
        System.out.println("--------------------------------------------");
        listarEventos(0);
        System.out.println("Digite o ID do evento para inscrever o participante:");
        int idEvento = scanner.nextInt();
        System.out.println("--------------------------------------------");

        if (idEventoCadastrado.contains(idEvento)) {
            if (numeroParticipantesCadastradosEvento[idEvento - 1] < capacidadeEventoCadastrado.get(idEvento - 1)) {
                numeroParticipantesCadastradosEvento[idEvento - 1]++;
                participanteCadastradoEvento.add(idEvento - 1);
                totalParticipantesCadastrados++;
                idParticipanteCadastrado.add(totalParticipantesCadastrados);

                nomeParticipanteCadastrado.add(nome);
                contatoParticipateCadastrado.add(contato);
                System.out.println("Participante " + nome + " inscrito no evento " + nomeEventoCadastrado.get(idEvento - 1) + " com sucesso!");
            } else {
                System.out.println("Evento lotado! Não foi possível inscrever o participante.");
            }
            break;

        } else {
            System.out.println("ID do evento inválido! Tente novamente.");
        }
    } while (true);
}

public static void exibirParticipantesInscritos(int opcao) {
    if (totalParticipantesCadastrados == 0) {
        System.out.println("Nenhum participante cadastrado anteriormente.");
        return;
    }

    for (int i = 0; i < totalParticipantesCadastrados; i++) {
        System.out.println("Participante " + (i + 1));
        System.out.println("Nome: " + nomeParticipanteCadastrado.get(i));
        System.out.println("Contato: " + contatoParticipateCadastrado.get(i));
        System.out.println("Evento: " +  nomeEventoCadastrado.get(participanteCadastradoEvento.get(i)));
        System.out.println("--------------------------------------------");
    }

    if (opcao == 4) {
        System.out.println("Participantes inscritos exibidos com sucesso!");
    }
}

public static void confirmarPresencaParticipante() {
    if (totalParticipantesCadastrados == 0) {
        System.out.println("Nenhum participante cadastrado anteriormente.");
        return;
    }

    int idParticipante;
    do {
        try {
            System.out.println("      Confirmar Presença de Participante\n--------------------------------------------");
            exibirParticipantesInscritos(0);
            System.out.println("Digite o ID do participante:");
            idParticipante = scanner.nextInt();
            scanner.nextLine();
            break;
        } catch (InputMismatchException e) {
            System.out.println("[ERRO]: Digite um número!");
        }
    } while (true);

    do {
        if (idParticipanteCadastrado.contains(idParticipante)) {
            System.out.println("Você deseja confirmar presença do participante " + nomeParticipanteCadastrado.get(idParticipante - 1) + "? (s/n)");
            String resposta = scanner.nextLine();
            resposta = resposta.toLowerCase();

            if (resposta.equalsIgnoreCase("s") || resposta.equalsIgnoreCase("sim")) {
                System.out.println("Presença confirmada com sucesso!");
                break;
            } else if (resposta.equalsIgnoreCase("n") || resposta.equalsIgnoreCase("não") || resposta.equalsIgnoreCase("nao")) {
                System.out.println("Presença não foi confirmada.");
                break;
            } else {
                System.out.println("Resposta inválida! Tente novamente.");
            }
            break;

        } else {
            System.out.println("ID do participante inválido! Tente novamente.\n--------------------------------------------");
        }
    } while (true);
}

public static void confirmParticipantAttendance() {
    int participant_id = selectParticipant();

}

public static int selectParticipant() {
    participantRepository.readParticipant();
    System.out.println("Digite o ID do participante:");
    int participant_id = scanner.nextInt();
    return participant_id;
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