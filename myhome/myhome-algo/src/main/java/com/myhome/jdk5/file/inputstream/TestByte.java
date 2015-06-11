package com.myhome.jdk5.file.inputstream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.util.Random;

/* 
 * @(#)TestByte.java
 *
 * Copyright (c) 2008 DCNS SA. All rights reserved.
 * DCNS PROPRIETARY/CONFIDENTIAL.  Use is subject to license terms.
 *
 * This file, together  with  its accompanying  software product  and
 * documentation, is  protected by the  intellectual  property rights
 * in  France  and  other  countries, any  applicable  copyrights  or
 * patent rights, and international treaty provisions. No part may be
 * reproduced  in  any  form  by  any  mean  without   prior  written
 * authorization of DCNS.
 */

public class TestByte {
    // An example of using ByteBuffer

 

    public static void main(String[] args) {

        // Create a ByteBuffer using a byte array
        byte[] bytes = new byte[10];
        ByteBuffer buf = ByteBuffer.wrap(bytes);

        // Create a non-direct ByteBuffer with a 10 byte capacity
        // The underlying storage is a byte array.
        buf = ByteBuffer.allocate(10);

        // Create a direct (memory-mapped) ByteBuffer with a 10 byte capacity.
        buf = ByteBuffer.allocateDirect(10);

        // Get the ByteBuffer's capacity
        int capacity = buf.capacity(); // 10

        // Use the absolute get(). This method does not affect the position.
        byte b = buf.get(5); // position=0

        // Set the position
        buf.position(5);

        // Use the relative get()
        b = buf.get();

        // Get the new position
        int pos = buf.position(); // 6

        // Get remaining byte count
        int rem = buf.remaining(); // 4

        // Set the limit
        buf.limit(7); // remaining=1

        // This convenience method sets the position to 0
        buf.rewind(); // remaining=7

        // Use the absolute put(). This method does not affect the position.
        buf.put((byte) 0xFF); // position=0

        // Use the relative put()
        buf.put((byte) 0xFF);

        // This convenience method sets the position to 0
        buf.rewind(); // remaining=7

    }
}
