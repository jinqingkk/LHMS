package edu.xhu.lhms.module.common.vo;

/**
 * 枚举步骤
 * 1、创建枚举类
 * 2、分号间隔
 * 3、书写枚举属性，使用 public 修饰符
 * 4、写枚举带参构造
 * 5、书写具体的枚举（全大写、逗号间隔）
 */
public enum ImageType {
	TEST("test", 100, 100, 2048),
	PROFILE("profile", 500, 500, 2048),
	NEWS("news", 500, 500, 2048),
	VOLUNACT("volunAct", 500, 500, 2048),
	OLDDONATE("oldDonate", 500, 500, 2048),
	LOVEDONATE("loveDonate", 500, 500, 2048),
	NEEDTHNG("needthing", 500, 500, 2048)
	;

	public String name;
	public int width;
	public int height;
	public int size;

	ImageType(String name, int width, int height, int size) {
		this.name = name;
		this.width = width;
		this.height = height;
		this.size = size;
	}

	public static ImageType getImageTypeByName(String name) {
		for (ImageType item : ImageType.values()) {
			if (item.name.equals(name)) {
				return item;
			}
		}
		 return null;
	}
}
