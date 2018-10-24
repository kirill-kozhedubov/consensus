package iq.ven.portal.consensus.database.project.model;

import iq.ven.portal.consensus.database.Base;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
@PrimaryKeyJoinColumn(name="id")
public class Project extends Base {


}
