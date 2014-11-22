package ar.edu.unq.desapp.grupob.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.desapp.grupob.model.SubCategory;

public class SubCategoryDAO extends HibernateGenericDAO<SubCategory> implements
        GenericDAO<SubCategory> {
    private static final long serialVersionUID = 939209274312970536L;

    @Override
    protected Class<SubCategory> getDomainClass() {
        return SubCategory.class;
    }

    @SuppressWarnings("unchecked")
    public List<SubCategory> filterByName(String name) {
        List<SubCategory> subcategories = this.getSession()
                .createCriteria(getDomainClass())
                .add(Restrictions.ilike("name", name, MatchMode.EXACT))
                .list();
        return subcategories;
    }

    @Override
    public SubCategory findByName(String name) {
        SubCategory subcategory = (SubCategory) this.getSession()
                    .createCriteria(getDomainClass())
                    .add(Restrictions.ilike("name", name, MatchMode.ANYWHERE))
                    .uniqueResult();
        return subcategory;
    }
}
