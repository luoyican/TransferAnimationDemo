package com.example.lyc.transferanimation.ViewPageCards;


/**
 * 定义卡片结构的控件
 */
public class CardItem {
    private int viewContent;
    private int ivBg;
    private String txtName;
    private String txtTitle;
    private String txtTip;
    private int viewCode;


    private CardItem() {
    }

    public int getViewContent() {
        return viewContent;
    }

    public int getIvBg() {
        return ivBg;
    }

    public String getTxtName() {
        return txtName;
    }

    public String getTxtTitle() {
        return txtTitle;
    }

    public String getTxtTip() {
        return txtTip;
    }

    public int getViewCode() {
        return viewCode;
    }

    public static class Builder {
        private CardItem mCardItem;

        public Builder() {
            mCardItem = new CardItem();
        }

        public Builder setViewContent(int viewContent) {
            mCardItem.viewContent = viewContent;
            return this;
        }

        public Builder setIvBg(int ivBg) {
            mCardItem.ivBg = ivBg;
            return this;
        }

        public Builder setTxtName(String txtName) {
            mCardItem.txtName = txtName;
            return this;
        }

        public Builder setTxtTitle(String txtTitle) {
            mCardItem.txtTitle = txtTitle;
            return this;
        }

        public Builder setTxtTip(String txtTip) {
            mCardItem.txtTip = txtTip;
            return this;
        }

        public Builder setViewCode(int viewCode) {
            mCardItem.viewCode = viewCode;
            return this;
        }

        public CardItem build() {
            return mCardItem;
        }
    }
}
