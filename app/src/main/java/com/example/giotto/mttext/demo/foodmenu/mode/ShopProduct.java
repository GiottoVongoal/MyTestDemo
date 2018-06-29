package com.example.giotto.mttext.demo.foodmenu.mode;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by caobo on 2016/7/12 0012.
 * 小区商圈商品属性
 */
public class ShopProduct implements Parcelable {
    private int id;
    private String shopName;//店名
    private String price;//单价
    private String goods;//货物名称
    private String picture;//货物图片
    private String type;//货物类型
    private String createtime;
    /**
     * 商品数目
     */
    private int number;

    private String money;

    public ShopProduct() {
        super();
    }

    protected ShopProduct(Parcel in) {
        id = in.readInt();
        shopName = in.readString();
        price = in.readString();
        goods = in.readString();
        picture = in.readString();
        type = in.readString();
        createtime = in.readString();
        number = in.readInt();
        money = in.readString();
    }

    public static final Creator<ShopProduct> CREATOR = new Creator<ShopProduct>() {
        @Override
        public ShopProduct createFromParcel(Parcel in) {
            return new ShopProduct(in);
        }

        @Override
        public ShopProduct[] newArray(int size) {
            return new ShopProduct[size];
        }
    };

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(shopName);
        parcel.writeString(price);
        parcel.writeString(goods);
        parcel.writeString(picture);
        parcel.writeString(type);
        parcel.writeString(createtime);
        parcel.writeInt(number);
        parcel.writeString(money);
    }
}
