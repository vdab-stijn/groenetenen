package be.vdab.groenetenen.restservices;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import com.google.common.net.HttpHeaders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql("/insertBranch.sql")
public class BranchRestControllerTest
extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private MockMvc mvc;
	
	private final long idTestBranch() {
		return super.jdbcTemplate.queryForObject(
				"SELECT id FROM filialen WHERE naam='test'", Long.class);
	}
	
	@Test
	public void readNonExistantBranch() throws Exception {
		final long id = idTestBranch();
		
		mvc.perform(get("/branches/" + id)
			.header(HttpHeaders.AUTHORIZATION,
				"Basic " + Base64Utils.encodeToString("joe:theboss".getBytes()))
			.accept(MediaType.APPLICATION_XML))
			.andExpect(status().isOk())
			.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_XML))
			.andExpect(xpath("/branchResource/branch/id")
				.string(String.valueOf(id)));
	}
	
	@Test
	public void readBranchInJSONFormat() throws Exception {
		final long id = idTestBranch();
		
		mvc.perform(get("/branches/" + id)
			.header(HttpHeaders.AUTHORIZATION,
				"Basic " + Base64Utils.encodeToString("joe:theboss".getBytes()))
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.branch.id").value((int)id));
	}
}
