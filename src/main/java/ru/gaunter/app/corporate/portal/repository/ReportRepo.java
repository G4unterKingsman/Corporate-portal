package ru.gaunter.app.corporate.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gaunter.app.corporate.portal.entity.ReportEntity;


@Repository
public interface ReportRepo extends JpaRepository<ReportEntity,Long> {

}
