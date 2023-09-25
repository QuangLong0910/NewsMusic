package longbqph27075.fpoly.newsmusic;

public class ItemMenu {
      public int id;
    public String tenItem;
    public int icon;

    public ItemMenu(int id, String tenItem, int icon) {
        this.id = id;
        this.tenItem = tenItem;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenItem() {
        return tenItem;
    }

    public void setTenItem(String tenItem) {
        this.tenItem = tenItem;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
