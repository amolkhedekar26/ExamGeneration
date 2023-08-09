package com.example.theexamapi.config;

import com.example.theexamapi.controller.*;
import com.example.theexamapi.cors.CORSFilter;
import com.example.theexamapi.entity.TQuestion;
import com.example.theexamapi.repo.ClassRepository;
import com.example.theexamapi.repo.CourseRepository;
import com.example.theexamapi.repo.DepartmentRepository;
import com.example.theexamapi.repo.StreamRepository;
import com.example.theexamapi.repositories.*;
import com.example.theexamapi.service.*;
import com.example.theexamapi.service.impl.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class AppConfig extends ResourceConfig {

    public AppConfig() {
        registerResources();

        // Now you can expect validation errors to be sent to the
        // client.
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    }

    public void registerResources() {
        register(CORSFilter.class);

        register(StreamResource.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(StreamServiceImpl.class).to(StreamService.class);
                bind(StreamRepository.class).to(StreamRepository.class);
            }
        });

        register(CourseResource.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(CourseServiceImpl.class).to(CourseService.class);
                bind(CourseRepository.class).to(CourseRepository.class);
            }
        });

        register(DepartmentResource.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(DepartmentServiceImpl.class).to(DepartmentService.class);
                bind(DepartmentRepository.class).to(DepartmentRepository.class);
            }
        });

        register(ClassResource.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(ClassServiceImpl.class).to(ClassService.class);
                bind(ClassRepository.class).to(ClassRepository.class);
            }
        });

        register(TStreamResource.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(TStreamServiceImpl.class).to(TStreamService.class);
                bind(TStreamRepository.class).to(TStreamRepository.class);
            }
        });
        register(TCourseResource.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(TCourseServiceImpl.class).to(TCourseService.class);
                bind(TCourseRepository.class).to(TCourseRepository.class);
            }
        });
        register(TDepartmentResource.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(TDepartmentServiceImpl.class).to(TDepartmentService.class);
                bind(TDepartmentRepository.class).to(TDepartmentRepository.class);
            }
        });
        register(TClassResource.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(TClassServiceImpl.class).to(TClassService.class);
                bind(TClassRepository.class).to(TClassRepository.class);
            }
        });
        register(TDepartmentClassMappingResource.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(TDepartmentClassMappingServiceImpl.class).to(TDepartmentClassMappingService.class);
                bind(TDepartmentClassMappingRepository.class).to(TDepartmentClassMappingRepository.class);
            }
        });
        register(TSemesterResource.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(TSemesterServiceImpl.class).to(TSemesterService.class);
                bind(TSemesterRepository.class).to(TSemesterRepository.class);
            }
        });
        register(TDepartmentClassMapSemesterMappingResource.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(TDepartmentClassMapSemesterMappingServiceImpl.class).to(TDepartmentClassMapSemesterMappingService.class);
                bind(TDepartmentClassMapSemesterMappingRepository.class).to(TDepartmentClassMapSemesterMappingRepository.class);
            }
        });
        register(TSubjectResource.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(TSubjectServiceImpl.class).to(TSubjectService.class);
                bind(TSubjectRepository.class).to(TSubjectRepository.class);
            }
        });
        register(TExamResource.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(TExamServiceImpl.class).to(TExamService.class);
                bind(TExamRepository.class).to(TExamRepository.class);
            }
        });
        register(TQuestionResource.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(TQuestionServiceImpl.class).to(TQuestionService.class);
                bind(TQuestionRepository.class).to(TQuestionRepository.class);
            }
        });
        register(TMcqOptionResource.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(TMcqOptionServiceImpl.class).to(TMcqOptionService.class);
                bind(TMcqOptionRepository.class).to(TMcqOptionRepository.class);
            }
        });
        register(TExamQuestionMappingResource.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(TExamQuestionMappingServiceImpl.class).to(TExamQuestionMappingService.class);
                bind(TExamQuestionMappingRepository.class).to(TExamQuestionMappingRepository.class);
            }
        });
    }
}