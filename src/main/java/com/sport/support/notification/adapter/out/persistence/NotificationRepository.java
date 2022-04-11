package com.sport.support.notification.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

}
