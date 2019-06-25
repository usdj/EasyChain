package com.usdj.easychain;

/**
 * @author gerrydeng
 * @date 2019-06-24 22:33
 * @Description:
 */
public class Block {

    public String hash;

    public String previousHash;
    //作为简单教程，这里data将存简要信息
    public String data;

    private long timeStamp;

    private int nonce;

    public Block(String data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = System.currentTimeMillis();
        //确保调用该方法放在最后
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return StringUtil.applySha256(
                previousHash +
                        timeStamp +
                        nonce +
                        data
        );
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }
}
