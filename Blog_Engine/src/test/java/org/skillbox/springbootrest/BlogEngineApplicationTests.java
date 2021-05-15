package org.skillbox.springbootrest;

import org.junit.jupiter.api.Test;
import org.skillbox.springbootrest.repository.GlobalSettingRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.assertEquals;

// @Sql({"src/main/resources/test_schema_and_tables_and_init_data_script.sql"})
@SpringBootTest
class BlogEngineApplicationTests {

    private GlobalSettingRepository globalSettingRepository;

    BlogEngineApplicationTests(GlobalSettingRepository globalSettingRepository) {
        this.globalSettingRepository = globalSettingRepository;
    }

    @Test
    public void testGlobalSetting() {

//        assertEquals(3, globalSettingRepository.findAll().size());
    }

}