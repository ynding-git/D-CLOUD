package com.ynding.cloud.route.gateway.service.impl;

import com.ynding.cloud.route.gateway.data.AuditLogRepository;
import com.ynding.cloud.route.gateway.entity.AuditLog;
import com.ynding.cloud.route.gateway.service.IAuditLogService;
import org.springframework.stereotype.Service;

/**
 * <p> 日志服务</p>
 *
 * @author dyn
 * @version 2020/9/27
 * @since JDK 1.8
 */
@Service
public class AuditLogServiceImpl implements IAuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogServiceImpl(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public AuditLog save(AuditLog log) {
        return auditLogRepository.save(log);
    }

    @Override
    public AuditLog updateById(AuditLog log) {
        return auditLogRepository.save(log);
    }

    @Override
    public AuditLog getById(Long auditLogId) {
        return auditLogRepository.findById(auditLogId).orElse(null);
    }
}
