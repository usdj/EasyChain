package com.usdj.easychain;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * @author gerrydeng
 * @date 2019-06-25 00:31
 * @Description:
 */
public class EasyChain {
    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;

    public static void main(String[] args) {
        blockchain.add(new Block("The first block", "0"));
        blockchain.get(0).mineBlock(difficulty);
        blockchain.add(new Block("The second block",blockchain.get(blockchain.size()-1).hash));
        blockchain.get(1).mineBlock(difficulty);
        blockchain.add(new Block("The third block",blockchain.get(blockchain.size()-1).hash));
        blockchain.get(2).mineBlock(difficulty);
        System.out.println("\nBlockChain is Valid:"  + isChainValid());
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\n Th BlockChain:");
        System.out.println(blockchainJson);
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        for(int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
}
