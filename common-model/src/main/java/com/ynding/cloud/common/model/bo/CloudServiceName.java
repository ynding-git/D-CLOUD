package com.ynding.cloud.common.model.bo;

/**
 * 服务实例名称
 */
public final class CloudServiceName {

    //构造器私有化
    private CloudServiceName(){
        throw new AssertionError();
    }

    public final static String CORE_SERVER_BUS = "core-server-bus";

    public final static String PHYSICAL_BOOK_META = "physical-book-meta";
}
