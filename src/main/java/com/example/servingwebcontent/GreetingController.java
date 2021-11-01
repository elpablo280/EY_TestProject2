package com.example.servingwebcontent;

import com.example.servingwebcontent.domain.ExcelToDataBase;
import com.example.servingwebcontent.domain.Message;
import com.example.servingwebcontent.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class GreetingController
{
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/demo")
    public String main(Map<String, Object> model)
    {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";    // главная страница с кнопкой "загрузить"
    }

    @GetMapping("/show")
    public String getAllMessages(Map<String, Object> model)
    {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "all";    // просмотр данных, импортированных из excel в mysql, с возможностью вернуться на главную страницу
    }

    @PostMapping("/import")
    public String Import(Map<String, Object> model, @RequestParam(name="f", required=false, defaultValue="Trening.xlsx") String excelFilePath)
    {
        model.put("f", excelFilePath);
        ExcelToDataBase.Import(excelFilePath);
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "loaded";     // промежуточное меню с кнопками "просмотреть" и "назад"
    }
}