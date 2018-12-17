package Restaurante.main;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Abaixo temos a classe para converter em int as datas e horas que foram passadas em string; e seu construtor.
 * E depois disso convertemos para LocalDate ou LocalDateTime.
 */
public class MetodosOutros {


    /**
     * Método para saber o dia e hora atuais, formatados para LocalDateTime.
     * Método static, pois não precisamos instanciá-lo para termos acesso à ele e usá-lo.
     * @return Retorna a data e hora atuais, formatados.
     */
    public static LocalDateTime saberDiaHoraAtualFormatado(){
        int dia = LocalDate.now().getDayOfMonth();
        int mes = LocalDate.now().getMonthValue();
        int ano = LocalDate.now().getYear();
        int hora = LocalDateTime.now().getHour();
        int minute = LocalDateTime.now().getMinute();
        LocalDateTime datFormatada = LocalDateTime.of(ano, mes, dia, hora, minute);
        return datFormatada;
    }


    /**
     * Método para saber a data, sem a formatação.
     * Método static, pois não precisamos instanciá-lo para termos acesso à ele e usá-lo.
     * @param diaSemFormatar Dia que compõe a data.
     * @param mesSemFormatar Mês que compõe a data.
     * @param anoSemFormatar Ano que compõe a data.
     * @return Retorna a data, sem formatar.
     */
    public static LocalDate retornaData(String diaSemFormatar, String mesSemFormatar, String anoSemFormatar){
        int dia = Integer.parseInt(diaSemFormatar);
        int mes = Integer.parseInt(mesSemFormatar);
        int ano = Integer.parseInt(anoSemFormatar);
        LocalDate localDate = LocalDate.of(ano, mes, dia);
        return localDate;
    }

    /**
     * Método para saber a data e a hora, sem a formatação.
     * Método static, pois não precisamos instanciá-lo para termos acesso à ele e usá-lo.
     * @param diaSemFormatar Dia que compõe a data.
     * @param mesSemFormatar Mês que compõe a data.
     * @param anoSemFormatar Ano que compõe a data.
     * @param horaSemFormatar Hora.
     * @param minutoSemFormatar Minutos.
     * @return Retorna a data e a hora.
     */
    public static LocalDateTime retornaDataHora(String diaSemFormatar, String mesSemFormatar, String anoSemFormatar, String horaSemFormatar, String minutoSemFormatar){
        int dia = Integer.parseInt(diaSemFormatar);
        int mes = Integer.parseInt(mesSemFormatar);
        int ano = Integer.parseInt(anoSemFormatar);
        int horas = Integer.parseInt(horaSemFormatar);
        int minutos = Integer.parseInt(minutoSemFormatar);
        LocalDateTime localDate = LocalDateTime.of(ano, mes, dia, horas, minutos);
        return localDate;
    }
}
