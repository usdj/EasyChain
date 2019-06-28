package com.usdj.blockchain;

import java.util.ArrayList;

/**
 * @author gerrydeng
 * @date 2019-06-24 11:24
 * @Description:
 */
public class Block {

    public String hash;
    public String previousHash;
    public String merkleRoot;
    public long timeStamp;
    public ArrayList<Transaction> transactions = new ArrayList<>();
    public int nonce;

    public Block(String previousHash) {
        this.previousHash = previousHash;
        this.timeStamp = System.currentTimeMillis();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return StringUtil.applySha256(
                previousHash +
                        timeStamp +
                        nonce +
                        merkleRoot
        );
    }

    public void mineBlock(int difficulty) {
        merkleRoot = StringUtil.getMerkleRoot(transactions);
        String target = StringUtil.getDificultyString(difficulty);
        while(!hash.substring(0,difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!!" + hash);
    }

    public boolean addTransaction(Transaction transaction) {
        if (transaction == null) {
            return false;
        }
        if ((!"0".equals(previousHash))) {
            if ((transaction.processTransaction() != true)) {
                System.out.println("Transaction failed to process. Discarded.");
                return false;
            }
        }
        transactions.add(transaction);
        System.out.println("Transaction Successfully added to Block");
        return true;
    }
}
