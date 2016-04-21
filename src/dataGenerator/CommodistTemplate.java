package dataGenerator;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

public class CommodistTemplate {

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int[] batchInsert(Integer[] coms) {
		String name = "name";
		String description = "description";
		int[] updateCounts = jdbcTemplate.batchUpdate("insert into commodity values( ?,?,?,?);",
				new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setInt(1, i + 1);
						ps.setString(2, name + new Integer(i + 1).toString());
						ps.setString(3, description + new Integer(i + 1).toString());
						ps.setInt(4, (i % 18) + 1);

					}

					public int getBatchSize() {
						return coms.length;
					}
				});
		return updateCounts;
	}
}
