package edu.blockchain.hello.storage;

import edu.blockchain.hello.db.BlockReporitory;
import edu.blockchain.hello.entity.Block;
import edu.blockchain.hello.entity.BlockMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("db")
@AllArgsConstructor
@Primary
public class StorageDBImpl implements Storage {

    private BlockReporitory blockReporitory;
    private BlockMapper blockMapper;

    @Override
    public void saveBlock(Block block) {
        final var blockDB = blockMapper.map(block);
        blockReporitory.save(blockDB);
    }

    @Override
    public Optional<Block> getLastBlock() {
        return blockReporitory.findTop1ByOrderByCreateDateDesc()
                .map(blockMapper::map);
    }

    @Override
    public Optional<Block> getBlockByPreviousHash(String previousHash) {
        return Optional.ofNullable(blockReporitory.findBlockDBByPreviousHashEquals(previousHash)
                .map(blockDB -> blockMapper.map(blockDB))
                .orElse(null));
    }
}
