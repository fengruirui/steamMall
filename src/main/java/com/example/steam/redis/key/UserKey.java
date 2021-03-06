package com.example.steam.redis.key;

import com.example.steam.redis.BaseKey;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 17:30
 */
public class UserKey extends BaseKey {

    public static final String CONTAINS_KEY="gamecontains:";

    public static final String CONTAINS_CART_KEY="cartcontains:";

    public static final String ADMIN_KEY="admin:";

    private final static int expireTime=7200;

    private UserKey(int expireTime){
        super(expireTime);
    }

    public final static UserKey USER_ID=new UserKey(0);

    public final static UserKey COOKIE_ID=new UserKey(expireTime);

    public final static UserKey CONTAINS_GAMES=new UserKey(0);

    public final static UserKey CONTAINS_CART=new UserKey(0);

    public final static UserKey ADMIN_EMAIL=new UserKey(expireTime);

}
