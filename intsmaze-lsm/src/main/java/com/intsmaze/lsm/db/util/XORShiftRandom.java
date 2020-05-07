package com.intsmaze.lsm.db.util;

/**
 * A simple xor shift random number generator. Generates a predictable stream of pseudo-random numbers from a
 * starting seed.
 */
public class XORShiftRandom {

    private long last;

    public XORShiftRandom(long seed) {
        this.last = seed;
    }

    public int nextInt(int max) {
        last ^= (last << 21);
        last ^= (last >>> 35);
        last ^= (last << 4);
        int out = (int) last % max;
        return (out < 0) ? -out : out;
    }

    public int nextInt() {
        last ^= (last << 21);
        last ^= (last >>> 35);
        last ^= (last << 4);
        int out = (int) last;
        return (out < 0) ? -out : out;
    }
}
