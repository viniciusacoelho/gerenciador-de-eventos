package test;

import model.Event;
//import org.mindrot.jbcrypt.BCrypt;

import java.util.Comparator;

import static view.EventView.eventRepository;

/**
 * Classe utilizada para executar e testar as funcionalidades do sistema.
 */
public class Test {

    static void main() {
//        System.out.println((encryptPassword("1234")));
        String message = "Mensagem aleatória somente para teste";
        System.out.println(message.trim()); // remove os espaços em branco do início e do fim da String
    }

    /**
     * Encrypt the password
     *
     //     * @param password
     * @return password encrypted.
     */
//    public static String encryptPassword(String password) {
//        return BCrypt.hashpw(password, BCrypt.gensalt());
//    }

    /**
     * Checks if the password are encrypted.
     *
     //     * @param password
     //     * @param hash
     * @return True or false
     */
//    public static boolean checkPassword(String password, String hash) {
//        return BCrypt.checkpw(password, hash);
//    }

    public static boolean test(String str) {
        return str.isBlank();
    }

    public static void filterEventByName() {
        eventRepository.getEvents().stream()
                .map(Event::getName)
                .forEach(System.out::println);
    }

    public static void filterEventByPopularity() {
        eventRepository.getEvents().stream()
                .sorted(Comparator.comparing(Event::getCapacity).reversed())
                .limit(3)
                .forEach(System.out::println);
    }

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
// TODO: Create a abstract class Ticket and others extends from it and create a interface of their methods
// TODO: Event Billing using Ticket sales, summing the payments
// TODO: Event revenue
// TODO: Participants can choose to view Tickets alphabetically or by price