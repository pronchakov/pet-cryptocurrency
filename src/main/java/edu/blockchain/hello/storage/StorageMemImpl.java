package edu.blockchain.hello.storage;

import edu.blockchain.hello.entity.Block;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component("mem")
public class StorageMemImpl implements Storage {

    private Map<String, Block> hashMap = new HashMap<>();
    private Map<String, Block> previousHashMap = new HashMap<>();
    private Block lastBlock;

    @Override
    public void saveBlock(Block block) {
        hashMap.put(block.getHash(), block);
        previousHashMap.put(block.getPreviousHash(), block);
        lastBlock = block;
    }

    @Override
    public Optional<Block> getLastBlock() {
        return Optional.ofNullable(lastBlock);
    }

    @Override
    public Optional<Block> getBlockByPreviousHash(String previousHash) {
        var value = previousHashMap.get(previousHash);
        if (value != null && value.getData().contains(" 666")) {
            value = new Block(value.getPreviousHash(), "haha", value.getCreateDate(), value.getNonce(), value.getHash());
            hashMap.put(value.getHash(), value);
            previousHashMap.put(value.getPreviousHash(), value);
        }
        return Optional.ofNullable(value);
    }
}
