package com.sancorsalud.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ManejadorFechas {

	public static String right(String value, int length) {
		return value.substring(value.length() - length);
	}

	public static Calendar getCalendar(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return cal;
	}

	public static int getEdad(int fechaNacimiento) throws ParseException {
		Date fechaNacimiento_ = intToDate(fechaNacimiento, 0); 
	    return getDiffAños(fechaNacimiento_, new Date());
	}

    public static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        return formateador.format(ahora);
    }

    public static String getHoraActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss");
        return formateador.format(ahora);
    }

    public static Date sumarDiasFecha(Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, dias);
        return new Date(cal.getTimeInMillis());
    }

    public static Date restarDiasFecha(Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, -dias);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    //Retorna la diferencia en años entre 2 fechas
	public static int getDiffAños(Date ini, Date fin) {
	    Calendar a = getCalendar(ini);
	    Calendar b = getCalendar(fin);
	    int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
	    if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) || 
	        (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
	        diff--;
	    }
	    return diff;
	}

    //Diferencias de días entre dos fechas
    public static int getDiffDias(Date ini, Date fin) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(ini);
        try {
        	ini = df.parse(fechaInicioString);
        } catch (ParseException ex) {}
        String fechaFinalString = df.format(fin);
        try {
        	fin = df.parse(fechaFinalString);
        } catch (ParseException ex) {}
        long fechaInicialMs = ini.getTime();
        long fechaFinalMs = fin.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        return ((int) dias);
    }

    //Diferencia de minutos entre dos fechas
    public static int getDiffMinutos(Date ini, Date fin) {
        long diffInMillies = fin.getTime() - ini.getTime();
        return (int) Math.abs (diffInMillies / (60 * 1000));
    }

	//Convierte una fecha de formato String a Date
    public static Date stringToDate(String fecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaEnviar = null;
        try {
            fechaEnviar = formatoDelTexto.parse(fecha);
            return fechaEnviar;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }

	//Convierte una fecha de formato Date a String
	public static String dateToString(Date fecha) {
		if (fecha == null) {
			return "";
		}
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		return f.format(fecha);
	}

	//Convierte una fecha y hora en formato int a Date
	public static Date intToDate(int fecha, int hora) throws ParseException {
		if (fecha == 0) {
			return null;
		}
		String fecha_ = Long.toString(fecha);
		String hora_ = Long.toString(hora);
		String año = fecha_.substring(0, 4);
		String mes = fecha_.substring(4, 6);
		String dia = fecha_.substring(6, 8);
		String dateInString;
		SimpleDateFormat sdf;
		if (hora == 0) {
			sdf = new SimpleDateFormat("dd-MM-yyyy");
			dateInString = dia + "-" + mes + "-" + año;
		}
		else {
			hora_ = right("000000" + hora_, 6);
			String hor = hora_.substring(0, 2);
			String min = hora_.substring(2, 4);
			String seg = hora_.substring(4, 6);
			sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			dateInString = dia + "-" + mes + "-" + año + " " + hor + ":" + min + ":" + seg;
		}
		Date date = sdf.parse(dateInString);
		return date;
	}

	//Convierte una fecha a int
	public static int dateToInt(Date fecha) {
		if (fecha == null) {
			return 0;
		}
		Calendar calendar = getCalendar(fecha);
        int anio = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH)+1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        return (((anio * 100) + mes) * 100) + dia;
	}

	//Convierte una fecha y hora en formato numerico a String
	public static String intToString(int fecha, int hora) throws ParseException {
		String dateInString = "";
		if (fecha > 9999999) {
			String fecha_ = Long.toString(fecha);
			String hora_ = Long.toString(hora);
			String año = fecha_.substring(0, 4);
			String mes = fecha_.substring(4, 6);
			String dia = fecha_.substring(6, 8);
			if (hora == 0) {
				dateInString = dia + "/" + mes + "/" + año;
			}
			else {
				hora_ = right("000000" + hora_, 6);
				String hor = hora_.substring(0, 2);
				String min = hora_.substring(2, 4);
				String seg = hora_.substring(4, 6);
				dateInString = dia + "/" + mes + "/" + año + " " + hor + ":" + min + ":" + seg;
			}
		}
		return dateInString;
	}

	//Convierte una hora a int
	public static int timeToInt(Date fecha) {
		if (fecha == null) {
			return 0;
		}
		Calendar calendar = getCalendar(fecha);
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int minuto = calendar.get(Calendar.MINUTE);
        int segundo = calendar.get(Calendar.SECOND);
        return (((hora * 100) + minuto) * 100) + segundo;
	}

	//Convierte una hora a String
	public static String timeToString(Date fecha) {
		if (fecha == null) {
			return "";
		}
		SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
		return f.format(fecha);
	}

	//Devuelve una cadena String a partir de una cantidad de minutos
    public static synchronized String minutosToString(Integer integer) {
		if (integer == null) {
			return "";
		}
		else {
			long espera = integer.longValue();
			if (espera < 60) {
				return String.valueOf(espera) + " min";
			}
			else {
				int horas = (int) (espera / 60);
				int minutos = (int) (espera % 60);
				return String.valueOf(horas) + " hs, " + String.valueOf(minutos) + " min";
			}
		}
    }

}