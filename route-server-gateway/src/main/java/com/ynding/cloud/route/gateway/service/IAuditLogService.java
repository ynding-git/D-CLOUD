package com.ynding.cloud.route.gateway.service;


import com.ynding.cloud.route.gateway.entity.AuditLog;

/**
 * <p> 审计日志服务</p>
 *
 * @author dyn
 * @version 2020/9/27
 * @since JDK 1.8
 */
public interface IAuditLogService {
    /**
     * 保存审计日志
     * @param log
     * @return
     */
    AuditLog save(AuditLog log);

    /**
     * 更新审计日志
     * @param log
     * @return
     */
    AuditLog updateById(AuditLog log);

    /**
     * 根据id查询
     * @param auditLogId
     * @return
     */
    AuditLog getById(Long auditLogId);

}
