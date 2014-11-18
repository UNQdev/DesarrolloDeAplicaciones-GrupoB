package ar.edu.unq.desapp.grupob.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupob.model.Operation;

public class OperationDAO extends HibernateGenericDAO<Operation> implements
        GenericDAO<Operation> {

    private static final long serialVersionUID = 9035841822549761007L;

    @Override
    public List<Operation> filterByName(String concept) {
        @SuppressWarnings("unchecked")
        List<Operation> operations = this.getSession().createCriteria(getDomainClass())
                .add(Restrictions.ilike("concept", concept, MatchMode.ANYWHERE))
                .list();
        return operations;
    }
    @Override
    protected Class<Operation> getDomainClass() {
        return Operation.class;
    }

    @Override
    public Operation findByName(String concept) {
       Operation operation = (Operation) this.getSession().createCriteria(getDomainClass())
               .add(Restrictions.ilike("concept", concept, MatchMode.ANYWHERE))
               .uniqueResult();
       return operation;
    }

}
