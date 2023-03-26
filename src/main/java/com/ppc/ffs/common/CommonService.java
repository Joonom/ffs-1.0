package com.ppc.ffs.common;

public class CommonService {
    static int g_count = 0;
    public static final String makeKey(int nSize) {
        String sResult = "";
        long tv = System.currentTimeMillis() / 1000L + (long)(g_count++);
        if (g_count > 1000) {
            g_count = 0;
        }

        sResult = Long.toHexString(tv) + Long.toHexString(Double.doubleToLongBits(Math.random()));
        int nLen = sResult.length();
        if (nLen > nSize) {
            return sResult.substring(0, nSize);
        } else {
            for(int i = nLen; i < nSize; ++i) {
                long lRand = (long)(Math.random() * 10.0D);
                sResult = sResult + lRand;
            }

            return sResult;
        }
    }
}
