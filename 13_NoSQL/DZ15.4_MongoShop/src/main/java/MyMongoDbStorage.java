import com.mongodb.*;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import java.util.Arrays;

public class MyMongoDbStorage {

    private final Datastore storage;

    public MyMongoDbStorage(String url, int port, String db) {
        final Morphia morphia = new Morphia();
        morphia.map(Shop.class);
        morphia.map(Product.class);
        this.storage = morphia.createDatastore(new MongoClient(url, port), db);
    }

    public void addNewShop(String name) {
        storage.save(new Shop(name));
        // нужен db.shops.updateOne({"name": shop}, {"products": []}, {upsert: true})
    }

    public Shop getShopByName(String name) {
        return storage
                .createQuery( Shop.class )
                .field("name").equal(name)
                .get();
    }

    public WriteResult deleteShopByName(String name) {
        return storage.delete(this.getShopByName(name));
    }

    public void addNewProduct(String name, int price) {
        storage.save(new Product(name, price));
        // нужен db.products.updateOne({"name": product}, {"price": price}, {upsert: true})
    }

    public Product getProductByName(String name) {
        return storage
                .createQuery( Product.class )
                .field("name").equal(name)
                .get();
    }

    public WriteResult deleteProductByName(String name) {
        return storage.delete(this.getProductByName(name));
    }

    public void uploadProductToShop(String name, String shop) {
        Query<Shop> query = storage.createQuery(Shop.class).filter("name", shop);
        UpdateOperations<Shop> update = storage.createUpdateOperations(Shop.class)
                .addToSet("productNames", name);
        storage.update(query, update);
        // db.shops.updateOne({"name": shop}, {$addToSet: {"products": name}});
    }

    public void printStatistics() {
        final DBCollection collection = storage.getCollection(Product.class);
        final AggregationOutput output = collection.aggregate(Arrays.asList(
                new BasicDBObject("$lookup",
                        new Document("from", "shops")
                                .append("localField", "name")
                                .append("foreignField", "productNames")
                                .append("as", "shop_list")),
                new BasicDBObject("$unwind",
                        new Document("path", "$shop_list")),
                new BasicDBObject("$group",
                        new Document("_id",
                                new Document("name", "$shop_list.name"))
                                .append("productCounter", new Document("$sum", 1))
                                .append("avgPrice", new Document("$avg", "$price"))
                                .append("maxPrice", new Document("$max", "$price"))
                                .append("minPrice", new Document("$min", "$price"))
                                .append("counter100less", new Document("$sum",
                                        new Document("$cond",
                                                new Document("if",
                                                        new Document("$lt", Arrays.asList("$price", 100)))
                                                        .append("then", 1)
                                                        .append("else", 0)))))));
        for (DBObject dbObject : output.results()) {
            System.out.println("Магазин = " + ((DBObject) dbObject.get("_id")).get("name")
                    + ", кол-во продуктов = " + dbObject.get("productCounter")
                    + ", Ср.Цена = " + dbObject.get("avgPrice")
                    + ", Макс.Цена = " + dbObject.get("maxPrice")
                    + ", Мин.Цена = " + dbObject.get("minPrice")
                    + ", кол-во продуктов дешевле 100 = " + dbObject.get("counter100less"));
        }
    }
}