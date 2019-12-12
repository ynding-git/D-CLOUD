package com.ynding.cloud.common.model.bo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * GET请求参数
 */
public class GQuery extends LinkedHashMap<String, Object> {
    private int page = 1;
    private int limit = 10;
    private int offset = 0;

    public GQuery() {
    }

    public GQuery(Map<String, Object> params) {
        this.putAll(params);
        if (params.get("page") != null) {
            this.page = Integer.parseInt(params.get("page").toString());
            this.put("page", this.page);
        }

        if (params.get("offset") != null) {
            this.offset = Integer.parseInt(params.get("offset").toString());
            this.put("offset", this.offset);
        }

        if (params.get("start") != null) {
            this.offset = Integer.parseInt(params.get("start").toString());
            this.put("offset", this.offset);
            this.remove("start");
        }

        if (params.get("limit") != null) {
            this.limit = Integer.parseInt(params.get("limit").toString());
            this.put("limit", this.limit);
        }

        if (this.limit > 0) {
            if (params.get("page") != null) {
                this.offset = (this.page - 1) * this.limit;
            } else {
                this.page = this.offset / this.limit + 1;
            }
        }

        if (params.get("filter") != null) {
            this.put("filter", ((String)params.get("filter")).split("[\\s]"));
        }

    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
