package view;

import model.Ticket;
import repository.TicketRepository;
import service.TicketService;
import util.IsEqualUtil;
import util.ValidateUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicketView {

    private static final Scanner scanner = new Scanner(System.in);

    private static final ValidateUtil validateUtil = new ValidateUtil();
    private static final IsEqualUtil isEqualUtil = new IsEqualUtil();

    private static final TicketRepository ticketRepository = new TicketRepository();
    private static final TicketService ticketService = new TicketService();

    public void panel() {
        String[] menu = {
                "Cadastrar Ingresso", "Listar Ingressos", "Buscar Ingresso", "Atualizar Ingresso", "Remover Ingresso", "Voltar"
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
                    case 1 -> registerTicket();
                    case 2 -> ticketRepository.readTickets();
                    case 3 -> findTicket();
                    case 4 -> updateTicket();
                    case 5 -> removeTicket();
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

    public static void registerTicket() {
        System.out.println("              Cadastrar Ingresso\n--------------------------------------------");

//        String name;
//        String description;
//        double price;
//
//        do {
//            try {
//                String[] menu = new String[] {"Ingresso Padrão", "Ingresso Grátis", "Ingresso Meia-Entrada", "Ingresso Vip"};
//
//                for (int i = 0; i < menu.length; i++) {
//                    System.out.println((i + 1) + menu[i]);
//                }
//
//                System.out.println("Digite o tipo do ingresso:");
//                int ticketType = scanner.nextInt();
//                break;
//
//            } catch (InputMismatchException e) {
//                System.out.println("[ERRO]: Digite um número!");
//            }
//        } while (true);
//
//        do {
//            System.out.println("Digite o nome do ingresso:");
//            name = scanner.nextLine();
//            boolean validatedName = validateUtil.validateName(name);
//
//            if (validatedName) {
//                break;
//            } else {
//                System.out.println("Nome inválido! Tente novamente.");
//            }
//        } while (true);
//
//        do {
//            System.out.println("Digite a descrição do ingresso:");
//            description = scanner.nextLine();
//            boolean validatedDescription = ticketService.validateDescription(description);
//
//            if (validatedDescription) {
//                break;
//            } else {
//                System.out.println("Descrição inválida! Tente novamente.");
//            }
//        } while (true);
//
//        do {
//            try {
//                System.out.println("Digite o preço do ingresso:");
//                price = scanner.nextDouble();
//                boolean validatedPrice = ticketService.validatePrice(price);
//
//                if (validatedPrice) {
//                    break;
//                } else {
//                    System.out.println("Preço inválido! Tente novamente.");
//                }
//            } catch (InputMismatchException e) {
//                System.err.println("[ERRO]: Digite um número!");
//                scanner.nextLine();
//            }
//        } while (true);

//        TicketDefault ticketDefault = new TicketDefault(name, description, price);
//        ticketRepository.createTicket(ticketDefault);

        Ticket ticket = new Ticket("Ingresso", "Descrição do Ingresso", 100d);
        ticketRepository.createTicket(ticket);

//        TicketDefault ticketDefault = new TicketDefault("Ingresso Padrão", "Descrição do Ingresso Padrão", 100d);
//        TicketFree ticketFree = new TicketFree("Ingresso Grátis", "Descrição do Ingresso Grátis", 0d);
//        TicketHalfPrice ticketHalfPrice = new TicketHalfPrice("Ingresso Meia-Entrada", "Descrição do Ingresso Meia-Entrada", 50d);
//        TicketVip ticketVip = new TicketVip("Ingresso Vip", "Descrição do Ingresso Vip", 200d);

//        ticketRepository.createTicket(ticketDefault);
//        ticketRepository.createTicket(ticketFree);
//        ticketRepository.createTicket(ticketHalfPrice);
//        ticketRepository.createTicket(ticketVip);

//        System.out.println("Ingresso '" + ticketDefault.getName() + "' cadastrado com sucesso!");

        System.out.println("Ingresso '" + ticket.getName() + "' cadastrado com sucesso!");
    }

    public static void findTicket() {
        System.out.println("           Buscar Ingresso\n--------------------------------------------");
        Ticket ticket = chooseTicket("buscar");

//        try {
//            TicketService.hasTicket(Ticket);
//        } catch (TicketNotFoundException e) {
//            System.out.println(e.getMessage());
//            return;
//        }

        System.out.println(ticket);
    }

    public static void updateTicket() {
        System.out.println("           Atualizar Ingresso\n--------------------------------------------");
        Ticket Ticket = chooseTicket("atualizar");

//        try {
//            TicketService.hasTicket(Ticket);
//        } catch (TicketNotFoundException e) {
//            System.out.println(e.getMessage());
//            return;
//        }

        System.out.println("\n--------------------------------------------\n" + Ticket + "\n--------------------------------------------\n");

        String[] menu = {
                "Atualizar Nome", "Atualizar Descrição",
                "Atualizar Preço", "Voltar"
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
                    case 1 -> updateName(Ticket);
                    case 2 -> updateDescription(Ticket);
                    case 3-> updatePrice(Ticket);
//                    case 4 -> updateType(Ticket);
                    case 4 -> {
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

    public static void updateName(Ticket ticket) {
        do {
            System.out.println("Digite o novo nome para atualizar:");
            String newName = scanner.nextLine();
            boolean isNewNameValidated = validateUtil.validateName(newName);

            if (!isNewNameValidated) {
                return;
            }

            if (!newName.equalsIgnoreCase(ticket.getName())) {
                ticketRepository.updateTicket("Nome", ticket, newName);
                break;
            } else {
                System.out.println("Novo nome inválido! Tente novamente.");
            }
        } while (true);
    }
    
    public static void updateDescription(Ticket ticket) {
        do {
            System.out.println("Digite a nova descrição para atualizar:");
            String newDescription = scanner.nextLine();
            boolean isNewDescriptionValidated = ticketService.validateDescription(newDescription);

            if (!isNewDescriptionValidated) {
                return;
            }

            if (!newDescription.equalsIgnoreCase(ticket.getDescription())) {
                ticketRepository.updateTicket("Descrição", ticket, newDescription);
                break;
            } else {
                System.out.println("Nova descrição inválida! Tente novamente.");
            }
        } while (true);
    }

    public static void updatePrice(Ticket ticket) {
        do {
            try {
                System.out.println("Digite o novo preço para atualizar:");
                double newPrice = scanner.nextInt();
                boolean validatedNewPrice = ticketService.validatePrice(newPrice);

                if (!validatedNewPrice) {
                    return;
                }

                if (!isEqualUtil.isEqual(newPrice, ticket.getPrice())) {
                    ticketRepository.updateTicket("Preço", ticket, newPrice);
                    break;
                } else {
                    System.out.println("Nova capacidade inválida! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERR]: Digite um número!");
            }
        } while (true);
    }

    public static void removeTicket() {
        System.out.println("           Deletar Ingresso\n--------------------------------------------");
        Ticket ticket = chooseTicket("deletar");

//        try {
//            TicketService.hasTicket(Ticket);
//        } catch (TicketNotFoundException e) {
//            System.out.println(e.getMessage());
//            return;
//        }

        ticketRepository.deleteTicket(ticket);
    }

    public static Ticket chooseTicket(String action) {
        do {
            try {
                System.out.println("Digite o ID do ingresso para " + action + ':');
                int ticketId = scanner.nextInt();
                return ticketRepository.findTicketById(ticketId);
            } catch (InputMismatchException e) {
                System.out.println("[ERRO]: Digite um número!");
            }
        } while (true);
    }

}
