package edu.blockchain.hello.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlockReporitory extends CrudRepository<BlockDB, Long> {

    boolean existsBlockDBSByHashEquals(String hash);
    Optional<BlockDB> findTop1ByOrderByCreateDateDesc();
    Optional<BlockDB> findBlockDBByPreviousHashEquals(String previousHash);

}
