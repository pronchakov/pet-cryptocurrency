package edu.blockchain.hello.hash;

import edu.blockchain.hello.entity.Block;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.StringJoiner;

@Component
public class HashImpl implements Hash {

    private MessageDigest digest;

    @SneakyThrows
    public HashImpl() {
        digest = MessageDigest.getInstance("SHA-256");
    }

    @SneakyThrows
    @Override
    public String hash(String data) {
        return Hex.encodeHexString(digest.digest(data.getBytes()));
    }

    @Override
    public String hash(String previousHash, String data, long dateTime, long nonce) {
        String result = hash(new StringJoiner("|")
                .add(previousHash)
                .add(data)
                .add(String.valueOf(dateTime))
                .add(String.valueOf(nonce))
                .toString());
        return result;
    }

    @Override
    public String hash(Block block) {
        return hash(block.getPreviousHash(), block.getData(), block.getCreateDate(), block.getNonce());
    }
}
