/*
package jpa.test;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.ejb.QueryHints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.atguigu.jpa.helloworld.Category;
import com.atguigu.jpa.helloworld.Customer;
import com.atguigu.jpa.helloworld.Department;
import com.atguigu.jpa.helloworld.Item;
import com.atguigu.jpa.helloworld.Manager;
import com.atguigu.jpa.helloworld.Order;

public class JPATest {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;
	
	@Before
	public void init(){
		entityManagerFactory = Persistence.createEntityManagerFactory("jpa-1");
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		transaction.begin();
	}
	
	@After
	public void destroy(){
		transaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}
	
	//����ʹ�� JPQL ��� UPDATE �� DELETE ����. 
	@Test
	public void testExecuteUpdate(){
		String jpql = "UPDATE Customer c SET c.lastName = ? WHERE c.id = ?";
		Query query = entityManager.createQuery(jpql).setParameter(1, "YYY").setParameter(2, 12);
		
		query.executeUpdate();
	}

	//ʹ�� jpql �ڽ��ĺ���
	@Test
	public void testJpqlFunction(){
		String jpql = "SELECT lower(c.email) FROM Customer c";
		
		List<String> emails = entityManager.createQuery(jpql).getResultList();
		System.out.println(emails);
	}
	
	@Test
	public void testSubQuery(){
		//��ѯ���� Customer �� lastName Ϊ YY �� Order
		String jpql = "SELECT o FROM Order o "
				+ "WHERE o.customer = (SELECT c FROM Customer c WHERE c.lastName = ?)";
		
		Query query = entityManager.createQuery(jpql).setParameter(1, "YY");
		List<Order> orders = query.getResultList();
		System.out.println(orders.size());
	}
	
	*/
/**
	 * JPQL �Ĺ�����ѯͬ HQL �Ĺ�����ѯ. 
	 *//*

	@Test
	public void testLeftOuterJoinFetch(){
		String jpql = "FROM Customer c LEFT OUTER JOIN FETCH c.orders WHERE c.id = ?";
		
		Customer customer = 
				(Customer) entityManager.createQuery(jpql).setParameter(1, 12).getSingleResult();
		System.out.println(customer.getLastName());
		System.out.println(customer.getOrders().size());
		
//		List<Object[]> result = entityManager.createQuery(jpql).setParameter(1, 12).getResultList();
//		System.out.println(result);
	}
	
	//��ѯ order �������� 2 ����Щ Customer
	@Test
	public void testGroupBy(){
		String jpql = "SELECT o.customer FROM Order o "
				+ "GROUP BY o.customer "
				+ "HAVING count(o.id) >= 2";
		List<Customer> customers = entityManager.createQuery(jpql).getResultList();
		
		System.out.println(customers);
	}
	
	@Test
	public void testOrderBy(){
		String jpql = "FROM Customer c WHERE c.age > ? ORDER BY c.age DESC";
		Query query = entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE, true);
		
		//ռλ���������Ǵ� 1 ��ʼ
		query.setParameter(1, 1);
		List<Customer> customers = query.getResultList();
		System.out.println(customers.size());
	}
	
	//ʹ�� hibernate �Ĳ�ѯ����. 
	@Test
	public void testQueryCache(){
		String jpql = "FROM Customer c WHERE c.age > ?";
		Query query = entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE, true);
		
		//ռλ���������Ǵ� 1 ��ʼ
		query.setParameter(1, 1);
		List<Customer> customers = query.getResultList();
		System.out.println(customers.size());
		
		query = entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE, true);
		
		//ռλ���������Ǵ� 1 ��ʼ
		query.setParameter(1, 1);
		customers = query.getResultList();
		System.out.println(customers.size());
	}
	
	//createNativeQuery �����ڱ��� SQL
	@Test
	public void testNativeQuery(){
		String sql = "SELECT age FROM jpa_cutomers WHERE id = ?";
		Query query = entityManager.createNativeQuery(sql).setParameter(1, 3);
		
		Object result = query.getSingleResult();
		System.out.println(result);
	}
	
	//createNamedQuery ��������ʵ����ǰʹ�� @NamedQuery ��ǵĲ�ѯ���
	@Test
	public void testNamedQuery(){
		Query query = entityManager.createNamedQuery("testNamedQuery").setParameter(1, 3);
		Customer customer = (Customer) query.getSingleResult();
		
		System.out.println(customer);
	}
	
	//Ĭ�������, ��ֻ��ѯ��������, �򽫷��� Object[] ���͵Ľ��. ���� Object[] ���͵� List.
	//Ҳ������ʵ�����д�����Ӧ�Ĺ�����, Ȼ���� JPQL ��������ö�Ӧ�Ĺ���������ʵ����Ķ���.
	@Test
	public void testPartlyProperties(){
		String jpql = "SELECT new Customer(c.lastName, c.age) FROM Customer c WHERE c.id > ?";
		List result = entityManager.createQuery(jpql).setParameter(1, 1).getResultList();
		
		System.out.println(result);
	}
	
	@Test
	public void testHelloJPQL(){
		String jpql = "FROM Customer c WHERE c.age > ?";
		Query query = entityManager.createQuery(jpql);
		
		//ռλ���������Ǵ� 1 ��ʼ
		query.setParameter(1, 1);
		List<Customer> customers = query.getResultList();
		System.out.println(customers.size());
	}
	
	@Test
	public void testSecondLevelCache(){
		Customer customer1 = entityManager.find(Customer.class, 1);
		
		transaction.commit();
		entityManager.close();
		
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		transaction.begin();
		
		Customer customer2 = entityManager.find(Customer.class, 1);
	}
	
	//���ڹ����ļ��϶���, Ĭ��ʹ�������صĲ���.
	//ʹ��ά��������ϵ��һ����ȡ, ����ʹ�ò�ά��������ϵ��һ����ȡ, SQL �����ͬ. 
	@Test
	public void testManyToManyFind(){
//		Item item = entityManager.find(Item.class, 5);
//		System.out.println(item.getItemName());
//		
//		System.out.println(item.getCategories().size());
		
		Category category = entityManager.find(Category.class, 3);
		System.out.println(category.getCategoryName());
		System.out.println(category.getItems().size());
	}
	
	//������ı���
	@Test
	public void testManyToManyPersist(){
		Item i1 = new Item();
		i1.setItemName("i-1");
	
		Item i2 = new Item();
		i2.setItemName("i-2");
		
		Category c1 = new Category();
		c1.setCategoryName("C-1");
		
		Category c2 = new Category();
		c2.setCategoryName("C-2");
		
		//���ù�����ϵ
		i1.getCategories().add(c1);
		i1.getCategories().add(c2);
		
		i2.getCategories().add(c1);
		i2.getCategories().add(c2);
		
		c1.getItems().add(i1);
		c1.getItems().add(i2);
		
		c2.getItems().add(i1);
		c2.getItems().add(i2);
		
		//ִ�б���
		entityManager.persist(i1);
		entityManager.persist(i2);
		entityManager.persist(c1);
		entityManager.persist(c2);
	}
	
	//1. Ĭ�������, ����ȡ��ά��������ϵ��һ��, ��Ҳ��ͨ���������ӻ�ȡ������Ķ���. 
	//����ͨ�� @OneToOne �� fetch �������޸ļ��ز���. ����Ȼ���ٷ��� SQL �������ʼ��������Ķ���
	//��˵���ڲ�ά��������ϵ��һ��, �������޸� fetch ����. 
	@Test
	public void testOneToOneFind2(){
		Manager mgr = entityManager.find(Manager.class, 1);
		System.out.println(mgr.getMgrName());
		
		System.out.println(mgr.getDept().getClass().getName());
	}
	
	//1.Ĭ�������, ����ȡά��������ϵ��һ��, ���ͨ���������ӻ�ȡ������Ķ���. 
	//������ͨ�� @OntToOne �� fetch �������޸ļ��ز���.
	@Test
	public void testOneToOneFind(){
		Department dept = entityManager.find(Department.class, 1);
		System.out.println(dept.getDeptName());
		System.out.println(dept.getMgr().getClass().getName());
	}
	
	//˫�� 1-1 �Ĺ�����ϵ, �����ȱ��治ά��������ϵ��һ��, ��û�������һ��, ���������� UPDATE ���.
	@Test
	public void testOneToOnePersistence(){
		Manager mgr = new Manager();
		mgr.setMgrName("M-BB");
		
		Department dept = new Department();
		dept.setDeptName("D-BB");
		
		//���ù�����ϵ
		mgr.setDept(dept);
		dept.setMgr(mgr);
		
		//ִ�б������
		entityManager.persist(mgr);
		entityManager.persist(dept);
	}
	
	@Test
	public void testUpdate(){
		Customer customer = entityManager.find(Customer.class, 10);
		
		customer.getOrders().iterator().next().setOrderName("O-XXX-10");
	}
	
	//Ĭ�������, ��ɾ�� 1 ��һ��, ����Ȱѹ����� n ��һ�˵�����ÿ�, Ȼ�����ɾ��. 
	//����ͨ�� @OneToMany �� cascade �������޸�Ĭ�ϵ�ɾ������. 
	@Test
	public void testOneToManyRemove(){
		Customer customer = entityManager.find(Customer.class, 8);
		entityManager.remove(customer);
	}
	
	//Ĭ�϶Թ����Ķ��һ��ʹ�������صļ��ز���. 
	//����ʹ�� @OneToMany �� fetch �������޸�Ĭ�ϵļ��ز���
	@Test
	public void testOneToManyFind(){
		Customer customer = entityManager.find(Customer.class, 9);
		System.out.println(customer.getLastName());
		
		System.out.println(customer.getOrders().size());
	}
	
	//����˫�� 1-n �Ĺ�����ϵ, ִ�б���ʱ
	//���ȱ��� n ��һ��, �ٱ��� 1 ��һ��, Ĭ�������, ���� n �� UPDATE ���.
	//���ȱ��� 1 ��һ��, ����� n �� UPDATE ���
	//�ڽ���˫�� 1-n ������ϵʱ, ����ʹ�� n ��һ����ά��������ϵ, �� 1 ��һ����ά������ϵ, ��������Ч�ļ��� SQL ���. 
	//ע��: ���� 1 ��һ�˵� @OneToMany ��ʹ�� mappedBy ����, �� @OneToMany �˾Ͳ�����ʹ�� @JoinColumn ������. 
	
	//���� 1-n ������ϵִ�б���ʱ, һ������ UPDATE ���.
	//��Ϊ n ��һ���ڲ���ʱ����ͬʱ���������. 
	@Test
	public void testOneToManyPersist(){
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("mm@163.com");
		customer.setLastName("MM");
		
		Order order1 = new Order();
		order1.setOrderName("O-MM-1");
		
		Order order2 = new Order();
		order2.setOrderName("O-MM-2");
		
		//����������ϵ
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);
		
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		
		//ִ�б������
		entityManager.persist(customer);

		entityManager.persist(order1);
		entityManager.persist(order2);
	}
	
	*/
/*
	@Test
	public void testManyToOneUpdate(){
		Order order = entityManager.find(Order.class, 2);
		order.getCustomer().setLastName("FFF");
	}
	
	//����ֱ��ɾ�� 1 ��һ��, ��Ϊ�����Լ��. 
	@Test
	public void testManyToOneRemove(){
//		Order order = entityManager.find(Order.class, 1);
//		entityManager.remove(order);
		
		Customer customer = entityManager.find(Customer.class, 7);
		entityManager.remove(customer);
	}
	
	//Ĭ�������, ʹ���������ӵķ�ʽ����ȡ n ��һ�˵Ķ����������� 1 ��һ�˵Ķ���. 
	//��ʹ�� @ManyToOne �� fetch �������޸�Ĭ�ϵĹ������Եļ��ز���
	@Test
	public void testManyToOneFind(){
		Order order = entityManager.find(Order.class, 1);
		System.out.println(order.getOrderName());
		
		System.out.println(order.getCustomer().getLastName());
	}
	*//*

	
	*/
/**
	 * ������һʱ, �����ȱ��� 1 ��һ��, �󱣴� n ��һ��, ��������������� UPDATE ���.
	 *//*

	*/
/*
	@Test
	public void testManyToOnePersist(){
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("gg@163.com");
		customer.setLastName("GG");
		
		Order order1 = new Order();
		order1.setOrderName("G-GG-1");
		
		Order order2 = new Order();
		order2.setOrderName("G-GG-2");
		
		//���ù�����ϵ
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		
		//ִ�б������
		entityManager.persist(order1);
		entityManager.persist(order2);
		
		entityManager.persist(customer);
	}
	*//*

	
	*/
/**
	 * ͬ hibernate �� Session �� refresh ����. 
	 *//*

	@Test
	public void testRefresh(){
		Customer customer = entityManager.find(Customer.class, 1);
		customer = entityManager.find(Customer.class, 1);
		
		entityManager.refresh(customer);
	}
	
	*/
/**
	 * ͬ hibernate �� Session �� flush ����. 
	 *//*

	@Test
	public void testFlush(){
		Customer customer = entityManager.find(Customer.class, 1);
		System.out.println(customer);
		
		customer.setLastName("AA");
		
		entityManager.flush();
	}
	
	//���������һ���������, ������Ķ����� OID. 
	//1. ���� EntityManager �������ж�Ӧ�Ķ���
	//2. JPA ��������������Ը��Ƶ���ѯ��EntityManager �����еĶ�����.
	//3. EntityManager �����еĶ���ִ�� UPDATE. 
	@Test
	public void testMerge4(){
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("dd@163.com");
		customer.setLastName("DD");
		
		customer.setId(4);
		Customer customer2 = entityManager.find(Customer.class, 4);
		
		entityManager.merge(customer);
		
		System.out.println(customer == customer2); //false
	}
	
	//���������һ���������, ������Ķ����� OID. 
	//1. ���� EntityManager ������û�иö���
	//2. �������ݿ���Ҳ�ж�Ӧ�ļ�¼
	//3. JPA ���ѯ��Ӧ�ļ�¼, Ȼ�󷵻ظü�¼��һ���Ķ���, ��Ȼ���������������Ը��Ƶ���ѯ���Ķ�����.
	//4. �Բ�ѯ���Ķ���ִ�� update ����. 
	@Test
	public void testMerge3(){
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("ee@163.com");
		customer.setLastName("EE");
		
		customer.setId(4);
		
		Customer customer2 = entityManager.merge(customer);
		
		System.out.println(customer == customer2); //false
	}
	
	//���������һ���������, ������Ķ����� OID. 
	//1. ���� EntityManager ������û�иö���
	//2. �������ݿ���Ҳû�ж�Ӧ�ļ�¼
	//3. JPA �ᴴ��һ���µĶ���, Ȼ��ѵ�ǰ�����������Ը��Ƶ��´����Ķ�����
	//4. ���´����Ķ���ִ�� insert ����. 
	@Test
	public void testMerge2(){
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("dd@163.com");
		customer.setLastName("DD");
		
		customer.setId(100);
		
		Customer customer2 = entityManager.merge(customer);
		
		System.out.println("customer#id:" + customer.getId());
		System.out.println("customer2#id:" + customer2.getId());
	}
	
	*/
/**
	 * �ܵ���˵: ������ hibernate Session �� saveOrUpdate ����.
	 *//*

	//1. ���������һ����ʱ����
	//�ᴴ��һ���µĶ���, ����ʱ��������Ը��Ƶ��µĶ�����, Ȼ����µĶ���ִ�г־û�����. ����
	//�µĶ������� id, ����ǰ����ʱ������û�� id. 
	@Test
	public void testMerge1(){
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("cc@163.com");
		customer.setLastName("CC");
		
		Customer customer2 = entityManager.merge(customer);
		
		System.out.println("customer#id:" + customer.getId());
		System.out.println("customer2#id:" + customer2.getId());
	}
	
	//������ hibernate �� Session �� delete ����. �Ѷ����Ӧ�ļ�¼�����ݿ����Ƴ�
	//��ע��: �÷���ֻ���Ƴ� �־û� ����. �� hibernate �� delete ����ʵ���ϻ������Ƴ� �������.
	@Test
	public void testRemove(){
//		Customer customer = new Customer();
//		customer.setId(2);
		
		Customer customer = entityManager.find(Customer.class, 2);
		entityManager.remove(customer);
	}
	
	//������ hibernate �� save ����. ʹ��������ʱ״̬��Ϊ�־û�״̬. 
	//�� hibernate �� save �����Ĳ�֮ͬ��: �������� id, ����ִ�� insert ����, �����׳��쳣. 
	@Test
	public void testPersistence(){
		Customer customer = new Customer();
		customer.setAge(15);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("bb@163.com");
		customer.setLastName("BB");
		customer.setId(100);
		
		entityManager.persist(customer);
		System.out.println(customer.getId());
	}
	
	//������ hibernate �� Session �� load ����
	@Test
	public void testGetReference(){
		Customer customer = entityManager.getReference(Customer.class, 1);
		System.out.println(customer.getClass().getName());
		
		System.out.println("-------------------------------------");
//		transaction.commit();
//		entityManager.close();
		
		System.out.println(customer);
	}
	
	//������ hibernate �� Session �� get ����. 
	@Test
	public void testFind() {
		Customer customer = entityManager.find(Customer.class, 1);
		System.out.println("-------------------------------------");
		
		System.out.println(customer);
	}

}
*/
