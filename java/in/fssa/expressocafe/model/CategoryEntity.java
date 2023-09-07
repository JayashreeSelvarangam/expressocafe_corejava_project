package in.fssa.expressocafe.model;

public  abstract class CategoryEntity implements Comparable<CategoryEntity>  {
	private int categoryId;
	private String categoryName;
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int category) {
		this.categoryId = category;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Override
	public String toString() {
		return "CategoryEntity [category=" + categoryId + ", categoryName=" + categoryName + "]";
	}
	
	 public int compareByName(CategoryEntity otherCategory) {
	        return this.categoryName.compareTo(otherCategory.getCategoryName());
	    }

	 public int compareTo(CategoryEntity otherCategory) {
	        if (this.categoryId == otherCategory.getCategoryId()) {
	            return 0;
	        } else {
	            return Integer.compare(this.categoryId, otherCategory.getCategoryId());
	        }
	    }

}
