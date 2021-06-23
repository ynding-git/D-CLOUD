package com.ynding.cloud.route.gateway.data;

import com.ynding.cloud.route.gateway.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p> 审计日志repository</p>
 *
 * @author dyn
 * @version 2020/9/27
 * @since JDK 1.8
 */
public interface AuditLogRepository extends JpaRepository<AuditLog, Long>, JpaSpecificationExecutor<AuditLog> {
}
