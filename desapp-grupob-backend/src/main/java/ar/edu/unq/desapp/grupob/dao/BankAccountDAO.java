package ar.edu.unq.desapp.grupob.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupob.model.BankAccount;

public class BankAccountDAO extends HibernateGenericDAO<BankAccount> implements
        GenericDAO<BankAccount> {

    private static final long serialVersionUID = 7723773699720888349L;

    @Override
    public List<BankAccount> filterByName(String name) {
        @SuppressWarnings("unchecked")
        List<BankAccount> b = this.getSession().createCriteria(BankAccount.class)
                .add(Restrictions.ilike("name", name, MatchMode.ANYWHERE))
                .list();
        return b;
    }

    @Override
    protected Class<BankAccount> getDomainClass() {
        return BankAccount.class;
    }

}
