import java.util.*;

/**
 * Esta clase representa el catálogo del supermercado.
 * Para simplificar, solo contiene un arreglo constante de los productos
 * En un software más real, el catálogo se vincula con una base de datos
 * en la cual puede hacer operaciones crud para manipular el inventario
 */
public class Catalog {
    private final List<Stock> articles = new ArrayList<>();
    Catalog(){
        articles.add(new Stock(new StoreArticle(1,"Papel higiénico Higienol Doble Hoja 30Mx 4 unidades", 924.81f), 200));
        articles.add(new Stock(new StoreArticle(2,"SHAMPOO DOVE X 400ML", 809.51f), 300));
        articles.add(new Stock(new StoreArticle(3,"YERBA MATE PORONGO ORGÁNICA 500G", 2449f), 30));
        articles.add(new Stock(new StoreArticle(4,"CERVEZA PAMPA CREAM STOUT 473CC X 6 LATAS", 3024f), 400));
        articles.add(new Stock(new StoreArticle(5,"CAJA 40 ALFAJORES GUAYMALLÉN CHOCOLATE NEGRO DULCE DE LECHE", 3325f), 10));
        articles.add(new Stock(new StoreArticle(6,"YERBA MATE CANARIAS SABOR TRADICIONAL 1KG", 3825f), 5));
        articles.add(new Stock(new StoreArticle(7,"BUDIN DE VAINILLA BONAFIDE X 200G", 756.24f), 3));
    }

    List<Stock> getAvailableArticles(){
        return articles.stream().filter(stock -> stock.getStock() > 0 ).toList();
    }

    Stock getStock(int index){
        return articles.stream().filter(article -> article.getArticle().getID() == index).findFirst().get();
    }

    /*
    private static final String DATABASE_NAME = "bootcamp";
    private static final String TABLE_NAME = "product";
    private static final String USER = "kertz";
    private static final String PASSWORD = "0901";
    private Connection connection;

    Catalog(){
        try{
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/" + DATABASE_NAME, USER, PASSWORD);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    boolean logIn(String user, String password){
        return user.equals(USER) && password.equals(PASSWORD);
    }

    /**
     * Agrega un producto al inventario
     * @param product el producto a agregar
     * @return true si se agregó correctamente; false si hubo algún error
    public boolean add(Article product){
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + TABLE_NAME + " (name, price, stock) values(?, ?, ?);");
            statement.setString(1, product.getName());
            statement.setFloat(2, product.getPrice());
            statement.setInt(3, product.getStock());
            statement.executeQuery();
            return true;
        }
        catch(Exception e){ e.printStackTrace(); }
        return false;
    }
     */

    /*
     * Elimina un producto del inventario
     * @param product el producto a eliminar
     * @return true si se eliminó correctamente; false si hubo algún error
    public boolean remove(StoreArticle product){
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE id = ?");
            statement.setInt(1, product.getID());
            statement.executeQuery();
            return true;
        }
        catch(Exception e){ e.printStackTrace(); }
        return false;
    }
     */

    /*
     * Modifica el stock de un producto
     * @param product el producto a modificar
     * @param difference si se agrega stock el valor es positivo; si se elimina, es negativo
    public void modifyStock(StoreArticle product, int difference){
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE " + TABLE_NAME + " SET stock = ? WHERE id = ?");
            statement.setInt(1, product.getStock() + difference );
            statement.setInt(2, product.getID() );
            statement.executeQuery();
        }
        catch(Exception e){ e.printStackTrace(); }
    }
     */

    /*
    public Optional<StoreArticle> getArticle(int index){
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?");
            statement.setInt(1, index);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                int id = result.getInt(1);
                String name = result.getString(2);
                float price = result.getFloat(3);
                int stock = result.getInt(4);
                return Optional.of(new StoreArticle(id, name, price, stock));
            }
        }
        catch(Exception e){ e.printStackTrace(); }
        return Optional.empty();

    }

    public List<StoreArticle> findArticle(String searchString){
        List<StoreArticle> articles = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE name LIKE ?");
            statement.setString(1, "%" + searchString + "%");
            ResultSet result = statement.executeQuery();
            while(result.next()){
                int id = result.getInt(1);
                String name = result.getString(2);
                float price = result.getFloat(3);
                int stock = result.getInt(4);
                articles.add(new StoreArticle(id, name, price, stock));
            }
        }
        catch(Exception e){ e.printStackTrace(); }
        return articles;
    }


    public List<StoreArticle> getAllArticles(){
        List<StoreArticle> articles = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                int id = result.getInt(1);
                String name = result.getString(2);
                float price = result.getFloat(3);
                int stock = result.getInt(4);
                articles.add( new StoreArticle(id, name, price, stock) );
            }
        }
        catch(Exception e){ e.printStackTrace(); }
        return articles;
    }
    */
}
