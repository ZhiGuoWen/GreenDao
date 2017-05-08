package com.wenzhiguo.greendao;

import java.util.List;

/**
 * Created by dell on 2017/5/3.
 * action :GreenDao增删改查
 */

public class LoveDao {
    /**
     * 添加数据，如果有重复则覆盖
     */
    public void insertLove(Bean shop) {
        App.daoSession().getBeanDao().insertOrReplace(shop);
    }
    /**
     * 删除数据
     */
    public void deleteLove(long id) {
        App.daoSession().getBeanDao().deleteByKey(id);
    }
    /**
     * 更新数据
     */
    public void updateLove(Bean shop) {
        App.daoSession().getBeanDao().update(shop);
    }
    /**
     * 查询全部数据
     */
    public List<Bean> queryAll() {
        return App.daoSession().getBeanDao().loadAll();
    }
}
