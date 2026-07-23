package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe utilitária responsável por fornecer métodos relacionados à manipulação,
 * formatação e conversão de datas e horários.
 */
public class DateTimeUtil {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Converte uma data e hora no formato de texto para um objeto {@link LocalDateTime}.
     *
     * @param dateTime data e hora em formato de texto.
     * @return objeto {@link LocalDateTime} correspondente à data e hora informada.
     */
    public LocalDateTime convertDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, dateTimeFormatter);
    }

    /**
     * Formata um objeto {@link LocalDateTime} para sua representação em texto.
     *
     * @param dateTime data e hora que será formatada.
     * @return data e hora formatadas em texto.
     */
    public String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(dateTimeFormatter);
    }

}
