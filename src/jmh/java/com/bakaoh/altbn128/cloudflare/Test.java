package com.bakaoh.altbn128.cloudflare;

import com.bakaoh.altbn128.BenchMarkData;

import java.util.Arrays;

/**
 * Created by bakaking on 29/10/2019.
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(Arrays.equals(new BN128Addition().execute(BenchMarkData.ADDITION_INPUT), BenchMarkData.ADDITION_EXPECTED));
        System.out.println(Arrays.equals(new BN128Multiplication().execute(BenchMarkData.MULTIPLICATION_INPUT), BenchMarkData.MULTIPLICATION_EXPECTED));
        System.out.println(Arrays.equals(new BN128Pairing().execute(BenchMarkData.PAIRING_INPUT), BenchMarkData.PAIRING_EXPECTED));
    }
}
