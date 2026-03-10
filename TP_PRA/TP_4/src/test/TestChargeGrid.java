package test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

import model.ChargeGrid;
import model.Crossword;

public class TestChargeGrid {

	@Test
	public void test() {
		ChargeGrid cg = new ChargeGrid();
		Crossword cw = cg.extractGrid(10);
		StringBuffer str = new StringBuffer();

		int h = cw.getHeight();
		int w = cw.getWidth();

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (cw.isBlackSquare(i, j)) {
					str.append("*");
				} else {
					str.append(cw.getSolution(i, j));
				}
			}
		}
		
		assertEquals("Crossword Invalid!",str.toString(), cg.getControle(10));
		

	}

}
