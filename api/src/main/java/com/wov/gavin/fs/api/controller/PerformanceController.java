package com.wov.gavin.fs.api.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@RestController
@RequiredArgsConstructor
@Slf4j
public class PerformanceController {

    @GetMapping("/performance/{parallel}")
    @ResponseBody
    public List<Integer> listFile(@PathVariable int parallel) {
        return IntStream.range(0, parallel)
                .boxed()
                .parallel()
                .map(t -> {
                    testLog(t);
                    testAllocateArray(t);
                    testRandomAndReplace(t);
                    return t+2;
                }).collect(Collectors.<Integer>toList());
    }

    public void testLog(int i) {
        log.info("This is the log for {} -> 1", i);
        log.info("This is the log for {} -> 2", i);
        log.info("This is the log for {} -> 3", i);
        log.info("This is the log for {} -> 4", i);
        log.info("This is the log for {} -> 5", i);
    }

//    @SneakyThrows
//    public void testSleep(int i) {
//        long v = System.currentTimeMillis() % 1000;
//        log.info("sleeping start {} in {} ms", i, v);
//        Thread.sleep(v);
//        log.info("sleeping end {} in {} ms", i, v);
//    }

    public void testAllocateArray(int i) {
        int[] array = new int[i * 1000 + i * 100 + i * 10 + i + 1];
        array[i] = i;
        log.info("array with value {}", array[i]);
    }

    public void testRandomAndReplace(int i) {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString().replace("-", "");
        log.info("uuid is {}, where i={}", str,i);
    }
}