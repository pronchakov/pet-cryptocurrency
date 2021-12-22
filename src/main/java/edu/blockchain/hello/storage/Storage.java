package edu.blockchain.hello.storage;

import edu.blockchain.hello.entity.Block;

import java.util.Optional;

public interface Storage {

    void saveBlock(Block block);
    Optional<Block> getLastBlock();
    Optional<Block> getBlockByPreviousHash(String previousHash);

}
