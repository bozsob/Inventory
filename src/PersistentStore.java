/**
 * Created by trixi on 2017.02.03..
 */
import java.util.ArrayList;

public class PersistentStore extends Store
{
    private ArrayList<Product> productArrayList = new ArrayList<>();


    public ArrayList<Product> getProductArrayList()
    {
        return productArrayList;
    }


    protected void storeProduct(Product product)
    {
        productArrayList.add(product);
    }


    public ArrayList<Product> getAllProduct()
    {
        ArrayList<Product> products = getProductArrayList();
        return products;
    }
}
