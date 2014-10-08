package ar.edu.unq.desapp.grupob.dao;

import ar.edu.unq.desapp.grupob.model.Category;

public class CategoryDAO extends HibernateGenericDAO<Category> implements
		GenericDAO<Category> {

	private static final long serialVersionUID = 939209274312970536L;

	@Override
	protected Class<Category> getDomainClass() {
		return Category.class;
	}

}
