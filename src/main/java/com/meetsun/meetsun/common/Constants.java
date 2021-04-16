package com.meetsun.meetsun.common;

public enum Constants {
	
	SAVE("新增",1),DELETE("删除",2),UPDATE("修改",3),SELECT("查询",4),LOGIN("登录",5);
	
	private String name;  
    private int index; 
    
    // 构造方法
    private Constants(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }  
    // 普通方法  
    public static String getName(int index) {  
        for (Constants c : Constants.values()) {  
            if (c.getIndex() == index) {  
                return c.name;  
            }  
        }  
        return null;  
    }  
    // get set 方法  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public int getIndex() {  
        return index;  
    }  
    public void setIndex(int index) {  
        this.index = index;  
    }  
}
