import com.demo.bean.Book;
import com.demo.common.util.HibernateTemplate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import java.io.*;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by adon on 2016/2/20 0020.
 */
public class HibernateTest {

	static HibernateTemplate hibernateTemplate = null;

	static SessionFactory sessionFactory = null;

	static JdbcTemplate jdbcTemplate = null;

	static {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("dataSource-mysql.xml", "spring.xml");
		sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		hibernateTemplate = (HibernateTemplate) ctx.getBean("hibernateTemplate");
	}

	@Test
	public void testHibernate(){

		List list = jdbcTemplate.queryForList("SELECT * FROM tb_book WHERE id=?", 1l);
		System.out.println(list);

	}

	@Test
	public void testSession(){

		Session session = sessionFactory.openSession();

		Book book = (Book) session.get(Book.class, 1l);
		System.out.println(book);

	}

	@Test
	public void testHibernateTemplate() throws IOException {

		Book book = hibernateTemplate.query("SELECT * FROM tb_book WHERE id=?", new Object[]{2l}, new ResultSetExtractor<Book>() {
			@Override
			public Book extractData(ResultSet rs) throws SQLException, DataAccessException {
				Book b = new Book();
				try {
					while (rs.next()) {
						ResultSetMetaData rsMetaData = rs.getMetaData();
						int columnCount = rsMetaData.getColumnCount();
						for(int colIndex = 1; colIndex <= columnCount; colIndex ++){
							String colName = rsMetaData.getColumnName(colIndex);
							Object value = JdbcUtils.getResultSetValue(rs, colIndex);
							if("id".equalsIgnoreCase(colName)){
								b.setId(((Number) value).longValue());
							} else if ("name".equalsIgnoreCase(colName)){
								b.setName((String) value);
							} else if ("author".equalsIgnoreCase(colName)){
								b.setAuthor((String) value);
							} else if ("price".equalsIgnoreCase(colName)){
								b.setPrice(new BigDecimal(value.toString()));
							}
						}
					}
				} catch (Throwable e) {
					throw new ObjectRetrievalFailureException("拼装对象出错！", e);
				}
				return b;
			}
		});

		System.out.println(book);

	}


	@Test
	public void testHibernateTemplate2(){

		Book book = hibernateTemplate.query("SELECT * FROM tb_book WHERE id=?", new Object[]{2l}, new ResultSetExtractor<Book>() {
			@Override
			public Book extractData(ResultSet rs) throws SQLException, DataAccessException {
				try {
					while (rs.next()) {
						Book b = new Book();
						b.setId(rs.getLong("id"));
						b.setAuthor(rs.getString("author"));
						b.setName(rs.getString("name"));
						b.setPrice(rs.getBigDecimal("price"));
						return b;
					}
					return null;
				} catch (Throwable e) {
					throw new ObjectRetrievalFailureException("拼装对象出错！", e);
				}
			}
		});

		System.out.println(book);

	}

	@Test
	public void testHibernateTemplate3(){

		List<Book> list = hibernateTemplate.query("SELECT * FROM tb_book", new Object[]{}, new ResultSetExtractor<List<Book>>() {
			@Override
			public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
				try {
					List<Book> rus = new ArrayList<Book>();
					while (rs.next()) {
						Book b = new Book();
						b.setId(rs.getLong("id"));
						b.setAuthor(rs.getString("author"));
						b.setName(rs.getString("name"));
						b.setPrice(rs.getBigDecimal("price"));
						rus.add(b);
					}
					return rus;
				} catch (Throwable e) {
					throw new ObjectRetrievalFailureException("拼装对象出错！", e);
				}
			}
		});
		System.out.println(list);
	}

	@Test
	public void testHibernateTemplate4(){
		List list = hibernateTemplate.queryForList("SELECT * FROM tb_book WHERE id<?", 10l);
		System.out.println(list);

		Map map = hibernateTemplate.queryForMap("SELECT * FROM tb_book WHERE id=?", 2l);
		System.out.println(map);

		System.out.println(
				hibernateTemplate.queryForList("SELECT * FROM tb_book WHERE id=?", new Object[]{2l})
		);

		List<Book> books = (List<Book>) hibernateTemplate.find("FROM Book e WHERE e.id<?", 10l);
		System.out.println(books);
	}

}
