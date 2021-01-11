package org.xxx;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashSet;

/**
 * @author Deveik
 */
public class BasicTest {

    @Test
    public void conditionAssert() {
        System.out.println("Exe 1");
        Assert.assertEquals(1, 1);
    }

    @Test
    public void repeatAddLinkedSet() {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        set.add(1);
        set.add(5);
        set.add(4);
        set.add(5);
        set.add(3);
        set.add(1);
        for (Integer val : set) {
            System.out.println(val);
        }
    }

}
