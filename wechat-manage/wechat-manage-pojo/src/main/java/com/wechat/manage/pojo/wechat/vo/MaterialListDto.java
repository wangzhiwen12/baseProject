package com.wechat.manage.pojo.wechat.vo;

import com.wechat.manage.pojo.wechat.entity.Material;

public class MaterialListDto {
	private Material material_1;
	private Material material_2;
	private Material material_3;
	private Material material_4;
	private Material material_5;

	public Material getMaterial_1() {
		return material_1;
	}

	public void setMaterial_1(Material material_1) {
		this.material_1 = material_1;
	}

	public Material getMaterial_2() {
		return material_2;
	}

	public void setMaterial_2(Material material_2) {
		this.material_2 = material_2;
	}

	public Material getMaterial_3() {
		return material_3;
	}

	public void setMaterial_3(Material material_3) {
		this.material_3 = material_3;
	}

	public Material getMaterial_4() {
		return material_4;
	}

	public void setMaterial_4(Material material_4) {
		this.material_4 = material_4;
	}

	public Material getMaterial_5() {
		return material_5;
	}

	public void setMaterial_5(Material material_5) {
		this.material_5 = material_5;
	}

	@Override
	public String toString() {
		return "MaterialListDto [material_1=" + material_1 + ", material_2=" + material_2
				+ ", material_3=" + material_3 + ", material_4=" + material_4 + ", material_5="
				+ material_5 + "]";
	}

}
