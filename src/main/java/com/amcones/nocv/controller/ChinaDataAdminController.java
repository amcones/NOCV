package com.amcones.nocv.controller;

import com.amcones.nocv.dao.NocvDataMapper;
import com.amcones.nocv.entity.NocvData;
import com.amcones.nocv.service.IndexService;
import com.amcones.nocv.view.DataView;
import com.amcones.nocv.view.NocvDataView;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ChinaDataAdminController {

    @Autowired
    private NocvDataMapper nocvDataMapper;
    @Autowired
    private IndexService indexService;

    @RequestMapping("toChinaManager")
    public String toChinaData() {
        return "admin/chinadatamanager";
    }

    @RequestMapping("/listDataByPage")
    @ResponseBody
    public DataView listDataByPage(NocvDataView nocvDataView) {
        QueryWrapper<NocvData> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(!(nocvDataView.getName() == null), "name", nocvDataView.getName());

        queryWrapper.orderByDesc("value");
        IPage<NocvData> page = nocvDataMapper.selectPage(new Page<>(nocvDataView.getPage(), nocvDataView.getLimit()), queryWrapper);

        DataView dataView = new DataView(page.getTotal(), page.getRecords());
        return dataView;
    }

    @RequestMapping("/china/deleteById")
    @ResponseBody
    public DataView deleteById(Integer id) {
        indexService.removeById(id);
        DataView dataView = new DataView();
        dataView.setCode(200);
        dataView.setMsg("删除中国地图数据成功");
        return dataView;
    }

    @RequestMapping("/china/addOrUpdateChina")
    @ResponseBody
    public DataView addOrUpdateChina(NocvData nocvData) {
        nocvData.setUpdateTime(new Date());
        boolean save = indexService.saveOrUpdate(nocvData);
        DataView dataView = new DataView();
        if (save) {
            dataView.setCode(200);
            dataView.setMsg("新增或修改中国地图数据成功");
            return dataView;
        }
        dataView.setData(100);
        dataView.setMsg("新增或修改中国地图数据失败");
        return dataView;
    }

    @RequestMapping("/excelImportChina")
    @ResponseBody
    public DataView excelImportChina(@RequestParam("file") MultipartFile file) throws IOException {
        DataView dataView = new DataView();
        if (file.isEmpty()) {
            dataView.setMsg("文件为空，不能上传");
        }
        XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = wb.getSheetAt(0);

        List<NocvData> list = new ArrayList<>();
        XSSFRow row = null;

        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            NocvData nocvData = new NocvData();
            row = sheet.getRow(i);
            nocvData.setName(row.getCell(0).getStringCellValue());
            nocvData.setValue((int)row.getCell(1).getNumericCellValue());
            list.add(nocvData);
        }
        indexService.saveBatch(list);
        dataView.setCode(200);
        dataView.setMsg("数据插入成功");
        return dataView;
    }

    @RequestMapping("/excelOutPortChina")
    @ResponseBody
    public void excelOutPortChina(HttpServletResponse response) throws IOException {
        List<NocvData> list = indexService.list();
        response.setCharacterEncoding("UTF-8");
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("中国疫情数据sheet1");
        XSSFRow xssfRow = sheet.createRow(0);
        xssfRow.createCell(0).setCellValue("省份名称");
        xssfRow.createCell(1).setCellValue("确诊人数");

        for(NocvData data:list){
            XSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
            dataRow.createCell(0).setCellValue(data.getName());
            dataRow.createCell(1).setCellValue(data.getValue());
        }
        OutputStream os= null;
        response.setContentType("application/octet-stream;charset=utf8");
        response.setHeader("Content-Disposition","attachment;filename="+new String("中国疫情数据表".getBytes(StandardCharsets.UTF_8),"iso-8859-1")+".xlsx");

        os = response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();
    }

}
