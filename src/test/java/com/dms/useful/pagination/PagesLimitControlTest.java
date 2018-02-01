package com.dms.useful.pagination;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dms.exception.ValidateException;
import com.dms.useful.pagination.PagesLimitControl;

public class PagesLimitControlTest {

	private PagesLimitControl pages;
	private int current;
	private int totalPages;

	@Test
	public void deveGerarUmLimiteParaMenosDeCincoPaginas() throws Exception {
		this.current = 2;
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
	 * 
	 * @throws Exception
	 *             nenhuma excessão esperada
	 */
	@Test
	public void deveGerarUmIntervaloDeSeisPaginas() throws Exception {
		this.current = 26;
		this.totalPages = 29;
		int expectedMim = 25;
		int expectedMax = 29;
		pages = new PagesLimitControl(current, totalPages, 6);

		assertEquals(expectedMim, pages.getFirst());
		assertEquals(expectedMax, pages.getLast());
	}

	/**
	 * testar os valores mínimos
	 * 
	 * @throws ValidateException
	 *             algum valor de um dos parâmetros não aceitável
	 */
	@Test
	public void deveAceitarValoresMinimos() throws ValidateException {
		pages = new PagesLimitControl(0, 1, 1);
		assertEquals(1, pages.getFirst());
		assertEquals(1, pages.getLast());
	}

	@Test(expected = ValidateException.class)
	public void deveFalharComLimiteMenorQueUm() throws Exception {
		pages = new PagesLimitControl(0, 1, 0);
		fail("deveria falhar: limite mínimo de ser um");
	}

	//@Test(expected = ValidateException.class)
	public void deveFalharSePageCurrentMaiorQueTotalPages() throws Exception {
		pages = new PagesLimitControl(1, 1, 1);
		fail("deveria falhar: a página atual deve ser menor que o total de páginas");
	}

	//@Test(expected = ValidateException.class)
	public void deveFalharComDeTotalPagesZero() throws Exception {
		pages = new PagesLimitControl(-1, 0);
		fail("deveria falhar: total de páginas deve ser maior que zero");
	}

	/**
	 * Deve interar de 0 a (TOTAL - 1) gerando intervalos de páginas (ver
	 * exemplo da tabela:
	 * {@link PagesLimitControlTest#deveGerarUmIntervaloDeSeisPaginas()}
	 * <pre>
	 * TOTAL = 1153;
	 * se current = 0 e new PagesLimitControl(current, TOTAL)
	 * o {@link PagesLimitControl#getFirst()} = 1 e {@link PagesLimitControl#getLast()} = 5
	 * 
	 * se current = 5 e new PagesLimitControl(current, TOTAL)
	 * o {@link PagesLimitControl#getFirst()} = 6 e {@link PagesLimitControl#getLast()} = 10
	 * 
	 * ... assim por diante
	 * </pre>
	 * 
	 * @throws Exception
	 *             não é esperado
	 */
	@Test
	public void deveGerarContinuamenteNumeroMaximoDePagina() throws Exception {

		int total = 1153;
		pages = new PagesLimitControl(current, total);

		int last = pages.getLast();

		for (int current = 0; current < total; current++) {
			pages = new PagesLimitControl(current, total);

			last = pages.getLast();

			assertEquals(true, current < last && current != last);
		}
	}
}
