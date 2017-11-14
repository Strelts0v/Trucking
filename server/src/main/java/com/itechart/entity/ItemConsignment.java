public class ItemConsignment {

    private Integer id;
    private Integer amount;
    private String status;
    private Integer idItem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    @Override
    public String toString() {
        return "ItemConsignment{" +
                "id=" + id +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", idItem=" + idItem +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemConsignment that = (ItemConsignment) o;

        if (!id.equals(that.id)) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return idItem.equals(that.idItem);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + idItem.hashCode();
        return result;
    }
}
