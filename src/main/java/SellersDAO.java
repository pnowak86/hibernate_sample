import java.util.List;

/**
 * Created by RENT on 2017-07-21.
 */
public interface SellersDAO {
    public interface UsersDAO {
        List<Seller> get();
        void add(Seller seller);
        void update(Seller seller);
        void delete(Seller seller);
    }
}
