/**
 * Created by Administrator on 2017/4/21.
 */
import javax.xml.bind.annotation.*;
import java.util.Set;


@XmlRootElement
@XmlType(propOrder = { "id", "name", "age", "book"})
public class Customer<T> {
    String[] names;
    int age;
    int id;
   // T t;
   Set<Book> book;

    public String[] getName() {
        return names;
    }
    @XmlElementWrapper(name = "names")
    @XmlElement
    public void setName(String[] names) {
        this.names = names;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getId() {
        return id;
    }
    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

/*    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }*/

    @XmlElementWrapper(name="books")
    @XmlElement(name="book")
    public Set<Book> getBook() {
        return book;
    }

    public void setBook(Set<Book> book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Customer [name=" + names + ", age=" + age + ", id=" + id + "]";
    }
}