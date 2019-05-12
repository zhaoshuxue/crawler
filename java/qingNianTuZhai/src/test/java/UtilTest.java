import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zsx.Application;
import com.zsx.dao.TuserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class UtilTest {

	@Autowired
	private TuserMapper userMapper;

	@Test
	public void test1() {
		userMapper.selectByPrimaryKey(1L);
	}

	@Test
	public void 分页查询() {
		
		Page<Object> pageHelper = PageHelper.startPage(1, 10, true);
//		userMapper.queryList();
		long total = pageHelper.getTotal(); // 总条数
		int pages = pageHelper.getPages(); // 总页数
		System.out.println();
		System.out.println(total);
		System.out.println(pages);
		System.out.println();
	}

}