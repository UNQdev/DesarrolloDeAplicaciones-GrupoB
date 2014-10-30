package ar.edu.unq.desapp.grupob.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupob.model.CurrentAccount;

public class CurrentAccountDAO extends HibernateGenericDAO<CurrentAccount>
        implements GenericDAO<CurrentAccount> {

    private static final long serialVersionUID = 8688972464261432514L;

    @Override
    public List<CurrentAccount> filterByName(String name) {
        @SuppressWarnings("unchecked")
        List<CurrentAccount> c = this.getSession().createCriteria(CurrentAccount.class)
                .add(Restrictions.ilike("name", name, MatchMode.ANYWHERE))
                .list();
        return c;
    }

    @Override
    protected Class<CurrentAccount> getDomainClass() {
        return CurrentAccount.class;
    }

}
