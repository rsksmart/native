package com.bakaoh.altbn128.cloudflare;

import com.bakaoh.altbn128.PrecompiledContract;

/**
 * Created by bakaking on 29/10/2019.
 */
public class ContractFactory implements PrecompiledContract.Factory {
    @Override
    public PrecompiledContract newAddContract() {
        return new BN128Addition();
    }

    @Override
    public PrecompiledContract newMulContract() {
        return new BN128Multiplication();
    }

    @Override
    public PrecompiledContract newPairingContract() {
        return new BN128Pairing();
    }
}
