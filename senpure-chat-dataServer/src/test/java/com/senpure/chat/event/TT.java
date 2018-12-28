package com.senpure.chat.event;

import com.senpure.chat.DataBoot;
import org.junit.Test;

import java.net.URL;

/**
 * TT
 *
 * @author senpure
 * @time 2018-12-17 11:04:58
 */
public class TT {

    @Test
    public void test()
    {
        Class clazz = TT.class;
        URL url = clazz.getResource("");
        System.out.println(url);
    }

    public static void main(String[] args) {
        Class clazz = DataBoot.class;
        URL url = clazz.getResource("/");
        System.out.println(url);
    }
}
