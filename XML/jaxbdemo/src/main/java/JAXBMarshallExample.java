/**
 * Created by Administrator on 2017/4/21.
 */
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.HashSet;

public class JAXBMarshallExample {
    public static void main(String[] args) {
    //    Customer<BookSet> customer = new Customer <BookSet>();
        Customer<HashSet<Book>> customer = new Customer <HashSet<Book>>();
        customer.setId(1000);
        customer.setName(new String[]{"moon"});
        customer.setAge(18);

        Book book = new Book();
        book.setId("1");
        book.setName("哈里波特");
        book.setPrice(100);

        Book book2 = new Book();
        book2.setId("2");
        book2.setName("苹果");
        book2.setPrice(50);

        HashSet<Book> books = new HashSet<Book>();
        books.add(book);
        books.add(book2);
        customer.setBook(books);
/*        BookSet bookSet = new BookSet();
        bookSet.addBook(book);
        bookSet.addBook(book2);

        customer.setT(bookSet);*/

        try {
            File file = new File("C:\\file.xml");
            //JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class,BookSet.class);
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(customer, file);
            jaxbMarshaller.marshal(customer, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
