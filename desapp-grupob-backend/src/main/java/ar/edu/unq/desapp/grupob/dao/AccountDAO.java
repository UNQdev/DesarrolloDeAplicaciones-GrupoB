package ar.edu.unq.desapp.grupob.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupob.model.Account;
import ar.edu.unq.desapp.grupob.model.Category;

public class AccountDAO extends HibernateGenericDAO<Account> implements
        GenericDAO<Account> {

    private static final long serialVersionUID = 7723773699720888349L;

    @Override
    public List<Account> filterByName(String name) {
        @SuppressWarnings("unchecked")
        List<Account> accounts = this.getSession().createCriteria(this.getDomainClass())
                .add(Restrictions.ilike("accountName", name, MatchMode.ANYWHERE))
                .list();
        return accounts;
    }
    
    @Override
    protected Class<Account> getDomainClass() {
        return Account.class;
    }
    
    @Override
    public Account findByName(String name) {
        Account account = (Account) this.getSession().createCriteria(getDomainClass())
                .add(Restrictions.ilike("accountName", name, MatchMode.EXACT)).uniqueResult();
        return account;
    }

}
