package io.lemon.tree.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

@Slf4j
public class AsyncTask {

    @Async
    public void doTask() {
        log.info(Thread.currentThread().getName());
    }

}
