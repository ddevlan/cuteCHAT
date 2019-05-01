package me.ohvalsgod.cutechat.util;

import org.bson.conversions.Bson;

public class MongoUtil {

    public static Bson find(String key, Object value) {
        return com.mongodb.client.model.Filters.eq(key, String.valueOf(value));
    }

}
