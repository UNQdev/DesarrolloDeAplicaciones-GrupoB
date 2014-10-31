package ar.edu.unq.desapp.grupob.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupob.model.Operation;

public class OperationDAO extends HibernateGenericDAO<Operation> implements
        GenericDAO<Operation> {

    private static final long serialVersionUID = 9035841822549761007L;

    @Override
    public List<Operation> filterByName(String name) {
        @SuppressWarnings("unchecked")
        List<Operation> operations = this.getSession().createCriteria(Operation.class)
                .add(Restrictions.ilike("name", name, MatchMode.ANYWHERE))
                .list();
        return operations;
    }

    @Override
    protected Class<Operation> getDomainClass() {
        return Operation.class;
    }

}
