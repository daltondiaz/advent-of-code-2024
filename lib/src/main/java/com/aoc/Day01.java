package com.aoc;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day01 {

    Logger log = Logger.getLogger(Day01.class.getClass().getName());

    public void Day01() throws Exception{
        setupLog();
    }

    public void setupLog() throws Exception{
        try {
            Handler fileHandler = new FileHandler("./output.log");
            SimpleFormatter sf = new SimpleFormatter();
            fileHandler.setFormatter(sf);
            log.addHandler(fileHandler);
        } catch (Exception e) {
            throw e;
        }
    }

    public int runPart1(String inputFile) throws Exception {
        log.info(inputFile);
        URL url = ClassLoader.getSystemClassLoader().getResource(inputFile);
        var lines = Resources.readLines(url, Charsets.UTF_8);
        List<Integer> r = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        lines.stream()
            .forEach(e -> {
                var result = e.split("\\s+");
                r.add(Integer.parseInt(result[0]));
                l.add(Integer.parseInt(result[1]));
            });
        Collections.sort(r);
        Collections.sort(l);
        
        int res = 0;
        for(int i=0; i<r.size(); i++){
            res += Math.abs(r.get(i) - l.get(i));
        }

        return res;
    }

    public int runPart2(String inputFile) throws Exception {
        log.info(inputFile);
        URL url = ClassLoader.getSystemClassLoader().getResource(inputFile);
        log.info(url.toString());
        var lines = Resources.readLines(url, Charsets.UTF_8);
        List<Integer> r = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        lines.stream()
            .forEach(e -> {
                var result = e.split("\\s+");
                r.add(Integer.parseInt(result[0]));
                l.add(Integer.parseInt(result[1]));
            });
        Integer res = 0;
        for(int i=0; i<r.size(); i++){
            Integer times = 0;
            for(int j=0; j<l.size(); j++){
                if (r.get(i).equals(l.get(j))){
                    times ++;
                }
            }
            res += r.get(i) * times;
        }

        return res;
    }
}
