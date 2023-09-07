package in.fssa.expressocafe.model;

public enum SizeEnum {
	LARGE(1),
    MEDIUM(2),
    SMALL(3);

    private final int sizeId;
	

    SizeEnum(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getSizeId() {
        return sizeId;
    }
    
    public static SizeEnum getSizeEnumById(int sizeId) {
        for (SizeEnum sizeEnum : SizeEnum.values()) {
            if (sizeEnum.getSizeId() == sizeId) {
                return sizeEnum;
            }
        }
        return null; 
    }
    
	

}