package in.fssa.expressocafe.model;

public  class Category  extends CategoryEntity{

	public Category() {
		super();
		
	}
	public Category(int category_id ,  String categoryName) {
		super.setCategoryId(category_id);
		super.setCategoryName(categoryName);
		
	}


}
