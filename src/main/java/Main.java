import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static final Scanner keyboard = new Scanner(System.in);
    private static final ShoppingCart shoppingCart = new ShoppingCart();
    private static Catalog catalog = new Catalog();

    public static void main(String[] args) {

        while(true){
            System.out.println(" ".repeat(70) + "MENÚ PRINCIPAL");
            System.out.println("""
            1. Comprar
            2. Ver carrito
            3. Salir""");
            System.out.print("Su opcion: ");

            int input = keyboard.nextInt();

            switch (input) {
                case 1 -> buy();
                case 2 -> showCart();
                default -> { return; }
            }
        }
    }


    private static void buy(){
        boolean keepBuying = true;
        while(keepBuying){
            Stock item = null;
            while(item == null){
                showCatalog();
                System.out.print("\nElija un producto con su id para agregar al carrito: ");

                try{
                    item = catalog.getStock(keyboard.nextInt());
                }
                catch (NoSuchElementException exception){
                    System.out.println("\n\nError: Ingrese un id válido");
                }
            }

            Integer quantity = null;
            while(quantity == null){
                System.out.printf("Qué cantidad de productos agregar? (disponibles = %d): ", item.getStock());
                quantity = keyboard.nextInt();

                if(quantity <= 0 || item.getStock() < quantity ){
                    System.out.println("\n\nError: Ingrese una cantidad válida");
                    quantity = null;
                }
            }

            shoppingCart.add(item.getArticle(), quantity);
            System.out.println("\n\nArtículo agregado a tu carrito!");

            item.decreaseStock(quantity);

            System.out.print("\n\nQuerés seguir comprando? 1 = Sí, 0 = No: ");
            keepBuying = keyboard.nextInt() == 1;
        }
        showCart();
    }


    private static void showCatalog(){
        final List<Stock> stocks = catalog.getAvailableArticles();
        System.out.println("\n\n" + " ".repeat(70) + "CATÁLOGO");
        System.out.printf("| %-3s | %-80s | %-20s | %-11s |%n", "ID", "DESCRIPCIÓN", "Precio (IVA incluido)", "DISPONIBLES");
        System.out.println(hbar());
        for(var stock : stocks)
            System.out.println(stock);
        System.out.println(hbar());
    }

    private static void showCart(){
        if(shoppingCart.isEmpty()){
            System.out.println("\n\nNo tenés artículos en tu carrito\nAgregá artículos yendo a la opción de comprar del menú principal\n\n");
            return;
        }

        while(true){
            List<SelectedArticle> selectedArticles = shoppingCart.getAllArticles();
            System.out.println("\n\n" + " ".repeat(70) + "CARRITO");
            System.out.printf("| %-3s | %-80s | %-20s | %-11s |%n", "ID", "DESCRIPCIÓN", "Precio (IVA incluido)", "CANTIDAD");
            System.out.println(hbar());
            for(var article : selectedArticles) System.out.println(article);
            System.out.println(hbar());
            System.out.printf(" ".repeat(83) + "TOTAL | $ %19.2f |", shoppingCart.totalImport());

            System.out.print("""
                    
                    1. Finalizar compra
                    2. Eliminar artículo
                    3. Volver al menú principal
                    """);
            System.out.print("Su opción: ");
            int choice = keyboard.nextInt();

            if(choice == 1){ finishBuy(); break; }
            else if(choice == 2) { removeItemFromCart(); }
            else break;
        }
    }

    private static void removeItemFromCart(){
        while(true){
            System.out.print("Ingrese ID del artículo a eliminar: ");
            int id = keyboard.nextInt();

            System.out.print("Ingrese cantidad de items a eliminar: ");
            int quantity = keyboard.nextInt();

            if(shoppingCart.remove( id, quantity)) break;
            else System.out.print("\n\nError: Ingrese un ID y una cantidad válidos\n\n");
        }
    }


    private static String hbar(){
        return '|' + "-".repeat(5) + '|' + "-".repeat(82) + '|' + "-".repeat(23) + '|' + "-".repeat(13) + '|';
    }

    private static void finishBuy(){
        final float totalImport = shoppingCart.totalImport();
        System.out.printf("\n\nEl total a pagar es %.2f $. Con cuánto dinero desea abonar?: ", totalImport);
        float amount;
        while((amount = keyboard.nextFloat()) < totalImport)
            System.out.print("\n\nError: La cantidad ingresada no es suficiente para pagar\nPor favor, ingrese otra cantidad: ");

        shoppingCart.clearCart();
        System.out.printf("\n\nPago realizado con exito! El vuelto es de %.2f $", amount - totalImport);
        System.out.println("\nMuchas gracias por elegirnos :)");
    }

    /*
    private static void seeShoppingCart(){
        while(true){
            if(carrito.estaVacio()){
                System.out.println("Tu carrito esta vacio. Entre en la seccion de comprar para poder agregar productos");
                return;
            }
            else{
                carrito.mostrar();
                System.out.println("1. Finalizar compra");
                System.out.println("2. Seguir comprando");
                System.out.print("Su opcion: ");
                int opcion = keyboard.nextInt();
                if(opcion == 1) {
                    main.finalizarCompra();
                    return;
                }
                else return;
            }
        }
    }

    private void finalizarCompra(){
        final float importeTotal = carrito.importeTotal();
        System.out.println("El total a pagar es $" + importeTotal);
        System.out.print("Con cuanto dinero abona la compra? ");
        float dinero = keyboard.nextFloat();

        if(dinero >= importeTotal ){
            float cambio = importeTotal - dinero;
            if(cambio > 0)
                System.out.println("Su cambio es de " + cambio );

            System.out.println("Gracias por comprar! Vuelva pronto");
        }
    }

     */

    /*
    private static void adminInventory(){
        System.out.println("\n--------------INGRESO AL INVENTARIO--------------\n");
        System.out.print("Usuario: ");
        String user = keyboard.next();
        System.out.print("Contraseña: ");
        String password = keyboard.next();
        if(!catalog.logIn(user,password)){
            System.out.println("Datos incorrectos :c");
            return;
        }

        while(true){
            System.out.println("\n--------------ADMINISTRACIÓN DEL INVENTARIO--------------\n");
            System.out.println("""
                    1. Ver inventario
                    2. Buscar producto
                    3. Agregar producto
                    4. Eliminar producto
                    5. Agregar o quitar stock
                    6. Volver
                    """);
            System.out.print("Su opción: ");

            int choice = keyboard.nextInt();

            switch(choice){
                case 1 -> showInventory();
                case 2 -> findProductInInventory();
                case 3 -> addProductToInventory();
                case 4 -> removeProductFromInventory();
                case 5 -> modifyStock();
                default -> { return; }
            }
        }
    }


    private static void findProductInInventory(){
        System.out.println("\n--------------BUSCAR PRODUCTOS--------------\n");
        System.out.print("Buscar producto: ");
        String searchString = keyboard.next();

        List<StoreProduct> products = catalog.findProduct(searchString);
        System.out.print("\n\n\n");
        if(products.isEmpty())
            System.out.println("No se encontraron coincidencias");
        else{
            for(var product : products)
                System.out.println(product);
        }
    }

    private static void addProductToInventory(){
        System.out.println("\n--------------AGREGAR PRODUCTO--------------\n");
        System.out.print("Ingrese nombre del producto: ");
        keyboard.nextLine();
        String name = keyboard.nextLine();
        System.out.print("Ingrese precio unitario: ");
        float price = keyboard.nextFloat();
        System.out.print("Ingrese cantidad de stock: ");
        int quantity = keyboard.nextInt();

        Product product = new Product(name, price, quantity);
        if(catalog.add(product))
            System.out.println("Producto agregado!");
        else
            System.out.println("No se pudo agregar el producto");
    }

    private static void modifyStock(){
        System.out.println("\n--------------MODIFICAR STOCK--------------\n");
        showInventory();
        System.out.print("Seleccionar id del producto: ");
        Optional<StoreProduct> op = catalog.getProduct(keyboard.nextInt());
        op.ifPresentOrElse(storeProduct ->  {
                System.out.print("Ingrese el stock agregado o eliminado (valores <0 para eliminar): ");
                int newStock = keyboard.nextInt();
                catalog.modifyStock(storeProduct, newStock);
                System.out.println("Stock modificado :)");

            }
            , () -> System.out.println("El id ingresado no es válido")
        );
    }

    private static void removeProductFromInventory(){
        System.out.println("\n--------------ELIMINAR PRODUCTO--------------\n");
        showInventory();
        System.out.print("Seleccionar id del producto: ");
        Optional<StoreProduct> op = catalog.getProduct(keyboard.nextInt());
        op.ifPresentOrElse(storeProduct -> {
                if(catalog.remove(storeProduct))
                    System.out.println("Producto eliminado! :P");
                else
                    System.out.println("No se pudo eliminar el producto :(");
                }
                , () -> System.out.println("El id ingresado no es válido")
        );
    }
     */
}