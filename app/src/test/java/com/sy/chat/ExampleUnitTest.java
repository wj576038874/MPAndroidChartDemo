package com.sy.chat;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Random random = new Random();
        random.nextFloat();
        System.out.println(random.nextFloat());
        System.out.println(Math.random()*5.0);
    }
}