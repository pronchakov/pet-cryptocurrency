package edu.blockchain.hello.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Block {

    private final String previousHash;
    private final String data;
    private final long createDate;
    private final long nonce;
    private final String hash;

}
