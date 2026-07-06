package view;

import exceptions.EventNotFoundException;
import model.Event;
import repository.EventRepository;
import service.EventService;
import util.DateTimeUtil;
import util.IsEqualUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

import static view.TicketView.addTicket;

public class EventView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final IsEqualUtil isEqualUtil = new IsEqualUtil();

    public static final Event event = new Event();
    public static final EventRepository eventRepository = new EventRepository();
    public static final EventService eventService = new EventService();

    public static DateTimeUtil dateTimeUtil = new DateTimeUtil();

    public void panel() {
        String[] menu = {
                "Cadastrar Evento", "Listar Eventos",
                "Buscar Evento", "Atualizar Evento",
                "Remover Evento", "Adicionar Ingresso ao Evento",
                "Voltar"
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
                    case 1 -> registerEvent();
                    case 2 -> eventRepository.readEvents();
                    case 3 -> findEvent();
                    case 4 -> updateEvent();
                    case 5 -> removeEvent();
                    case 6 -> addTicket();
                    case 7 -> {
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
//            String dateTime;
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

//            do {
//                try {
//                    System.out.println("Digite a data e hora do evento: (dd/MM/yyyy HH:mm)");
//                    dateTime = scanner.nextLine();
//                    LocalDateTime dateTimeConverted = dateTimeUtil.convertDateTime(dateTime);
//                    break;
//                } catch (DateTimeParseException e) {
//                    System.out.println("[ERRO]: Horário inválido. Tente: 'dd/MM/yyyy HH:mm'.");
//                }
//            } while (true);

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

        //    Event event = new Event(name, dateTimeConverted, location, capacity);
        //    eventRepository.createEvent(event);

        Event event1 = new Event("Verão Massayo - Show do Matuê", dateTimeUtil.convertDateTime("17/01/2026 21:10"), "Estacionamento Jaraguá", 30000);
        Event event2 = new Event("Verão Massayo - Show do Teto", dateTimeUtil.convertDateTime("17/01/2026 21:10"), "Estacionamento Jaraguá", 30000);
        Event event3 = new Event("Verão Massayo - Show do Wiu", dateTimeUtil.convertDateTime("17/01/2026 21:10"), "Estacionamento Jaraguá", 30000);
        Event event4 = new Event("Verão Massayo - Show do Brandão", dateTimeUtil.convertDateTime("17/01/2026 21:10"), "Estacionamento Jaraguá", 30000);

        eventRepository.createEvent(event1);
        eventRepository.createEvent(event2);
        eventRepository.createEvent(event3);
        eventRepository.createEvent(event4);

        System.out.println("Evento '" + event1.getName() + "' cadastrado com sucesso!");
    }

    public static void findEvent() {
        System.out.println("           Buscar Evento\n--------------------------------------------");
        Event event = chooseEvent("buscar");

        try {
            eventService.hasEvent(event);
        } catch (EventNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println(event);
    }

    public static void updateEvent() {
        System.out.println("           Atualizar Evento\n--------------------------------------------");
        Event event = chooseEvent("atualizar");

        try {
            eventService.hasEvent(event);
        } catch (EventNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("\n--------------------------------------------\n" + event + "\n--------------------------------------------\n");

        String[] menu = {
                "Atualizar Nome", "Atualizar Data e Hora",
                "Atualizar Local", "Atualizar Capacidade", "Voltar"
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
            boolean isNewNameValidated = eventService.validateName(newName);

            if (!isNewNameValidated) {
                return;
            }

            if (!newName.equalsIgnoreCase(event.getName())) {
                eventRepository.updateEvent(event.getEventId(), newName, "Nome");
                break;
            } else {
                System.out.println("Novo nome inválido! Tente novamente.");
            }
        } while (true);
    }

    public static void updateDateTime(Event event) {
        do {
            try {
                System.out.println("Digite o novo horário para atualizar:");
                String newDateTime = scanner.nextLine();
                LocalDateTime validatedNewDateTime = dateTimeUtil.convertDateTime(newDateTime);

                if (!isEqualUtil.isEqual(validatedNewDateTime, event.getDateTime())) {
                    eventRepository.updateEvent(event.getEventId(), validatedNewDateTime, "Horário");
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Novo horário inválido! Tente novamente.");
            }
        } while (true);
    }

    public static void updateLocation(Event event) {
        do {
            System.out.println("Digite o novo local para atualizar:");
            String newLocation = scanner.nextLine();
            boolean isNewLocationValidated = eventService.validateLocation(newLocation);

            if (!isNewLocationValidated) {
                return;
            }

            if (!newLocation.equalsIgnoreCase(event.getLocation())) {
                eventRepository.updateEvent(event.getEventId(), newLocation, "Local");
                break;
            } else {
                System.out.println("Novo local inválido! Tente novamente.");
            }
        } while (true);
    }

    public static void updateCapacity(Event event) {
        do {
            try {
                System.out.println("Digite a nova capacidade para atualizar:");
                int newCapacity = scanner.nextInt();
                boolean validatedNewCapacity = eventService.validateCapacity(newCapacity);

                if (!validatedNewCapacity) {
                    return;
                }

                if (!isEqualUtil.isEqual(newCapacity, event.getDateTime())) {
                    eventRepository.updateEvent(event.getEventId(), newCapacity, "Local");
                    break;
                } else {
                    System.out.println("Nova capacidade inválida! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.err.println("[ERRO]: Digite um número!");
            }
        } while (true);
    }

    public static void removeEvent() {
        System.out.println("           Deletar Evento\n--------------------------------------------");
        Event event = chooseEvent("deletar");

        try {
            eventService.hasEvent(event);
        } catch (EventNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        eventRepository.deleteEvent(event);
    }

    // TODO: Maybe put the exception just here to not use in the others methods
    public static Event chooseEvent(String action) {
        do {
            try {
                System.out.println("Digite o ID do evento para " + action + ':');
                int eventId = scanner.nextInt();
                return eventRepository.findEventById(eventId);
            } catch (InputMismatchException e) {
                System.err.println("[ERRO]: Digite um número!");
            }
        } while (true);
    }

}
