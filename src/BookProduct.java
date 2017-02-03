/**
 * Created by trixi on 2017.02.03..
 */
public class BookProduct extends Product
{
    public int pageSize;

    public BookProduct(String name, int price, int pageSize)
    {
        super(name, price);
        this.pageSize = pageSize;
    }
}
