package ua.bolt;

import org.junit.Test;
import ua.bolt.ex.FullMapException;

import java.util.ArrayList;

/**
 * Created by ackiybolt on 19.02.15.
 */
public class HashMapSpeedTest {


    int initialCapacity = 100000;

    @Test
    public void writeSpeedCheck () throws FullMapException {

        long myMapResults = 0;
        long hashMapResults = 0;

        ArrayList<Long> numbers = new ArrayList<>(initialCapacity);

        for (long i = 0; i < initialCapacity; i++) {
            numbers.add(i);
        }

        myMapResults = myMapWriteTest(numbers, 10);
        hashMapResults = hashMapWriteTest(numbers, 10);

        System.out.printf("Write:\nMy map time results:   %s ns\nHash map time results: %s ns\n %s\n",
                myMapResults,
                hashMapResults,
                resolveSmile(myMapResults, hashMapResults));
    }

    private long myMapWriteTest(ArrayList<Long> numbers, int testCounts) throws FullMapException {

        long result = 0;

        for (int i = 0; i < testCounts; i++) {

            HashMap myMap = new HashMap(numbers.size());

            long start = System.nanoTime();
            for (int j = 0; j < numbers.size(); j++) {
                Long num = numbers.get(j);
                myMap.put(num.intValue(), num);
            }
            long stop = System.nanoTime();
            result += stop - start;
        }

        return result/testCounts;
    }

    private long hashMapWriteTest(ArrayList<Long> numbers, int testCounts) throws FullMapException {

        long result = 0;

        for (int i = 0; i < testCounts; i++) {
            java.util.HashMap<Integer, Long> hashMap = new java.util.HashMap<>(numbers.size());

            long start = System.nanoTime();
            for (int j = 0; j < numbers.size(); j++) {
                Long num = numbers.get(j);
                hashMap.put(num.intValue(), num);
            }
            long stop = System.nanoTime();
            result += stop - start;
        }

        return result/testCounts;
    }


    @Test
    public void readSpeedCheck () throws FullMapException {

        long myMapResults = 0;
        long hashMapResults = 0;

        ArrayList<Long> numbers = new ArrayList<>(initialCapacity);

        for (long i = 0; i < initialCapacity; i++) {
            numbers.add(i);
        }

        myMapResults = myMapReedTest(numbers, 10);
        hashMapResults = hashMapReedTest(numbers, 10);

        System.out.printf("Read:\nMy map time results:   %s ns\nHash map time results: %s ns\n %s\n",
                myMapResults,
                hashMapResults,
                resolveSmile(myMapResults, hashMapResults));
    }

    private long myMapReedTest(ArrayList<Long> numbers, int testCounts) throws FullMapException {

        long result = 0;

        for (int i = 0; i < testCounts; i++) {

            HashMap myMap = new HashMap(numbers.size());

            for (int j = 0; j < numbers.size(); j++) {
                Long num = numbers.get(j);
                myMap.put(num.intValue(), num);
            }

            long start = System.nanoTime();
            for (int j = 0; j < numbers.size(); j++) {
                Long num = numbers.get(j);
                myMap.get(num.intValue());
            }
            long stop = System.nanoTime();

            result += stop - start;
        }

        return result/testCounts;
    }

    private long hashMapReedTest(ArrayList<Long> numbers, int testCounts) throws FullMapException {

        long result = 0;

        for (int i = 0; i < testCounts; i++) {
            java.util.HashMap<Integer, Long> hashMap = new java.util.HashMap<>(numbers.size());

            for (int j = 0; j < numbers.size(); j++) {
                Long num = numbers.get(j);
                hashMap.put(num.intValue(), num);
            }

            long start = System.nanoTime();
            for (int j = 0; j < numbers.size(); j++) {
                Long num = numbers.get(j);
                hashMap.get(num.intValue());
            }
            long stop = System.nanoTime();
            result += stop - start;
        }

        return result/testCounts;
    }


    private String resolveSmile(long myMapResults, long hashMapResults) {
        return myMapResults > hashMapResults ? ":(" : ":)";
    }

}
