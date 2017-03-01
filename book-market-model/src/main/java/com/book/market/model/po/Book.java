package com.book.market.model.po;

/**
 * Created by shj on 16-10-1.
 */
public class Book {
    private Integer id;
    private String name;
    private String desc;
    private String imgUrl;
    private Integer price;
    private String relate;
    private Integer isDelete;

    @Override
    public String
    toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", price=" + price +
                ", relate='" + relate + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (desc != null ? !desc.equals(book.desc) : book.desc != null) return false;
        if (imgUrl != null ? !imgUrl.equals(book.imgUrl) : book.imgUrl != null) return false;
        if (price != null ? !price.equals(book.price) : book.price != null) return false;
        if (relate != null ? !relate.equals(book.relate) : book.relate != null) return false;
        return isDelete != null ? isDelete.equals(book.isDelete) : book.isDelete == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (imgUrl != null ? imgUrl.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (relate != null ? relate.hashCode() : 0);
        result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getRelate() {
        return relate;
    }

    public void setRelate(String relate) {
        this.relate = relate;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
