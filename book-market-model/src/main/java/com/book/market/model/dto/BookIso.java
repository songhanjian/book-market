package com.book.market.model.dto;

/**
 * Created by shj on 16-10-3.
 */
public class BookIso {
    private Integer id;
    private String name;
    private String imgUrl;


    @Override
    public String toString() {
        return "BookIso{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {


        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookIso bookIso = (BookIso) o;

        if (id != null ? !id.equals(bookIso.id) : bookIso.id != null) return false;
        if (name != null ? !name.equals(bookIso.name) : bookIso.name != null) return false;
        return imgUrl != null ? imgUrl.equals(bookIso.imgUrl) : bookIso.imgUrl == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (imgUrl != null ? imgUrl.hashCode() : 0);
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
