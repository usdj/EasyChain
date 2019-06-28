package com.usdj.blockchain;

import java.security.PublicKey;

/**
 * @author gerrydeng
 * @date 2019-06-24 16:13
 * @Description:
 */
public class TransactionOutput {
    public String id;
    public PublicKey reciepient;
    public float value;
    public String parentTransactionId;

    public TransactionOutput(PublicKey reciepient, float value, String parentTransactionId) {
        this.reciepient = reciepient;
        this.value = value;
        this.parentTransactionId = parentTransactionId;
        this.id = StringUtil.applySha256(StringUtil.getStringFromKey(reciepient) + value + parentTransactionId);
    }

    public boolean isMine(PublicKey publicKey) {
        return (publicKey == reciepient);
    }
}
