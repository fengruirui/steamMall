package com.example.steam.redis.key;

import com.example.steam.redis.BaseKey;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-17
 * @time: 22:23
 */
public class SpikeGameKey extends BaseKey {

    public SpikeGameKey(int expiredTime) {
        super(expiredTime);
    }

    public static SpikeGameKey SPIKE_ID=new SpikeGameKey(0);
}