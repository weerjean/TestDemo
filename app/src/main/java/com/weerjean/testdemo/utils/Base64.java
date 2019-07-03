package com.weerjean.testdemo.utils;

import java.io.UnsupportedEncodingException;

public class Base64 {
    private static final byte[] MAP = new byte[]{(byte)65, (byte)66, (byte)67, (byte)68, (byte)69, (byte)70, (byte)71, (byte)72, (byte)73, (byte)74, (byte)75, (byte)76, (byte)77, (byte)78, (byte)79, (byte)80, (byte)81, (byte)82, (byte)83, (byte)84, (byte)85, (byte)86, (byte)87, (byte)88, (byte)89, (byte)90, (byte)97, (byte)98, (byte)99, (byte)100, (byte)101, (byte)102, (byte)103, (byte)104, (byte)105, (byte)106, (byte)107, (byte)108, (byte)109, (byte)110, (byte)111, (byte)112, (byte)113, (byte)114, (byte)115, (byte)116, (byte)117, (byte)118, (byte)119, (byte)120, (byte)121, (byte)122, (byte)48, (byte)49, (byte)50, (byte)51, (byte)52, (byte)53, (byte)54, (byte)55, (byte)56, (byte)57, (byte)43, (byte)47};

    private Base64() {
    }

    public static byte[] decode(byte[] in) {
        return decode(in, in.length);
    }

    public static byte[] decode(byte[] in, int len) {
        int length = len / 4 * 3;
        if(length == 0) {
            return new byte[0];
        } else {
            byte[] out = new byte[length];
            int pad = 0;

            while(true) {
                byte chr = in[len - 1];
                if(chr != 10 && chr != 13 && chr != 32 && chr != 9) {
                    if(chr != 61) {
                        int outIndex = 0;
                        int inIndex = 0;
                        boolean bits = false;
                        int quantum = 0;

                        for(int result = 0; result < len; ++result) {
                            chr = in[result];
                            if(chr != 10 && chr != 13 && chr != 32 && chr != 9) {
                                int var11;
                                if(chr >= 65 && chr <= 90) {
                                    var11 = chr - 65;
                                } else if(chr >= 97 && chr <= 122) {
                                    var11 = chr - 71;
                                } else if(chr >= 48 && chr <= 57) {
                                    var11 = chr + 4;
                                } else if(chr == 43) {
                                    var11 = 62;
                                } else {
                                    if(chr != 47) {
                                        return null;
                                    }

                                    var11 = 63;
                                }

                                quantum = quantum << 6 | (byte)var11;
                                if(inIndex % 4 == 3) {
                                    out[outIndex++] = (byte)(quantum >> 16);
                                    out[outIndex++] = (byte)(quantum >> 8);
                                    out[outIndex++] = (byte)quantum;
                                }

                                ++inIndex;
                            }
                        }

                        if(pad > 0) {
                            quantum <<= 6 * pad;
                            out[outIndex++] = (byte)(quantum >> 16);
                            if(pad == 1) {
                                out[outIndex++] = (byte)(quantum >> 8);
                            }
                        }

                        byte[] var12 = new byte[outIndex];
                        System.arraycopy(out, 0, var12, 0, outIndex);
                        return var12;
                    }

                    ++pad;
                }

                --len;
            }
        }
    }

    public static String encode(byte[] in) {
        int length = (in.length + 2) * 4 / 3;
        byte[] out = new byte[length];
        int index = 0;
        int end = in.length - in.length % 3;

        for(int e = 0; e < end; e += 3) {
            out[index++] = MAP[(in[e] & 255) >> 2];
            out[index++] = MAP[(in[e] & 3) << 4 | (in[e + 1] & 255) >> 4];
            out[index++] = MAP[(in[e + 1] & 15) << 2 | (in[e + 2] & 255) >> 6];
            out[index++] = MAP[in[e + 2] & 63];
        }

        switch(in.length % 3) {
            case 1:
                out[index++] = MAP[(in[end] & 255) >> 2];
                out[index++] = MAP[(in[end] & 3) << 4];
                out[index++] = 61;
                out[index++] = 61;
                break;
            case 2:
                out[index++] = MAP[(in[end] & 255) >> 2];
                out[index++] = MAP[(in[end] & 3) << 4 | (in[end + 1] & 255) >> 4];
                out[index++] = MAP[(in[end + 1] & 15) << 2];
                out[index++] = 61;
                break;
            default:
                break;
        }

        try {
            return new String(out, 0, index, "US-ASCII");
        } catch (UnsupportedEncodingException var6) {
            throw new AssertionError(var6);
        }
    }
}
