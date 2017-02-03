/**
 * Created by trixi on 2017.02.03..
 */
public class Main extends StoreManager
{
    public static void main(String[] args)
    {
        StoreManager store = new StoreManager();
        StoreCapable capable = new PersistentStore();
        store.addStorage(capable);

        store.addCDProduct("Best of ...", 2000, 20);
        store.addBookProduct("Frei összes", 3000, 400);

        System.out.println(store.listProducts());
        System.out.println(store.getTotalProductPrice());
    }
}
