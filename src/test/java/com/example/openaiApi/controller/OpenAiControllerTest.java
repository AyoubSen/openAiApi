package com.example.openaiApi.controller;

import com.example.openaiApi.model.request.OurMessage;
import com.example.openaiApi.model.response.Choice;
import com.example.openaiApi.model.response.OpenAiResponse;
import com.example.openaiApi.model.response.Usage;
import com.example.openaiApi.service.OpenAiService;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OpenAiControllerTest {

    @Test
    void sendMessage() throws IOException {
        OpenAiService openAiService = mock(OpenAiService.class);
        OpenAiController openAiController = new OpenAiController(openAiService);
        OurMessage message = new OurMessage("what is the capital of Morocco?");
        List<Choice> choices = new ArrayList<>();
        LocalDate localDate = LocalDate.now();
        choices.add(new Choice(1, "the capital of Morocco is Rabat", null,"stop"));
        Usage usage = new Usage(7,3, 10);
        OpenAiResponse response = new OpenAiResponse("1","text_completion","text-davinci-003",localDate,choices,usage);
        when(openAiService.askQuestion(message)).thenReturn(response);
        OpenAiResponse actualResponse = openAiController.sendMessage(message);
        assertEquals(response, actualResponse);

        File file = new File("/app/myOutputFile.csv");
        assertTrue(file.exists());

        try{
            FileWriter outputfile = new FileWriter(file,false);
            CSVWriter writer = new CSVWriter(outputfile,';',CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            if (file.length() == 0) {
                String[] header = {"Question", "Answer"};
                writer.writeNext(header);
            }
            String[] data = {message.getMessage(),response.getChoices().get(0).getText()};
            writer.writeNext(data);
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        List<String> csvLines = Files.readAllLines(Paths.get("C:\\Users\\Ayoub\\Desktop\\newFolder\\openaiApi\\myOutputFile.csv"));
        assertThat(csvLines).hasSize(2);
    }
}