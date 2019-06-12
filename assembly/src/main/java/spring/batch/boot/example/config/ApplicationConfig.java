package spring.batch.boot.example.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import spring.batch.boot.example.model.User;
import spring.batch.boot.example.services.processor.UserProcessor;
import spring.batch.boot.example.services.writer.UserWriter;

@Configuration
public class ApplicationConfig {


    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Autowired
    private ItemWriter<User> userWriter;

    @Autowired
    private ItemProcessor<User,User> userProcessor;

    @Bean
    public Job job(){

        Step step = stepBuilderFactory.get("test-step-name")
                .<User,User>chunk(5)
                .reader(flatFileItemReader())
                .processor(userProcessor)
                .writer(userWriter)
                .build();

        return jobBuilderFactory.get("test-job-name")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();

    }



    @Bean(name = "flatFileItemReader;")
    public FlatFileItemReader<User> flatFileItemReader(){
        FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setLineMapper(createLineMapper());
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setName("CSV-Reader");
        ClassPathResource resource = new ClassPathResource("usersData.csv");
        flatFileItemReader.setResource(resource);


        return flatFileItemReader;
    }

    @Bean
    public LineMapper<User> createLineMapper() {
        DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<>();


        BeanWrapperFieldSetMapper<User> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(User.class);

        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);


        defaultLineMapper.setLineTokenizer(createLineTokenizer());
        return defaultLineMapper;
    }

    @Bean
    public LineTokenizer createLineTokenizer() {
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setStrict(false);
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setNames("id", "name", "dept", "salary");
        return delimitedLineTokenizer;
    }
}
