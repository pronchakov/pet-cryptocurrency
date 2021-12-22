package edu.blockchain.hello.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "blocks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockDB {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "edu.blockchain.hello.db.UUIDIdentifierGenerator")
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "previous_hash", nullable = false)
    private String previousHash;

    @Column(name = "data", nullable = false)
    private String data;

    @Column(name = "create_date", nullable = false)
    private long createDate;

    @Column(name = "nonce", nullable = false)
    private long nonce;

    @Column(name = "hash", nullable = false)
    private String hash;

}
