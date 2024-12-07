package com.aoc;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day02 {

    Logger log = Logger.getLogger(Day01.class.getClass().getName());

    public void Day01() throws Exception {
        setupLog();
    }

    /* @TODO separete log in a single file */
    public void setupLog() throws Exception {
        try {
            Handler fileHandler = new FileHandler("./output.log");
            SimpleFormatter sf = new SimpleFormatter();
            fileHandler.setFormatter(sf);
            log.addHandler(fileHandler);
        } catch (Exception e) {
            throw e;
        }
    }

    public int runPart2(String inputFile) throws Exception {
        log.info(inputFile);
        URL url = ClassLoader.getSystemClassLoader().getResource(inputFile);
        var lines = Resources.readLines(url, Charsets.UTF_8);
        var res = lines.stream().filter(e -> {
            int[] result = Arrays.stream(e.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            return this.validation(result, null, 1, 0);
        });
        // Arrays.asList(res.toArray()).stream().forEach(e -> log.info(e.toString()));
        return res.toArray().length;
    }

    public boolean validation(int[] values, Integer ignore, int idxCurr, int idxPrev) {
        var first = values[0];
        var last = values[values.length - 1];
        boolean isIncrease = (first < last);
        boolean levelUnsafe = true;
        int idxToBeIgnored = 0;
        boolean isIgnored = false;
        boolean duplicate = false;

        //System.out.println("============ current ==============");
        for(int value: values){
            if (!isIgnored && ignore != null && ignore == value ) {
                continue;
            }
            //System.out.print(value);
            //System.out.print(" ");
        }
            //System.out.println();

        while (idxCurr != values.length) {
            if (!isIgnored && ignore != null && ignore == idxCurr ) {
               // log.info(String.format("ignore: %s", ignore));
                System.out.println(String.format("ignore: %s", values[idxCurr]));
                isIgnored = true;
                idxCurr++;
                continue;
            }

            // if the current value greather than next value
            if (isIncrease && values[idxCurr] < values[idxPrev]) {
                System.out.println(String.format("value %s is less previous value %s", values[idxCurr], values[idxPrev]));
                idxToBeIgnored = idxCurr;
                levelUnsafe = false;
                break;
            }

            if (!isIncrease && values[idxCurr] > values[idxPrev]) {
                System.out.println(String.format("value %s is greather previous value %s", values[idxCurr], values[idxPrev]));
                idxToBeIgnored = idxCurr;
                levelUnsafe = false;
                break;
            }

            var diff = Math.abs(values[idxCurr] - values[idxPrev]);
            if (diff > 3) {
                System.out.println(String.format("diff of %s minus %s is greather than 3 or equal to 0", values[idxCurr], values[idxPrev]));
                idxToBeIgnored = idxCurr;
                levelUnsafe = false;
                break;
            }
            if (diff == 0) {
                System.out.println(String.format("diff of %s minus %s is equal to 0", values[idxCurr], values[idxPrev]));
                idxToBeIgnored = idxCurr;
                duplicate = true;
                break;
            }
            idxPrev = idxCurr;
            idxCurr++;
        }
        if (duplicate) {
            System.out.println("============ /duplicate ==============");
            return validation(values, idxToBeIgnored, idxCurr, idxPrev);
        }

        //System.out.println("============ /current ==============");
        if (!levelUnsafe && ignore == null) {
            return validation(values, idxToBeIgnored, idxCurr, idxPrev);
        }
        if (!levelUnsafe){
        System.out.println(" ==== ignored ====");
            for(int value: values){
                System.out.print(value);
                System.out.print(" ");
            }
        System.out.println();
        System.out.println(" ==== /ignored ====");
        }
        return levelUnsafe;
    }

    public int runPart1(String inputFile) throws Exception {
        log.info(inputFile);
        URL url = ClassLoader.getSystemClassLoader().getResource(inputFile);
        var lines = Resources.readLines(url, Charsets.UTF_8);
        var res = lines.stream().filter(e -> {
            int[] result = Arrays.stream(e.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            return this.validation(result);
        });
        // Arrays.asList(res.toArray()).stream().forEach(e -> log.info(e.toString()));
        return res.toArray().length;
    }

    public boolean validation(int[] values) {
        var first = values[0];
        var last = values[values.length - 1];
        boolean isIncrease = (first < last);
        for (int i = 0; i < values.length; i++) {
            // if first item go to next one
            if (i == 0) {
                continue;
            }
            // if increase the previous values can not be greather than current value
            if (isIncrease && values[i - 1] > values[i]) {
                return false;
            }

            // if decrease the previous values can not be less than current value
            if (!isIncrease && values[i - 1] < values[i]) {
                return false;
            }

            var diff = Math.abs(values[i] - values[i - 1]);
            if (diff > 3 || diff == 0) {
                return false;
            }

        }
        return true;
    }

}
