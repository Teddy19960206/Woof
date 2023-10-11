package com.woof.productphoto.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name="product_photo")
public class ProductPhotoVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PROD_PHOTO_NO")
	private Integer prodPhotoNo;

    @Column(name="PROD_NO")
	private Integer prodNo;

    @Lob
    @Column(name="PROD_PHOTO")
	private byte[] prodPhoto;


	public ProductPhotoVO() {
	}

	public Integer getProdPhotoNo() {
		return prodPhotoNo;
	}

	public void setProdPhotoNo(Integer prodPhotoNo) {
		this.prodPhotoNo = prodPhotoNo;
	}

	public Integer getProdNo() {
		return prodNo;
	}

	public void setProdNo(Integer prodNo) {
		this.prodNo = prodNo;
	}

	public byte[] getProdPhoto() {
		return prodPhoto;
	}

	public void setProdPhoto(byte[] prodPhoto) {
		this.prodPhoto = prodPhoto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(prodPhoto);
		result = prime * result + Objects.hash(prodNo, prodPhotoNo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductPhotoVO other = (ProductPhotoVO) obj;
		return Objects.equals(prodNo, other.prodNo) && Arrays.equals(prodPhoto, other.prodPhoto)
				&& Objects.equals(prodPhotoNo, other.prodPhotoNo);
	}

	@Override
	public String toString() {
		return "ProductPhotoVO [prodPhotoNo=" + prodPhotoNo + ", prodNo=" + prodNo + ", prodPhoto="
				+ Arrays.toString(prodPhoto) + "]";
	}
}
