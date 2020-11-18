package com.example.bfci.Adapters;

import java.io.Serializable;
import java.util.List;

public class Practice implements Serializable {

    //questions->each level_name have ~20 questions
    //images: load one image from url
    //answers->4 answers(a,b,c,d), answer can be of different number (a,b,c,d) or just (a,b,c)
    //correct answer-> only one is correct

    public Number level_number;
    public String level_color;
    public String level_name;
    public String question;
    public String answer_a;
    public String answer_b;
    public String answer_c;
    public String answer_d;
    public String correct_answer;
    public String image_url;

    //quiz: array of objects containing the questions,answers,image and correct answer
    public List<String> quiz;

    public Practice() {
        this.level_number = level_number;
        this.level_color = level_color;
        this.level_name = level_name;
        this.question = question;
        this.answer_a = answer_a;
        this.answer_b = answer_b;
        this.answer_c = answer_c;
        this.answer_d = answer_d;
        this.correct_answer = correct_answer;
        this.image_url = image_url;
        this.quiz = quiz;
    }

    public Number getLevel_number() {
        return level_number;
    }

    public void setLevel_number(Number level_number) {
        this.level_number = level_number;
    }

    public String getLevel_color() {
        return level_color;
    }

    public void setLevel_color(String level_color) {
        this.level_color = level_color;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer_a() {
        return answer_a;
    }

    public void setAnswer_a(String answer_a) {
        this.answer_a = answer_a;
    }

    public String getAnswer_b() {
        return answer_b;
    }

    public void setAnswer_b(String answer_b) {
        this.answer_b = answer_b;
    }

    public String getAnswer_c() {
        return answer_c;
    }

    public void setAnswer_c(String answer_c) {
        this.answer_c = answer_c;
    }

    public String getAnswer_d() {
        return answer_d;
    }

    public void setAnswer_d(String answer_d) {
        this.answer_d = answer_d;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public List<String> getQuiz() {
        return quiz;
    }

    public void setQuiz(List<String> quiz) {
        this.quiz = quiz;
    }
}
