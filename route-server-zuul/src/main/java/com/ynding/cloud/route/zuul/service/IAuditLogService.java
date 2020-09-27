package com.ynding.cloud.route.zuul.service;

import com.ynding.cloud.route.zuul.entity.AuditLog;

/**
 * <p> </p>
 *
 * @author dyn
 * @version 2020/9/27
 * @since JDK 1.8
 */
public interface IAuditLogService {
    AuditLog save(AuditLog log);

    AuditLog updateById(AuditLog log);
}
