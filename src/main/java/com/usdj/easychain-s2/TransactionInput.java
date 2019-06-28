package com.usdj.blockchain;

/**
 * @author gerrydeng
 * @date 2019-06-24 16:13
 * @Description:
 */
public class TransactionInput {
    public String transactionOutputId;
    public TransactionOutput UTXO;

    public TransactionInput(String transactionOutputId) {
        this.transactionOutputId = transactionOutputId;
    }
}
