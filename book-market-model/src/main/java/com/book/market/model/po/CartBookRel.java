package com.book.market.model.po;

/**
 * Created by shj on 16-10-1.
 */
public class CartBookRel {
    private Integer id;
    private Integer cartId;
    private Integer bookId;
    private String bookName;
    private Integer bookPrice;
    private Integer count;
    private Integer totalPrice;
    private Integer isDelete;

    @Override
    public String toString() {
        return "CartBookRel{" +
                "id=" + id +
                ", cartId=" + cartId +
                ", bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookPrice=" + bookPrice +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                ", isDelete=" + isDelete +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartBookRel that = (CartBookRel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (cartId != null ? !cartId.equals(that.cartId) : that.cartId != null) return false;
        if (bookId != null ? !bookId.equals(that.bookId) : that.bookId != null) return false;
        if (bookName != null ? !bookName.equals(that.bookName) : that.bookName != null) return false;
        if (bookPrice != null ? !bookPrice.equals(that.bookPrice) : that.bookPrice != null) return false;
        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        if (totalPrice != null ? !totalPrice.equals(that.totalPrice) : that.totalPrice != null) return false;
        if (isDelete != null ? !isDelete.equals(that.isDelete) : that.isDelete != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cartId != null ? cartId.hashCode() : 0);
        result = 31 * result + (bookId != null ? bookId.hashCode() : 0);
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (bookPrice != null ? bookPrice.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Integer bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
