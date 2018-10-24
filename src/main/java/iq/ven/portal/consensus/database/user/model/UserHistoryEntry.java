package iq.ven.portal.consensus.database.user.model;

import iq.ven.portal.consensus.database.Base;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@PrimaryKeyJoinColumn(name="id")
public class UserHistoryEntry extends Base{

    private User user;

    private String whatsDone;

    private String doneTo;

    private String doneToLink;

    private Date timestamp;
}
