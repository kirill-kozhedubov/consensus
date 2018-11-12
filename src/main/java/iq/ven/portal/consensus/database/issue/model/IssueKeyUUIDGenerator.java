package iq.ven.portal.consensus.database.issue.model;


import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

public class IssueKeyUUIDGenerator extends UUIDGenerator {

    private String entityName;

    @Override
    public void configure(Type type, Properties params, ServiceRegistry dialect) {
        entityName = params.getProperty(ENTITY_NAME);
        super.configure(type, params, dialect);
    }

    @Override
    public Serializable generate(SessionImplementor session, Object object) {
        Serializable id = session
                .getEntityPersister(entityName, object)
                .getIdentifier(object, session);


        if (id == null) {
            return super.generate(session, object);
        } else {
            return id;
        }
    }
}
