package view;

import enums.Status;
import enums.TicketType;
import exceptions.EventNotFoundException;
import exceptions.TicketNotFoundException;
import model.*;
import repository.TicketRepository;
import service.TicketService;
import util.IsEqualUtil;
import util.ValidateUtil;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static view.EventView.*;

public class TicketView {

    private static final Scanner scanner = new Scanner(System.in);

    private static final ValidateUtil validateUtil = new ValidateUtil();
    private static final IsEqualUtil isEqualUtil = new IsEqualUtil();

    private static final TicketRepository ticketRepository = new TicketRepository();
    private static final TicketService ticketService = new TicketService();

    public void panel() {
        String[] menu = {
                "Cadastrar Ingresso", "Listar Ingressos",
                "Buscar Ingresso", "Atualizar Ingresso",
                "Remover Ingresso", "Voltar"
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
/*
        Ticket ticket;
        TicketType ticketType = null;
        String name;
        String description;
        double price = 0;
        List<String> benefits = new ArrayList<>();
        boolean studentId = false;

        do {
            for (int i = 0; i < TicketType.values().length; i++) {
                System.out.println((i + 1) + " - " + TicketType.values()[i].getTicketType());
            }
            System.out.println("--------------------------------------------");

            try {
                System.out.println("Digite o tipo do ingresso:");
                int response = scanner.nextInt();

                switch (response) {
                    case 1 -> ticketType = TicketType.TICKET_DEFAULT;
                    case 2 -> ticketType = TicketType.TICKET_FREE;
                    case 3 -> ticketType = TicketType.TICKET_HALF_PRICE;
                    case 4 -> ticketType = TicketType.TICKET_VIP;
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }

                scanner.nextLine();
                break;

            } catch (InputMismatchException e) {
                System.out.println("[ERRO]: Digite um número!");
            }
        } while (true);

        do {
            System.out.println("Digite o nome do ingresso:");
            name = scanner.nextLine().trim();
            boolean validatedName = validateUtil.validateName(name);

            if (validatedName) {
                break;
            } else {
                System.out.println("Nome inválido! Tente novamente.");
            }
        } while (true);

        do {
            System.out.println("Digite a descrição do ingresso:");
            description = scanner.nextLine().trim();
            boolean validatedDescription = ticketService.validateDescription(description);

            if (validatedDescription) {
                break;
            } else {
                System.out.println("Descrição inválida! Tente novamente.");
            }
        } while (true);

        if (ticketType != TicketType.TICKET_FREE) {
            do {
                try {
                    System.out.println("Digite o preço do ingresso:");
                    price = scanner.nextDouble();
                    boolean validatedPrice = ticketService.validatePrice(price);
    
                    if (validatedPrice) {
                        break;
                    } else {
                        System.out.println("Preço inválido! Tente novamente.");
                    }
                } catch (InputMismatchException e) {
                    System.err.println("[ERRO]: Digite um número!");
                    scanner.nextLine();
                }
            } while (true);
        }

        int quantity;
        if (ticketType == TicketType.TICKET_VIP) {
            do {
                try {
                    System.out.println("Quantos benefícios você deseja adicionar?");
                    quantity = scanner.nextInt();
                    boolean validatedQuantity = ticketService.validateQuantity(quantity);

                    if (validatedQuantity) {
                        break;
                    } else {
                        System.out.println("Quantidade inválida! Tente novamente.");
                    }

                } catch (InputMismatchException e) {
                    System.err.println("[ERRO]: Digite um número!");
                    scanner.nextLine();
                }
            } while (true);

            do {
                System.out.println("Digite o benefício:");
                String benefit = scanner.nextLine().strip();
                boolean validatedBenefit = ticketService.validateBenefit(benefit);

                if (validatedBenefit) {
                    benefits.add(benefit);
                    quantity--;
                } else {
                    System.out.println("Benefício inválido! Tente novamente.");
                }

            } while (quantity != 0);
        } else if (ticketType == TicketType.TICKET_HALF_PRICE) {
            do {
                System.out.println("Você tem carteirinha de estudante? (s/n)");
                String response = scanner.nextLine().toLowerCase().strip();

                if (response.equalsIgnoreCase("s") || response.equalsIgnoreCase("sim")) {
                    studentId = true;
                    break;
                } else if (response.equalsIgnoreCase("n") || response.equalsIgnoreCase("nao") || response.equalsIgnoreCase("não")) {
                    studentId = false;
                    break;
                } else {
                    System.out.println("Resposta inválida! Tente novamente.");
                }
            } while (true);
        }

        if (ticketType == TicketType.TICKET_DEFAULT) {
            ticket = new TicketDefault(name, description, price, ticketType);
            ticketRepository.createTicket(ticket);
        } else if (ticketType == TicketType.TICKET_FREE) {
            ticket = new TicketFree(name, description, price, ticketType);
            ticketRepository.createTicket(ticket);
        } else if (ticketType == TicketType.TICKET_HALF_PRICE) {
            ticket = new TicketHalfPrice(name, description, price, ticketType, studentId);
            ticketRepository.createTicket(ticket);
        } else {
            ticket = new TicketVip(name, description, price, ticketType, benefits);
            ticketRepository.createTicket(ticket);
        }
*/
//        if (ticket instanceof TicketVip) {
//            TicketVip ticketVip = new TicketVip("Ingresso", "Descrição do Ingresso", 100d, TicketType.TICKET_VIP, new ArrayList<>(List.of("Benefício 1", "Benefício 2", "Benefício 3")));
//            ticketRepository.createTicket(ticketVip);
//        }

        TicketDefault ticketDefault = new TicketDefault("Ingresso Padrão", "Descrição do Ingresso Padrão", 100d, TicketType.TICKET_DEFAULT);
        TicketFree ticketFree = new TicketFree("Ingresso Grátis", "Descrição do Ingresso Grátis", 0d, TicketType.TICKET_FREE);
        TicketHalfPrice ticketHalfPrice = new TicketHalfPrice("Ingresso Meia-Entrada", "Descrição do Ingresso Meia-Entrada", 50d, TicketType.TICKET_HALF_PRICE, true);
        TicketVip ticketVip = new TicketVip("Ingresso Vip", "Descrição do Ingresso Vip", 200d, TicketType.TICKET_VIP, new ArrayList<>(List.of("Benefício 1", "Benefício 2", "Benefício 3")));

        ticketRepository.createTicket(ticketDefault);
        ticketRepository.createTicket(ticketFree);
        ticketRepository.createTicket(ticketHalfPrice);
        ticketRepository.createTicket(ticketVip);

        System.out.println("Ingresso '" + ticketDefault.getName() + "' cadastrado com sucesso!");

//        System.out.println("Ingresso '" + ticket.getName() + "' cadastrado com sucesso!");
    }

    public static void findTicket() {
        System.out.println("           Buscar Ingresso\n--------------------------------------------");
        Ticket ticket = chooseTicket("buscar");

        try {
            ticketService.hasTicket(ticket);
        } catch (TicketNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println(ticket);
    }

    public static void updateTicket() {
        System.out.println("           Atualizar Ingresso\n--------------------------------------------");
        ticketRepository.readTickets();
        Ticket ticket = chooseTicket("atualizar");

        try {
            ticketService.hasTicket(ticket);
        } catch (TicketNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("\n--------------------------------------------\n");
        System.out.println(ticket);
        if (ticket instanceof TicketVip) {
            ticketService.listBenefits(((TicketVip) ticket).getBenefits());
        }
        System.out.println("\n--------------------------------------------\n");

        String[] menu = getMenu(ticket);

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
                    case 1 -> updateName(ticket);
                    case 2 -> updateDescription(ticket);
                    case 3 -> updatePrice(ticket);
                    case 4 -> updateTicketType(ticket);
                    case 5 -> {
                        assert ticket instanceof TicketVip;
                        updateBenefits((TicketVip) ticket);
                    }
                    case 6 -> {
                        assert ticket instanceof TicketHalfPrice;
                        updateStudentId((TicketHalfPrice) ticket);
                    }
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

    private static String[] getMenu(Ticket ticket) {
        String[] menu;
        if (ticket instanceof TicketHalfPrice) {
            menu = new String[] {
                    "Atualizar Nome", "Atualizar Descrição",
                    "Atualizar Preço", "Atualizar Tipo",
                    "Atualizar Carteirinha de Estudante", "Voltar"
            };
        } else if (ticket instanceof TicketVip) {
            menu = new String[] {
                    "Atualizar Nome", "Atualizar Descrição",
                    "Atualizar Preço", "Atualizar Tipo",
                    "Atualizar Benefício", "Voltar"
            };
        } else {
            menu = new String[] {
                    "Atualizar Nome", "Atualizar Descrição",
                    "Atualizar Preço", "Atualizar Tipo",
                    "Voltar"
            };
        }
        return menu;
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
                ticketRepository.updateTicket(ticket, newName, "Nome");
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
                ticketRepository.updateTicket(ticket, newDescription, "Descrição");
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
                    ticketRepository.updateTicket(ticket, newPrice, "Preço");
                    break;
                } else {
                    System.out.println("Nova capacidade inválida! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERR]: Digite um número!");
            }
        } while (true);
    }

    public static void updateTicketType(Ticket ticket) {
        do {
            try {
                String[] menu = new String[] {"Ingresso Padrão", "Ingresso Grátis", "Ingresso Meia-Entrada", "Ingresso Vip"};

                for (int i = 0; i < menu.length; i++) {
                    System.out.println((i + 1) + menu[i]);
                }

                System.out.println("Digite o ID do novo tipo do ingresso para atualizar:");
                int response = scanner.nextInt();

                TicketType newTicketType = null;
                switch (response) {
                    case 1 -> newTicketType = TicketType.TICKET_DEFAULT;
                    case 2 -> newTicketType = TicketType.TICKET_FREE;
                    case 3 -> newTicketType = TicketType.TICKET_HALF_PRICE;
                    case 4 -> newTicketType = TicketType.TICKET_VIP;
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }

                if (newTicketType == ticket.getTicketType()) {
                    ticketRepository.updateTicket(ticket, newTicketType, "Tipo do ingresso");
                    break;
                } else {
                    System.out.println("Novo tipo do ingresso inválido! Tente novamente.");
                }

                break;

            } catch (InputMismatchException e) {
                System.out.println("[ERRO]: Digite um número!");
            }
        } while (true);
    }

    public static void updateStudentId(TicketHalfPrice ticket) {
        boolean newStudentId = false;

        do {
            System.out.println("Você tem carteirinha de estudante agora? (s/n)");
            String response = scanner.nextLine().toLowerCase().strip();

            if (response.equalsIgnoreCase("s") || response.equalsIgnoreCase("sim")) {
                newStudentId = true;
            } else if (response.equalsIgnoreCase("n") || response.equalsIgnoreCase("nao") || response.equalsIgnoreCase("não")) {
                break;
            } else {
                System.out.println("Resposta inválida! Tente novamente.");
            }

            if (!isEqualUtil.isEqual(newStudentId, ticket.hasStudentId())) {
                ticketRepository.updateTicket(ticket, newStudentId, "Nome");
                break;
            } else {
                System.out.println("Novo nome inválido/já cadastrado! Tente novamente.");
            }

        } while (true);
    }

    public static void updateBenefits(TicketVip ticket) {
        do {
            ticketService.listBenefits(ticket.getBenefits());
            System.out.println("Digite o ID no benefício para atualizar:");
            int benefitId = scanner.nextInt();
            scanner.nextLine();
            boolean isBefitIdValidated = ticketService.validateBenefitId(benefitId - 1, ticket.getBenefits());

            if (isBefitIdValidated) {
                System.out.println("Digite o novo benefício para atualizar:");
                String newBenefit = scanner.nextLine();
                boolean isNewBefitValidated = ticketService.validateBenefit(newBenefit);

                if (!isNewBefitValidated) {
                    return;
                }

                if (!newBenefit.equalsIgnoreCase(ticket.getDescription())) {
                    ticketService.editBenefit(ticket.getBenefits(), benefitId - 1, newBenefit);
                    break;
                } else {
                    System.out.println("Nova descrição inválida! Tente novamente.");
                }
                break;
            } else {
                System.out.println("ID do benefício inválido! Tente novamente.");
            }
        } while (true);
    }

    public static void removeTicket() {
        System.out.println("           Deletar Ingresso\n--------------------------------------------");
        ticketRepository.readTickets();
        Ticket ticket = chooseTicket("deletar");

        try {
            ticketService.hasTicket(ticket);
        } catch (TicketNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

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

    public Ticket buyTicket(Event event) {
        System.out.println("           Comprar Ingresso\n--------------------------------------------");
        ticketRepository.readEventTickets(event);
        Ticket ticket = chooseTicket("comprar");

        try {
            ticketService.hasTicket(ticket);
        } catch (TicketNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }

        if (ticket.getStatus() == Status.SOLD_OUT) {
            System.out.println("Ingresso esgotado!");
            return null;
        }

        System.out.println("Ingresso '" + ticket.getName() + "' comprado com sucesso!");
        return ticket;
    }

    public static void addTicket() {
        System.out.println("           Adicionar Ingresso ao Evento\n--------------------------------------------");

        eventRepository.readEvents();
        Event event = chooseEvent("adicionar ingresso");
        try {
            eventService.hasEvent(event);
        } catch (EventNotFoundException e) {
            System.err.println(e.getMessage());
        }

        ticketRepository.readTickets();
        Ticket ticket = chooseTicket("adicionar ao evento");
        try {
            ticketService.hasTicket(ticket);
        } catch (TicketNotFoundException e) {
            System.err.println(e.getMessage());
        }

        event.setTickets(ticket);
        System.out.println("Ingresso '" + ticket.getName() + "' adicionado ao evento '" + event.getName() + "' com sucesso!");
    }

}
