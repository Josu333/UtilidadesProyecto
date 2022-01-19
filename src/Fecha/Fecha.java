/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fecha;

import java.util.Calendar;//clase abstracta
import java.util.GregorianCalendar;
import textos.Textos;
//clase que hereda de la clase abstracta Calendar

/**
 *
 * @author dam
 */
public class Fecha {

    private int dia;
    private int mes;
    private int anno;

    private static int[] diasMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String[] MESES = {"Enero, Febrero, Marzo, Abril,Mayo,"
        + "Junio,Julio,Agosto,Septiembre,Octubre,Noviembre,Diciembre"};

    public Fecha() {
        Calendar c = new GregorianCalendar();
        dia = c.get(Calendar.DATE);
        mes = c.get(Calendar.MONTH);
        anno = c.get(Calendar.YEAR);
    }

    public Fecha(int dia, int mes, int anno) {
        this.dia = dia;
        this.mes = mes;
        this.anno = anno;
    }

    public String fechaCompletaMesLetras() {
        return dia + "/" + MESES[mes] + "/" + anno;
    }

    public String fechaCompleta() {
        return dia + "/" + (mes + 1) + "/" + anno;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAnno() {
        return anno;
    }

    /**
     *
     * @param dato
     * @return -1 incorrecta -2 dia -3 mes -4 anno 0 correcta
     */
    /**public int validarFecha(String dato) {
        int resultado = 0;
        String[] elementos = dato.split("/");

        if (elementos.length != 3) {
            resultado = -1;
        } else {
            try {
                dia = Integer.parseInt(elementos[0]);
                mes = Integer.parseInt(elementos[1]) - 1;//por qué
                anno = Integer.parseInt(elementos[2]);
                if (anno < 1000 || anno > 3000) {
                    resultado = -4;
                } else {
                    if (mes < 0 || mes > 11) {
                        resultado = -3;
                    } else {
                        bisiesto();
                        if (dia < 1 || dia > diasMes[mes]) {
                            resultado = -2;

                        }

                    }
                }

            } catch (NumberFormatException ex) {
                resultado = -1;
            }
        }

        return resultado;
    }**/
    public int validarFecha(String dato) {
        int resultado = 0;
        String[] elementos = dato.split("/");
        if (elementos.length != 3) {
            resultado = -1;
        } else {
            try {
                dia = Integer.parseInt(elementos[0]);
                mes = Integer.parseInt(elementos[1]);
                anno = Integer.parseInt(elementos[2]);

                if (anno < 1000 || anno > 3000) {
                    resultado = -4;
                } else {
                    if (mes < 1 || mes > 12) {
                        resultado = -3;
                    } else {
                        bisiesto();
                        if (dia < 1 || dia > diasMes[mes - 1]) {
                            resultado = -2;
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                resultado = -1;
            }
        }
        return resultado;
    }

    public void bisiesto() {
        if (anno % 4 == 0 && anno % 100 != 0 || anno % 400 == 0) {
            diasMes[1] = 29;
        } else {
            diasMes[1] = 28;
        }

    }

    /**
     * Método para saber los dias transcurridos segun fecha y dentro de un año
     *
     * @return dias transcurridos hasta la fecha introducida en lo que va de año
     */
    public int calcularOrden() {
        int ndias;
        int mesx;
        ndias = 0;
        mesx = 1;
        bisiesto();
        while (mesx < mes) {//por que no es mes -1
            ndias = ndias + diasMes[mesx];
            mesx++;
        }
        ndias = ndias + dia;
        return ndias;
    }

    /**
     *
     * @return faltan. Dias que faltan para acabar el año.
     */
    public int finAno() {
        int faltan = 365;
        if (anno % 4 == 0 && anno % 100 != 0 || anno % 400 == 0) {
            faltan = 366;
            faltan = faltan - calcularOrden();
        }
        return faltan;
    }

    // resultados : 0 = incorrecto ; 1 = correcto
    public int compararFechas(Fecha dato) {
        Fecha f1 = dato;//f1 es la fecha introducida
        Fecha factual = new Fecha();
        int resultado;

        if (f1.getAnno() > factual.getAnno()) {
            resultado = 0;
        } else {
            if (f1.getMes() > factual.getMes()) {
                resultado = 0;
            } else {
                if (f1.getDia() > factual.getDia()) {
                    resultado = 0;
                } else {
                    resultado = 1;
                }
            }

        }

        return resultado;
    }
/**
 * 
 * @param dv. Dias de vencimiento de cara a una fecha.
 */
    public void calcularVencimiento(int dv) {

        dia = dia + dv;
        bisiesto();
        while (dia > diasMes[mes]) {
            dia = dia - diasMes[mes];
            mes++;
            if (mes > 11) {
                anno++;
                mes = 0;
                bisiesto();
            }
        }

    }

}
