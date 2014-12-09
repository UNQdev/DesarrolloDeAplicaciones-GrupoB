package ar.edu.unq.desapp.grupob.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.grupob.model.Category;
import ar.edu.unq.desapp.grupob.model.Operation;

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

	public HashMap<String, Double> calculateCategoriesTotals() {
		List<Operation> monthOperations = new ArrayList<Operation>();
		HashMap<String, Double> categoriesTotals = new HashMap<String, Double>();
		
		//Get MONTH OPERATIONS
		monthOperations = getOperationService().getMonthOperations(DateTime.now());
		
		//Iterate OPERATIONS and inject the real amount to the CATEGORY
		for(Operation operation : monthOperations) {
			String operationCategory = operation.getCategory().getName();
			if (categoriesTotals.containsKey(operationCategory)) {
				categoriesTotals.put(operationCategory, categoriesTotals.get(operationCategory)
						+ operation.getRealAmount());
			} else {
				categoriesTotals.put(operationCategory, operation.getRealAmount());
			}
		}
		
		return categoriesTotals;
	}

	public HashMap<String, Double> calculateSubCategoriesTotals() {
		List<Operation> monthOperations = new ArrayList<Operation>();
		HashMap<String, Double> subCategoriesTotals = new HashMap<String, Double>();
		
		//Get MONTH OPERATIONS
		monthOperations = getOperationService().getMonthOperations(DateTime.now());
		
		//Iterate OPERATIONS and inject the real amount to the CATEGORY
		for(Operation operation : monthOperations) {
			String operationSubCategory = operation.getSubcategory().getName();
			if (subCategoriesTotals.containsKey(operationSubCategory)) {
				subCategoriesTotals.put(operationSubCategory, subCategoriesTotals.get(operationSubCategory)
						+ operation.getRealAmount());
			} else {
				subCategoriesTotals.put(operationSubCategory, operation.getRealAmount());
			}
		}
		
		return subCategoriesTotals;
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
