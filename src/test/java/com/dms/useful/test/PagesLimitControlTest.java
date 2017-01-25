package com.dms.useful.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dms.useful.PagesLimitControl;

public class PagesLimitControlTest {

	private PagesLimitControl pages;
	private int current;
	private int totalPages;

	@Test
	public void deveGerarUmLimiteParaMenosDeCincoPaginas() throws Exception {
		this.current = 3;
		this.totalPages = 3;
		int expectedMim = 1;
		int expectedMax = 3;
		pages = new PagesLimitControl(current, totalPages);

		assertEquals(expectedMim, pages.getFirst());
		assertEquals(expectedMax, pages.getLast());
	}

	@Test
	public void deveGerarUmLimiteParaMaisDeCincoPaginas() throws Exception {
		this.current = 26;
		this.totalPages = 1153;
		int expectedMim = 26;
		int expectedMax = 30;
		pages = new PagesLimitControl(current, totalPages, 5);

		assertEquals(expectedMim, pages.getFirst());
		assertEquals(expectedMax, pages.getLast());
	}

	/**
	 * Tabela verdade (limitMaximun: 6) index: array, numberOfPage: número
	 * mínimo e máximo para gerar as páginas
	 * 
	 * <pre>
	 * index:		numberOfPage
	 * 0	5		1	6
	 * 6	11		7	12
	 * 12	17		13	18
	 * 18	23		19	24
	 * 24	29		25	30
	 * ...			...
	 * </pre>
	 */
	@Test
	public void deveGerarUmIntervalodeSeisPaginas() throws Exception {
		this.current = 26;
		this.totalPages = 29;
		int expectedMim = 25;
		int expectedMax = 29;
		pages = new PagesLimitControl(current, totalPages, 6);

		assertEquals(expectedMim, pages.getFirst());
		assertEquals(expectedMax, pages.getLast());
	}
}
