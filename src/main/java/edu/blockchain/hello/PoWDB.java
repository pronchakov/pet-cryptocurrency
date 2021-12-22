package edu.blockchain.hello;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@AllArgsConstructor
@Slf4j
public class PoWDB {

    private EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteAllData() {
        log.info("Deleting old data");
        entityManager.createQuery("delete from BlockDB").executeUpdate();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void wasteData(int i) {
        log.info("");
        log.info("");
        log.info("Make changes to block with data " + i + ", compromising data...");
        entityManager.createQuery("update BlockDB set data = :new_data where data = :old_data")
                .setParameter("new_data", "Data(changed) " + i)
                .setParameter("old_data", "Data " + i).executeUpdate();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void restoreData(int i) {
        log.info("");
        log.info("");
        log.info("Changing data to initial state...");
        entityManager.createQuery("update BlockDB set data = :new_data where data = :old_data")
                .setParameter("new_data", "Data " + i)
                .setParameter("old_data", "Data(changed) " + i).executeUpdate();
    }
}
