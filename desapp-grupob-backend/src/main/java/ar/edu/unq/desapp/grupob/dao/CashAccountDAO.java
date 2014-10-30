package ar.edu.unq.desapp.grupob.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupob.model.CashAccount;

public class CashAccountDAO extends HibernateGenericDAO<CashAccount> implements
        GenericDAO<CashAccount> {

    private static final long serialVersionUID = -7390724273934306164L;

    @Override
    public List<CashAccount> filterByName(String name) {
        @SuppressWarnings("unchecked")
        List<CashAccount> c = this.getSession().createCriteria(CashAccount.class)
                .add(Restrictions.ilike("name", name, MatchMode.ANYWHERE))
                .list();
        return c;
    }

    @Override
    protected Class<CashAccount> getDomainClass() {
        return CashAccount.class;
    }

}
