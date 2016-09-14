package leevin.retrofitdemo.model.entity;

/**
 * Created by  Leevin
 * on 2016/9/9 ,22:42.
 */
public class BaseResponse<T> {
    private int total_count;
    private boolean incomplete_results;
    private T items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public T getItems() {
        return items;
    }

    public void setItems(T items) {
        this.items = items;
    }
}
