package iq.ven.portal.consensus.database.project.repository;

import iq.ven.portal.consensus.database.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("projectRepository")
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findById(Long id);

    Project findByName(String name);

    Project findByAbbreviation(String abbreviation);
}
