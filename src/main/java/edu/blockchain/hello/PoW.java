package edu.blockchain.hello;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@AllArgsConstructor
@Slf4j
public class PoW implements ApplicationListener<ApplicationReadyEvent> {

    private Blockchain blockchain;
    private PoWDB powDb;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("");
        log.info("***");
        log.info("Blockchain Proof Of Work");
        log.info("");
        powDb.deleteAllData();

        final var size = 10;
        log.info("Creating blocks: {}", size);
        generateBlocks(size);
        checkConsistency();

        powDb.wasteData(size/3);
        checkConsistency();

        powDb.restoreData(size/3);
        checkConsistency();

        log.info("");
        log.info("End Proof Of Work");
        log.info("***");
        log.info("");
    }

    private void generateBlocks(int size) {
        for (int i = 1; i <= size; i++) {
            final var data = "Data " + i;
            final var time = ZonedDateTime.now();
            blockchain.addData(data, time);
            if (i % (size / 10) == 0) {
                log.info("Processed {} of {} blocks", i, size);
            }
        }
    }

    private void checkConsistency() {
        log.info("Calculating consistency...");
        log.info("Consistency: {}", blockchain.isBlockchainInConsistency());
    }

}
