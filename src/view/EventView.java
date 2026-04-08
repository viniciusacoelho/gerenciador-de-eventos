package view;

import model.Event;
import repository.EventRepository;
import repository.ParticipantRepository;
import service.EventService;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EventView {

    private static final Scanner scanner = new Scanner(System.in);

    public static Event event = new Event();
    public static EventRepository eventRepository = new EventRepository();
    public static EventService eventService = new EventService();

    public static ParticipantRepository participantRepository = new ParticipantRepository();

    public void panel() {
        String[] menu = {
                "Cadastrar Evento", "Listar Eventos", "Buscar Evento", "Atualizar Evento", "Remover Evento", "Voltar"
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
                    case 1 -> registerEvent();
                    case 2 -> eventRepository.readEvents();
                    case 3 -> findEvent();
                    case 4 -> updateEvent();
                    case 5 -> removeEvent();
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

        Event event1 = new Event("Verão Massayo - Show do Matuê", dateTimeConverted1, "Estacionamento Jaraguá", 2);
        Event event2 = new Event("Verão Massayo - Show do Teto", dateTimeConverted2, "Estacionamento Jaraguá", 30000);
        Event event3 = new Event("Verão Massayo - Show do Wiu", dateTimeConverted3, "Estacionamento Jaraguá", 30000);
        Event event4 = new Event("Verão Massayo - Show do Brandão", dateTimeConverted4, "Estacionamento Jaraguá", 30000);

        eventRepository.createEvent(event1);
        eventRepository.createEvent(event2);
        eventRepository.createEvent(event3);
        eventRepository.createEvent(event4);

        System.out.println("Evento '" + event1.getName() + "' cadastrado com sucesso!");
    }

    public static void findEvent() {
        System.out.println("           Buscar Evento\n--------------------------------------------");
        Event event = chooseEvent("buscar");

        if (event == null) {
            System.out.println("Nome do evento não encontrado.");
            return;
        }

        System.out.println(event);
    }

    public static void updateEvent() {
        System.out.println("           Atualizar Evento\n--------------------------------------------");
        Event event = chooseEvent("atualizar");

        if (event == null) {
            System.out.println("Evento não encontrado.");
            return;
        }

        System.out.println("\n--------------------------------------------\n" + event + "\n--------------------------------------------\n");

        String[] menu = {
                "Atualizar Nome", "Atualizar Data e Hora", "Atualizar Local", "Atualizar Capacidade", "Voltar"
        };

        do {
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
                    case 1 -> updateName(event);
                    case 2 -> updateDateTime(event);
                    case 3-> updateLocation(event);
                    case 4 -> updateCapacity(event);
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

    public static void updateName(Event event) {
        do {
            System.out.println("Digite o novo nome para atualizar:");
            String newName = scanner.nextLine();
            boolean validatedNewName = eventService.validateName(newName);

            if (validatedNewName && !event.getName().equalsIgnoreCase(newName)) {
                eventRepository.updateEvent(event.getEventId(), newName, "Nome");
                break;
            } else {
                System.out.println("Novo nome inválido! Tente novamente.");
            }
        } while (true);
    }

//    TODO: Fix ->
    public static void updateDateTime(Event event) {
//        do {
//            System.out.println("Digite o novo horário para atualizar:");
//            String newDateTime = scanner.nextLine();
//            LocalDateTime validatedNewDateTime = eventService.convertDateTime(newDateTime);
//            LocalDateTime validatedNewDateTime2 = LocalDateTime.parse(eventService.formatDateTime(validatedNewDateTime));
//            if (!event.getName().equalsIgnoreCase(String.valueOf(validatedNewDateTime))) {
//                if (validatedNewDateTime2) {
//                    eventRepository.updateEvent(event.getEventId(), validatedNewDateTime2, "Horário");
//                    break;
//                } else {
//                    System.out.println("Novo horário inválido! Tente novamente.");
//                }
//            } else {
//                System.out.println("Novo horário inválido! Tente novamente.");
//            }
//        } while (true);
    }

    public static void updateLocation(Event event) {
        do {
            System.out.println("Digite o novo local para atualizar:");
            String newLocation = scanner.nextLine();
            boolean validatedNewLocation = eventService.validateLocation(newLocation);

            if (validatedNewLocation && !event.getLocation().equalsIgnoreCase(newLocation)) {
                eventRepository.updateEvent(event.getEventId(), newLocation, "Local");
                break;
            } else {
                System.out.println("Novo local inválido! Tente novamente.");
            }
        } while (true);
    }

//    TODO: Fix ->
    public static void updateCapacity(Event event) {
//        do {
//            System.out.println("Digite a nova capacidade para atualizar:");
//            int newCapacity = scanner.nextInt();
//            boolean validatedNewCapacity = eventService.validateCapacity(newCapacity);
//
//            if (validatedNewCapacity && (event.getCapacity() != newCapacity)) {
//                eventRepository.updateEvent(event.getEventId(), newCapacity, "Local");
//                break;
//            } else {
//                System.out.println("Nova capacidade inválida! Tente novamente.");
//            }
//        } while (true);
    }

    public static void removeEvent() {
        System.out.println("           Deletar Evento\n--------------------------------------------");
        Event event = chooseEvent("deletar");

        if (event == null) {
            System.out.println("Nome do evento não encontrado.");
            return;
        }

        eventRepository.deleteEvent(event);
    }

    public static Event chooseEvent(String action) {
        System.out.println("Digite o ID do evento para " + action + ':');
        int eventId = scanner.nextInt();
        return eventRepository.findEventById(eventId);
    }

}
