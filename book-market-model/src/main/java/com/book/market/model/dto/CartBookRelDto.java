package com.book.market.model.dto;

import com.book.market.model.po.CartBookRel;

/**
 * Created by shj on 16-10-4.
 */
public class CartBookRelDto extends CartBookRel {
    private boolean isChosen;
    private String imgUrl;



    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CartBookRelDto that = (CartBookRelDto) o;

        if (isChosen != that.isChosen) return false;
        if (imgUrl != null ? !imgUrl.equals(that.imgUrl) : that.imgUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (isChosen ? 1 : 0);
        result = 31 * result + (imgUrl != null ? imgUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CartBookRelDto{" +
                "isChosen=" + isChosen +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public boolean getIsChosen() {
        return isChosen;
    }

    public void setIsChosen(boolean chosen) {
        isChosen = chosen;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
