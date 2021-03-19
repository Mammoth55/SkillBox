public class Main {

    static final String MONGO_URL = "127.0.0.1";
    static final int MONGO_PORT = 27017;
    static final String DATABASE_NAME = "skill-mongo";
    static final String COLLECTION_NAME = "students";
    static final String CSV_FILE = "src/main/resources/students.csv";

    public static void main(String[] args) {
        MyMongoDbCollection myCollection = new MyMongoDbCollection(MONGO_URL, MONGO_PORT, DATABASE_NAME, COLLECTION_NAME);
        ParserCSVtoMongoDB parser = new ParserCSVtoMongoDB(CSV_FILE, myCollection.getCollection());
        System.out.println("Total Students : " + myCollection.getCollectionSize());
        printSeparateLine();
        System.out.println("Total Students with ages 40+ : " + myCollection.getAgesGreaterThanValue(40));
        printSeparateLine();
        System.out.println("Total Students with MIN ages : " + myCollection.getMinAgesAndPrint());
        printSeparateLine();
        System.out.println("Total Students with MAX ages : " + myCollection.getMaxAgesAndPrint());
        printSeparateLine();
    }

    static void printSeparateLine() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\r\n");
    }
}