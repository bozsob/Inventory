/**
 * Created by trixi on 2017.02.03..
 */
import java.util.ArrayList;

public interface StoreCapable {
    public abstract ArrayList<Product> getAllProduct();
    public abstract void storeCDProduct(String name, int price, int size);
    public abstract void storeBookProduct(String name, int price, int size);

}
