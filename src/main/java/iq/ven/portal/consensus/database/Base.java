package iq.ven.portal.consensus.database;


import javax.persistence.*;

@Entity
@Table(name = "base")
@Inheritance(strategy = InheritanceType.JOINED)
public class Base {
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
