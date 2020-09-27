package com.ynding.cloud.route.zuul.service.impl;

import com.ynding.cloud.route.zuul.data.AuditLogRepository;
import com.ynding.cloud.route.zuul.entity.AuditLog;
import com.ynding.cloud.route.zuul.service.IAuditLogService;

import org.springframework.stereotype.Service;

/**
 * <p> </p>
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
}
