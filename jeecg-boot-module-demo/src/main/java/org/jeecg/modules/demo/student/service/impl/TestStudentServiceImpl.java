package org.jeecg.modules.demo.student.service.impl;

import org.jeecg.modules.demo.student.entity.TestStudent;
import org.jeecg.modules.demo.student.mapper.TestStudentMapper;
import org.jeecg.modules.demo.student.service.ITestStudentService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: test_student
 * @Author: jeecg-boot
 * @Date:   2021-11-04
 * @Version: V1.0
 */
@Service
public class TestStudentServiceImpl extends ServiceImpl<TestStudentMapper, TestStudent> implements ITestStudentService {

}
