package ar.edu.unq.desapp.grupob.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupob.model.Operation;

public class OperationDAO extends HibernateGenericDAO<Operation> implements
        GenericDAO<Operation> {

    private static final long serialVersionUID = 939209274312970536L;

    @Override
    protected Class<Operation> getDomainClass() {
        return Operation.class;
    }

    @SuppressWarnings("unchecked")
    public List<Operation> filterByName(String name) {
        List<Operation> operations = this.getSession().createCriteria(Operation.class)
                .add(Restrictions.ilike("name", name, MatchMode.ANYWHERE))
                .list();
        return operations;
    }
}
