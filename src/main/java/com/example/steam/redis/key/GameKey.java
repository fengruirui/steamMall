package com.example.steam.redis.key;

import com.example.steam.redis.BaseKey;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-28
 * @time: 14:07
 */
public class GameKey extends BaseKey {

    private final static int FUTURED_KEY_TIME=30;

    public final static String FUTURED_KEY="futured";

    public final static String SPECIAL_INDEX_KEY="special_index";

    private final static int SPECIAL_KEY_TIME=40;

    public final static String NEW_RELEASE_INDEX_KEY="newRelease_index";

    private final static int NEW_RELEASE_INDEX_KEY_TIME=60;

    public final static String HOT_SELL_INDEX_KEY="hotSell_index";

    private final static int HOT_SELL_INDEX_KEY_TIME=60;

    public final static String UP_COMING_INDEX_KEY="upComing_index";

    private final static int UP_COMING_INDEX_KEY_TIME=60;

    public final static String GAME_SUM_KEY="gameSum";

    public final static String GAME_RANK_TIME="gameRankTime";

    public final static String GAME_RANK_SELLNUM="gameRankSellNum";

    public final static String GAME_RANK_UPCOMING="gameUpComing";

    private GameKey(int expiredTime){
        super(expiredTime);
    }

    public final static GameKey GAME_ID=new GameKey(0);

    public final static GameKey FETURED_GAME=new GameKey(FUTURED_KEY_TIME);

    public final static GameKey SPECIAL_INDEX_GAME=new GameKey(SPECIAL_KEY_TIME);

    public final static GameKey NEW_RELEASE_INDEX_GAME=new GameKey(NEW_RELEASE_INDEX_KEY_TIME);

    public final static GameKey HOT_SELL_INDEX_GAME=new GameKey(HOT_SELL_INDEX_KEY_TIME);

    public final static GameKey UP_COMING_INDEX_GAME=new GameKey(UP_COMING_INDEX_KEY_TIME);

    public final static GameKey GAME_SUM=new GameKey(0);

    public final static GameKey RANK_TIME=new GameKey(0);

    public final static GameKey RANK_SELLNUM=new GameKey(0);

    public final static GameKey RANK_UPCOMING=new GameKey(0);
}
