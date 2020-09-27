package com.ynding.cloud.route.zuul.data;

import com.ynding.cloud.route.zuul.entity.AuditLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p> </p>
 *
 * @author dyn
 * @version 2020/9/27
 * @since JDK 1.8
 */
public interface AuditLogRepository extends JpaRepository<AuditLog, Long>, JpaSpecificationExecutor<AuditLog> {
}
