package org.dchmyga.data.parser;

import org.junit.Test;

public class SomeTest {

	@Test
	public void test() {
		int a = 56;
		int b = 12;
		String hexA = Integer.toHexString(a);
		String hexB = Integer.toHexString(b);
		System.out.println(hexA);
		System.out.println(hexB);
		System.out.println(Integer.parseInt(hexA + hexB, 16));
		String binA = String.format("%8s", Integer.toBinaryString(a)).replace(' ', '0');
		String binB = String.format("%8s", Integer.toBinaryString(b)).replace(' ', '0');
		System.out.println(binA + binB);
		System.out.println(Integer.parseInt(binA + binB, 2));
	}

}
