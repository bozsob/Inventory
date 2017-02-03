/**
 * Created by trixi on 2017.02.03..
 */
public abstract class Product extends PersistentStore
{
    public String name;
    public Integer price;

    public Product(String name, int price)
    {
        this.name = name;
        this.price = price;
    }
}
