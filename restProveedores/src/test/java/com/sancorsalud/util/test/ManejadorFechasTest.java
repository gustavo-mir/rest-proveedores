package com.sancorsalud.util.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

import com.sancorsalud.util.ManejadorFechas;

public class ManejadorFechasTest {

	@Test
	public void testGetEdad() {

		try {
			int edad = ManejadorFechas.getEdad(19750727);
			if (edad < 0 || edad > 150) {
				fail("ERROR EN: ManejadorFechas.getEdad()");
			}

		} catch (ParseException e) {
			fail("ERROR EN: ManejadorFechas.getEdad()");
		}
	}

	@Test
	public void testGetFechaActual() {

		String fechaActual = ManejadorFechas.getFechaActual();
		if (fechaActual.trim().length() != 10) {
			fail("ERROR EN: ManejadorFechas.getFechaActual()");
		}
	}

	@Test
	public void testGetHoraActual() {

		String horaActual = ManejadorFechas.getHoraActual();
		if (horaActual.trim().length() != 8) {
			fail("ERROR EN: ManejadorFechas.getHoraActual()");
		}
	}

	@Test
	public void test_sumarDiasFecha_restarDiasFecha() {

		Date fch1 = new Date(0);
		Date fch2 = ManejadorFechas.sumarDiasFecha(fch1, 10);
		Date fch3 = ManejadorFechas.restarDiasFecha(fch2, 10);
		assertEquals("ERROR EN: ManejadorFechas.sumarDiasFecha() o ManejadorFechas.restarDiasFecha()", fch1, fch3);
	}

	@Test
	public void testGetDiffAños() {

		Date fch = new Date();
		int años = ManejadorFechas.getDiffAños(fch, fch);
		assertEquals("ERROR EN: ManejadorFechas.getDiffAños()", 0, años);
	}

	@Test
	public void testGetDiffDias() {

		Date fch = new Date();
		int dias = ManejadorFechas.getDiffDias(fch, fch);
		assertEquals("ERROR EN: ManejadorFechas.getDiffDias()", 0, dias);
	}

	@Test
	public void testGetDiffMinutos() {

		Date fch = new Date();
		int minutos = ManejadorFechas.getDiffMinutos(fch, fch);
		assertEquals("ERROR EN: ManejadorFechas.getDiffMinutos()", 0, minutos);
	}

	@Test
	public void test_stringToDate_dateToString() {

		Date fch1 = ManejadorFechas.stringToDate("14-09-2016");
		String fch2 = ManejadorFechas.dateToString(fch1);
		assertEquals("ERROR EN: ManejadorFechas.stringToDate() o ManejadorFechas.dateToString()", "14-09-2016", fch2);
	}

	@Test
	public void test_intToDate_dateToInt_intToString() {

		try {
			Date fch1 = ManejadorFechas.intToDate(20160914, 0);
			int fch2 = ManejadorFechas.dateToInt(fch1);
			String fch3 = ManejadorFechas.intToString(fch2, 0);
			assertEquals("ERROR EN: ManejadorFechas.intToDate() o ManejadorFechas.dateToInt()", 20160914, fch2);
			assertEquals("ERROR EN: ManejadorFechas.dateToInt() o ManejadorFechas.intToString()", "14/09/2016", fch3);

		} catch (ParseException e) {
			fail("ERROR EN: ManejadorFechas.intToDate() o ManejadorFechas.dateToInt() o ManejadorFechas.intToString()");
		}
	}

	@Test
	public void testTimeToInt() {

		int hora = ManejadorFechas.timeToInt(new Date());
		if (hora <= 0 || hora > 235959) {
			fail("ERROR EN: ManejadorFechas.timeToInt()");
		}
	}

	@Test
	public void testTimeToString() {

		String hora = ManejadorFechas.timeToString(new Date());
		if (hora.trim().length() == 0 || hora.trim().length() > 8) {
			fail("ERROR EN: ManejadorFechas.timeToString()");
		}
	}

	@Test
	public void testMinutosToString() {

		String minutos = ManejadorFechas.minutosToString(1500);
		if (minutos.trim().length() == 0) {
			fail("ERROR EN: ManejadorFechas.minutosToString()");
		}
	}

}