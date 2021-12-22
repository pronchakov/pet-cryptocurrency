package edu.blockchain.hello.hash;

import edu.blockchain.hello.entity.Block;

public interface Hash {

    String hash(String data);
    String hash(String previousHash, String data, long dateTime, long nonce);
    String hash(Block block);

}
