package ar.edu.unq.desapp.grupob.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupob.model.Category;
import ar.edu.unq.desapp.grupob.model.Operation;
import ar.edu.unq.desapp.grupob.model.SubCategory;

/**
 * 
 * @author bananee
 *
 */
public class CategoryAnalysisService extends GenericService<Category> {

	private static final long serialVersionUID = -945077247660549087L;
	
	private static OperationService operationService;
	private static CategoryService categoryService;
	private static SubCategoryService subCategoryService;

	public HashMap<String, Double> calculateCategoriesIncomingTotals() {
		List<Operation> monthOperations = new ArrayList<Operation>();
		monthOperations = getOperationService().getMonthOperations(DateTime.now());
		
		List<Category> categoriesDomain = new ArrayList<Category>();
		categoriesDomain = this.getCategoryService().retriveAll();

		HashMap<String, Double> categoriesIncomingTotals = new HashMap<String, Double>();

		for(Category category : categoriesDomain) {
			categoriesIncomingTotals.put(category.getName(), 0.0);
		}

		for(Operation operation : monthOperations) {
			String operationCategory = operation.getCategory().getName();
			if (operation.getRealAmount() > 0) {
				categoriesIncomingTotals.put(operationCategory, categoriesIncomingTotals.get(operationCategory)
						+ operation.getAmount());
			}
		}
		return categoriesIncomingTotals;
	}
	
	public HashMap<String, Double> calculateCategoriesOutcomingTotals() {
		List<Operation> monthOperations = new ArrayList<Operation>();
		monthOperations = getOperationService().getMonthOperations(DateTime.now());
		
		List<Category> categoriesDomain = new ArrayList<Category>();
		categoriesDomain = this.getCategoryService().retriveAll();

		HashMap<String, Double> categoriesOutcomingTotals = new HashMap<String, Double>();

		for(Category category : categoriesDomain) {
			categoriesOutcomingTotals.put(category.getName(), 0.0);
		}
		
		for(Operation operation : monthOperations) {
			String operationCategory = operation.getCategory().getName();
			if (operation.getRealAmount() < 0) {
				categoriesOutcomingTotals.put(operationCategory, categoriesOutcomingTotals.get(operationCategory)
						+ operation.getAmount());
			}
		}
		return categoriesOutcomingTotals;
	}

	public HashMap<String, Double> calculateSubCategoriesIncomingTotals() {
		List<Operation> monthOperations = new ArrayList<Operation>();
		monthOperations = getOperationService().getMonthOperations(DateTime.now());
		
		List<SubCategory> subCategoriesDomain = new ArrayList<SubCategory>();
		subCategoriesDomain = this.getSubCategoryService().retriveAll();

		HashMap<String, Double> subCategoriesIncomingTotals = new HashMap<String, Double>();

		for(SubCategory subcategory : subCategoriesDomain) {
			subCategoriesIncomingTotals.put(subcategory.getName(), 0.0);
		}
		
		for(Operation operation : monthOperations) {
			String operationSubCategory = operation.getSubcategory().getName();
			if (operation.getRealAmount() > 0) {
				subCategoriesIncomingTotals.put(operationSubCategory, subCategoriesIncomingTotals.get(operationSubCategory)
						+ operation.getAmount());
			}
		}
		
		return subCategoriesIncomingTotals;
	}
	
	public HashMap<String, Double> calculateSubCategoriesOutcomingTotals() {
		List<Operation> monthOperations = new ArrayList<Operation>();
		monthOperations = getOperationService().getMonthOperations(DateTime.now());
		
		List<SubCategory> subCategoriesDomain = new ArrayList<SubCategory>();
		subCategoriesDomain = this.getSubCategoryService().retriveAll();

		HashMap<String, Double> subCategoriesOutcomingTotals = new HashMap<String, Double>();

		for(SubCategory subcategory : subCategoriesDomain) {
			subCategoriesOutcomingTotals.put(subcategory.getName(), 0.0);
		}
		
		for(Operation operation : monthOperations) {
			String operationSubCategory = operation.getSubcategory().getName();
			if (operation.getRealAmount() > 0) {
				subCategoriesOutcomingTotals.put(operationSubCategory, subCategoriesOutcomingTotals.get(operationSubCategory)
						+ operation.getAmount());
			}
		}
		
		return subCategoriesOutcomingTotals;
	}

	public OperationService getOperationService() {
		return operationService;
	}

	public void setOperationService(OperationService operationService) {
		this.operationService = operationService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public SubCategoryService getSubCategoryService() {
		return subCategoryService;
	}

	public void setSubCategoryService(SubCategoryService subCategoryService) {
		this.subCategoryService = subCategoryService;
	}

	
}
