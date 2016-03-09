import com.demo.bean.Book;
import com.demo.service.BookService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by adon on 2016/2/1 0001.
 */
public class Test {

	private static SqlSessionFactory sessionFactory;

	static {

		//mybatis的配置文件
		String resource = "data-source.xml";
		//使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
		InputStream is = Test.class.getClassLoader().getResourceAsStream(resource);
		//构建sqlSession的工厂
		sessionFactory = new SqlSessionFactoryBuilder().build(is);

	}

	@org.junit.Test
	public void test(){
		Book book = new Book(1l, "sjkfe", "张三");
		System.out.println(book);
	}

	@org.junit.Test
	public void testGet(){

		SqlSession session = sessionFactory.openSession();
		/**
		 * 映射sql的标识字符串，
		 * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
		 * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
		 */
		String statement = "com.demo.service.BookService";//映射sql的标识字符串
		//执行查询返回一个唯一user对象的sql
		Book book = session.selectOne(statement, 1l);
		System.out.println(book.getId()+";"+book.getName()+";"+book.getAuthor()+";"+book.getPrice()+";");
		System.out.println(book);

	}

	@org.junit.Test
	public void testInterface(){

		SqlSession session = sessionFactory.openSession();
		try{

			BookService bookService = session.getMapper(BookService.class);

			Book book = bookService.getBook(2l);
			System.out.println(book.getId()+";"+book.getName()+";"+book.getAuthor()+";"+book.getPrice()+";");
			System.out.println(book);

		} finally {
			session.close();
		}

	}

	@org.junit.Test
	public void testAdd(){

		SqlSession session = sessionFactory.openSession();

		try{

			BookService bookService = session.getMapper(BookService.class);

			Book book = new Book();
			book.setName("Java多线程2");
			book.setAuthor("李四");
			book.setPrice(new BigDecimal("68.00"));
			bookService.addBook(book);
			session.commit();
			System.out.println(book.getId()+";"+book.getName()+";"+book.getAuthor()+";"+book.getPrice()+";");
			System.out.println(book);

		} finally {
			session.close();
		}

	}

	@org.junit.Test
	public void testRemove(){

		SqlSession session = sessionFactory.openSession();
		try{

			BookService bookService = session.getMapper(BookService.class);

			bookService.remove(7l);
			session.commit();

		} finally {
			session.close();
		}

	}

	@org.junit.Test
	public void testUpdate(){

		SqlSession session = sessionFactory.openSession();
		try{

			BookService bookService = session.getMapper(BookService.class);

			Book book = bookService.getBook(2l);
			book.setName("Java高并发实战");
			book.setAuthor("王五");
			book.setPrice(new BigDecimal("58"));
			bookService.update(book);
			session.commit();

		} finally {
			session.close();
		}

	}

	@org.junit.Test
	public void testGetMap(){

		SqlSession session = sessionFactory.openSession();
		try{

			BookService bookService = session.getMapper(BookService.class);

			Map book = bookService.getBookAsMap(2l);
			System.out.println(book);

		} finally {
			session.close();
		}

	}


}
