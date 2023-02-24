package com.example.openaiApi.controller;

import com.example.openaiApi.model.request.OurMessage;
import com.example.openaiApi.model.response.Choice;
import com.example.openaiApi.model.response.OpenAiResponse;
import com.example.openaiApi.service.OpenAiService;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bot")
@RequiredArgsConstructor
public class OpenAiController{

    private final OpenAiService openAiService;

    @PostMapping("/send")
    public OpenAiResponse sendMessage(@RequestBody OurMessage ourMessage) {
        File file = new File("C:\\Users\\Ayoub\\Desktop\\newFolder\\openaiApi\\myOutputFile.csv");
        OpenAiResponse theResponse = openAiService.askQuestion(ourMessage);
        List<Choice> currentChoices = theResponse.getChoices();
        String actualAnswer = currentChoices.get(0).getText();
        file.setReadable(true,false);
        file.setWritable(true,false);
        try{
            FileWriter outputfile = new FileWriter(file,true);
           CSVWriter writer = new CSVWriter(outputfile,';',CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
           if (file.length() == 0){
               String[] header = {"Question","Answer"};
               writer.writeNext(header);
           }
            String[] data = { ourMessage.getMessage(), actualAnswer.trim() };
            writer.writeNext(data);
            writer.close();
        }catch (IOException e) {

            e.printStackTrace();
        }
        return theResponse ;
    }
}
