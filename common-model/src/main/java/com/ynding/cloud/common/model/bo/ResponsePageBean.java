package com.ynding.cloud.common.model.bo;

/**
 * 分页bean
 */
public class ResponsePageBean<Entity> extends ResponseBean<Entity> {
    private long total;

    private ResponsePageBean(Entity data, long total) {
        super(data);
        this.total = total;
    }

    public static <Entity> ResponsePageBean<Entity> ok(Entity data, long total) {
        return new ResponsePageBean(data, total);
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}