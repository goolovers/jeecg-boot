package org.jeecg.modules.demo.student.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.demo.student.entity.TestStudent;
import org.jeecg.modules.demo.student.service.ITestStudentService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: test_student
 * @Author: jeecg-boot
 * @Date:   2021-11-04
 * @Version: V1.0
 */
@Api(tags="测试单表")
@RestController
@RequestMapping("/student/testStudent")
@Slf4j
public class TestStudentController extends JeecgController<TestStudent, ITestStudentService> {
	@Autowired
	private ITestStudentService testStudentService;
	
	/**
	 * 分页列表查询
	 *
	 * @param testStudent
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "测试单表-分页列表查询")
	@ApiOperation(value="测试单表-分页列表查询", notes="测试单表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TestStudent testStudent,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TestStudent> queryWrapper = QueryGenerator.initQueryWrapper(testStudent, req.getParameterMap());
		Page<TestStudent> page = new Page<TestStudent>(pageNo, pageSize);
		IPage<TestStudent> pageList = testStudentService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param testStudent
	 * @return
	 */
	@AutoLog(value = "测试单表-添加")
	@ApiOperation(value="测试单表-添加", notes="测试单表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TestStudent testStudent) {
		testStudentService.save(testStudent);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param testStudent
	 * @return
	 */
	@AutoLog(value = "测试单表-编辑")
	@ApiOperation(value="测试单表-编辑", notes="测试单表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TestStudent testStudent) {
		testStudentService.updateById(testStudent);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "测试单表-通过id删除")
	@ApiOperation(value="通过id删除", notes="通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		testStudentService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "测试单表-批量删除")
	@ApiOperation(value="测试单表-批量删除", notes="测试单表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.testStudentService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "测试单表-通过id查询")
	@ApiOperation(value="测试单表-通过id查询", notes="测试单表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TestStudent testStudent = testStudentService.getById(id);
		if(testStudent==null) {
			Result.error("未找到对应数据");
		}
		return Result.ok(testStudent);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param testStudent
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TestStudent testStudent) {
        return super.exportXls(request, testStudent, TestStudent.class, "test_student");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TestStudent.class);
    }

}
