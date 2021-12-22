package edu.blockchain.hello;

import edu.blockchain.hello.entity.Block;
import edu.blockchain.hello.hash.Hash;
import edu.blockchain.hello.storage.Storage;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Data
@Slf4j
public class Blockchain {

    @NonNull private Storage storage;
    @NonNull private Hash hash;
    private String previousHash;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addData(String data, ZonedDateTime dateTime) {
        previousHash = Optional.ofNullable(previousHash)
                .orElse(storage.getLastBlock()
                        .map(block -> block.getHash())
                        .orElse("0"));
        final var timestamp = Date.from(dateTime.toInstant()).getTime();

        var hashStr = "";
        long nonce = 0;
        for (; !hashStr.startsWith("000000") && nonce < Long.MAX_VALUE; nonce++) {
            hashStr = hash.hash(previousHash, data, timestamp, nonce);
        }

        final var block = new Block(previousHash, data, timestamp, nonce - 1, hashStr);
        storage.saveBlock(block);
        previousHash = block.getHash();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean isBlockchainInConsistency() {
        var previousHash = "0";
        Optional<Block> block = null;
        while ((block = storage.getBlockByPreviousHash(previousHash)).isPresent()) {
            final var b = block.get();
            final var calculatedHash = hash.hash(b.getPreviousHash(), b.getData(), b.getCreateDate(), b.getNonce());
            if (calculatedHash.equals(b.getHash())) {
                previousHash = b.getHash();
            } else {
                return false;
            }
        }
        return true;
    }

}
