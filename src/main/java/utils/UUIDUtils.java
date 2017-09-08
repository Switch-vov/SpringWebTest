package utils;

import java.util.UUID;

/**
 * @author mike.huang
 * @since 2016-12-05
 */
public class UUIDUtils {

    public static String genUUID(){
        StringBuilder sbd = new StringBuilder(UUID.randomUUID().toString());
        return sbd.deleteCharAt(23).deleteCharAt(18).
                deleteCharAt(13).deleteCharAt(8).toString();
    }

}
